package edu.virginia.cs.threelayer.data;

import edu.virginia.cs.threelayer.BestSellersList;
import edu.virginia.cs.threelayer.Book;
import org.json.JSONArray;
import org.json.JSONObject;

public class BestSellersJSON {

    public BestSellersList getBestSellerList(JSONObject jsonObject) {
        BestSellersList bestSellersList = new BestSellersList();
        JSONArray bookList = jsonObject.getJSONObject("results").getJSONArray("books");
        for (Object object : bookList) {
            if (object instanceof JSONObject bookJson) {
                Book book = getBookFromJSON(bookJson);
                int rank = bookJson.getInt("rank");
                bestSellersList.addBook(book, rank);
            }
        }
        return bestSellersList;
    }

    public Book getBookFromJSON(JSONObject bookJson) {
        String isbn = bookJson.getString("primary_isbn10");
        String title = bookJson.getString("title");
        String author = bookJson.getString("author");
        int weeksOnList = bookJson.getInt("weeks_on_list");
        return new Book(isbn, title, author, weeksOnList);
    }
}
