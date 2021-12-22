package com.example.projekt;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projekt.model.Salon;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SalonInformationFragment extends Fragment implements OnMapReadyCallback {
    ImageView salonPhoto;
    TextView salonName, salonEmail, salonPhoneNumber, salonAddress;
    private GoogleMap mMap;
    String address, name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        Salon salon = bundle.getParcelable("salonInfo");
        address = salon.getStreet() + " " + salon.getCity();

        View view = inflater.inflate(R.layout.fragment_salon_information, container, false);
        salonPhoto = (ImageView) view.findViewById(R.id.salon_info_img_view);
        Picasso.get()
                .load(salon.getPhoto())
                .into(salonPhoto);

        salonName = view.findViewById(R.id.salon_name);
        salonName.setText(salon.getName());
        name = salon.getName();

        salonEmail = view.findViewById(R.id.salon_email);
        salonEmail.setText(salon.getEmail());

        salonPhoneNumber = view.findViewById(R.id.salon_phone);
        salonPhoneNumber.setText(Integer.toString(salon.getPhoneNumber()));

        salonAddress = view.findViewById(R.id.salon_address);
        salonAddress.setText(salon.getStreet() + ", " + salon.getCity());

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map_salon_info);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocationName(address, 1);
            if(addresses.size() > 0) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();
                LatLng location = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(location).title(name));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,15));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}