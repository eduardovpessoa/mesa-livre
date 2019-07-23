package br.com.monstersoftware.mesalivre.ui.register

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.monstersoftware.mesalivre.R

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private var presenter: RegisterPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter = RegisterPresenter(this)
    }

    override fun initViews() {

    }

    override fun registerOk() {
        Toast.makeText(this, R.string.register_ok, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun registerError(msg: String?) {
        Toast.makeText(this, String.format("%s", getString(R.string.register_error), msg), Toast.LENGTH_LONG).show()
    }
}
