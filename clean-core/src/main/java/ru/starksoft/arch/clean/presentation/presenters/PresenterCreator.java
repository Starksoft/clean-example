package ru.starksoft.arch.clean.presentation.presenters;

import android.support.annotation.NonNull;

public interface PresenterCreator {

	@NonNull
	BasePresenter createPresenter();

	@NonNull
	String getPresenterTag();
}