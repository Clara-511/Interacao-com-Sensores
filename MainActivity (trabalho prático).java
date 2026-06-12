package com.example.listadetarefas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        audioManager.registerAudioDeviceCallback(new AudioDeviceCallback() {

            @Override
            public void onAudioDevicesAdded(AudioDeviceInfo[] addedDevices) {
                super.onAudioDevicesAdded(addedDevices);
            }

            @Override
            public void onAudioDevicesRemoved(AudioDeviceInfo[] removedDevices) {
                super.onAudioDevicesRemoved(removedDevices);
            }

        }, null);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btn = findViewById(R.id.btnAdicionar);

        btn.setOnClickListener(v -> {

            boolean speaker = audioOutputAvailable(AudioDeviceInfo.TYPE_BUILTIN_SPEAKER);
            boolean bluetooth = audioOutputAvailable(AudioDeviceInfo.TYPE_BLUETOOTH_A2DP);

            Toast.makeText(
                    MainActivity.this,
                    "Speaker: " + speaker + " | Bluetooth: " + bluetooth,
                    Toast.LENGTH_LONG
            ).show();
        });
    }

    private boolean audioOutputAvailable(int type) {

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_AUDIO_OUTPUT)) {
            return false;
        }

        AudioDeviceInfo[] devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS);

        for (AudioDeviceInfo device : devices) {
            if (device.getType() == type) {
                return true;
            }
        }

        return false;
    }
}
