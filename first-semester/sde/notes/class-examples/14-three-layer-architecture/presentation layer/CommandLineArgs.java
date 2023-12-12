package edu.virginia.cs.threelayer.presentation;

import edu.virginia.cs.threelayer.*;
import edu.virginia.cs.threelayer.business.BestSellersService;

public class CommandLineArgs {
    public static void main(String [] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException(getBadArgumentsMessage());
        }
        ListName listName = getListNameFromArgs(args);
        BestSellersService bestSellersService = new BestSellersService();
        BestSellersList bestSellersList = bestSellersService.getCurrentBestSellerList(listName);
        System.out.println("Best Sellers List:");
        for (int rank = 1; rank <= bestSellersList.getMaxRank(); rank++) {
            Book book = bestSellersList.getBookByRank(rank);
            System.out.printf("\t%2d. %s - by %s%n", rank, book.getTitle(), book.getAuthorName());
        }
        Book longestRunning = bestSellersService.getLongestCurrentBestSeller(listName);
        System.out.println("\nLongest Running Book:");
        System.out.printf("\t%s - by %s\n\tIt has been on the BestSellers List for %d weeks",
                longestRunning.getTitle(), longestRunning.getAuthorName(), longestRunning.getWeeksOnList());
    }

    private static ListName getListNameFromArgs(String[] args) {
        String flagsToken = args[0];
        if (flagsToken.length() != 3 || !flagsToken.startsWith("-")) {
            throw new IllegalArgumentException(getBadArgumentsMessage());
        }
        if (flagsToken.contains("f")) {
            return getFictionListName(flagsToken);
        } else if (flagsToken.contains("n")) {
            return getNonFictionListName(flagsToken);
        } else {
            throw new IllegalArgumentException(getBadArgumentsMessage());
        }
    }



    private static ListName getFictionListName(String flagsToken) {
        if (flagsToken.contains("e")) {
            return ListName.EBOOK_FICTION;
        } else if (flagsToken.contains("h")) {
            return ListName.HARDCOVER_FICTION;
        } else if (flagsToken.contains("c")) {
            return ListName.COMBINED_FICTION;
        } else throw new IllegalArgumentException(getBadArgumentsMessage());
    }

    private static ListName getNonFictionListName(String flagsToken) {
        if (flagsToken.contains("e")) {
            return ListName.EBOOK_NONFICTION;
        } else if (flagsToken.contains("h")) {
            return ListName.HARDCOVER_NONFICTION;
        } else if (flagsToken.contains("c")) {
            return ListName.COMBINED_NONFICTION;
        } else throw new IllegalArgumentException(getBadArgumentsMessage());
    }

    private static String getBadArgumentsMessage() {
        return """
                Bad Arguments :
                - Exactly two flags are required (-nc):
                Fiction/NonFiction:
                    [-f] - Fiction
                    [-n] - Non-Fiction
                Format:
                    [-e] - E-Book
                    [-h] - Hardback
                    [-c] - Combined
                    
                Example: -fe - Fiction E-Book
                """;
    }
}