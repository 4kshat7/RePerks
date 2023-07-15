package com.example.reperksproto2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class RewardFragment extends Fragment {

    ImageButton imgbtn1;
    TextView RPtextView2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reward, container, false);
        Bundle rp = new Bundle();

        imgbtn1 = view.findViewById(R.id.back_buttonRe);
        RPtextView2 = view.findViewById(R.id.reward_points);
        getParentFragmentManager().setFragmentResultListener("datafrom1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                String rpp = result.getString("df1");
                RPtextView2.setText(rpp);
                rp.putString("df2",rpp);

            }
        });


        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getParentFragmentManager().setFragmentResult("datafrom2",rp);

                Navigation.findNavController(v).navigate(R.id.action_rewardFragment_to_homeFragment);

            }
        });
        return view;
    }
}