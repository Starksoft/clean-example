package com.arch.clean.core.presentation.presenters;

import android.support.annotation.NonNull;

public interface BasePresenter <V> {

	void attachView(@NonNull V view);

	void detachView(@NonNull V view);
}