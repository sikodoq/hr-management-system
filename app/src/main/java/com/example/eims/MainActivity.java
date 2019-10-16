package com.example.eims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
public class MainActivity extends AppCompatActivity  {
    String nameAndEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        try {
            JSONObject output = new JSONObject(bundle.getString("employee_data"));
            nameAndEmail = output.getString("first_name") + " " +output.getString("last_name") + ",\n" +output.getString("email");
            Fragment fragment = null;
            if(output.getString("is_pm").equalsIgnoreCase("1")){
                 fragment = new MainPM();
            }
            else{
                fragment = new MainEmployee();
            }
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentMainPage, fragment);
            ft.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        TextView employee_data =  findViewById(R.id.nameAndEmail);
        employee_data.setText(nameAndEmail);

    }



}
