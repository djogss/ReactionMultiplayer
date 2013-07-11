package com.aity.game.myreactionmultiplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.aity.game.myreactionmultiplayer.R;

public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(startButtonClickListener);
    }

    /**
     * Starting game:
     * Creating new thread that is responsible of displaying objects at random times.
     */
    private View.OnClickListener startButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Log.d("CustomLog", "start button clicked");

            Intent intent = new Intent(Main.this, ReactionGame.class);
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}


