package com.example.reperksproto2;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;


public class HomeFragment extends Fragment {
    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;

    TextView textView;

    FirebaseUser user;
    DatabaseReference reference;
    String userID;
    TextView RPtextView;

    int arr[] = {0};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imageButton1 = view.findViewById(R.id.rectangle_6);
        imageButton2 = view.findViewById(R.id.rectangle_8);
        imageButton3 = view.findViewById(R.id.rectangle_9);
        imageButton4 = view.findViewById(R.id.rectangle_7);
        textView = view.findViewById(R.id.greeting_txt);
        RPtextView = view.findViewById(R.id.reward_points);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID =user.getUid();

        final TextView greetingTextView =(TextView) view.findViewById(R.id.greeting_txt);

        getParentFragmentManager().setFragmentResultListener("datafrom2", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String rpp = result.getString("df2");
                RPtextView.setText(rpp);
            }
        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                greetingTextView.setText("HI "+userProfile.name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Something went wrong!",Toast.LENGTH_SHORT);

            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_rewardFragment);
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_binSitesFragment);
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_historyFragment);
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
            builder.setMessage(result.getContents()+" RP");

            builder.setPositiveButton("Click to Redeem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    int n = arr.length;
                    int oldi = arr[n-1];
//                    String old = RPtextView.getText().toString();
                    String reS = (result.getContents());
                    int re = Integer.parseInt(reS);
//                    int oldi =Integer.parseInt(old);
                    int newi = oldi + re;
                    arr[n-1]= newi;
                    String newS = Integer.toString(newi);
                    RPtextView.setText(newS);
                    Bundle rp = new Bundle();
                    rp.putString("df1",newS);
                    getParentFragmentManager().setFragmentResult("datafrom1",rp);
//                    Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_rewardFragment);

                }
            }).show();
        }
    });
}