package com.example.saldonarede

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.saldonarede.databinding.ActivityLoginBinding
import com.example.saldonarede.databinding.ActivityMainBinding

class login : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding:ActivityLoginBinding
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(this, "o button ta funcionando!!", Toast.LENGTH_SHORT).show()
    }
}