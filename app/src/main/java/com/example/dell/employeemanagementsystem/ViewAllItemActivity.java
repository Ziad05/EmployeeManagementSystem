package com.example.dell.employeemanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewAllItemActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    private ListView listView;
    private String JSON_STRING;

    SimpleAdapter adapter;

    EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_item);


        search=(EditText)findViewById(R.id.edittextSearch) ;
        listView=(ListView)findViewById(R.id.listViewAll);
        listView.setOnItemClickListener(this);
        getJSON();
        Addsearch();
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading=ProgressDialog.show(ViewAllItemActivity.this,
                        "Fetching Data","Wait...", false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING=s;
                showAllData();

            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh= new RequestHandler();
                String s=rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj= new GetJSON();
        gj.execute();

    }
    private void showAllData(){
        JSONObject jsonObject=null;
        ArrayList<HashMap<String,String>>list= new ArrayList<>();
        try {
            jsonObject= new JSONObject(JSON_STRING);

            JSONArray result= jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            for(int i=0; i<result.length();i++){
                JSONObject jo=result.getJSONObject(i);
                String id= jo.getString(Config.TAG_ID);

                String name=jo.getString(Config.TAG_NAME);
                String designation=jo.getString(Config.TAG_DESG);
                String salary=jo.getString(Config.TAG_SAL);

                HashMap<String,String> employee= new HashMap<>();

                employee.put(Config.TAG_ID,id);

                employee.put(Config.TAG_NAME,name);

                employee.put(Config.TAG_DESG,designation);

                employee.put(Config.TAG_SAL,salary);

                list.add(employee);


            }


        }
        catch (JSONException e){
            e.printStackTrace();

        }
        adapter=new SimpleAdapter(
                ViewAllItemActivity.this,list ,
                R.layout.all_list_item,new String[]
                {Config.TAG_ID,Config.TAG_NAME,Config.TAG_DESG,Config.TAG_SAL},new int[]{R.id.id,R.id.name,R.id.desg,R.id.salary});
        listView.setAdapter(adapter);

        }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Intent intent = new Intent(this,ViewEmployeeActivity.class);
        HashMap<String,String> map=(HashMap)adapterView.getItemAtPosition(position);
        String empId= map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.KEY_EMP_ID,empId);
        startActivity(intent);


    }
    private void Addsearch(){
        search.addTextChangedListener(new TextWatcher(){

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3){

               ViewAllItemActivity.this.adapter.getFilter().filter(cs);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void beforeTextChanged(CharSequence arg0,int arg1, int arg2, int arg3 ){

            }

            @Override
            public void afterTextChanged(Editable arg0){

            }

        });

    }
}
