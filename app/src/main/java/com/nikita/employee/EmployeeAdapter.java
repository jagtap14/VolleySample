package com.nikita.employee;

import android.app.ActionBar;
import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.BookedVenueViewHolder> {

    Context context;
    List<EmployeePojo> employeePojoList;


    public EmployeeAdapter(Context context,List<EmployeePojo> bookedVeneuPojoList) {
        this.context = context;
        this.employeePojoList = bookedVeneuPojoList;
    }

    @NonNull
    @Override
    public BookedVenueViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.raw_file_for_emmployee, viewGroup, false);
        return new BookedVenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookedVenueViewHolder bookedVenueViewHolder, final int i) {

        bookedVenueViewHolder.txt_name.setText(""+employeePojoList.get(i).getEmployee_name());
        bookedVenueViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,EmployeeDetailsActivity.class);
                intent.putExtra("id",employeePojoList.get(i).getId());
                intent.putExtra("name",employeePojoList.get(i).getEmployee_name());
                intent.putExtra("age",employeePojoList.get(i).getEmployee_age());
                intent.putExtra("salary",employeePojoList.get(i).getEmployee_salary());
                intent.putExtra("pic",employeePojoList.get(i).getProfile_image());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return employeePojoList.size();
    }

    public class BookedVenueViewHolder extends RecyclerView.ViewHolder {


        TextView txt_name;

        public BookedVenueViewHolder(@NonNull View itemView) {
            super(itemView);

          txt_name = itemView.findViewById(R.id.txt_name);
        }
    }
}

