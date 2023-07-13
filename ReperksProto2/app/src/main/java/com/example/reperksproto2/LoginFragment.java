package com.example.reperksproto2;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

public class LoginFragment extends Fragment {

    ImageButton imgbtn;
//    this is a test comment
    Button login_button;
    TextInputEditText editTextEmail , editTextPassword;
    TextView textView;
    FirebaseAuth mAuth;
    ProgressBar progressBar1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        imgbtn = view.findViewById(R.id.back_buttonLp);
        login_button=view.findViewById(R.id.login_btn);
        editTextEmail = view.findViewById(R.id.email);
        editTextPassword = view.findViewById(R.id.pass);
        textView = view.findViewById(R.id.don_t_have_);
        mAuth = FirebaseAuth.getInstance();

        progressBar1 = view.findViewById(R.id.progressBar1);
;


        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_splashFragment);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registrationFragment);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar1.setVisibility(View.VISIBLE);
                String email,password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Log.d("WORK!!","EMAIL FIELD IS EMPTY!!!!");
                    Toast.makeText(getActivity(),"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getActivity(),"Please Enter Password",Toast.LENGTH_SHORT).show();
                    Log.d("WORK!!","PASSWORD FIELD IS EMPTY!!!!");
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar1.setVisibility(View.GONE);
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("LOGIN WORKK!!", "signInWithEmail:success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Log.d("USERs NAME!!", "NAME:");
                                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_homeFragment);
                                } else {
                                    progressBar1.setVisibility(View.GONE);
                                    // If sign in fails, display a message to the user.
                                    Log.w("LOGIN NOT WORK!!", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getActivity(), "Authentication failed, Enter valid email and password",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        return view;
    }
}