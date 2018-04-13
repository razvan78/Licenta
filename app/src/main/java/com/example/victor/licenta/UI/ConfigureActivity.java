package com.example.victor.licenta.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.victor.licenta.R;
import com.example.victor.licenta.backend.InformerManager;
import com.example.victor.licenta.data.Configurations;
import com.example.victor.licenta.data.DataManager;
import com.example.victor.licenta.errorsAndExceptions.UnableToAddInformerException;
import com.example.victor.licenta.modelClass.EmailInformer;
import com.example.victor.licenta.modelClass.TextMessageInformer;

public class ConfigureActivity extends AppCompatActivity {

    private Button cancelButton;
    private Button saveButton;
    private CheckBox notifOnStartcheck;
   private EditText emailEditor;
   private EditText numberEditor;
    private Configurations myConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myConfig = DataManager.getInstance().getAppConfig();
        loadUI();
        setUI();
    }

    private void loadUI() {
        cancelButton = (Button) findViewById(R.id.cancelButtonConfig);
        saveButton = (Button) findViewById(R.id.saveButtonConfig);
        notifOnStartcheck = (CheckBox) findViewById(R.id.notifyOnStartCheck);
        emailEditor = (EditText) findViewById(R.id.emailConfigEditor);
        numberEditor = (EditText) findViewById(R.id.phoneNumberConfig);
    }

    private void setUI() {
        loadPreviousConfig();
        addListeners();
    }

    private void loadPreviousConfig() {
        notifOnStartcheck.setChecked(myConfig.getNotifOnStart());
        emailEditor.setText(myConfig.getEmail());
        numberEditor.setText(myConfig.getPhoneNumber());
        //TODO:Load previous config
    }

    private void addListeners() {
        cancelButton.setOnClickListener((e)->{
            finish();
        });
        saveButton.setOnClickListener(e->{
            saveConfig();
            finish();
        });
    }

    private void saveConfig(){
        myConfig.setNotifOnStart(notifOnStartcheck.isChecked());
        //TODO: validate those 2
        final String email = emailEditor.getText().toString();
        myConfig.setEmail(email);
        InformerManager informer = InformerManager.getInstance();
        if(!informer.containsInformer(new EmailInformer(email))){
            try {
                informer.addInformer(new EmailInformer(email));
            } catch (UnableToAddInformerException e) {
                e.printStackTrace();
            }
        }

        String number = numberEditor.getText().toString();
        myConfig.setPhoneNumber(number);
        if(!informer.containsInformer(new TextMessageInformer(number))){
            try {
                informer.addInformer(new TextMessageInformer(number));
            } catch (UnableToAddInformerException e) {
                e.printStackTrace();
            }
        }
        //TODO: save currentConfig;

        DataManager.getInstance().saveToDevice();

    }
}
