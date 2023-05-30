package com.kafka.moneyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kafka.moneyapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(){

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    //private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()


        binding.textViewSignUp.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.buttonSignUp.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()


            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if(pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                        if (it.isSuccessful){
                            saveData()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Пустые поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveData(){
        dbRef = FirebaseDatabase.getInstance().getReference("Users ")
        val email = binding.emailEt.text.toString()
        val userId = FirebaseAuth.getInstance().uid.toString()

        val user = userModel(email, userId)

        dbRef.child(userId).setValue(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Id Created", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{err ->
                Toast.makeText(this, "Id not Created", Toast.LENGTH_SHORT).show()
            }
    }
}