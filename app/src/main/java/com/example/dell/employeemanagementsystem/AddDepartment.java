package com.example.dell.employeemanagementsystem;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AddDepartment extends AppCompatActivity implements View.OnClickListener {
    EditText editTextaddDept;
    Button btnAddDept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);

        editTextaddDept=(EditText)findViewById(R.id.editTextaddDept);
        btnAddDept=(Button)findViewById(R.id.btnAddDept);


        btnAddDept.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v== btnAddDept){
            addDepartment();
            //cleantable();
        }
    }

    public void addDepartment(){
        final String dept_name= editTextaddDept.getText().toString().trim();

        class AddDept extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddDepartment.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AddDepartment.this,s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_DEPT_NAME,dept_name);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_DEPT, params);
                return res;
            }
        }

        AddDept ad = new AddDept();
        ad.execute();
    }
}
