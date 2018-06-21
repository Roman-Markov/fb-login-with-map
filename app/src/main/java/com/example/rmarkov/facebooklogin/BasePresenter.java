package com.example.rmarkov.facebooklogin;

public abstract class BasePresenter<V> {

    protected V v;
    V getView() {
        return v;
    }

    public void attachView(V view) {
        this.v = view;
    }

    public void detachView() {
        v = null;
    }
}
