package budget.sorting;

import budget.UserInput;
import budget.helpers.AlgoHelper;
import budget.helpers.StringUtils;
import budget.purchase.Purchase;
import budget.purchase.PurchaseType;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

public class SortCertainType implements SortingMethod {

    @Override
    public void sort(List<Purchase> fullList) {

        int purchaseTypeChoice = UserInput.getPurchaseFilterForSort();

        List<Purchase> filteredList = filterListByUserChoice(purchaseTypeChoice, fullList);

        if (filteredList.isEmpty()) {
            System.out.println();
            System.out.println("The purchase list is empty!");
            System.out.println();
            return;
        }
        List<Purchase> sortedList = sortFilteredList(filteredList);

        printSortedList(sortedList);
    }

    private List<Purchase> filterListByUserChoice(int purchaseTypeChoice, List<Purchase> fullList) {
        List<Purchase> filteredList = new ArrayList<>();
        switch (purchaseTypeChoice) {
            case 1:
                filteredList = filterListByPurchaseType(PurchaseType.FOOD, fullList);
                break;
            case 2:
                filteredList = filterListByPurchaseType(PurchaseType.CLOTHES, fullList);
                break;
            case 3:
                filteredList = filterListByPurchaseType(PurchaseType.ENTERTAINMENT, fullList);
                break;
            case 4:
                filteredList = filterListByPurchaseType(PurchaseType.OTHER, fullList);
                break;
        }
        return filteredList;
    }

    private List<Purchase> filterListByPurchaseType(PurchaseType type, List<Purchase> list) {
        return list.stream().filter(purchase -> purchase.getType() == type).collect(Collectors.toList());
    }

    private List<Purchase> sortFilteredList(List<Purchase> filteredList) {
        return AlgoHelper.bubbleSort(filteredList);
    }

    private void printSortedList(List<Purchase> sortedList) {
        double categoryTotal = 0.0;
        System.out.println();
        System.out.println(StringUtils.firstLetterCaps(sortedList.get(0).getType().toString()) + ":");
        for (Purchase purchase : sortedList) {
            System.out.printf("%s $%.2f\n", purchase.getDescription(), purchase.getPrice());
            categoryTotal += purchase.getPrice();
        }
        System.out.printf("Total sum: $%.2f\n", categoryTotal);
        System.out.println();
    }

}
