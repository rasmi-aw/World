package com.beastwall.world.fetcher;

import android.os.Handler;
import android.os.Looper;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * A task template to excecute an http call
 */
public abstract class BackgroundTask<Input, Output> {

    public abstract Output doInBackground(Input input);

    public abstract void doInMainThread(Output output);

    public abstract void progress(long totalBytes, long numberOfReadBytes, int percentage);

    private void doInMain(Output output) {
        new Handler(Looper.getMainLooper()).post(() -> doInMainThread(output));
    }

    public final void updateProgress(long totalBytes, long numberOfReadBytes, int percentage) {
        new Handler(Looper.getMainLooper()).post(() -> progress(totalBytes, numberOfReadBytes, percentage));
    }

    public final void start(Input input) {
        //
        new Thread(() -> {
            Output result = doInBackground(input);
            doInMain(result);
        }).start();
    }


    /**
     * A call back to get your results
     */
    public interface ResultCallback<Result> {

        void result(Result result);
    }
}
