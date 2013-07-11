package com.aity.game.myreactionmultiplayer.util;

import android.util.Log;

import com.aity.game.myreactionmultiplayer.activity.ReactionGame;
import com.aity.game.myreactionmultiplayer.model.ReactionTimes;

import java.util.Random;
import java.util.concurrent.Callable;

public class Processor implements Callable {

    private final int MAX_WAITING_TIME = 5;
    private final ReactionTimes reactionTimes;
    private ReactionGame reactionGame;

    public Processor(ReactionGame m){
        reactionGame = m;
        reactionTimes = m.getReactionTimes();
    }

    @Override
    public Object call() throws Exception {

        int attempts;
        while((attempts = reactionTimes.getAttempts()) > 0) {
            Log.d("CustomLog", " Creating img " + attempts + " time");
            if(attempts != 0) {
                try {
                    Thread.sleep(getWaitingTime(MAX_WAITING_TIME) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (reactionTimes) {

                try {
                    reactionTimes.setAttempts(attempts);
                    reactionTimes.setStartTime();
                    reactionGame.showObject();
                    reactionTimes.wait();
                    reactionTimes.calculateReactionTime();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        reactionTimes.printRunningTimes();

        return 0;
    }

    /**
     * Returns random number size between 2 and maxWaitingTime
     * @param maxWaitingTime
     * @return
     */
    private int getWaitingTime(int maxWaitingTime){
       return getWaitingTime(2,maxWaitingTime);
    }

    /**
     * Returns random number size between minWaitingTime and maxWaitingTime
     * @param minWaitingTime
     * @param maxWaitingTime
     * @return
     */
    private int getWaitingTime(int minWaitingTime, int maxWaitingTime) {
        Random rnd = new Random();
        int waitingTime;
        while(true) {
            waitingTime = rnd.nextInt(maxWaitingTime);
            if(waitingTime > minWaitingTime && waitingTime < maxWaitingTime) {
                Log.d("CustomLog", "waiting time " + waitingTime);
                return waitingTime;
            }
        }
    }
}
