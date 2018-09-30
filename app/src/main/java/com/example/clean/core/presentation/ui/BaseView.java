package com.example.clean.core.presentation.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpView;

/**
 * <p>
 * This interface represents a basic view. All views should implement these common methods.
 * </p>
 */
public interface BaseView extends MvpView {

	/**
	 * This is a general method used for showing some kind of progress during a background task. For example, this
	 * method should show a progress bar and/or disable buttons before some background work starts.
	 */
	void showProgress();

	/**
	 * This is a general method used for hiding progress information after a background task finishes.
	 */
	void hideProgress();

	/**
	 * This method is used for showing error messages on the UI.
	 *
	 * @param message The error message to be displayed.
	 */
	void showError(@Nullable String message);
}
