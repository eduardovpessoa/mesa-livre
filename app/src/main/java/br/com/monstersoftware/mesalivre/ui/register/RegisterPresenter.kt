package br.com.monstersoftware.mesalivre.ui.register

import br.com.monstersoftware.mesalivre.R
import br.com.monstersoftware.mesalivre.data.local.AppDatabase
import br.com.monstersoftware.mesalivre.data.local.dao.UserDao
import br.com.monstersoftware.mesalivre.data.local.entity.User
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_register.*
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
                        view.tilCnpj.visibility = TextInputLayout.GONE
                        view.tilRazao.visibility = TextInputLayout.GONE
                        view.tilName.visibility = TextInputLayout.VISIBLE
                        view.tilPhone.visibility = TextInputLayout.VISIBLE
                        view.tilCpf.visibility = TextInputLayout.VISIBLE
                        view.tilRg.visibility = TextInputLayout.VISIBLE
                    }
                    else -> {
                        view.tilCnpj.visibility = TextInputLayout.VISIBLE
                        view.tilRazao.visibility = TextInputLayout.VISIBLE
                        view.tilName.visibility = TextInputLayout.GONE
                        view.tilPhone.visibility = TextInputLayout.GONE
                        view.tilCpf.visibility = TextInputLayout.GONE
                        view.tilRg.visibility = TextInputLayout.GONE
                    }
                }
            }
        }
    }

    override fun register() {
        launch {
            try {
                var newUser: User
                withContext(Dispatchers.Main) {
                    when (view.rgType.checkedRadioButtonId) {
                        R.id.rbProfessional -> {
                            newUser = User(
                                0,
                                view.edtUsername.text.toString(),
                                "", "",
                                view.edtPass.text.toString(),
                                view.edtMail.text.toString(),
                                view.edtName.text.toString(),
                                view.edtPhone.toString(),
                                view.edtCpf.toString(),
                                view.edtRg.toString(),
                                3
                            )
                        }
                        else -> {
                            newUser = User(
                                0,
                                view.edtUsername.text.toString(),
                                view.edtRazaoSocial.text.toString(),
                                view.edtCnpj.text.toString(),
                                view.edtPass.text.toString(),
                                view.edtMail.text.toString(),
                                "", "", "", "",
                                if (view.rgType.checkedRadioButtonId == R.id.rbAdministrator) 1 else 2
                            )
                        }
                    }
                    withContext(Dispatchers.IO) {
                        userDao.insert(newUser)
                    }
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