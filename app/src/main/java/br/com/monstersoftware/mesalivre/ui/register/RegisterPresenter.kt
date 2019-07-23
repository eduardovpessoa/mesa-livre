package br.com.monstersoftware.mesalivre.ui.register

import android.widget.EditText
import br.com.monstersoftware.mesalivre.R
import br.com.monstersoftware.mesalivre.data.local.AppDatabase
import br.com.monstersoftware.mesalivre.data.local.dao.UserDao
import br.com.monstersoftware.mesalivre.data.local.entity.User
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*
import kotlinx.android.synthetic.main.activity_register.view.edtCnpj
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

    override fun changeUserType(id: Int) {
        launch {
            withContext(Dispatchers.Main) {
                when (id) {
                    R.id.rbProfessional -> {
                        view.edtCnpj.visibility = EditText.INVISIBLE
                        view.edtRazaoSocial.visibility = EditText.INVISIBLE
                        view.edtName.visibility = EditText.VISIBLE
                        view.edtPhone.visibility = EditText.VISIBLE
                        view.edtCpf.visibility = EditText.VISIBLE
                        view.edtRg.visibility = EditText.VISIBLE
                    }
                    else -> {
                        view.edtCnpj.visibility = EditText.VISIBLE
                        view.edtRazaoSocial.visibility = EditText.VISIBLE
                        view.edtName.visibility = EditText.INVISIBLE
                        view.edtPhone.visibility = EditText.INVISIBLE
                        view.edtCpf.visibility = EditText.INVISIBLE
                        view.edtRg.visibility = EditText.INVISIBLE
                    }
                }
            }
        }
    }

    override fun register(user: User) {
        launch {
            try {
                userDao.insert(user)
                withContext(Dispatchers.Main) {
                    view.registerOk()
                }
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    view.registerError(e.message)
                }
            }
        }
    }

    override suspend fun sendMail() {
        //TODO Call API to SendMail
    }

}