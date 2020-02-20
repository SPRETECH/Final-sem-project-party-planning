package com.example.squadpartyplannerapp;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.squadpartyplannerapp.ListAdapters.UpcomingEventListAdapter;
import com.example.squadpartyplannerapp.ModelClass.EventData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class upcoming_frag extends Fragment {

    RecyclerView recyclerView;
    TextView textView;
    Context context;
    ProgressBar progressBar;

    ArrayList<EventData> eventDataArrayList = new ArrayList<>();
    UpcomingEventListAdapter upcomingEventListAdapter ;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String uid,eventName,eventDate,eventStartTime,eventEndTime,eventImageURL;


    Calendar calendar = Calendar.getInstance();
    final SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
    final SimpleDateFormat time_format = new SimpleDateFormat("hh:mm");
    int dd = calendar.get(Calendar.DAY_OF_MONTH);
    final int mm = calendar.get(Calendar.MONTH) + 1;
    int yy = calendar.get(Calendar.YEAR);
    int hh = calendar.get(Calendar.HOUR_OF_DAY);
    int MM = calendar.get(Calendar.MINUTE);
    final String current_time_s = hh + ":" + MM;
    final String current_date_s = dd + "/" + (mm) + "/" + yy;
    Date current_date_d = null;
    Date current_time_d = null;
    //Current Date and Time Get End

    public upcoming_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_frag, container, false);
    }

    public void getcurrentDateAndTimeObj()
    {
        try {
            current_date_d = date_format.parse(current_date_s);
            Log.i("D1", current_date_d.toString());
            current_time_d = time_format.parse(current_time_s);
            Log.i("T1", current_time_d.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getcurrentDateAndTimeObj();
        context = getActivity().getApplicationContext();
        progressBar = view.findViewById(R.id.progress_upcoming);
        recyclerView = view.findViewById(R.id.upcomingEvent_list);
        textView = view.findViewById(R.id.no_upcoming_text);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Events");
        uid = firebaseAuth.getCurrentUser().getUid();
        Log.e("user id",uid);
        eventDataArrayList.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    progressBar.setVisibility(View.VISIBLE);
                    for(DataSnapshot ds : dataSnapshot.getChildren())
                    {
                        if(uid.contentEquals(ds.child("hostID").getValue().toString()))
                        {

                            //Log.i("Date",ds.child("eventDate").getValue().toString());
                            eventName = ds.child("eventName").getValue().toString();
                            eventDate = ds.child("eventDate").getValue().toString();
                            eventStartTime = ds.child("eventStartTime").getValue().toString();
                            eventEndTime = ds.child("eventEndTime").getValue().toString();
                            eventImageURL = ds.child("eventImage").getValue().toString();


                            Date E_date= null;
                            Date E_startTime = null;
                            Date E_endTime = null;
                            try {
                                E_date = date_format.parse(eventDate);
                                E_startTime = time_format.parse(eventStartTime);
                                E_endTime = time_format.parse(eventEndTime);
                            }
                            catch (ParseException e)
                            {
                                e.printStackTrace();
                            }

                            if(E_date.compareTo(current_date_d) > 0 )
                            {
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                textView.setVisibility(View.GONE);
                                eventDataArrayList.add(new EventData(eventDate,eventStartTime,eventEndTime,eventImageURL,eventName));
                            }
                            else if(E_date.compareTo(current_date_d) == 0)
                            {
                                if(E_startTime.compareTo(current_time_d) > 0)
                                {
                                    if(E_endTime.compareTo(current_time_d) < 0) {
                                        progressBar.setVisibility(View.GONE);
                                        recyclerView.setVisibility(View.VISIBLE);
                                        textView.setVisibility(View.GONE);
                                        eventDataArrayList.add(new EventData(eventDate, eventStartTime, eventEndTime, eventImageURL, eventName));
                                    }

                                }
                            }



                        }
                    }
                    upcomingEventListAdapter = new UpcomingEventListAdapter(eventDataArrayList,context);
                    recyclerView.setAdapter(upcomingEventListAdapter);
                    upcomingEventListAdapter.notifyDataSetChanged();
                    if(eventDataArrayList.isEmpty())
                    {
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        textView.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
