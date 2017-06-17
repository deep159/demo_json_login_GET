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

public class LogIn_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText mEmail,mPassword;
    Button mLoginButton;
    String EMAIL,PASSWORD,STATUS;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_);

        mEmail= (EditText) findViewById(R.id.login_email_editext);
        mPassword= (EditText) findViewById(R.id.login_password_editext);
        mLoginButton= (Button) findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        EMAIL=mEmail.getText().toString().trim();
        PASSWORD=mPassword.getText().toString().trim();

        new Login().execute();
    }

    class Login extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(LogIn_Activity.this);
            dialog.setMessage("please wait...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String url="http://sachtechsolution.com/cricket/webservice/registeration.php?block=login&email="+EMAIL+"&password="+PASSWORD;
            String response=MyDumClass.readURL(url);

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
                Toast.makeText(LogIn_Activity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LogIn_Activity.this,HomeActivity.class));
            }
            else
            {
                Toast.makeText(LogIn_Activity.this,STATUS, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
