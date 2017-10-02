package com.example.cooperpellaton.pupperlicks;

import android.app.Application;
import android.content.Context;

/**
 * A class that simply gets the context of the application.
 * Needed for Firebase.
 */
public class pupperlicks extends Application {

    private static Application sApplication;

    /**
     * Get the current application.
     * @return the current application.
     */
    public static Application getApplication() {
        return sApplication;
    }

    /**
     * Get the current context.
     * @return the current context.
     */
    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    /**
     * Default values.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
