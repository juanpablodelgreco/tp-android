package com.example.tpsoa.presenters;

import android.content.Context;

public interface LoginPresenter {
    void validateCredentials(Context ct, String username, String password);
}
