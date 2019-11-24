package com.example.praktikumprognet17;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyWorker extends Worker {
    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "Performing long running task in scheduled job");
        // TODO(developer): add long running task here.
        return Result.SUCCESS;
    }
}
