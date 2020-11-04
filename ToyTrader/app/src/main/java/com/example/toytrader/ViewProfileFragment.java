package com.example.toytrader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ViewProfileFragment extends Fragment implements FirebaseListener {
    private Button editProfileButton;
    private TextView changePasswordText;

    public ViewProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_view_profile, container, false);
        FirebaseHelper.getInstance().getDetailsForCurrentUser();
        editProfileButton = (Button)view.findViewById(R.id.edit_profile_btn);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.profile_fragment,new EditProfileFragment()).commit();
            }
        });

        changePasswordText  = (TextView)view.findViewById(R.id.change_password_link);
        changePasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.profile_fragment,new ChangePasswordFragment()).commit();
            }
        });
        return view;
    }

    @Override
    public <T> void getFBData(T event) {
        if (event != null){
            System.out.println(event);
        }
    }
}