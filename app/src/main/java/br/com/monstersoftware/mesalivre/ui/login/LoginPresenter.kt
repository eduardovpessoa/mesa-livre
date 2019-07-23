package br.com.monstersoftware.mesalivre.ui.login

import android.content.Intent
import br.com.monstersoftware.mesalivre.data.local.AppDatabase
import br.com.monstersoftware.mesalivre.data.local.dao.UserDao
import br.com.monstersoftware.mesalivre.data.local.entity.User
import br.com.monstersoftware.mesalivre.ui.main.MainActivity
import br.com.monstersoftware.mesalivre.ui.register.RegisterActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginPresenter(loginView: LoginActivity) : LoginContract.Presenter, CoroutineScope {

    private val view: LoginActivity = loginView
    private val db: AppDatabase
    private val userDao: UserDao
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = Dispatchers.IO + job

    init {
        view.initViews()
        db = AppDatabase.getDatabase(view)
        userDao = db.userDao()
    }

    override fun doLogin(user: String, passwd: String) {
        if (user.isBlank()) view.setUsernameError()
        if (passwd.isBlank()) view.setPasswordError()
        launch {
            val users: List<User> = userDao.findUser(user, passwd)
            if (users.isNullOrEmpty()) {
                withContext(Dispatchers.Main) {
                    view.showLoginError()
                }
            } else {
                withContext(Dispatchers.Main) {
                    view.startActivity(Intent(view, MainActivity::class.java))
                    view.finish()
                }
            }
        }
    }

    override fun openRegister() {
        launch {
            withContext(Dispatchers.Main) {
                view.startActivity(Intent(view, RegisterActivity::class.java))
            }
        }
    }

}