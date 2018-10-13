package ru.starksoft.arch.clean;

import android.os.Bundle;
import android.support.annotation.NonNull;

import ru.starksoft.arch.clean.presentation.presenters.BasePresenter;
import ru.starksoft.arch.clean.presentation.presenters.PresenterCreator;
import ru.starksoft.arch.clean.presentation.BaseMvpView;

import java.util.HashMap;
import java.util.Map;

public final class PresenterStore {

	private static PresenterStore instance;
	private final Map<String, BasePresenter> presentersMap = new HashMap<>();

	private PresenterStore() {
		//no instance
	}

	@NonNull
	public static PresenterStore getInstance() {
		if (instance == null) {
			instance = new PresenterStore();
		}
		return instance;
	}

	public void onCreate(@NonNull PresenterCreator presenterCreator, Bundle savedInstanceState) {
		getPresenter(presenterCreator);
	}

	public void onAttach(@NonNull PresenterCreator presenterCreator, @NonNull BaseMvpView baseMvpView) {
		//noinspection unchecked
		getPresenter(presenterCreator).attachView(baseMvpView);
	}

	public void onSaveInstanceState(@NonNull String presenterTag, Bundle outState) {

	}

	public void onDetach(@NonNull PresenterCreator presenterCreator, @NonNull BaseMvpView baseMvpView) {
		//noinspection unchecked
		getPresenter(presenterCreator).detachView(baseMvpView);
	}

	public void onDestroyView(@NonNull PresenterCreator presenterCreator) {

	}

	public void onDestroy(@NonNull PresenterCreator presenterCreator) {

	}

	@NonNull
	public BasePresenter getPresenter(@NonNull PresenterCreator presenterCreator) {
		String presenterTag = presenterCreator.getPresenterTag();
		BasePresenter basePresenter = presentersMap.get(presenterTag);
		if (basePresenter == null) {
			presentersMap.put(presenterTag, basePresenter = presenterCreator.createPresenter());
		}

		return basePresenter;
	}
}