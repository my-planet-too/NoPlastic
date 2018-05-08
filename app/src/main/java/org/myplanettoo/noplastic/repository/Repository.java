package org.myplanettoo.noplastic.repository;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;
import org.myplanettoo.noplastic.NoPlasticApplication;
import org.myplanettoo.noplastic.database.NoPlasticDatabase;

@EBean
public class Repository {
    @App
    protected NoPlasticApplication application;

    protected NoPlasticDatabase db;

    @AfterInject
    void initRepository() {
        db = NoPlasticDatabase.getInstance(application);
    }
}
