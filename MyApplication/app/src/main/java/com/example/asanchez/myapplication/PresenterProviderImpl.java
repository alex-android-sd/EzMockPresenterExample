package com.example.asanchez.myapplication;

/**
 * Created by asanchez on 4/20/17.
 */

public class PresenterProviderImpl implements  PresenterProvider {

    AccountDal mAccountDal;

    public PresenterProviderImpl(AccountDal dal)
    {
        mAccountDal = dal;
    }


    @Override
    public MainPresenter getMainPresenter() {
        return  new MainPresenterImpl(mAccountDal);
    }
}
