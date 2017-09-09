package com.example.arushi.hackathon;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pallavi on 9/9/17.
 */

public class CustomListAdapter extends ArrayAdapter<Club> {

    ArrayList<Club> clubs;
    Context context;
    int resource;

    public CustomListAdapter(Context context, int resource, ArrayList<Club> clubs) {
        super(context,resource,clubs);
        this.clubs = clubs;
        this.context = context;
        this.resource = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_custom_list_adapter,null,true);

        }

        Club club = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_logo);
        Picasso.with(context).load(club.getImagePath()).into(imageView);
        TextView textView = (TextView) convertView.findViewById(R.id.tv_club);
        textView.setText(club.getName());

        return convertView;
    }


}