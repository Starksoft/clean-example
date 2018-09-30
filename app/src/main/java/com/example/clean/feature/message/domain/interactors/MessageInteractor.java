package com.example.clean.feature.message.domain.interactors;

import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

public interface MessageInteractor {

	@AnyThread
	void executeLoadMessage();

	@AnyThread
	void executeSaveMessage(@NonNull String message);

	@UiThread
	interface Callback {

		@UiThread
		void onMessageSaved(boolean success, @Nullable String explanation);

		@UiThread
		void onMessageRetrieved(boolean success, @Nullable String message);
	}
}