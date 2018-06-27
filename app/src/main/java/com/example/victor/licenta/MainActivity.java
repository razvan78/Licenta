package com.example.victor.licenta;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.victor.licenta.UI.ConfigureActivity;
import com.example.victor.licenta.backend.BackendManager;
import com.example.victor.licenta.data.DataManager;
import com.example.victor.licenta.util.events.ApplicationStartedEvent;
import com.example.victor.licenta.util.StoppableTimer;
import com.example.victor.licenta.util.ToastMaker;

import org.greenrobot.eventbus.EventBus;

import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //TODO: refactorize asa incat la instalare sa iti ceara permisiuni
    private EditText timerPicker;
    private Button startButton;
    private Button stopButton;
    private Button configButton;
    private Spinner timeUnitSpinenr;


    private StoppableTimer timer;
    public static ToastMaker tost;
    public static Activity currentActivity;
    public static TextureView textureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentActivity = this;
        tost = new ToastMaker(currentActivity);
        DataManager.getInstance().loadFromDevice();
        loadUI();
        setUI();
        startConfigureActivity(true);
    }

    @Override
    protected void onDestroy() {
        BackendManager.getInstance().stop();
        currentActivity = null;
        super.onDestroy();

    }

    private void loadUI() {
        timerPicker = (EditText) findViewById(R.id.timerPicker);
        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        configButton = (Button) findViewById(R.id.configButton);
        timeUnitSpinenr = (Spinner) findViewById(R.id.timeUnitSpinner);
        textureView = (TextureView) findViewById(R.id.textureView);

    }

    private void setUI() {
        addListeners();
        timerPicker.setText("0");
        String[] items = new String[]{"sec", "min"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        timeUnitSpinenr.setAdapter(adapter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CAMERA_PERMISSION)
        {
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "You can't use camera without permission", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private void addListeners() {
        startButton.setOnClickListener((e) -> {


            if (!BackendManager.getInstance().isWorking()) {
                if (timer != null && timer.hasStartedTimer()) {
                    timer.cancel();
                }

                timer = new StoppableTimer();
                int duration = Integer.parseInt(timerPicker.getEditableText().toString());
                int waitTime = duration * 1000;
                if (timeUnitSpinenr.getSelectedItemPosition() == 1)//position for minutes
                {
                    waitTime *= 60;
                }

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        BackendManager.getInstance().start();
                        if(DataManager.getInstance().getAppConfig().getNotifOnStart())
                        { EventBus.getDefault().post(new ApplicationStartedEvent("Application has started"));}
                        runOnUiThread(() -> {

                            tost.show("Application has started");
                        });
                    }
                }, waitTime);
                if (duration != 0) {
                    tost.show("Application will start in " + duration + " " + timeUnitSpinenr.getSelectedItem());
                }
            } else {
                tost.show("Application has already started");
            }
        });

        stopButton.setOnClickListener((e) -> {

            if (!BackendManager.getInstance().isWorking() && timer == null) {
                tost.show("Application has not been started");
            }

            if (timer != null && timer.hasStartedTimer()) {
                timer.cancel();
                timer = null;
                tost.show("Application has been stopped");
            }
            if (BackendManager.getInstance().isWorking()) {
                BackendManager.getInstance().stop();
                tost.show("Application has been stopped");
            }


        });

        configButton.setOnClickListener((e) -> {
            startConfigureActivity(false);
        });
    }

    private void startConfigureActivity(boolean atStartup) {
        final Intent configIntent = new Intent(this, ConfigureActivity.class);
        configIntent.putExtra("Start",atStartup);
        startActivity(configIntent);
    }


}
