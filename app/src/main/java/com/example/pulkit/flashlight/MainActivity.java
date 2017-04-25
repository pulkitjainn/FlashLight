package com.example.pulkit.flashlight;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button on,off;
    public static Camera cam = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        on = (Button) findViewById(R.id.on);
        off = (Button) findViewById(R.id.off);

        off.setEnabled(false);


        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cam = Camera.open();
                Camera.Parameters p = cam.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(p);
                cam.startPreview();
                off.setEnabled(true);
                on.setEnabled(false);
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cam.stopPreview();
                cam.release();
                cam = null;
                off.setEnabled(false);
                on.setEnabled(true);
            }
        });

    }
}
