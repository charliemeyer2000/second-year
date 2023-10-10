package edu.virginia.cs.threelayer.presentation;

import edu.virginia.cs.threelayer.BestSellersList;
import edu.virginia.cs.threelayer.Book;
import edu.virginia.cs.threelayer.ListName;
import edu.virginia.cs.threelayer.business.BestSellersService;

import java.util.Scanner;

public class CommandLineUI {
    private BestSellersService service;
    private Scanner scanner;

    public static void main(String[] args) {
        CommandLineUI clui = new CommandLineUI();
        clui.run();
    }

    private void run() {
        initialize();
        String entry = "";
        while(!entry.equalsIgnoreCase("quit")) {
            entry = getUserEntry();
            if (isValidMenuNumber(entry)) {
                getTopTenListForEntry(entry);
            }
        }
        terminate();
    }

    private void getTopTenListForEntry(String entry) {
        int choice = Integer.parseInt(entry);
        ListName listName = getListNameFromEntry(choice);
        BestSellersList bestSellersList = service.getCurrentBestSellerList(listName);
        for (int rank = 1; rank <= bestSellersList.getMaxRank(); rank++) {
            Book book = bestSellersList.getBookByRank(rank);
            System.out.printf("\t%2d. %s - by %s%n", rank, book.getTitle(), book.getAuthorName());
        }
    }

    private ListName getListNameFromEntry(int choice) {
        return switch(choice) {
            case 1 -> ListName.HARDCOVER_FICTION;
            case 2 -> ListName.EBOOK_FICTION;
            case 3 -> ListName.COMBINED_FICTION;
            case 4 -> ListName.HARDCOVER_NONFICTION;
            case 5 -> ListName.EBOOK_NONFICTION;
            case 6 -> ListName.COMBINED_NONFICTION;
            default -> throw new IllegalArgumentException("Invalid ListName Entry choice: " + choice);
        };
    }

    private boolean isValidMenuNumber(String entry) {
        try {
            int choice = Integer.parseInt(entry);
            return 1 <= choice && choice <= 6;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void terminate() {
        scanner.close();
    }

    private void initialize() {
        service = new BestSellersService();
        scanner = new Scanner(System.in);
    }


    private String getUserEntry() {
        System.out.println("""
                Please make a selection:
                    1) Fiction Hardcover
                    2) Fiction E-Book
                    3) Fiction Combined
                    4) NonFiction HardCover
                    5) NonFiction E-Book
                    6) NonFiction Combined
                """);
        return scanner.nextLine().strip();
    }
}
