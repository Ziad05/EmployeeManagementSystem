package com.example.dell.employeemanagementsystem;

/**
 * Created by DELL on 04/22/2018.
 */

public class Config {

    public static final String MAIN_URL = "http://192.168.1.15";

    public static final String URL_ADD= MAIN_URL+"/android/crudmysql/addEmp.php";

    public static final String URL_GET_ALL=MAIN_URL+"/android/crudmysql/getAllEmp.php";
    public static final String URL_DELETE_EMP=MAIN_URL+"/android/crudmysql/deleteEmp.php?id=";
    public static final String URL_UPDATE_EMP=MAIN_URL+"/android/crudmysql/updateEmp.php";
    public static final String URL_GET_EMP= MAIN_URL+"/android/crudmysql/getEmp.php?id=";


    public static final String URL_ADD_DEPT=MAIN_URL+"/android/crudmysql/department/addDept.php";

    //addValue In spinner
    static String urlAddress="http://192.168.1.15/Android/crudmysql/department/viewDept.php";


    //keys that will be used to send the request to php script
    public static final String KEY_EMP_ID="id";
    public static final String KEY_EMP_NAME="name";
    public static final String KEY_EMP_DESG="designation";
    public static final String KEY_EMP_SAL="salary";

    public static final String KEY_DEPT_ID="dept_id";
    public static final String KEY_DEPT_NAME="dept_name";


    public static final String KEY_DEPT="department_name";

    //json tag
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID="id";
    public static final String TAG_NAME="name";
    public static final String TAG_DESG="designation";
    public static final String TAG_SAL="salary";
    public static final String TAG_DEPT="department";



}
