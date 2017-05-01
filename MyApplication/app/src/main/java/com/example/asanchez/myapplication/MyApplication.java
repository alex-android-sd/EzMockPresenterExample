package com.example.asanchez.myapplication;

import android.app.Application;
import android.util.Log;

/**
 * Created by asanchez on 4/20/17.
 */

public class MyApplication extends Application {

    PresenterProvider mProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        mProvider = new PresenterProviderImpl(new AccountDal() {
            @Override
            public boolean isValidLoginId(String userId) {
                Log.d("MVP","Returning true");
                return true;
            }
        });
    }

    public void setPresenterProvider(PresenterProvider provider) throws Exception {
        if (BuildConfig.DEBUG) {
            Log.d("MVP","new provider set");
            mProvider = provider;
        }
        else
        {
            throw new Exception("probably not a good idea");
        }

    }

    public PresenterProvider getPresenterProvider()
    {
        return mProvider;
    }

}

