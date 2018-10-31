package com.example.user.ansprj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CameraGalleryActivity extends AppCompatActivity {
    Bitmap bitmap;
    Button camera;

    private static final int SELECT_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gallery);
    }

    public void OnClick(View v)
    {
        if(v == camera)
        {
            //Intent i = new Intent(MediaStore);
        }
    }
}
