package com.example.user.ansprj;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CameraGalleryActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private static final int CAMERA_REQUEST = 0;
    private static final int SELECT_IMAGE = 1;

    Button btCamera, btGallery;
    ImageView imageView;
    EditText etImage;
    Bitmap bitmap;
    ListView imageList;
    ArrayList<Item> itemArrayList = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gallery);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            //if you dont have required permissions ask for it (only required for API 23+)
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        etImage = findViewById(R.id.etImage);

        imageView = findViewById(R.id.imageView2);
        SharedPreferences pref = getSharedPreferences("mypref",MODE_PRIVATE);
        String image = pref.getString("image",null);
        if(image != null){
            //Bitmap bmImg = BitmapFactory.decodeFile(image);
            imageView.setImageBitmap(BitmapFactory.decodeFile(image));
        }


        imageList = findViewById(R.id.imageList);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemArrayList);
        imageList.setAdapter(adapter);
        imageList.setOnItemClickListener(this);

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
            itemArrayList.add(new Item(etImage.getText().toString(), imagePath));
            adapter.notifyDataSetChanged();


        } else if (requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri targetUri = data.getData();
            try {
                bitmap  = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                String imagePath= saveImage(bitmap);

                imageView.setImageBitmap(bitmap);
                itemArrayList.add(new Item(etImage.getText().toString(), imagePath));
                adapter.notifyDataSetChanged();

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, PhotoActivity.class);
        i.putExtra("item",itemArrayList.get(position));
        startActivity(i);
    }
    @Override // android recommended class to handle permissions
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("", "granted");
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.uujm
                    Toast.makeText(CameraGalleryActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();

                    //app cannot function without this permission for now so close it...
                    onDestroy();
                }
                return;
            }

            // other 'case' line to check fosr other
            // permissions this app might request
        }
    }

}
