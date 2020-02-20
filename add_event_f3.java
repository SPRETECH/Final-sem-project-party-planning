package com.example.squadpartyplannerapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class add_event_f3 extends Fragment implements View.OnClickListener{
    Context context;
    NavController navController;
    EditText Description;
    CheckBox Assistance,Decoration,Food;
    Button Next;
    ProgressBar progressBar;
    public String assistance,decoration,food,description;
    SharedPreferences sharedPreferences;
    public add_event_f3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_event_f3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Add Event");

        sharedPreferences = getActivity().getSharedPreferences("AddEventData",Context.MODE_PRIVATE);
        context = getActivity().getApplicationContext();
        navController = Navigation.findNavController(getActivity(),R.id.nav_addEvent_host_fragment);
        Description = view.findViewById(R.id.event_description_f3);
        Assistance = view.findViewById(R.id.event_assistance_f3);
        Decoration = view.findViewById(R.id.event_decoration_f3);
        Food = view.findViewById(R.id.event_food_f3);
        Next = view.findViewById(R.id.next_btn_f3);
        progressBar = view.findViewById(R.id.progress_add_event_f3);

        Next.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == Next)
        {
            if (Assistance.isChecked())
            {
                assistance = "Yes";
            }else
            {
                assistance="No";
            }
            if(Decoration.isChecked())
            {
                decoration = "Yes";
            }
            else {
                decoration="No";
            }
            if(Food.isChecked())
            {
                food = "Yes";
            }
            else
            {
                food="No";
            }
            description = Description.getText().toString();

            if(!description.isEmpty())
            {
                Log.e("extras ",assistance+"\n"+decoration+"\n"+food);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("event_description",description);
                editor.putString("event_assistance",assistance);
                editor.putString("event_decoration",decoration);
                editor.putString("event_food",food);
                editor.commit();
                //Toast.makeText(context,"Description: "+description+"\n Assistance: "+assistance+"\n Decoration: "+decoration+"\n Food: "+food,Toast.LENGTH_LONG).show();
                navController.navigate(R.id.action_add_event_f3_to_add_event_f4);
            }
            else
            {
                Toast.makeText(context,"Please fill all fields!",Toast.LENGTH_LONG).show();
            }
        }
    }
}
