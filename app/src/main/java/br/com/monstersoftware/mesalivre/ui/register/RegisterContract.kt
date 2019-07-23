package br.com.monstersoftware.mesalivre.ui.register

import br.com.monstersoftware.mesalivre.data.local.entity.User

interface RegisterContract {
    interface View{
        fun initViews()
        fun registerOk()
        fun registerError(msg : String?)
    }

    interface Presenter{
        suspend fun register(user : User) : Any
        suspend fun sendMail()
    }
}