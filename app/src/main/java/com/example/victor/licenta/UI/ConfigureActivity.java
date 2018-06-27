package com.example.victor.licenta.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.victor.licenta.R;
import com.example.victor.licenta.backend.BackendManager;
import com.example.victor.licenta.backend.InformerManager;
import com.example.victor.licenta.backend.ThreatManager;
import com.example.victor.licenta.data.Configurations;
import com.example.victor.licenta.data.DataManager;
import com.example.victor.licenta.modelClass.AudioSensor;
import com.example.victor.licenta.modelClass.CameraSensor;
import com.example.victor.licenta.modelClass.EmailInformer;
import com.example.victor.licenta.modelClass.INformer;
import com.example.victor.licenta.modelClass.InformerFactory;
import com.example.victor.licenta.modelClass.TextMessageInformer;
import com.example.victor.licenta.util.ToastMaker;

public class ConfigureActivity extends AppCompatActivity {

    private Button cancelButton;
    private Button saveButton;
    private CheckBox notifOnStartcheck;
    private EditText emailEditor;
    private EditText numberEditor;
    private EditText audioDecibelsEdit;
    private Configurations myConfig;
    private CheckBox activateAudioCheckBox;
    private CheckBox activateCameraCheckBox;
    private CheckBox useFrontCameraCheckBkox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myConfig = DataManager.getInstance().getAppConfig();
        loadUI();
        setUI();
      if(  getIntent().getBooleanExtra("Start",false)){
          cancelButton.setVisibility(View.INVISIBLE);
      }
    }

    private void loadUI() {
        cancelButton = (Button) findViewById(R.id.cancelButtonConfig);
        saveButton = (Button) findViewById(R.id.saveButtonConfig);
        notifOnStartcheck = (CheckBox) findViewById(R.id.notifyOnStartCheck);
        emailEditor = (EditText) findViewById(R.id.emailConfigEditor);
        numberEditor = (EditText) findViewById(R.id.phoneNumberConfig);
        audioDecibelsEdit = (EditText) findViewById(R.id.audioDecibelsEdit);
        activateAudioCheckBox = (CheckBox) findViewById(R.id.activateAudioCheckBox);
        activateCameraCheckBox = (CheckBox) findViewById(R.id.activateCameraCheckBox);
        useFrontCameraCheckBkox = (CheckBox) findViewById(R.id.useFrontCameraCheckBkox);
    }

    private void setUI() {
        loadPreviousConfig();
        addListeners();
    }

    private void loadPreviousConfig() {
        notifOnStartcheck.setChecked(myConfig.getNotifOnStart());
        emailEditor.setText(myConfig.getEmail());
        numberEditor.setText(myConfig.getPhoneNumber());
        audioDecibelsEdit.setText(myConfig.getAudioThresh() + "");
        activateAudioCheckBox.setChecked(myConfig.isUseAudio());
        activateCameraCheckBox.setChecked(myConfig.isUseCamera());
        useFrontCameraCheckBkox.setChecked(myConfig.isUseFrontCamera());
    }

    private void addListeners() {
        cancelButton.setOnClickListener((e) -> {
            finish();
        });
        saveButton.setOnClickListener(e -> {
            if (saveConfig())
                finish();
        });
    }

    private boolean isEmail(String mail) {
        boolean ok = false;
        if (mail.contains("@") && mail.contains(".")) {
            ok = true;
        }
        if(mail.isEmpty())
            ok = true;
        return ok;
    }

    private boolean isPhoneNumber(String number) {
        boolean ok = false;

        if (number.startsWith("0") || number.isEmpty()) {
            ok = true;
        }

        return ok;
    }

    private boolean saveConfig() {
        boolean saved = true;
        final InformerManager informer = InformerManager.getInstance();
        informer.clear();
        final ThreatManager threatManager = ThreatManager.getInstance();
        threatManager.clear();
        myConfig.setNotifOnStart(notifOnStartcheck.isChecked());


        myConfig.setUseCamera(activateCameraCheckBox.isChecked());
        if(activateCameraCheckBox.isChecked()){
            threatManager.addSensor(new CameraSensor());
        }
        myConfig.setUseAudio(activateAudioCheckBox.isChecked());
        if(activateAudioCheckBox.isChecked()){
            threatManager.addSensor(new AudioSensor());
        }

        myConfig.setUseFrontCamera(useFrontCameraCheckBkox.isChecked());

        final String email = emailEditor.getText().toString();

        if (isEmail(email)) {
            myConfig.setEmail(email);

            final INformer emailInformer = InformerFactory.getInformer(email);
            if (emailInformer!=null && !informer.containsInformer(emailInformer) && !email.isEmpty()) {
                informer.addInformer(emailInformer);
                emailInformer.inform("This email has been set to receive threat notifications from home security App");
            }
        } else {
            saved = false;
            ToastMaker.show("Your email has not a valid form");

        }

        final String number = numberEditor.getText().toString();
        if (isPhoneNumber(number)) {
            myConfig.setPhoneNumber(number);
            final INformer textMessageInformer = InformerFactory.getInformer(number);
            if ( textMessageInformer != null && !informer.containsInformer(textMessageInformer) && !number.isEmpty()) {
                informer.addInformer(textMessageInformer);
                textMessageInformer.inform("This phone number has been set to receive threat notifications from home security App");
            }
        } else {
            saved = false;
            ToastMaker.show("Your number has not a valid form");
        }

        final String audioNumber = audioDecibelsEdit.getText().toString();
        int value = Integer.parseInt(audioNumber);
        if (value > 100) {
            value = 100;
        }
        myConfig.setAudioThresh(value);


        DataManager.getInstance().saveToDevice();
        return saved;
    }
}
