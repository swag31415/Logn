package Logn;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;

public class Processor extends AnimationTimer {

    private int loopIntervalMillis;

    List<Runnable> runnables;

    public Processor(int loopIntervalMillis) {
        this.loopIntervalMillis = loopIntervalMillis;

        runnables = new ArrayList<Runnable>();
    }

    public void addRunnable(Runnable runnable) {
        runnables.add(runnable);
    }

    @Override
    public void handle(long now) {
        for (Runnable runnable : runnables) {
            runnable.run();
        }

        try {
            Thread.sleep(loopIntervalMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}