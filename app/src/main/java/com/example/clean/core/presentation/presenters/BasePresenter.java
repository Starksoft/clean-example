package com.example.clean.core.presentation.presenters;

import android.support.annotation.NonNull;

public interface BasePresenter <V> {

	void onError(@NonNull String message);
}