package com.example.clean.feature.message.presentation.presenters;

import ru.starksoft.arch.clean.presentation.presenters.BasePresenter;

public interface MainPresenter extends BasePresenter<MainPresenterView> {

	void readMessage();

	void saveMessage(String message);
}