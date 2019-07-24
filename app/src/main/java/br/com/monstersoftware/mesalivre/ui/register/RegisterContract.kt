package br.com.monstersoftware.mesalivre.ui.register

interface RegisterContract {
    interface View {
        fun initViews()
        fun registerOk()
        fun registerError(msg: String?)
    }

    interface Presenter {
        fun changeUserType(id: Int)
        fun register()
        suspend fun sendMail()
    }
}