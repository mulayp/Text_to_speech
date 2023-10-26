package com.example.text_to_speech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import com.example.text_to_speech.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

  private ActivityMainBinding binding;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        textToSpeech = new TextToSpeech(this ,  this);

        binding.button.setOnClickListener(
                v->{
                    String text = binding.editText1.getText().toString();
                    textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH, null,null);
                }
        );


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            int result = textToSpeech.setLanguage(Locale.ENGLISH);
            if(result == textToSpeech.LANG_MISSING_DATA){
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}