package com.example.projekt;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projekt.model.Visit;

import java.util.List;

public class VisitListAdapter extends ArrayAdapter<Visit> {
    private List<Visit> data;
    private Context context;

    public VisitListAdapter(Context context, List<Visit> data){
        super(context, R.layout.visit_list_row);
        this.data=data;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.visit_list_row, null);

            holder = new ViewHolder();
            holder.date = (TextView) row.findViewById(R.id.visit_date);
            holder.hour = (TextView) row.findViewById(R.id.visit_hour);
            holder.name = (TextView) row.findViewById(R.id.visit_salon_name);
            holder.service = (TextView) row.findViewById(R.id.visit_service_name);
            holder.street = (TextView) row.findViewById(R.id.visit_salon_address);
            holder.city = (TextView) row.findViewById(R.id.listview_salon_city);

            row.setTag(holder);
        } else{
            holder = (ViewHolder) row.getTag();
        }


        holder.date.setText(data.get(position).getDate());
        holder.hour.setText(data.get(position).getHour());
        holder.name.setText(data.get(position).getSalon().getName());
        holder.service.setText(data.get(position).getService().getName());
        holder.street.setText(data.get(position).getSalon().getStreet());
        holder.city.setText(data.get(position).getSalon().getCity());

        return row;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    static class ViewHolder{
        TextView date;
        TextView hour;
        TextView name;
        TextView service;
        TextView street;
        TextView city;
    }
}
