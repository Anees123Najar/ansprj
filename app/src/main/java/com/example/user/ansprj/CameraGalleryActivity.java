package com.example.user.ansprj;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraGalleryActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int CAMERA_REQUEST = 0;
    private static final int SELECT_IMAGE = 1;

    Button btCamera, btGallery;
    ImageView imageView;
    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gallery);


        imageView = findViewById(R.id.imageView2);
        SharedPreferences pref = getSharedPreferences("mypref",MODE_PRIVATE);
        String image = pref.getString("image",null);
        if(image != null){
            //Bitmap bmImg = BitmapFactory.decodeFile(image);
            imageView.setImageBitmap(BitmapFactory.decodeFile(image));
        }


        btGallery = findViewById(R.id.btGallery);
        btGallery.setOnClickListener(this);

        btCamera = findViewById(R.id.btImage);
        btCamera.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == btCamera) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);

        }else if(v == btGallery){
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, SELECT_IMAGE);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            String imagePath= saveImage(bitmap);
            imageView.setImageBitmap(bitmap);

            SharedPreferences pref = getSharedPreferences("mypref",MODE_PRIVATE);
            SharedPreferences.Editor editor= pref.edit();
            editor.putString("image",imagePath);
            editor.commit();

        } else if (requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri targetUri = data.getData();
            try {
                bitmap  = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public String saveImage(Bitmap bitmap) {
        File root = Environment.getExternalStorageDirectory();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = root.getAbsolutePath() + "/DCIM/Camera/IMG_" + timeStamp + ".jpg";
        //creating an object from type file

        File file = new File(filePath);
        //determinig the type of the file and its place

        try {
            file.createNewFile();
            FileOutputStream ostream= new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
            ostream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"Faild to save image", Toast.LENGTH_LONG).show();

        }
        return filePath;
    }
}
