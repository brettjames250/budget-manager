package budget.purchase;

public class Purchase{

    private final String description;
    private final PurchaseType type;
    private final double price;

    public Purchase(String description, double price, PurchaseType type) {
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public double getPrice() {
        return this.price;
    }

    public PurchaseType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public static Purchase formatPurchaseData(String[] data) {
        String name = data[0];
        double price = Double.parseDouble(data[1].substring(1));
        PurchaseType type = PurchaseType.valueOf(data[2]);

        return new Purchase(name, price, type);
    }
}

