package com.example.letsseatinmetro;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by 이승헌 on 2018-02-28.
 */

public class Remote {
    private static final String TAG = "ResponseCode : ";
    public static Context context;

    public Remote(Context context){
        this.context = context;
    }
    // http 연결은 static 권장
    public static String getData (String webURL) throws Exception{
        StringBuilder result = new StringBuilder();
        String dataLine;
        URL url = new URL(webURL);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();

        // 200
        if(responseCode == HttpsURLConnection.HTTP_OK ){
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while( (dataLine = br.readLine()) != null){
                result.append(dataLine);
            }
            br.close();
        }else{
            Log.d(TAG, "error");
        }
        return result.toString();
    }
}