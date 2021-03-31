package com.app.hitamado.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.app.hitamado.MainActivity
import com.app.hitamado.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val viewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val preference = this.getSharedPreferences("APP", Context.MODE_PRIVATE)
        val cek = preference.getString("SESSION", null)
        if (cek != "FAILED" && cek != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_login.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_login ->{
                progressbar.visibility = View.VISIBLE

                viewModel.doLogin(edt_email.text.toString(),edt_password.text.toString()).observe(this,
                    Observer {response->

                        val preference = this@LoginActivity.getSharedPreferences(
                            "APP",
                            Context.MODE_PRIVATE
                        )
                        if (response.status=="success"){
                            progressbar.visibility = View.GONE
                            preference.edit().putString("SESSION", "SUCCESS").apply()
                            preference.edit().putString("nama",response.barangAccount?.nama).apply()
                            preference.edit().putString("email",response.barangAccount?.email).apply()
                            preference.edit().putString("phone",response.barangAccount?.noWa).apply()
                            preference.edit().putString("photo",response.barangAccount?.foto).apply()
                            val intent =
                                Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else {
                            progressbar.visibility = View.GONE
                            Toast.makeText(
                                this@LoginActivity,
                                "Gagal Login. Email / Password Salah!",
                                Toast.LENGTH_SHORT
                            ).show()
                            preference.edit().putString("SESSION", "FAILED").apply()
                        }
                    })
            }
        }
    }
}