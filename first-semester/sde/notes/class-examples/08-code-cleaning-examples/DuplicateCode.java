import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DuplicateCode {
    public static void main(String[] args) {
        try {
            var inventory = getInventoryFileContents();
            var menu = getMenuFileContents();
            var sales = getInventoryFileContents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getInventoryFileContents() throws IOException {
        final var INVENTORY_FILE_NAME = "inventory.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(INVENTORY_FILE_NAME));
        List<String> fileContents = new ArrayList<>();
        for (var line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            fileContents.add(line);
        }
        return fileContents;
    }

    public static List<String> getMenuFileContents() throws IOException {
        final var MENU_FILE_NAME = "menu.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(MENU_FILE_NAME));
        List<String> fileContents = new ArrayList<>();
        for (var line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            fileContents.add(line);
        }
        return fileContents;
    }

    public static List<String> getSalesFileContents() throws IOException {
        final var SALES_FILE_NAME = "sales.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(SALES_FILE_NAME));
        List<String> fileContents = new ArrayList<>();
        for (var line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            fileContents.add(line);
        }
        return fileContents;
    }
}
