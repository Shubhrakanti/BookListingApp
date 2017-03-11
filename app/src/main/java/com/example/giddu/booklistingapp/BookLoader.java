package com.example.giddu.booklistingapp;

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

public class BookLoader extends Loader<ArrayList<Book>> {

    private String url;

    public BookLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        onForceLoad();
    }


    public ArrayList<Book> loadInBackground() {
        if (url == null) {
            Log.d("BookLoader", "null URL");
            return null;
        }

        ArrayList<Book> books = null;
        String response = null;

        // Perform the network request, parse the response, and extract a list of earthquakes.
        try {
            URL urlSender = new URL(url);
            Log.d("BookLoader", "making URL");
            try {
                response = QueryUtils.makeHttpRequest(urlSender);
                Log.d("BookLoader", "Making Request");
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


