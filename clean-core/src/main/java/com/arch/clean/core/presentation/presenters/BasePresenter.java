package com.arch.clean.core.presentation.presenters;

import android.support.annotation.NonNull;

import com.arch.clean.core.presentation.BaseMvpView;

public interface BasePresenter <V extends BaseMvpView> {

	void attachView(@NonNull V view);

	void detachView(@NonNull V view);
}