package com.example.hospitalmanagementsystem.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalmanagementsystem.ModelClasses.Room;
import com.example.hospitalmanagementsystem.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {
    private Context mContext;
    private List<Room> mList;

    public RoomAdapter(Context mContext, List<Room> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.room_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.setData(mList.get(position).getAvail(),mList.get(position).getRoomno());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView roomimage;
        private TextView roomno;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            roomimage = itemView.findViewById(R.id.roomimage);
            roomno = itemView.findViewById(R.id.roomno);
        }


        private void setData(int avail,int roomno){
            if (avail == 1){
                Picasso.get().load(R.drawable.room_available).into(roomimage);
            }else{
                Picasso.get().load(R.drawable.room_not_available).into(roomimage);
            }

            this.roomno.setText(roomno+"");
        }
    }
}
