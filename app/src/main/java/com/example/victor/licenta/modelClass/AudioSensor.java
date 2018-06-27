package com.example.victor.licenta.modelClass;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.victor.licenta.data.DataManager;
import com.example.victor.licenta.util.events.ThreatFoundEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;

/**
 * Created by Victor on 3/3/2018.
 */

public class AudioSensor implements ISensor {

    private static final double REFERENCE = 0.1;
    private MediaRecorder mediaRecorder;

    private AudioThread audioThread;

    public AudioSensor() {
        mediaRecorder = new MediaRecorder();
    }

    @NonNull
    private synchronized File getFile() {
        File path = Environment.getExternalStorageDirectory();
        File f = new File(path.getAbsolutePath() + "/Download/audiorecordtest.3gp");

        if (f.exists()) {
            f.delete();
        }

        try {
            f.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Audio", "Unable to create new audio file");
        }
        return f;
    }

    @Override
    public void startWorking() {
        audioThread = new AudioThread();
        audioThread.start();
    }

    @Override
    public void stopWorking() {
        audioThread.interrupt();
    }

    @Override
    public String getName() {
        return null;
    }

    private double getDecibels(double amplitude) {

        return amplitude > 0 ? (20 * Math.log10(amplitude / REFERENCE)) : 0;
    }

    private synchronized void setRecorder(File f) {
        mediaRecorder.reset();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(f.getAbsolutePath());
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    }

    private class AudioThread extends Thread {
        public AudioThread() {
            super();
        }

        @Override
        public void run() {
            try {
                for (int j = 0; true; j++) {
                    File f = getFile();
                    setRecorder(f);
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                    for (int i = 0; i < 10; i++) {
                        final double decibels = getDecibels(mediaRecorder.getMaxAmplitude());
                        Log.d("Audio", i + " " + decibels + "");


                        if (decibels > (double) DataManager.getInstance().getAppConfig().getAudioThresh()) {
                            for (int k = 0; k < 4; k++) {
                                EventBus.getDefault().post(new ThreatFoundEvent("Audio sensor found threat"));
                            }
                            try {
                                this.interrupt();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                        Thread.sleep(200);
                    }
                    mediaRecorder.stop();
                    f.delete();
                    Log.d("Audio", "Stop succesful");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Audio", "unable to prepare");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}
