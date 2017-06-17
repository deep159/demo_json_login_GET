package com.sts.jsondemosend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ProgressDialog dialog;
    ArrayList<String> IMGES;
    ImageView mImageView1,mImageView2,mImageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        IMGES=new ArrayList<>();

        mImageView1= (ImageView) findViewById(R.id.actor_image1);
        mImageView2= (ImageView) findViewById(R.id.actor_image2);
        mImageView3= (ImageView) findViewById(R.id.actor_image3);

        new Login().execute();
    }

    class Login extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(HomeActivity.this);
            dialog.setMessage("please wait...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String url="http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";
            String response=MyDumClass.readURL(url);

            try {
                JSONObject object=new JSONObject(response);
                JSONArray array=object.getJSONArray("actors");

                for(int i=0;i<array.length();i++)
                {
                    JSONObject inner_object=array.getJSONObject(i);
                    String image=inner_object.getString("image");
                    IMGES.add(image);
                }

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

            Glide.with(HomeActivity.this).load(IMGES.get(0)).into(mImageView1);
            //Picasso.with(HomeActivity.this).load(IMGES.get(0)).into(mImageView1);
            Picasso.with(HomeActivity.this).load(IMGES.get(1)).into(mImageView2);
            Picasso.with(HomeActivity.this).load(IMGES.get(2)).into(mImageView3);

        }
    }
}
