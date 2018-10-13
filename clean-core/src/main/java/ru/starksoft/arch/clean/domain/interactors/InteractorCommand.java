package ru.starksoft.arch.clean.domain.interactors;

import android.support.annotation.AnyThread;

public interface InteractorCommand {

	@AnyThread
	void executeCommand();
}