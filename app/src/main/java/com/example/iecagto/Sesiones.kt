package com.example.iecagto

import SeasonAdapter
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iecagto.datas.Season
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.sql.DriverManager

class Sesiones : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SeasonAdapter.OnItemClickListener{
    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var seasonAdapter: SeasonAdapter
    lateinit var drawer : DrawerLayout
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesiones)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val seasonsList: MutableList<Season> = mutableListOf()
        seasonAdapter = SeasonAdapter(seasonsList)
        recyclerView.adapter = seasonAdapter
        seasonAdapter.setOnItemClickListener(this)
        getSeasons()

        val mToolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        mToolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(mToolbar)
        supportActionBar?.title="Sesiones"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        drawer = findViewById(R.id.drawerLayout)

        toggle = ActionBarDrawerToggle(this,drawer,mToolbar, R.string.navDrawerOpen, R.string.navDrawerClose)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.navView)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()

        val currentUser: FirebaseUser? = auth.currentUser

        if (currentUser != null){
            if (currentUser.email != ""){
                Toast.makeText(this, "Sesiones de usuario", Toast.LENGTH_LONG).show()
            }
        }else{
            login("kaesquedac@gmail.com", "Saludos123")
        }
    }

    private fun login(email : String, password : String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
                task ->
            if (task.isSuccessful){
                val user = auth.currentUser
            }else{
                Toast.makeText(this, "Error en el inicio de sesión: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getSeasons(){
        val reference = database.getReference("seasons")

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val seasonsList: MutableList<Season> = mutableListOf()
                for (seasonSnapshot in dataSnapshot.children){
                    val season = seasonSnapshot.getValue(Season::class.java)
                    if (season != null) {
                        seasonsList.add(season)
                    }
                }
                seasonAdapter.updateData(seasonsList)
            }
            override fun onCancelled(databaseError : DatabaseError){
                DriverManager.println("Error al leer los datos ${databaseError.message}")
            }
        })
    }

    override fun onItemClick(season: Season) {
        Toast.makeText(this, "Clicked Season: ${season.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_rules-> {
                val intent = Intent(this, Reglamento::class.java)
                startActivity(intent)
            }
            R.id.nav_syllabus->  {
                val intent = Intent(this, Temario::class.java)
                startActivity(intent)
            }
            R.id.nav_summary-> {
                val intent = Intent(this, Menu::class.java)
                startActivity(intent)
            }
            R.id.nav_session -> Toast.makeText(this,"Sesiones", Toast.LENGTH_SHORT).show()

        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}