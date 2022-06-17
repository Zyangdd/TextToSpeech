package com.example.texttospeech;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class HelloController {
    @FXML
    private Label welcomeText;

    private Synthesizer synthesizer;

    public HelloController() {
        init();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        playSound();
    }

    private void init() {
        try {
            // Set property as Kevin Dictionary
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
            // Register Engine

            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));

            // Allocate synthesizer
            synthesizer.allocate();

            // Resume Synthesizer
            synthesizer.resume();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void playSound() {
        // Speaks the given text
        // until the queue is empty.
        synthesizer.speakPlainText("Hello", null);
        try {
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Deallocate the Synthesizer.
//        synthesizer.deallocate();
    }
}