package com.aity.game.myreactionmultiplayer.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ReactionTimes {

    private volatile int attempts;
    private long startTime;
    private List<String> runningTimes;

    public ReactionTimes(int a){
        this.attempts = a;
        runningTimes = new ArrayList<String>();
    }

    public synchronized void setStartTime() {
        startTime = System.currentTimeMillis();
        attempts--;
        Log.d("CustomLog", "Image showed, please press the button!");
    }

    public synchronized void calculateReactionTime() {
        long duration = (System.currentTimeMillis() - startTime);
        runningTimes.add(String.valueOf(duration));
        Log.d("CustomLog","Imaged was hidden " + duration);
    }

    public synchronized List<String> getRunningTimes() {

        ListIterator<String> it = runningTimes.listIterator(runningTimes.size());
        List<String> reverseList = new ArrayList<String>();
        while(it.hasPrevious())
            reverseList.add(it.previous());
        return reverseList;
    }
    public synchronized void printRunningTimes(){
        Iterator<String> it = runningTimes.iterator();
        while(it.hasNext()) {
            Log.d("CustomLog", "Running time : " + it.next());
        }
    }

    public synchronized void setAttempts(int attempt) {
        attempts = attempt;
    }

    public synchronized int getAttempts() {
        return attempts;
    }

    public String getBestTime() {
        Iterator<String> it = runningTimes.iterator();
        long bestTime = 10000000;
        long currentTime;
        while(it.hasNext()) {
            currentTime = Long.valueOf(it.next());
            if(currentTime < bestTime)
                bestTime = currentTime;
            Log.d("CustomLog", "Best Scores : " + currentTime);
        }
        Log.d("CustomLog", "Best Score : " + bestTime);
        return String.valueOf(bestTime);
    }
}
