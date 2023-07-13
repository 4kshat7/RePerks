package com.example.reperksproto2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegistrationFragment extends Fragment {
ImageButton imgbtn2;
Button register_button;
TextInputEditText editTextEmail , editTextPassword;
TextView textView;
FirebaseAuth mAuth;

ProgressBar progressBar1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

       imgbtn2 = view.findViewById(R.id.back_buttonSp);
       register_button = view.findViewById(R.id.Register_btn);
       editTextEmail = view.findViewById(R.id.email);
       editTextPassword = view.findViewById(R.id.pass);
       textView = view.findViewById(R.id.already_have_);
       mAuth = FirebaseAuth.getInstance();
       progressBar1 = view.findViewById(R.id.progressBar1);
       progressBar1.setVisibility(View.INVISIBLE);

       imgbtn2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(v).navigate(R.id.action_registrationFragment_to_loginFragment);
           }
       });

       textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(v).navigate(R.id.action_registrationFragment_to_loginFragment);
           }
       });

       register_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               progressBar1.setVisibility(View.VISIBLE);
               String email,password;
               email = String.valueOf(editTextEmail.getText());
               password = String.valueOf(editTextPassword.getText());

               if(TextUtils.isEmpty(email)){
                   progressBar1.setVisibility(View.INVISIBLE);
                   Log.d("EMPTY!!","EMAIL FIELD IS EMPTY!!!!");
                   Toast.makeText(getActivity(),"Please Enter Email Address",Toast.LENGTH_SHORT).show();
                   return;
               }

               if(TextUtils.isEmpty(password)){
                   progressBar1.setVisibility(View.INVISIBLE);
                   Toast.makeText(getActivity(),"Please Enter Password",Toast.LENGTH_SHORT).show();
                   Log.d("EMPTY!!","PASSWORD FIELD IS EMPTY!!!!");
                   return;
               }

               mAuth.createUserWithEmailAndPassword(email, password)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   // Sign in success, update UI with the signed-in user's information
                                   progressBar1.setVisibility(View.INVISIBLE);
                                   Toast.makeText(getActivity(), "Account Created Successfully.",
                                           Toast.LENGTH_SHORT).show();
                                   Navigation.findNavController(v).navigate(R.id.action_registrationFragment_to_loginFragment);
                                   FirebaseUser user = mAuth.getCurrentUser();
                               } else {
                                   // If sign in fails, display a message to the user.
                                   Toast.makeText(getActivity(), "Please enter a Valid email and minimum 6 digit password ",
                                           Toast.LENGTH_LONG).show();
                               }
                         }
                  });



           }
       });

        return view;
    }
}