package com.nikita.employee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    RecyclerView recyclerView_emp;
    EmployeePojo employeePojo;
    List<EmployeePojo> employeePojoList;
    EmployeeAdapter employeeAdapter;
    String baseURL = "http://dummy.restapiexample.com/api/v1/employees";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Employee List");

        requestQueue = Volley.newRequestQueue(this);

        recyclerView_emp = findViewById(R.id.recycler_view_employee);
        employeePojoList = new ArrayList<>();

        getEmployee(baseURL); // call


        recyclerView_emp.setHasFixedSize(true);
        recyclerView_emp.setLayoutManager(new LinearLayoutManager(this));

        employeeAdapter = new EmployeeAdapter(this, employeePojoList);
        recyclerView_emp.setAdapter(employeeAdapter);
        employeeAdapter.notifyDataSetChanged();

    }

    private void getEmployee(String baseURL) {
        stringRequest = new StringRequest(Request.Method.GET, baseURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray ja = null;
                        try {
                            ja = new JSONArray(response);
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject obj = (JSONObject) ja.get(i);
                                employeePojoList.add(new EmployeePojo(
                                        obj.getString("id"),
                                        obj.getString("employee_name"),
                                        obj.getString("employee_salary"),
                                        obj.getString("employee_age"),
                                        obj.getString("profile_image")
                                ));
                            }
                            employeeAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });   stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

}
