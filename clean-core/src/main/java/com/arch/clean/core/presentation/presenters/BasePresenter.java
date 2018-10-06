package com.arch.clean.core.presentation.presenters;

import android.support.annotation.NonNull;

import com.arch.clean.core.presentation.ui.BaseView;

public interface BasePresenter <V extends BaseView> {

	void attachView(@NonNull V view);

	void detachView(@NonNull V view);
}