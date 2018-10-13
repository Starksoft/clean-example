package ru.starksoft.arch.clean.presentation;

import android.support.annotation.Nullable;

public interface BaseMvpView {

	void showError(@Nullable String message);
}