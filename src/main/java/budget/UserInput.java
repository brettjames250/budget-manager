package budget;

import budget.helpers.Constants;
import budget.purchase.Purchase;
import budget.purchase.PurchaseType;

import java.util.Scanner;

import static budget.helpers.Constants.*;

public class UserInput {

    public static int getUserRequest() {
        Scanner sc = new Scanner(System.in);
        System.out.println(USER_OPTIONS);
        int input = sc.nextInt();
        System.out.println();
        return input;
    }

    public static int getUserSortRequest() {
        Scanner sc = new Scanner(System.in);
        System.out.println(SORT_OPTIONS);
        return Integer.parseInt(sc.nextLine());
    }

    public static double getRequestedIncome() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter income:");
        double amountToAdd = sc.nextDouble();
        System.out.println("Income was added!");
        return amountToAdd;
    }

    public static int getItemType() {
        Scanner sc = new Scanner(System.in);
        System.out.println(PURCHASE_OPTIONS);
        return sc.nextInt();
    }

    public static Purchase getPurchaseDetails(PurchaseType type) {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter purchase name:");
        String desc = sc.nextLine();
        System.out.println("Enter its price:");
        double price = Double.parseDouble(sc.nextLine());

        return new Purchase(desc, price, type);
    }

    public static int getPurchaseFilterForSort() {
        System.out.println();
        System.out.println(Constants.PURCHASE_TYPE_SORT_OPTIONS);
        System.out.println();
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }







}
