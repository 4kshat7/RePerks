package com.example.reperksproto2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.zxing.integration.android.IntentIntegrator;


public class HomeFragment extends Fragment {
    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imageButton1 = view.findViewById(R.id.rectangle_6);
        imageButton2 = view.findViewById(R.id.rectangle_7);
        imageButton3 = view.findViewById(R.id.rectangle_8);
        imageButton4 = view.findViewById(R.id.rectangle_9);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("Scan a QR");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();
            }
        });

        return view;

    }
}