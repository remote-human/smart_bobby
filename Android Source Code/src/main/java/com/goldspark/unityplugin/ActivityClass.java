package com.goldspark.unityplugin;

import android.content.Intent;
import android.speech.RecognizerIntent;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import android.util.Log;

import java.util.Locale;

public class ActivityClass extends UnityPlayerActivity {
    private static final int REQEST_CODE = 100;
    public static String objectname = "Main Camera";
    public static String methodName = "onActivityResult";
    public static int maxResults = 10;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQEST_CODE: {
                if (resultCode == RESULT_OK && data != null) {

                    String words = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);

                    for (int i = 1; i< data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).size(); i++){
                        words = words + " " + data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(i);
                    }

                    UnityPlayer.UnitySendMessage(objectname, methodName, words);
                }
                break;
            }


        }
    }
}
