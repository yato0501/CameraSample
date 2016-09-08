package tou.camerasample;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static final int CUSTOM_CAMERA_CODE = 3;
    static final int REQUEST_IMAGE_CAPTURE = 2;
    static final int BUILT_IN_CAMERA_CODE = 1;
    private Button btnBuiltInCamera;
    private Button btnCustomCamera;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBuiltInCamera = (Button) findViewById(R.id.btn_builtin_camera);
        btnCustomCamera = (Button) findViewById(R.id.btn_custom_camera);
        imgPhoto = (ImageView) findViewById(R.id.img_photo);

        btnBuiltInCamera.setOnClickListener(btnBuiltInCameraAction);
        btnCustomCamera.setOnClickListener(btnCustomCameraAction);

    }

    private View.OnClickListener btnBuiltInCameraAction = new  View.OnClickListener(){
        @Override
        @TargetApi(23)
        public void onClick(View v) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        BUILT_IN_CAMERA_CODE);
            }
            else{

                dispatchTakePictureIntent();
            }
        }

    };


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case BUILT_IN_CAMERA_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    dispatchTakePictureIntent();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case CUSTOM_CAMERA_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Intent myIntent = new Intent(MainActivity.this, CameraActivity.class);
                    MainActivity.this.startActivity(myIntent);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;

            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private View.OnClickListener btnCustomCameraAction = new  View.OnClickListener(){
        @Override
        @TargetApi(23)
        public void onClick(View v) {

            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        CUSTOM_CAMERA_CODE);
            }
            else{
                Intent myIntent = new Intent(MainActivity.this, CameraActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        }
    };

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgPhoto.setImageBitmap(imageBitmap);
        }
    }
}
