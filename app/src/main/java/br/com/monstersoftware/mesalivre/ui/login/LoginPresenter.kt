package br.com.monstersoftware.mesalivre.ui.login

class LoginPresenter(loginView : LoginActivity) : LoginContract.Presenter {

    private var view : LoginActivity = loginView

    init {
        view.initViews()
    }

}