package edu.virginia.cs.threelayer.business;

import edu.virginia.cs.threelayer.*;
import edu.virginia.cs.threelayer.data.BestSellersFacade;

import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

public class BestSellersService {
    private final BestSellersFacade bestSellersData;

    public BestSellersService() {
        bestSellersData = new BestSellersFacade();
    }

    public BestSellersList getCurrentBestSellerList(ListName listName) {
        return bestSellersData.getCurrentBestSellerList(listName);
    }

    public BestSellersList getHistoricBestSellerList(ListName listName, Date date) {
        return bestSellersData.getHistoricBestSellerList(listName, date);
    }

    public Book getLongestCurrentBestSeller(ListName listName) {
        BestSellersList bestSellers = getCurrentBestSellerList(listName);
        Optional<Book> longestCurrentBook = bestSellers.getBooks().stream()
                .max(Comparator.comparing(Book::getWeeksOnList));
        if (longestCurrentBook.isEmpty()) {
            throw new EmptyBestSellerListException(bestSellers.toString());
        }
        return longestCurrentBook.get();
    }
}
