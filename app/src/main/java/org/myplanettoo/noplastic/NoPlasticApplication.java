package org.myplanettoo.noplastic;

import android.app.Application;

import org.androidannotations.annotations.EApplication;
import org.myplanettoo.noplastic.service.NoPlasticService_;

@EApplication
public class NoPlasticApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        NoPlasticService_.intent(this).start();
    }
}
