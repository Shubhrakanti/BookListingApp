package com.example.giddu.booklistingapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<ArrayList<Book>> {

    EditText searchText;
    ListView bookListView;
    LinearLayout searchLayout;
    BookAdapter bookAdapter;
    private static final int THIS_LOADER_ID = 1;
    private static final String base_URL = "https://www.googleapis.com/books/v1/volumes?q=quilting";
    private static final String LOG_TAG = "Main Activity";
    private String addOn;
    private String finalURL = "";
    private boolean isConnected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = (EditText) findViewById(R.id.searchString);
        bookListView = (ListView) findViewById(R.id.list);
        searchLayout = (LinearLayout) findViewById(R.id.search_layout);

        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {
            Toast.makeText(this, "No interent connection", Toast.LENGTH_SHORT).show();



        } else{
            getLoaderManager().initLoader(THIS_LOADER_ID, null, this);
            bookListView.setEmptyView((findViewById(R.id.no_Result)));
        }



    }

    public void searchClicked(View v) {

        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            addOn = searchText.getText().toString() + "&maxResults=25";
            finalURL = base_URL + addOn;
            getLoaderManager().initLoader(THIS_LOADER_ID, null, this);
            bookListView.setEmptyView((findViewById(R.id.no_Result)));

        } else Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public android.content.Loader<ArrayList<Book>> onCreateLoader(int id, Bundle args) {

        return new BookLoader(this, base_URL);
    }

    @Override
    public void onLoadFinished(android.content.Loader<ArrayList<Book>> loader, ArrayList<Book> data) {

        bookAdapter = new BookAdapter(this, data);
        bookListView.setAdapter(bookAdapter);

        Log.d(LOG_TAG, "ONfinished");

    }

    @Override
    public void onLoaderReset(android.content.Loader<ArrayList<Book>> loader) {
        bookAdapter.clear();

    }

}
