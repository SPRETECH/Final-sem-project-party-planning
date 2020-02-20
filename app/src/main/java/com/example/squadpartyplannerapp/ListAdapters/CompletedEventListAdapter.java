package com.example.squadpartyplannerapp.ListAdapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.squadpartyplannerapp.ModelClass.EventData;
import com.example.squadpartyplannerapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CompletedEventListAdapter extends RecyclerView.Adapter<CompletedEventListAdapter.ItemViewHolder>
{
    Context context;
    ArrayList<EventData> dataArrayList;
    EventData eventData;
    Uri eventImage;

    public CompletedEventListAdapter(ArrayList<EventData> eventDataArrayList, Context context) {
        dataArrayList = eventDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new CompletedEventListAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        eventData = dataArrayList.get(position);
        eventImage = Uri.parse(eventData.getEventImageURL());
        Picasso.get().load(eventImage).into(holder.imageView);
        holder.eventName.setText(eventData.getEventName());
        holder.eventdate.setText(eventData.getEventDate());
        holder.eventStartTime.setText(eventData.getEventStartTime());
        holder.eventEndTime.setText(eventData.getEventEndTime());

    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView eventName,eventdate,eventStartTime,eventEndTime;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.event_image_list_item);
            eventName = itemView.findViewById(R.id.eventName_item);
            eventdate = itemView.findViewById(R.id.eventDate_item);
            eventStartTime = itemView.findViewById(R.id.eventStart_item);
            eventEndTime = itemView.findViewById(R.id.eventEnd_item);

        }
    }
}
