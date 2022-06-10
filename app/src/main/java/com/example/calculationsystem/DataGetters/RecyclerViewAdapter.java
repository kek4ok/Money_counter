package com.example.calculationsystem.DataGetters;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.calculationsystem.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<String> report;
    Context context;
    boolean isOwner;

    public RecyclerViewAdapter(Context context, List<String> report, boolean isOwner) {
        this.report = report;
        this.context = context;
        this.isOwner = isOwner;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_ticket, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {

        holder.name_money.setText(report.get(i));
        holder.parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intend = new Intent(context, DetailedReportActivity.class);
                intend.putExtra("type", isOwner);
                intend.putExtra("index", i);
                context.startActivity(intend);

            }
        });

    }

    @Override
    public int getItemCount() {
        return report.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout parent;
        TextView name_money;


        public ViewHolder(View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.ticket_parent);
            name_money = itemView.findViewById(R.id.ticket_name);
        }
    }

}
