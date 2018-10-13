package com.example.clean.feature.message.presentation.ui.fragments.test

import android.os.Bundle
import ru.starksoft.arch.clean.domain.executor.ThreadExecutor
import ru.starksoft.arch.clean.presentation.AbstractMvpFragment
import ru.starksoft.arch.clean.presentation.presenters.BasePresenter

class KotlinTestFragment : AbstractMvpFragment(), TestView {

	private val presenter = getPresenter<TestPresenter>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		presenter.action()
	}

	override fun createPresenter(): BasePresenter<*> {
		return TestPresenter(context!!, ThreadExecutor.getInstance())
	}

}