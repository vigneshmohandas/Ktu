package com.ktulive


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        displaySelectedScreen(0)
    }

    private fun displaySelectedScreen(itemID: Int) {
        var fragment: Fragment? = null
        when (itemID) {
            0// BTech or MTECH
            -> fragment = BtechOrMtech()
            1// Branch CHoosing
            -> {
            }
        }
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()

            ft.replace(R.id.homeFrame, fragment)
            ft.commit()


        }
    }
}

