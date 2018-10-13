package ru.starksoft.arch.clean.presentation.presenters;

import android.support.annotation.NonNull;

import ru.starksoft.arch.clean.presentation.BaseMvpView;

public interface BasePresenter <V extends BaseMvpView> {

	void attachView(@NonNull V view);

	void detachView(@NonNull V view);
}