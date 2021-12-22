package budget.sorting;

import budget.purchase.Purchase;

import java.util.List;

public interface SortingMethod {
    void sort(List<Purchase> fullList);
}
