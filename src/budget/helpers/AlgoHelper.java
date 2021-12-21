package budget.helpers;

import budget.purchase.Purchase;
import java.util.List;

public class AlgoHelper {

    public static List<Purchase> bubbleSort(List<Purchase> list) {
        int listLength = list.size();
        for (int i = 0; i < listLength - 1; i++) {
            for (int j = 0; j < listLength - i - 1; j++) {
                if (list.get(j).getPrice() < list.get(j + 1).getPrice()) {
                    Purchase temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }
}
