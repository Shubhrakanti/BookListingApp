package com.example.giddu.booklistingapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giddu on 3/10/17.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> objects){
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Book current = getItem(position);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(current.getTitle());

        TextView subtitle = (TextView) convertView.findViewById(R.id.subtitle);
        subtitle.setText(current.getSubtitle());

        TextView pubDate = (TextView) convertView.findViewById(R.id.date);
        pubDate.setText(current.getPublishDate());

        String [] authors = current.getAuthors();
        StringBuffer appendAuthors = new StringBuffer("");

        for (int i = 0; i < authors.length; i++){
            appendAuthors.append(authors[i]);
        }

        TextView authorTextView = (TextView) convertView.findViewById(R.id.authors);
        authorTextView.setText(appendAuthors);

        return listItemView;

    }
}
