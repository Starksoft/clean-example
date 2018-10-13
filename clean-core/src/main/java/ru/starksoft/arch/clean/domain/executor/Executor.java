package ru.starksoft.arch.clean.domain.executor;

import android.support.annotation.NonNull;

import ru.starksoft.arch.clean.domain.interactors.AbstractInteractor;
import ru.starksoft.arch.clean.domain.interactors.InteractorCommand;

public interface Executor {

	void execute(@NonNull AbstractInteractor interactor, @NonNull InteractorCommand command);
	
	void executeOnMainThread(@NonNull Runnable runnable);
}