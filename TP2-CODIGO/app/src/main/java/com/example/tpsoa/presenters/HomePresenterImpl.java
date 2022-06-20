package com.example.tpsoa.presenters;
import android.content.Context;

import com.example.tpsoa.dtos.responses.PublicApiResponse;
import com.example.tpsoa.models.HomeInteractor;
import com.example.tpsoa.views.HomeView;

import java.util.List;

public class HomePresenterImpl implements HomePresenter, OnFinishListenerPublic {
    private HomeView homeView;
    private HomeInteractor homeInteractor;

    public HomePresenterImpl(HomeView homeView, HomeInteractor homeInteractor){
        this.homeView = homeView;
        this.homeInteractor = homeInteractor;
    }

    @Override
    public void getData(Context ctx) {
            homeInteractor.getData(this, ctx);
    }

    @Override
    public void onFinished(int code, List<PublicApiResponse> data) {
        homeView.showData(data);
    }

    @Override
    public void onFinished(int code, String result) {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void showToast(String message) {

    }
}
