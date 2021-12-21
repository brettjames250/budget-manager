package budget.filemanagement;

import budget.purchase.Purchase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private static final String FILE_PATH = "purchases.txt";

    public boolean savePurchases(List<Purchase> purchases, double balance) throws IOException {
        File file = new File(FILE_PATH);

        if (fileCreatedOrAlreadyExists(file)) {
            PrintWriter fileWriter = new PrintWriter(file);

            if (file.length() == 0) {
                fileWriter.printf("%.2f\n", balance);
            } else {
                setBalanceInFile(String.valueOf(balance));
            }

            for (Purchase purchase : purchases) {
                fileWriter.printf("%s,$%.2f,%s\n", purchase.getDescription(), purchase.getPrice(), purchase.getType());
            }
            fileWriter.close();
            return true;
        } else {
            System.out.println("file was not saved");
            return false;
        }
    }

    private void printPurchasesToFile(File file, List<Purchase> purchases) throws FileNotFoundException {
        PrintWriter fileWriter = new PrintWriter(file);
        for (Purchase purchase : purchases) {
            fileWriter.printf("%s,$%.2f,%s\n", purchase.getDescription(), purchase.getPrice(), purchase.getType());
        }
        fileWriter.close();

    }

    private static void setBalanceInFile(String data) throws IOException {
        Path path = Paths.get(FILE_PATH);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(1, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public double getBalanceFromFile() throws IOException {
        Path path = Paths.get(FILE_PATH);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        return Double.parseDouble(lines.get(0));
    }

    public List<Purchase> loadPurchases() {
        File file = new File(FILE_PATH);
        List<Purchase> savedlist = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNext()) {
                String[] purchaseLine = scanner.nextLine().split(",");
                Purchase purchase = Purchase.formatPurchaseData(purchaseLine);
                savedlist.add(purchase);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        }
        return savedlist;
    }

    private boolean fileCreatedOrAlreadyExists(File file) {
        if (file.exists()) {
            return true;
        } else {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }
    }
}
