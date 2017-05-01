package com.example.asanchez.myapplication;

/**
 * Created by asanchez on 4/20/17.
 */

public class MainPresenterImpl implements MainPresenter {

    MainView mView;
    AccountDal mDal;

    public MainPresenterImpl(AccountDal dal)
    {
        mDal = dal;
    }

    @Override
    public void onAttach(MainView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    @Override
    public void onClickLogin(String userId) {
        if(mDal.isValidLoginId(userId)) {
            mView.showGreen();
        }
        else {
            mView.showRed();
        }
    }

}
