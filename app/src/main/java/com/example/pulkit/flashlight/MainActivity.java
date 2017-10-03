package com.example.pulkit.flashlight;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button on;
    public static Camera cam = null;
    public static final String TAG = "PERMS";
    public static final int PERM_REQ_CODE = 111;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int perm = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if (perm == PackageManager.PERMISSION_DENIED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,  Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Give the damn permission", Toast.LENGTH_SHORT).show();
            }

            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA }, PERM_REQ_CODE);
        }

        on = (Button) findViewById(R.id.on);

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check==false) {
                    cam = Camera.open();
                    Camera.Parameters p = cam.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    cam.startPreview();
                    check=true;
                    on.setText("OFF");
                }
                else if (check==true){
                    cam.stopPreview();
                    cam.release();
                    cam = null;
                    on.setText("ON");
                    check=false;
                }
            }
        });

    }
}

