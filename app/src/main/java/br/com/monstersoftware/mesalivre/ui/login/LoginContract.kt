package br.com.monstersoftware.mesalivre.ui.login

interface LoginContract {

    interface View {
        fun initViews()
        fun setUsernameError()
        fun setPasswordError()
        fun showLoginError()
    }

    interface Presenter {
        fun doLogin(user: String, passwd: String)
        fun openRegister()
    }

}