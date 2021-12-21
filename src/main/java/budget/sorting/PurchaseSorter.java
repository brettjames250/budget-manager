package budget.sorting;

import budget.purchase.Purchase;

import java.util.List;

public class PurchaseSorter {

    private SortingMethod sortingMethod;

    public void setSortMethod(SortingMethod sortingMethod) {
        this.sortingMethod = sortingMethod;
    }

    public void sort(List<Purchase> fullList) {
        this.sortingMethod.sort(fullList);
    }
}
