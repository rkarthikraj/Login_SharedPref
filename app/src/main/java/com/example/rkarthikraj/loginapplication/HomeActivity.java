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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeActivity extends AppCompatActivity {
    EditText name;
    String Name;
    TextView usernameTV;

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = (EditText) findViewById(R.id.inputPlace);
        usernameTV = (TextView) findViewById(R.id.usernameTV);

        SharedPreferences sharedpref = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String name = sharedpref.getString("username", "");

        usernameTV.setText(name);
    }

    public void onClickLogout(View v)
    {

        SharedPreferences sharedpref=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedpref.edit();
        editor.putString("username",null);
        editor.apply();

        //Here we call in the login page
        //and that will decided whether to show registeration
        //but because we just cleared the sharedpref it show registeration only
        Intent loginact = new Intent( getBaseContext() , LoginActivity.class);
        startActivity(loginact);
        finish();
    }

    public void onClickInsert(View v){
        Name = name.getText().toString();
        AsyncTaskDB runnerINS = new AsyncTaskDB();
        runnerINS.execute("", Name, "place");
    }

    //ASYNC TASK

    class AsyncTaskDB extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String ID = params[0];
            String NAME = params[1];
            String TYPE = params[2];
            int tmp;

            try {
                String data="";
                URL url = new URL("http://192.168.1.213/login.php/?id=" + ID + "&name=" + NAME + "&type=" + TYPE);
                Log.d("rk",url.toString());
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
            Toast.makeText(HomeActivity.this, "Place Added", Toast.LENGTH_LONG).show();
        }
    }

}
