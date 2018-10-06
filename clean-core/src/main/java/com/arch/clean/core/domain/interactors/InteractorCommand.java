package com.arch.clean.core.domain.interactors;

import android.support.annotation.AnyThread;

public interface InteractorCommand {

	@AnyThread
	void executeCommand();

	//	@UiThread
	//	void onCommandExecuted();
}