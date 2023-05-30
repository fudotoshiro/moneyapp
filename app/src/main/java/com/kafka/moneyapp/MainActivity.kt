package com.kafka.moneyapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.kafka.moneyapp.databinding.ActivityMainBinding
import com.kafka.moneyapp.databinding.FragmentHistoryBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var historyBinding: FragmentHistoryBinding
    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(category())


        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val budgetView: TextView = findViewById(R.id.budget)

        budgetView.text = "10000"




        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile_name -> Toast.makeText(applicationContext, "Name", Toast.LENGTH_SHORT).show()
                R.id.profile_passwordchange -> Toast.makeText(applicationContext, "Password", Toast.LENGTH_SHORT).show()
                R.id.profile_log_out -> logOut()
                R.id.profile_settigns -> Toast.makeText(applicationContext, "Settings", Toast.LENGTH_SHORT).show()
                R.id.profile_currency -> Toast.makeText(applicationContext, "Currency", Toast.LENGTH_SHORT).show()
            }
            true
        }


        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.category -> replaceFragment(category())
                R.id.history -> replaceFragment(history())
                R.id.home -> replaceFragment(home())

                else ->{

                }


            }
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return true
    }

    private fun logOut(){
        FirebaseAuth.getInstance().signOut()
        val intent= Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}

