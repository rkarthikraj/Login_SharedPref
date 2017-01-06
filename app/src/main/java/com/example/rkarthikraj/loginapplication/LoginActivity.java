package com.example.rkarthikraj.loginapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    EditText uname, pwd;
    String Uname, Pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname = (EditText) findViewById(R.id.uname);
        pwd = (EditText) findViewById(R.id.pwd);

        //Checking Shared Pref whether the user logs in

        SharedPreferences sharedpref = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String name = sharedpref.getString("username", null);

        //String name will be null if app logs out or opens first time

        if(name==null)
        {
            //Nothing to do
        }
        else
        {
            //Calling out home activity
            Intent homeact = new Intent( getBaseContext() , HomeActivity.class);
            startActivity(homeact);
            finish(); //This will close the activity, so if we call the activity again,
        }

    }
    public void onClickReset(View v){
        uname.setText("");
        pwd.setText("");
    }
    public void onClickSignup(View v){
        Intent i=new Intent(this,SignUpActivity.class);
        startActivity(i);
    }
    public void onClickVisitor(View v){
        Intent vis=new Intent(this,VisitorActivity.class);
        startActivity(vis);
    }
    public void onClickLogin(View v){
        Uname = uname.getText().toString();
        Pwd = pwd.getText().toString();
        AsyncTaskDB runnerSignIn = new AsyncTaskDB();
        runnerSignIn.execute(Uname, Pwd, "signin");
    }

//ASYNC TASK

    class AsyncTaskDB extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            String UsNAME = params[0];
            String PWD = params[1];
            String TYPE=params[2];

            int tmp;

            try {
                String data="";
                URL url = new URL("http://192.168.1.213/login.php/?uname=" + UsNAME + "&pwd=" + PWD + "&type=" + TYPE);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write("".getBytes());
                InputStream is = httpURLConnection.getInputStream();
                os.flush();
                os.close();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();
                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }
        protected void onPostExecute(String result) {
            JSONObject json = null;
            String output = "";
            String out = "false";
            try {
                json = new JSONObject(result);
                out = json.getString("output");
            }
            catch(JSONException e){
                e.printStackTrace();
        }
        if ( out.matches("true"))
        {
            SharedPreferences sharedpref=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedpref.edit();
            editor.putString("username",uname.getText().toString());
            editor.apply();

            Intent homeact = new Intent( getBaseContext() , HomeActivity.class);
            startActivity(homeact);
            finish(); //this will close the activity, so if we call the activity again,
            // it will open new instance, therefore it will check for the shared prefence value again
        }else {
            Toast.makeText(LoginActivity.this, "Username or Password is Incorrect", Toast.LENGTH_LONG).show();
        }

        }
    }
}
