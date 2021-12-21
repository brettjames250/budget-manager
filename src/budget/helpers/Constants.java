package budget.helpers;

public final class Constants {

    private Constants(){

    }

    public static final String USER_OPTIONS = "Choose your action:\n"
            + "1) Add income\n"
            + "2) Add purchase\n"
            + "3) Show list of purchases\n"
            + "4) Balance\n"
            + "5) Save\n"
            + "6) Load\n"
            + "7) Analyze (Sort)\n"
            + "0) Exit";

    public static final String PURCHASE_OPTIONS = "Choose the type of purchase\n"
            + "1) Food\n"
            + "2) Clothes\n"
            + "3) Entertainment\n"
            + "4) Other\n"
            + "5) Back";

    public static final String PURCHASE_TYPE_SORT_OPTIONS = "Choose the type of purchase\n"
            + "1) Food\n"
            + "2) Clothes\n"
            + "3) Entertainment\n"
            + "4) Other";

    public static final String LIST_OPTIONS = "Choose the type of purchases\n"
            + "1) Food\n"
            + "2) Clothes\n"
            + "3) Entertainment\n"
            + "4) Other\n"
            + "5) All\n"
            + "6) Back";

    public static final String SORT_OPTIONS = "How do you want to sort?\n"
            + "1) Sort all purchases\n"
            + "2) Sort by type\n"
            + "3) Sort certain type\n"
            + "4) Back";

    public static final String SORTED_BY_TYPE = "Types:\n"
            + "Food - $%.2f\n"
            + "Entertainment - $%.2f\n"
            + "Clothes - $%.2f\n"
            + "Other - $%.2f\n"
            + "Total sum: $%.2f\n";
}
