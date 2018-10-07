package com.example.clean.feature.message.presentation.presenters;

import android.support.annotation.Nullable;

import com.arch.clean.core.presentation.BaseMvpView;

public interface MainPresenterView extends BaseMvpView {

	void showProgress();

	void hideProgress();

	void manageProgress(boolean show);

	void displayMessage(@Nullable String message);
}