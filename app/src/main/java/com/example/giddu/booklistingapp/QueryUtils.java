package com.example.giddu.booklistingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giddu on 3/10/17.
 */

public final class QueryUtils {

    private QueryUtils() {
    }

    public static ArrayList<Book> fetchData(String JSON_response) {

        ArrayList<Book> books = new ArrayList<Book>();

        try {
            JSONObject root = new JSONObject(JSON_response);
            JSONArray bookList = root.getJSONArray("items");
            for (int i = 0; i < bookList.length(); i++) {
                JSONObject currentBook = bookList.getJSONObject(i);

                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");

                String[] authors;

                if (volumeInfo.has("authors")) {
                    JSONArray JSON_authors = volumeInfo.getJSONArray("authors");
                    authors = new String[JSON_authors.length()];
                    for (int x = 0; x < authors.length; x++) {
                        authors[x] = JSON_authors.getString(x);
                    }
                } else {
                    authors = new String[1];
                    authors[0] = "No authors's available";
                }

                books.add(new Book(title, authors));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return books;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            int responseCoode = urlConnection.getResponseCode();

            if (responseCoode == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            // TODO: Handle the exception
            Log.d("MAKE HTTP", "HERE");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}
