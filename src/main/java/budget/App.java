package budget;

import budget.filemanagement.FileManager;
import budget.helpers.StringUtils;
import budget.purchase.Purchase;
import budget.purchase.PurchaseType;
import budget.sorting.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static budget.helpers.Constants.*;

public class App {

    private boolean isAppRunning;
    private final Budget budget;
    private final FileManager fileManager;
    private final int BACK_OPTION = 5;

    public App() {
        this.budget = new Budget();
        this.isAppRunning = true;
        this.fileManager = new FileManager();
    }

    void run() throws IOException {

        while (isAppRunning) {
            int input = UserInput.getUserRequest();
            processUserRequest(input);
            System.out.println();
        }
    }

    private void processUserRequest(int request) throws IOException {

        switch (request) {
            case 1:
                addIncome();
                break;
            case 2:
                addPurchase();
                break;
            case 3:
                showPurchases();
                break;
            case 4:
                showBalance();
                break;
            case 5:
                save();
                break;
            case 6:
                load();
                break;
            case 7:
                sort();
                break;
            case 0:
                exit();
                break;
            default:
        }

    }

    private void sort() {
        boolean selectingSortType = true;

        while (selectingSortType) {
            int sortChoice = UserInput.getUserSortRequest();
            PurchaseSorter sorter = new PurchaseSorter();

            switch (sortChoice) {
                case 1:
                    sorter.setSortMethod(new SortAll());
                    sorter.sort(budget.getPurchases());
                    break;
                case 2:
                    sorter.setSortMethod(new SortByType());
                    sorter.sort(budget.getPurchases());
                    break;
                case 3:
                    sorter.setSortMethod(new SortCertainType());
                    sorter.sort(budget.getPurchases());
                    break;
                case 4:
                    selectingSortType = false;
                    break;
            }
        }
    }

    private void save() throws IOException {
        if (fileManager.savePurchases(budget.getUnsavedPurchases(), budget.getBalance())) {
            System.out.println("Purchases were saved!");
        }
    }

    private void load() throws IOException {
        budget.setBalance(fileManager.getBalanceFromFile());
        List<Purchase> loadList = fileManager.loadPurchases();
        budget.addLoadedList(loadList);
        System.out.println("Purchases were loaded!");
    }

    private void addIncome() {
        double amountToAdd = UserInput.getRequestedIncome();
        budget.setBalance(budget.getBalance() + amountToAdd);
    }

    private void addPurchase() {
        boolean addingItems = true;
        while (addingItems) {
            int option = UserInput.getItemType();
            if (option == BACK_OPTION) {
                addingItems = false;
            } else {
                PurchaseType type = getPurchaseType(option);
                Purchase purchase = UserInput.getPurchaseDetails(type);

                budget.addPurchase(purchase);
                budget.addUnsavedPurchase(purchase);

                budget.setRunningTotal(budget.getRunningTotal() + purchase.getPrice());
                budget.setBalance(budget.getBalance() - purchase.getPrice());

                System.out.println("Purchase was added!");
                System.out.println();
            }
        }
    }


    private PurchaseType getPurchaseType(int type) {
        switch (type) {
            case 1:
                return PurchaseType.FOOD;
            case 2:
                return PurchaseType.CLOTHES;
            case 3:
                return PurchaseType.ENTERTAINMENT;
            case 4:
                return PurchaseType.OTHER;
            case 5:
                return null;
            default:
        }
        return null;
    }

    private void showPurchases() {

        List<Purchase> purchaseList = budget.getPurchases();

        if (purchaseList.size() != 0) {

            boolean showingPurchases = true;

            while (showingPurchases) {

                int category = filterPurchases();
                if (category == 6) {
                    showingPurchases = false;
                    continue;
                }

                PurchaseType filterType = getCategoryFromNumber(category);

                String filteredTypeString = String.valueOf(filterType);

                if (filterType == PurchaseType.ALL) {
                    printPurchases(purchaseList, filteredTypeString);
                } else {
                    List<Purchase> filteredList = purchaseList.stream().filter(purchase -> purchase.getType() == filterType).collect(Collectors.toList());
                    printPurchases(filteredList, filteredTypeString);
                }
            }

        } else {
            System.out.println("The purchase list is empty!");
        }
    }


    private PurchaseType getCategoryFromNumber(int category) {
        PurchaseType filterType = null;

        switch (category) {
            case 1:
                filterType = PurchaseType.FOOD;
                break;
            case 2:
                filterType = PurchaseType.CLOTHES;
                break;
            case 3:
                filterType = PurchaseType.ENTERTAINMENT;
                break;
            case 4:
                filterType = PurchaseType.OTHER;
                break;
            case 5:
                filterType = PurchaseType.ALL;
            default:
        }
        return filterType;
    }

    private void printPurchases(List<Purchase> list, String purchaseType) {
        System.out.println();
        System.out.println(StringUtils.firstLetterCaps(purchaseType) + ":");
        if (list.isEmpty()) {
            System.out.println("The purchase list is empty!");
            System.out.println();
            return;
        }
        double totalSum = 0;
        for (Purchase purchase : list) {
            System.out.printf("%s $%.2f\n", purchase.getDescription(), purchase.getPrice());
            totalSum += purchase.getPrice();
        }
        System.out.printf("Total sum: $%.2f\n", totalSum);
        System.out.println();

    }

    private int filterPurchases() {
        System.out.println(LIST_OPTIONS);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private void showBalance() {
        System.out.printf("Balance: $%.2f", budget.getBalance());
        System.out.println();
    }

    private void exit() {
        isAppRunning = false;
        System.out.println("Bye!");
    }

}
