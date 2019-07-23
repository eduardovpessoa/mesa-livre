package br.com.monstersoftware.mesalivre.ui.main

class MainPresenter(mainView : MainActivity) : MainContract.Presenter {

    private var view : MainActivity = mainView

    init {
        view.initViews()
    }

    override fun doLogin() {

    }

}