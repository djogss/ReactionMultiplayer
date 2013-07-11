package com.aity.game.myreactionmultiplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aity.game.myreactionmultiplayer.R;

public class FinishGame extends Activity {


    @Override protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.game_finished_activity);

        Button settingsButton = (Button) findViewById(R.id.settingsButton);
        Button quitButton     = (Button) findViewById(R.id.quiteGameButton);
        Button restartButton  = (Button) findViewById(R.id.restartGameButton);

        TextView bestScoreTextView = (TextView) findViewById(R.id.bestScoreTextView);
        bestScoreTextView.setText(getIntent().getExtras().getString("bestScore"));
        restartButton.setOnClickListener(onRestartButtonClickListener);
    }

    private View.OnClickListener onRestartButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(FinishGame.this, ReactionGame.class);
            startActivity(intent);
            finish();
        }

    };
}
