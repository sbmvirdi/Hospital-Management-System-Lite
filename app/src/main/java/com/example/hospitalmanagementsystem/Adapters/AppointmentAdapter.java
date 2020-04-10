package com.example.hospitalmanagementsystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalmanagementsystem.ModelClasses.Appointment;
import com.example.hospitalmanagementsystem.R;

import org.w3c.dom.Text;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.MyViewHolder> {

    private Context mContext;
    private List<Appointment> mList;
    private boolean isDoctor;


    public AppointmentAdapter(Context mContext, List<Appointment> mList, boolean isDoctor) {
        this.mContext = mContext;
        this.mList = mList;
        this.isDoctor = isDoctor;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.appointmentrow,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     holder.setData(mList.get(position).getApid(),mList.get(position).getDname(),mList.get(position).getDept(),mList.get(position).getTiming(),mList.get(position).getPname());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder  extends  RecyclerView.ViewHolder{
        private TextView appid,dname,dept,timing,dnamelabel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            timing = itemView.findViewById(R.id.timing);
            appid = itemView.findViewById(R.id.appid);
            dname = itemView.findViewById(R.id.dname);
            dept = itemView.findViewById(R.id.dept);
            dnamelabel = itemView.findViewById(R.id.dnamelabel);
        }

        private void setData(int appid,String dname,String dept,int timing,String pname){
            this.appid.setText(appid+"");

            if (isDoctor) {
                this.dname.setText(pname);
                this.dnamelabel.setText("Patient Name:");
            }
            else{
                this.dname.setText(dname);
                this.dnamelabel.setText("Doctor Name:");
            }
            this.dept.setText(dept);
            if (timing == 1){
                this.timing.setText("9 AM - 10 AM");
            }else if (timing == 2){
                this.timing.setText("10 AM - 11 AM");
            }else{
                this.timing.setText("11 AM - 12 PM");
            }
        }
    }
}
