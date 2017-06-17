package com.sts.jsondemosend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Sunil on 22/3/2017.
 */

public class MyDumClass {

    public static String readURL(String hit_url)
    {
        StringBuilder builder=new StringBuilder();
        try {
            URL url=new URL(hit_url);
            URLConnection conn=url.openConnection();
            InputStreamReader reader=new InputStreamReader(conn.getInputStream());
            BufferedReader buffer=new BufferedReader(reader);
            String Line=new String();
            while ((Line=buffer.readLine())!=null)
            {
                builder.append(Line+"\n");
            }
            buffer.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
