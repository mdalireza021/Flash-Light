package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    LinearLayout linearLayout;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toggleButton=findViewById(R.id.id_toggle_button);
        linearLayout=findViewById(R.id.id_linear_layout);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    vibrate();
                    flashOn();
                    linearLayout.setBackgroundResource(R.drawable.fire_wood);
                }
                else
                {
                    vibrate();
                    flashOff();
                    linearLayout.setBackgroundResource(R.drawable.fire_wooden_off);
                }
            }
        });
    }

    public void flashOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String value = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(value, true);
        }

        catch (CameraAccessException e)

        { }
    }

    public void flashOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String value = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(value, false);
        }
        catch (CameraAccessException e)
        {
        }
    }
    public void vibrate()
    {
        vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(6000);
        vibrator.cancel();
    }
}