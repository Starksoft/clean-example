package com.example.clean.core.domain.interactors;

import android.support.annotation.AnyThread;
import android.support.annotation.UiThread;

public interface InteractorCommand {

	@AnyThread
	void executeCommand();

//	@UiThread
//	void onCommandExecuted();
}