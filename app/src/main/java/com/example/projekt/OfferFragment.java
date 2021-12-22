package com.example.projekt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class OfferFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //((MainActivity) getActivity()).setActionBarTitle("Your Title");
        return inflater.inflate(R.layout.fragment_offer, container, false);
    }
}