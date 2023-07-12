package com.example.reperksproto2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class RegistrationFragment extends Fragment {
ImageButton imgbtn2;
Button register_button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

       imgbtn2 = view.findViewById(R.id.back_buttonSp);
       register_button = view.findViewById(R.id.Register_btn);

       imgbtn2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(v).navigate(R.id.action_registrationFragment_to_loginFragment);
           }
       });

       register_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(v).navigate(R.id.action_registrationFragment_to_homeFragment);
           }
       });

        return view;
    }
}