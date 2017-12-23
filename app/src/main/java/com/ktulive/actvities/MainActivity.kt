package com.ktulive.actvities

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.ktulive.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import com.ktulive.GeneralData
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.nav_header_main.*
import org.w3c.dom.Text


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        var ft:FragmentTransaction  = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_location,HomeFragment())
        ft.commit()
        toolbar!!.background  =(ColorDrawable(Color.parseColor("#ffffff")))
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)



        var navigationView:NavigationView = findViewById(R.id.nav_view) as NavigationView
        var header:View = navigationView.getHeaderView(0)
       var  display = header.findViewById(R.id.displayName)as TextView
       var  email = header.findViewById(R.id.email)as TextView
       var  imageView = header.findViewById(R.id.imageView)as CircleImageView
        display.setText((FirebaseAuth.getInstance().currentUser?.displayName))
        email.setText((FirebaseAuth.getInstance().currentUser?.email))
       var photoURL =  FirebaseAuth.getInstance().currentUser?.photoUrl.toString()
        Toast.makeText(applicationContext,photoURL,Toast.LENGTH_SHORT).show()
        Picasso.with(applicationContext).load(FirebaseAuth.getInstance().currentUser?.photoUrl.toString()).into(imageView);


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.contact_us -> {
//                var fragment  =  ContactUsFragment()
                var ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_location,ContactUsFragment())
                ft.addToBackStack("1")
                ft.commit()



            }
            R.id.home_se -> {
                var ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_location,HomeFragment())
                ft.addToBackStack("1")
                ft.commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
