package com.goldspark.unityplugin;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;

import com.unity3d.player.UnityPlayer;

import java.util.Locale;

public class DetectionPlugin {
    private static final int REQUEST_CODE = 100; //Code to invoke onActivityResult
    private static boolean languageNotSet = true;
    private static String glanguage = "en-US"; //Sets the default language to en-US(can be changed by calling function setLanguage in unity
    private static int gMaxResults = 10;



    public static void setReturnObject(String objectname) {//We set unity's main camera to this(as a listener)
        ActivityClass.objectname = objectname;
    }


    public static void setLanguage(String language) {
        languageNotSet = false;
        glanguage = language;
    }

    public static void setMaxResults(int maxResults) { //set maximum number of words to show up
        gMaxResults = maxResults;
        ActivityClass.maxResults = maxResults;
    }

    public static void detectSpeech() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); //Initiate speech detector
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        if(languageNotSet) {
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()); //If language is not set it will put device's default language
        }
        else {
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, glanguage); //If language is set it will put it to the language you've specified in unity code
        }

        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, gMaxResults); //It puts number of words it can detect that you've specified in unity's code
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");

        try {
            UnityPlayer.currentActivity.startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException a) {
            //TODO: Set error message
        }
    }
}
