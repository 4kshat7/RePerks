package com.example.reperksproto2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(intentResult!=null){
            String contents = intentResult.getContents();
            if(contents!=null){
                Log.d( "/" + "BLA BAL BLACK SHEEP", "Data: " + data);
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_splashFragment);


            }
        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }

        super.onActivityResult(requestCode, resultCode, data);


    }
}