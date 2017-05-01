package com.example.asanchez.myapplication;

/**
 * Created by asanchez on 4/20/17.
 */

public interface MainPresenter {
    void onAttach(MainView view);
    void onDetach();
    void onClickLogin(String userId);


    interface MainView
    {
        void showGreen();
        void showRed();
    }
}
