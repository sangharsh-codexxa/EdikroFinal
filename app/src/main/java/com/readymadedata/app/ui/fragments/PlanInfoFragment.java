package com.readymadedata.app.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.readymadedata.app.R;
import com.readymadedata.app.items.UserItem;
import com.readymadedata.app.utils.Constant;
import com.readymadedata.app.utils.PrefManager;
import com.readymadedata.app.viewmodel.UserViewModel;

import java.util.Objects;


public class PlanInfoFragment extends Fragment {


    public PlanInfoFragment() {
        // Required empty public constructor
    }
    UserViewModel userViewModel;

    View view;
    UserItem userItem;
    PrefManager prefManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan_info, container, false);

        prefManager = new PrefManager(getContext());

        userViewModel = new ViewModelProvider(this.requireActivity()).get(UserViewModel.class);


        userViewModel.getDbUserData(prefManager.getString(Constant.USER_ID)).observe(this.getActivity(), item -> {
            if (item != null) {
                userItem = item.user;
                Log.e("UserId",userItem.planDuration);
               TextView textView =   view.findViewById(R.id.tv_activePlanInfo);
                textView.setText("Duration : "+userItem.planDuration);

                TextView textView1 =   view.findViewById(R.id.tv_plan_name);
                textView1.setText("Plan Name : "+userItem.planName);


                TextView textView2 =   view.findViewById(R.id.tv_duration);
                textView2.setText("Plan Start Date : "+userItem.planStartDate);

                TextView textView3 =   view.findViewById(R.id.tv_plan_endData);
                textView3.setText("Plan End Date : "+userItem.planEndDate);



            }
        });

        return view;
    }
}