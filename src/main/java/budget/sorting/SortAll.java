package budget.sorting;

import budget.helpers.AlgoHelper;
import budget.purchase.Purchase;

import java.util.List;

public class SortAll implements SortingMethod {
    @Override
    public void sort(List<Purchase> fullList) {
        if (!fullList.isEmpty()) {
            printSortedList(AlgoHelper.bubbleSort(fullList));
        } else {
            System.out.println();
            System.out.println("The purchase list is empty!\n");
        }
    }

    private void printSortedList(List<Purchase> sortedList) {
        double categoryTotal = 0.0;
        System.out.println();
        System.out.println("All:");
        for (Purchase purchase : sortedList) {
            System.out.printf("%s $%.2f\n", purchase.getDescription(), purchase.getPrice());
            categoryTotal += purchase.getPrice();
        }
        System.out.printf("Total: $%.2f\n", categoryTotal);
        System.out.println();
    }
}
