package com.example.clean.feature.message.presentation.presenters;

import android.support.annotation.Nullable;

import com.arch.clean.core.presentation.ui.BaseView;

public interface MainPresenterView extends BaseView {

	void displayMessage(@Nullable String message);
}