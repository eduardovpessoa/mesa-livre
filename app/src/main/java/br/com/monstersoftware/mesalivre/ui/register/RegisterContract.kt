package br.com.monstersoftware.mesalivre.ui.register

import br.com.monstersoftware.mesalivre.data.local.entity.User

interface RegisterContract {
    interface View {
        fun initViews()
        fun registerOk()
        fun registerError(msg: String?)
    }

    interface Presenter {
        fun changeUserType(id: Int)
        fun register(user: User)
        suspend fun sendMail()
    }
}