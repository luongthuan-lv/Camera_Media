package vn.edu.camera_media;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenCam(View view) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},888);

        }else {
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,999);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==999 && resultCode==RESULT_OK){
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            ImageView imageView=findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this,"Chụp ảnh không thành công",Toast.LENGTH_SHORT).show();
        }
    }

    public void OpenPlay(View view) {
        String url="https://data18.chiasenhac.com/downloads/2018/6/2017870-db9f83b0/320/Tung%20Yeu%20-%20Phan%20Duy%20Anh.mp3";
        Uri uri=Uri.parse(url);
//        MediaPlayer mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.kimi_no_na_wa);

        MediaPlayer mediaPlayer=MediaPlayer.create(MainActivity.this,uri);
//        mediaPlayer.start();

        if (mediaPlayer.isPlaying()){
            mediaPlayer.reset();
            mediaPlayer.stop();
        }else {
            try {
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
