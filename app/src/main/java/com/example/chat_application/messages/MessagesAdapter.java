package com.example.chat_application.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_application.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {
    private final List<MessagesList> messagesLists;
    private final Context context;

    public MessagesAdapter(List<MessagesList> messagesLists, Context context) {
        this.messagesLists = messagesLists;
        this.context = context;
    }

    @NonNull
    @Override
    public MessagesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder((ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView profilePic;
        private TextView name;
        private TextView lastMessage;
        private TextView unseenMessage;
        public MyViewHolder(@NonNull ViewGroup parent) {
            super(parent);
            profilePic = parent.findViewById(R.id.profilePic);
            name = parent.findViewById(R.id.name);
            lastMessage = parent.findViewById(R.id.lastMessage);
            unseenMessage = parent.findViewById(R.id.unseenMessage);

        }
    }
}

