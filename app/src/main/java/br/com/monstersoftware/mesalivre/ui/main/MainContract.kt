package br.com.monstersoftware.mesalivre.ui.main

interface MainContract {
    interface View{
        fun initViews()
    }

    interface Presenter{
        fun doLogin()
    }
}