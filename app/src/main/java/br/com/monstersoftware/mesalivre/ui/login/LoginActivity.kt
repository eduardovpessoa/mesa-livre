package br.com.monstersoftware.mesalivre.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.monstersoftware.mesalivre.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
    }

    override fun initViews() {
        btnLogin.setOnClickListener { presenter?.doLogin(edtUser.text.toString(), edtPass.text.toString()) }
        lblLogin.setOnClickListener { presenter?.openRegister() }
        txtLogin.setOnClickListener { presenter?.openRegister() }
        btnFacebook.setOnClickListener { facebookLogin() }
        btnGoogle.setOnClickListener { googleLogin() }
    }

    override fun facebookLogin() {
        Toast.makeText(this, getString(R.string.facebook_login), Toast.LENGTH_LONG).show()
    }

    override fun googleLogin() {
        Toast.makeText(this, getString(R.string.google_login), Toast.LENGTH_LONG).show()
    }

    override fun setUsernameError() {
        edtUser.error = getString(R.string.login_user_error)
    }

    override fun setPasswordError() {
        edtPass.error = getString(R.string.login_passwd_error)
    }

    override fun showLoginError() {
        Toast.makeText(this, R.string.login_error, Toast.LENGTH_LONG).show()
    }

}
