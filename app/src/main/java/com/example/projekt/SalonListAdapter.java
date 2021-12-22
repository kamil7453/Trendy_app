package com.example.projekt;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projekt.model.Salon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SalonListAdapter extends ArrayAdapter<Salon> {
    private List<Salon> data;
    private Context context;

    public SalonListAdapter(Context context, List<Salon> data){
        super(context, R.layout.salon_list_row);
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
            row = inflater.inflate(R.layout.salon_list_row, null);

            holder = new ViewHolder();
            holder.photo = (ImageView) row.findViewById(R.id.listView_salon_image);
            holder.name = (TextView) row.findViewById(R.id.listview_salon_name);
            holder.city = (TextView) row.findViewById(R.id.listview_salon_city);
            holder.address = (TextView) row.findViewById(R.id.listview_salon_adress);


            row.setTag(holder);
        } else{
            holder = (ViewHolder) row.getTag();
        }

        Picasso.get()
                .load(data.get(position).getPhoto())
                .into(holder.photo);
        holder.name.setText(data.get(position).getName());
        holder.address.setText(data.get(position).getStreet());
        holder.city.setText(data.get(position).getCity());

        return row;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    static class ViewHolder{
        ImageView photo;
        TextView name;
        TextView city;
        TextView address;
    }
}
