package com.sts.jsondemosend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog dialog;
    EditText mFirstName,mLastname,mEmail,mPassword,mMobile;
    Button mRegister;
    String FIRSTNAME,LASTNAME,EMAIL,PASSWORD,MOBILE,STATUS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirstName= (EditText) findViewById(R.id.first_name_editext);
        mLastname= (EditText) findViewById(R.id.last_name_editext);
        mEmail= (EditText) findViewById(R.id.email_editext);
        mPassword= (EditText) findViewById(R.id.password_editext);
        mMobile= (EditText) findViewById(R.id.mobile_editext);

        mRegister= (Button) findViewById(R.id.register_button);
        
        mRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        FIRSTNAME=mFirstName.getText().toString().trim();
        LASTNAME=mLastname.getText().toString().trim();
        EMAIL=mEmail.getText().toString().trim();
        PASSWORD=mPassword.getText().toString().trim();
        MOBILE=mMobile.getText().toString().trim();

        new SendDate().execute();
    }

    class SendDate extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog=new ProgressDialog(MainActivity.this);
            dialog.setTitle("loading");
            dialog.setMessage("please wait....");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String URL="http://sachtechsolution.com/cricket/webservice/registeration.php?block=register&first_name="+FIRSTNAME+"&last_name="+LASTNAME+"&email="+EMAIL+"&password="+PASSWORD+"&mobile="+MOBILE;
            String response=MyDumClass.readURL(URL);

            try {
                JSONObject object=new JSONObject(response);
                STATUS=object.getString("status");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(dialog.isShowing())
            {
                dialog.dismiss();
            }

            if(STATUS.equals("done"))
            {
                Toast.makeText(MainActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,LogIn_Activity.class));
                finish();
            }
            else
            {
                Toast.makeText(MainActivity.this,STATUS, Toast.LENGTH_SHORT).show();
            }


        }
    }
}
