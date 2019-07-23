package br.com.monstersoftware.mesalivre.ui.register

import br.com.monstersoftware.mesalivre.data.persistence.AppDatabase
import br.com.monstersoftware.mesalivre.data.persistence.dao.UserDao
import br.com.monstersoftware.mesalivre.data.persistence.entity.User
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RegisterPresenter(registerView: RegisterActivity) : RegisterContract.Presenter, CoroutineScope {

    private var view: RegisterActivity = registerView
    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao
    private val job = Job()

    init {
        view.initViews()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override suspend fun register(user: User) {
        try {
            db = AppDatabase.getDatabase(view)
            userDao = db.userDao()
            coroutineScope {
                launch {
                    userDao.insert(user)
                    withContext(Dispatchers.Main) {
                        view.registerOk()
                    }
                }
            }
        } catch (e: Throwable) {
            withContext(Dispatchers.Main) {
                view.registerError(e.message)
            }
        } finally {
            db.close()
        }
    }
}