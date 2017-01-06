package com.example.rkarthikraj.loginapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.List;

public class VisitorActivity extends AppCompatActivity {

    TextView outputPlace;
    List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);

        outputPlace=(TextView)findViewById(R.id.outputPlace);

        AsyncTaskDB runnerVisitor = new AsyncTaskDB();
        runnerVisitor.execute("selplace");

    }
    public void onClickLoginPage(View v){
        Intent logpage=new Intent(this,LoginActivity.class);
        startActivity(logpage);
        finish();
    }

    //ASYNC
    class AsyncTaskDB extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String TYPE = params[0];
            int tmp;

            try {
                String data="";
                URL url = new URL("http://192.168.1.213/login.php/?type=" + TYPE);
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
            Toast.makeText(VisitorActivity.this, "Viewing Places", Toast.LENGTH_LONG).show();
            String jsonplace;
            try {
                JSONArray jsonarray = new JSONArray(result);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonObject = jsonarray.getJSONObject(i);
                    jsonplace=jsonObject.getString("name").toString();
                    list.add(jsonplace);

                }
                outputPlace.setText(list.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
