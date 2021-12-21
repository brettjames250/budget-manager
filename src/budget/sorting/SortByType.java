package budget.sorting;

import budget.helpers.Constants;
import budget.purchase.Purchase;
import budget.purchase.PurchaseType;
import java.util.List;
import java.util.stream.Collectors;

public class SortByType implements SortingMethod {


    @Override
    public void sort(List<Purchase> fullList) {

        double foodTotal = getCategoryTotal(fullList, PurchaseType.FOOD);
        double entertainmentTotal = getCategoryTotal(fullList, PurchaseType.ENTERTAINMENT);
        double clothesTotal = getCategoryTotal(fullList, PurchaseType.CLOTHES);
        double otherTotal = getCategoryTotal(fullList, PurchaseType.OTHER);
        double total = getGrandTotal(fullList);
        System.out.println();
        System.out.printf(Constants.SORTED_BY_TYPE, foodTotal, entertainmentTotal, clothesTotal, otherTotal, total);
        System.out.println();
    }

    private double getCategoryTotal(List<Purchase> fullList, PurchaseType purchaseType) {
        double total = 0.0;
        List<Purchase> filteredList = fullList.stream().filter(purchase -> purchase.getType() == purchaseType).collect(Collectors.toList());
        for (Purchase purchase : filteredList) {
            total += purchase.getPrice();
        }
        return total;
    }

    private double getGrandTotal(List<Purchase> fullList) {
        double total = 0.0;
        for (Purchase purchase : fullList) {
            total += purchase.getPrice();
        }
        return total;
    }
}
