package br.com.monstersoftware.mesalivre.ui.register

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.monstersoftware.mesalivre.R
import br.com.monstersoftware.mesalivre.data.local.entity.User
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private var presenter: RegisterPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter = RegisterPresenter(this)
    }

    override fun initViews() {
        rgType.setOnCheckedChangeListener { _, i -> presenter?.changeUserType(i) }
        btnRegister.setOnClickListener { presenter?.register() }
        val cpfListener = MaskedTextChangedListener("[000]{.}[000]{.}[000]{-}[00]", edtCpf)
        edtCpf.addTextChangedListener(cpfListener)
        edtCpf.onFocusChangeListener = cpfListener
        val cnpjListener = MaskedTextChangedListener("[00]{.}[000]{.}[000]{/}[0000]{-}[00]", edtCnpj)
        edtCnpj.addTextChangedListener(cnpjListener)
        edtCnpj.onFocusChangeListener = cnpjListener
        val phoneListener = MaskedTextChangedListener("([00]) [00000]-[0000]", edtPhone)
        edtPhone.addTextChangedListener(phoneListener)
        edtPhone.onFocusChangeListener = phoneListener
    }

    override fun registerOk() {
        Toast.makeText(this, R.string.register_ok, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun registerError(msg: String?) {
        Toast.makeText(this, String.format("%s", getString(R.string.register_error), msg), Toast.LENGTH_LONG).show()
    }
}
