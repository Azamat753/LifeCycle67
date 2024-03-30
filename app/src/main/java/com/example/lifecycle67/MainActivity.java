package com.example.lifecycle67;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String TAG = "ololo";

    int SELECT_IMAGE = 12;

    ImageView avatarView;
    Button moveBtn, youtubeBtn, galleryBtn;
    EditText textEd;

    //hello world

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initViews();
        initClickers();

        String url = "https://cdn.britannica.com/30/150930-120-D3D93F1E/lion-Namibia.jpg";
        Glide.with(this).load(url).centerCrop().placeholder(R.drawable.ic_launcher_background).into(avatarView);
    }

    void functionFromMaster(){

    }

    private void initClickers() {
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
            }
        });

        moveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                String editValue = textEd.getText().toString();
                intent.putExtra("editValue", editValue);
                startActivity(intent);
            }
        });
        youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                String url = "https://www.youtube.com/watch?v=0Jb6NixZKaY&list=RD0Jb6NixZKaY&start_radio=1&ab_channel=Rai";
                try {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.google.android.youtube");
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            }
        });
    }

    void initViews() {
        moveBtn = findViewById(R.id.moveBtn);
        textEd = findViewById(R.id.textEd);
        youtubeBtn = findViewById(R.id.youTubeBtn);
        galleryBtn = findViewById(R.id.galleryBtn);
        avatarView = findViewById(R.id.avatarImage);
    }
//content://media/picker/0/com.android.providers.media.photopicker/media/1000000018

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        avatarView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}