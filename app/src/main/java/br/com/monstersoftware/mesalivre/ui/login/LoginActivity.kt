package br.com.monstersoftware.mesalivre.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.monstersoftware.mesalivre.R

class LoginActivity : AppCompatActivity(), LoginContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
