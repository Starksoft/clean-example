package com.arch.clean.core.presentation.ui;

import android.support.annotation.Nullable;

public interface BaseView {

	void showProgress();

	void hideProgress();

	void showError(@Nullable String message);

	void manageProgress(boolean show);
}
