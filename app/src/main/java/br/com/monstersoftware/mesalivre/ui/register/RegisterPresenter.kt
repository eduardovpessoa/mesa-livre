package br.com.monstersoftware.mesalivre.ui.register

import br.com.monstersoftware.mesalivre.data.local.AppDatabase
import br.com.monstersoftware.mesalivre.data.local.dao.UserDao
import br.com.monstersoftware.mesalivre.data.local.entity.User
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class RegisterPresenter(registerView: RegisterActivity) : RegisterContract.Presenter, CoroutineScope {

    private val view: RegisterActivity = registerView
    private val db: AppDatabase
    private val userDao: UserDao
    private val job = Job()

    init {
        view.initViews()
        db = AppDatabase.getDatabase(view)
        userDao = db.userDao()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override suspend fun register(user: User) = coroutineScope {
        try {
            launch {
                userDao.insert(user)
                withContext(Dispatchers.Main) {
                    view.registerOk()
                }
            }
        } catch (e: Throwable) {
            withContext(Dispatchers.Main) {
                view.registerError(e.message)
            }
        }
    }

    override suspend fun sendMail() {
        //TODO Call API to SendMail
    }

}