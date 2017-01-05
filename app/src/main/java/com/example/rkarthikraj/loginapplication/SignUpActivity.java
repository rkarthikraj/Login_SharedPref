package com.example.rkarthikraj.loginapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class SignUpActivity extends AppCompatActivity {
    EditText name, uname, pwd, email;
    String Name, Uname, Pwd, Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.name);
        uname = (EditText) findViewById(R.id.uname);
        pwd = (EditText) findViewById(R.id.pwd);
        email = (EditText) findViewById(R.id.email);
    }
    public void onClickLogin(View v){
        /*name.setText("");
        uname.setText("");
        pwd.setText("");
        email.setText("");*/

        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
    }
    public void onClickSignUp(View v){
        Name = name.getText().toString();
        Uname = uname.getText().toString();
        Pwd = pwd.getText().toString();
        Email = email.getText().toString();
        AsyncTaskDB runnerINS = new AsyncTaskDB();
        runnerINS.execute("", Name, Uname, Pwd, Email, "signup");
    }

    //ASYNC TASK

    class AsyncTaskDB extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String ID = params[0];
            String NAME = params[1];
            String UsNAME = params[2];
            String PWD = params[3];
            String EMAIL = params[4];
            String TYPE = params[5];
            int tmp;

            try {
                String data="";
                URL url = new URL("http://192.168.1.213/login.php/?id=" + ID + "&name=" + NAME + "&uname=" + UsNAME + "&pwd=" + PWD + "&email=" + EMAIL + "&type=" + TYPE);
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
            Toast.makeText(SignUpActivity.this, "Registered", Toast.LENGTH_LONG).show();
        }
    }

}
