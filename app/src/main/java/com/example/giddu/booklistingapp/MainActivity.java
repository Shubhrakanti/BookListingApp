package com.example.giddu.booklistingapp;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<ArrayList<Book>> {

    EditText searchText;
    ListView bookListView;
    LinearLayout searchLayout;
    BookAdapter bookAdapter;
    private static final int THIS_LOADER_ID = 1;
    private static final String base_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String LOG_TAG = "Main Activity";
    private String addOn;
    private String finalURL = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = (EditText) findViewById(R.id.searchString);
        bookListView = (ListView) findViewById(R.id.list);
        searchLayout = (LinearLayout) findViewById(R.id.search_layout);


        addOn = "quilting";

        finalURL = base_URL+addOn;

        Log.d(LOG_TAG, "OnCreate");

        getLoaderManager().initLoader(THIS_LOADER_ID, null, this);



    }

    public void searchClicked(View v){

        addOn = searchText.getText().toString();





    }

    @Override
    public android.content.Loader<ArrayList<Book>> onCreateLoader(int id, Bundle args) {

        Log.d(LOG_TAG, "OnCreateLoader");
        return new BookLoader(this, finalURL);

    }

    @Override
    public void onLoadFinished(android.content.Loader<ArrayList<Book>> loader, ArrayList<Book> data) {

        bookAdapter = new BookAdapter(this, data);
        bookListView.setAdapter(bookAdapter);

        Log.d(LOG_TAG, "OnFinishLoader");

        searchLayout.setVisibility(View.GONE);

        bookListView.setVisibility(View.VISIBLE);



    }

    @Override
    public void onLoaderReset(android.content.Loader<ArrayList<Book>> loader) {
        android.app.LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(THIS_LOADER_ID, null, this);



    }

}
