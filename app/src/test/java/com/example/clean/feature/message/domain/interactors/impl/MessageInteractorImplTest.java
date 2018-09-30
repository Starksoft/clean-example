package com.example.clean.feature.message.domain.interactors.impl;

import android.support.annotation.NonNull;

import com.example.clean.core.domain.executor.Executor;
import com.example.clean.core.domain.interactors.AbstractInteractor;
import com.example.clean.core.domain.interactors.InteractorCommand;
import com.example.clean.core.threading.MainThread;
import com.example.clean.feature.message.data.repositories.MessageRepositoryImpl;
import com.example.clean.feature.message.domain.interactors.MessageInteractor;
import com.example.clean.feature.message.domain.interactors.MessageInteractorImpl;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class MessageInteractorImplTest {

	private static final String TAG = "MessageInteractorImplTe";

	private final FakeThreadExecutor fakeThreadExecutor = new FakeThreadExecutor();
	private final FakeMainThread fakeMainThread = new FakeMainThread();

	@Test
	public void testMessage() {

		MessageInteractor.Callback callback = mock(MessageInteractor.Callback.class);
		MessageRepositoryImpl messageRepository = mock(MessageRepositoryImpl.class);

		MessageInteractorImpl messageInteractor =
				new MessageInteractorImpl(fakeThreadExecutor, fakeMainThread, callback, messageRepository);

		String mockedMessage = "Mocked message";

		when(messageRepository.getMessage()).thenReturn(mockedMessage);

		messageInteractor.executeLoadMessage();
		verify(messageRepository).getMessage();
		verifyNoMoreInteractions(messageRepository);
		verify(callback).onMessageRetrieved(true, mockedMessage);
	}

	static class FakeThreadExecutor implements Executor {

		@Override
		public void execute(@NonNull AbstractInteractor interactor, @NonNull InteractorCommand command) {
			command.executeCommand();
			interactor.onFinished();
		}
	}

	static class FakeMainThread implements MainThread {

		@Override
		public void post(@NonNull Runnable runnable) {
			runnable.run();
		}
	}
}