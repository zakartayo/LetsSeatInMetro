package com.example.letsseatinmetro.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.letsseatinmetro.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ShowImageActivity extends AppCompatActivity {

    private static final String requestAddress = "http://113.198.84.121/002&2301";
    private ImageView imageView;
    private Bitmap bitmap;
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        position = getIntent().getStringExtra("position");

        imageView = (ImageView)findViewById(R.id.imageView);
        PhotoViewAttacher photoView = new PhotoViewAttacher(imageView);
        photoView.update();

        Thread mThread = new Thread(){
            @Override
            public void run(){
                try {
                    URL url = new URL(requestAddress + position);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        };

        mThread.start();

        try{
            mThread.join();
            imageView.setImageBitmap(bitmap);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
