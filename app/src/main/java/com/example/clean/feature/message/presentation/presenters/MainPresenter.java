package com.example.clean.feature.message.presentation.presenters;

import com.arch.clean.core.presentation.presenters.BasePresenter;

public interface MainPresenter extends BasePresenter<MainPresenterView> {

	void readMessage();

	void saveMessage(String message);
}