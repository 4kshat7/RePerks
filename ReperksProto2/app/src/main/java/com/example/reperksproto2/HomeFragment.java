package com.example.reperksproto2;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;


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
                scanCode();
            }
        });

        return view;
    }

    private void scanCode() {

            ScanOptions options = new ScanOptions();
            options.setPrompt("Press the Volume up Button to switch on FLASH");
            options.setBeepEnabled(true);
            options.setOrientationLocked(true);
            options.setCaptureActivity(CaptureAct.class);
            barLaucher.launch((options));
    }

   public ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents()!=null){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Congratss!! You've Earned:");
            builder.setMessage(result.getContents());

            builder.setPositiveButton("Click to Redeem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            }).show();
        }
    });
}