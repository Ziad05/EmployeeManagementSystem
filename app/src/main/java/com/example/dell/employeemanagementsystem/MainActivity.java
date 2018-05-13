package com.example.dell.employeemanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextName;
    private EditText editTextDesignation;
    private EditText editTextSalary;
    private Spinner spinnerAddDept;

    private Button buttonAdd;
    private Button buttonView;

    Spinner sp;
    static String urlAddress="http://192.168.1.15/Android/crudmysql/department/viewDept.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextDesignation=(EditText)findViewById(R.id.editTextDesg);
        editTextSalary=(EditText)findViewById(R.id.editTextSalary);
        spinnerAddDept=(Spinner)findViewById(R.id.spinnerAddDept);

        buttonAdd=(Button)findViewById(R.id.buttonAdd);
        buttonView=(Button)findViewById(R.id.buttonView);

        //Setting listener to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);


        sp= (Spinner) findViewById(R.id.spinnerAddDept);


        Downloader d=new Downloader(MainActivity.this,urlAddress,sp);
        d.execute();


    }

    @Override
    public void onClick(View v) {
        if (v== buttonAdd){
           addEmployee();
            cleantable();
        }
        if (v== buttonView){
            startActivity(new Intent(this,ViewAllItemActivity.class));
        }


    }
    public void cleantable(){
        editTextName.setText(null);
        editTextDesignation.setText(null);
        editTextSalary.setText(null);
    }

    public void addEmployee(){
        final String name= editTextName.getText().toString().trim();
        final String desg= editTextDesignation.getText().toString().trim();
        final String sal= editTextSalary.getText().toString().trim();
        final String dept= spinnerAddDept.getSelectedItem().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME,name);
                params.put(Config.KEY_EMP_DESG,desg);
                params.put(Config.KEY_EMP_SAL,sal);
                params.put(Config.KEY_DEPT,dept);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }



}

