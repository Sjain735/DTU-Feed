package com.yatin.whatshappeningdtu;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yatin on 01-04-2017.
 */

public class CloudArrayAdapter extends ArrayAdapter {

    ArrayList<String> cloudEvents = new ArrayList<>();
    public CloudArrayAdapter(@NonNull Context context, int textViewResourceId,ArrayList<String> cloudEvents) {
        super(context, textViewResourceId);
        this.cloudEvents = cloudEvents;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.typeview1, null);

        TextView textView = (TextView)view.findViewById(R.id.type1_text);
        textView.setText(cloudEvents.get(position));

     /*   TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView societyTextView = (TextView) view.findViewById(R.id.society);
        TextView dateTextView = (TextView) view.findViewById(R.id.date);
        TextView timeTextView = (TextView) view.findViewById(R.id.time);
        TextView venueTextView = (TextView) view.findViewById(R.id.venue);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.description);

        //textView.setText(animalList.get(position).getAnimalName());
        nameTextView.setText(Name);
        societyTextView.setText(Society);
        dateTextView.setText(Date);
        timeTextView.setText(Time);
        venueTextView.setText(Venue);
        descriptionTextView.setText(Description);



*/

        return view;
    }
}
