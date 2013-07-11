package com.aity.game.myreactionmultiplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.aity.game.myreactionmultiplayer.gamemode.GameMode;
import com.aity.game.myreactionmultiplayer.gamemode.SimpleFragment;
import com.aity.game.myreactionmultiplayer.util.Processor;
import com.aity.game.myreactionmultiplayer.R;
import com.aity.game.myreactionmultiplayer.model.ReactionTimes;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ReactionGame extends Activity {

    private final int ATTEMPTS      = 3;

    private ReactionTimes reactionTimes;
    private GameMode gameMode;

    private volatile ImageView imageView;
    private ListView listView;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future future;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_action_activity);

        listView = (ListView) findViewById(R.id.rectionTimesListView);

        reactionTimes = new ReactionTimes(ATTEMPTS);
        gameMode = new SimpleFragment();

        Processor p = new Processor(ReactionGame.this);
        future = executor.submit(p);
    }



    /**
     * Makes object invisible and notifies running thread that he can show other object at any time.
     */
    public void hideObject() {
        Log.d("CustomLog", "Hide image method");
        int attempts = -1;

        gameMode.hideObject();
        synchronized (reactionTimes){
            attempts = reactionTimes.getAttempts();
            reactionTimes.notify();
        }

        if(attempts == 0)
        {
            try {
                Log.d("CustomLog", " Game finished!" + future.get().toString());
                executor.shutdownNow();

                Intent intent = new Intent(ReactionGame.this, FinishGame.class);
                intent.putExtra("bestScore", reactionTimes.getBestTime());
                startActivity(intent);
                finish();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        else
            Log.d("CustomLog", "Still playing!");
    }

    /**
     * Displays object in random position
     */
    public void showObject(){
        Log.d("CustomLog", "showObject Method start");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameMode.showObject();
            }
        });
        Log.d("CustomLog", "showObject Method end");
    }

    public ReactionTimes getReactionTimes(){
        return reactionTimes;
    }
}
