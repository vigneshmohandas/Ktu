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

import com.ktulive.GeneralData


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        var ft:FragmentTransaction  = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_location,HomeFragment())
        ft.commit()




//        btech.setOnClickListener {
//            btech.setBackgroundResource(R.drawable.button_selected_bg)
//            btech.setTextColor(getColor(R.color.white))
//
//            mtech.setBackgroundResource(R.drawable.button_unselected_bg)
//            mtech.setTextColor(getColor(R.color.colorPrimary))
//
//        }
//        mtech.setOnClickListener {
//            mtech.setBackgroundResource(R.drawable.button_selected_bg)
//            mtech.setTextColor(getColor(R.color.white))
//
//            btech.setBackgroundResource(R.drawable.button_unselected_bg)
//            btech.setTextColor(getColor(R.color.colorPrimary))
//
//
//            val url = "https://ktu.edu.in/eu/acd/academicRegulationsMtech.htm"
//            val i = Intent(Intent.ACTION_VIEW)
//            i.data = Uri.parse(url)
//            startActivity(i)
//        }
//
//
//        cse.setOnClickListener {
//
//            clearAllandHighlightRequiredBranches(cse)
//
//            branch = GeneralData.getBranchCode("CSE")
//            shared_pref_editor.putString("branch",branch)
//            shared_pref_editor.apply()
//
//        }
//        civil.setOnClickListener {
//
//            clearAllandHighlightRequiredBranches(civil)
//
//            branch = GeneralData.getBranchCode("CIVIL")
//            shared_pref_editor.putString("branch",branch)
//            shared_pref_editor.apply()
//
//        }
//        mech.setOnClickListener {
//
//            clearAllandHighlightRequiredBranches(mech)
//
//            branch = GeneralData.getBranchCode("MECH")
//            shared_pref_editor.putString("branch",branch)
//            shared_pref_editor.apply()
//
//        }
//        eee.setOnClickListener {
//
//            clearAllandHighlightRequiredBranches(eee)
//
//            branch = GeneralData.getBranchCode("EEE")
//            shared_pref_editor.putString("branch",branch)
//            shared_pref_editor.apply()
//
//        }
//        arch.setOnClickListener {
//
//            clearAllandHighlightRequiredBranches(arch)
//
//            branch = GeneralData.getBranchCode("ARCH")
//            shared_pref_editor.putString("branch",branch)
//            shared_pref_editor.apply()
//
//        }
//        ece.setOnClickListener {
//
//            clearAllandHighlightRequiredBranches(ece)
//
//            branch = GeneralData.getBranchCode("ECE")
//            shared_pref_editor.putString("branch",branch)
//            shared_pref_editor.apply()
//
//        }
//        chem.setOnClickListener {
//
//            clearAllandHighlightRequiredBranches(chem)
//
//            branch = GeneralData.getBranchCode("CHEM")
//            shared_pref_editor.putString("branch",branch)
//            shared_pref_editor.apply()
//
//        }
//
//        sem1.setOnClickListener {
//
//            shared_pref_editor.putInt("sem",1)
//            shared_pref_editor.apply()
//
//            clearAllandHighlightRequiredSemester(sem1)
//            sem = 1
//
//        }
//        sem2.setOnClickListener {
//            shared_pref_editor.putInt("sem",2)
//            shared_pref_editor.apply()
//            clearAllandHighlightRequiredSemester(sem2)
//            sem = 2
//
//
//
//
//        }
//        sem3.setOnClickListener {
//            shared_pref_editor.putInt("sem",3)
//            shared_pref_editor.apply()
//            clearAllandHighlightRequiredSemester(sem3)
//            sem = 3
//
//
//
//        }
//        sem4.setOnClickListener {
//            shared_pref_editor.putInt("sem",4)
//            shared_pref_editor.apply()
//            clearAllandHighlightRequiredSemester(sem4)
//            sem = 4
//
//
//        }
//        sem5.setOnClickListener {
//            shared_pref_editor.putInt("sem",5)
//            shared_pref_editor.apply()
//            clearAllandHighlightRequiredSemester(sem5)
//            sem = 5
//
//
//        }
//        sem6.setOnClickListener {
//            shared_pref_editor.putInt("sem",6)
//            shared_pref_editor.apply()
//            clearAllandHighlightRequiredSemester(sem6)
//            sem = 6
//
//
//        }
//        sem7.setOnClickListener {
//            shared_pref_editor.putInt("sem",7)
//            shared_pref_editor.apply()
//            clearAllandHighlightRequiredSemester(sem7)
//            sem = 7
//
//
//        }
//        sem8.setOnClickListener {
//            shared_pref_editor.putInt("sem",8)
//            shared_pref_editor.apply()
//            clearAllandHighlightRequiredSemester(sem8)
//            sem = 8
//
//
//
//        }
//
//        find.setOnClickListener {
//
//            if ((branch!=null) && (sem !=null)){
//
//                var i:Intent  = Intent(applicationContext, SubjectChoosingActivity::class.java)
//                i.putExtra("branch_sem",branch+sem.toString())
//                startActivity(i)
//            }
//
//            Toast.makeText(applicationContext,branch+sem.toString(),Toast.LENGTH_SHORT).show()
//
//
//        }




        toolbar!!.background  =(ColorDrawable(Color.parseColor("#ffffff")))


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
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
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }






}
