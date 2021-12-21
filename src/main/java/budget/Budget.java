package budget;

import budget.purchase.Purchase;

import java.util.ArrayList;
import java.util.List;

public class Budget {

    private double runningTotal;
    private double balance;
    private final List<Purchase> purchaseList = new ArrayList<>();
    private final List<Purchase> unsavedPurchases = new ArrayList<>();

    public double getRunningTotal() {
        return runningTotal;
    }

    public void setRunningTotal(double runningTotal) {
        this.runningTotal = runningTotal;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addPurchase(Purchase purchase) {
        purchaseList.add(purchase);
    }

    public void addUnsavedPurchase(Purchase purchase) {
        unsavedPurchases.add(purchase);
    }

    public List<Purchase> getPurchases() {
        return purchaseList;
    }

    public List<Purchase> getUnsavedPurchases() {
        return purchaseList;
    }

    public void addLoadedList(List<Purchase> loadList) {
        purchaseList.addAll(loadList);
    }
}
