package edu.virginia.cs.threelayer.data;

import edu.virginia.cs.threelayer.ListName;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

public class BestSellersApi {
    public static final String API_LINK = "https://api.nytimes.com/svc/books/v3/lists/";

    public static String apiKey;

    public BestSellersApi() {
        ApiKeyManager factory = new ApiKeyManager();
        apiKey = factory.getBestSellersAPIKey();
    }

    public JSONObject getCurrentBestSellers(ListName listName) {
        try {
            URL url = getCurrentBestSellersAddress(listName);
            String jsonData = getJSONStringFromURL(url);
            return new JSONObject(jsonData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getHistoricBestSellers(ListName listName, Date date) {
        try {
            URL url = getHistoricalBestSellersAddress(listName, date);
            String jsonData = getJSONStringFromURL(url);
            return new JSONObject(jsonData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getJSONStringFromURL(URL nyTimesBestSeller) throws IOException {
        InputStream inputStream = nyTimesBestSeller.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String jsonString = bufferedReader.lines().collect(Collectors.joining());
        bufferedReader.close();
        return jsonString;
    }

    private URL getCurrentBestSellersAddress(ListName listName) throws MalformedURLException {
        String urlString = API_LINK + getEncodedListName(listName) + "?" + getApiKeyString();
        return new URL(urlString);
    }

    private URL getHistoricalBestSellersAddress(ListName listName, Date date) throws MalformedURLException {
        DateStringFactory dateStringFactory = new DateStringFactory();
        String dateString = dateStringFactory.getDateString(date);
        String urlString = API_LINK + dateString + "/" + getEncodedListName(listName) + "?" + getApiKeyString();
        return new URL(urlString);
    }

    private String getApiKeyString() {
        return "api-key=" + apiKey;
    }

    public String getEncodedListName(ListName listName) {
        return switch (listName) {
            case COMBINED_NONFICTION -> "combined-print-and-e-book-nonfiction";
            case HARDCOVER_NONFICTION -> "hardcover-nonfiction";
            case COMBINED_FICTION -> "combined-print-and-e-book-fiction";
            case HARDCOVER_FICTION -> "hardcover-fiction";
            case EBOOK_FICTION -> "e-book-fiction";
            case EBOOK_NONFICTION -> "e-book-nonfiction";
        };
    }
}
