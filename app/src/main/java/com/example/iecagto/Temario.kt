package com.example.iecagto

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
import androidx.viewpager.widget.ViewPager
import com.example.iecagto.datas.ViewPageAdapter
import com.google.android.material.navigation.NavigationView

class Temario : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var drawer : DrawerLayout
    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temario)

        val viewPager: ViewPager = findViewById(R.id.vp1)
        val adapter = ViewPageAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        val mToolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        mToolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(mToolbar)
        supportActionBar?.title="Temario"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        drawer = findViewById(R.id.drawerLayout)

        toggle = ActionBarDrawerToggle(this,drawer,mToolbar, R.string.navDrawerOpen, R.string.navDrawerClose)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.navView)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_rules-> {
                val intent = Intent(this, Reglamento::class.java)
                startActivity(intent)
            }
            R.id.nav_session-> {
                val intent = Intent(this, Sesiones::class.java)
                startActivity(intent)
            }
            R.id.nav_summary -> {
                val intent = Intent(this, Menu::class.java)
                startActivity(intent)
            }
            R.id.nav_syllabus-> Toast.makeText(this,"Temario", Toast.LENGTH_SHORT).show()

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