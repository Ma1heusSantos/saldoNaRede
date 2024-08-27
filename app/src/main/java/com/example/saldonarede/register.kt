package com.example.saldonarede

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.saldonarede.databinding.ActivityMainBinding
import com.example.saldonarede.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class register : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private val auth = FirebaseAuth.getInstance()
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonSalvar.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        var email:String = binding.editLogin.text.toString()
        var password:String = binding.editPassword.text.toString()

        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "preencha os campos!", Toast.LENGTH_SHORT).show()
        }else{
           auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{cadastro ->
               if(cadastro.isSuccessful){
                   Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                   binding.editLogin.text.clear()
                   binding.editPassword.text.clear()
               }
           }.addOnFailureListener{exception ->
               val mensagemError = when(exception){
                   is FirebaseAuthWeakPasswordException -> "A senha deve conter pelo menos 6 caracteres!"
                   is FirebaseAuthInvalidCredentialsException -> " Digite um email válido!"
                   is FirebaseAuthUserCollisionException -> " esta conta já esta cadastrada!"
                   is FirebaseNetworkException -> "Sem conexão com a internet!"
                   else -> "erro ao cadastrar usuário!"
               }
               val snackbar = Snackbar(this,)
           }
        }
    }

}