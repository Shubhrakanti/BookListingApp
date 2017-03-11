package com.example.giddu.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by giddu on 3/10/17.
 */

public class BookLoader extends AsyncTaskLoader<ArrayList<Book>> {

    private String url;

    public BookLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        onForceLoad();
    }

    @Override
    public ArrayList<Book> loadInBackground() {

        ArrayList<Book> books = null;
        String response = null;

        // Perform the network request, parse the response, and extract a list of earthquakes.
        try {
            URL urlSender = new URL(url);
            try {
                response = QueryUtils.makeHttpRequest(urlSender);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (response != null){

            books = QueryUtils.fetchData(response);
        }

        return books;
    }
}


