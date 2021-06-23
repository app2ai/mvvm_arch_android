package com.vishalr.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.btnLogin
import kotlinx.android.synthetic.main.activity_main.password
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.username

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        btnLogin.setOnClickListener {
            viewModel.dataValidate(username.text.toString(), password.text.toString())
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.isValidate.observe(this, Observer {
            if (it){
                viewModel.goForLogin(username.text.toString(), password.text.toString())
            } else {
                Toast.makeText(this, "User and Pass must not be empty", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.progress.observe(this, Observer {
            if (it){
                progressBar.visibility = View.VISIBLE
            } else{
                progressBar.visibility = View.GONE
            }
        })

        viewModel.loginData.observe(this, Observer {
            Toast.makeText(this, "Userdata: $it", Toast.LENGTH_SHORT).show()
        })
    }
}