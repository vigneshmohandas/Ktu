package com.ktulive.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.btechormtech.*

import com.ktulive.R

/**
 * Created by asnimpansari on 8/20/17.
 */

class CourseChoosingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.btechormtech, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        btechbutton.setOnClickListener()
        {
            btechButtonClicked()
        }




    }
    fun btechButtonClicked(){
        Log.e("WHERE","BTECH CLICKED")
        Toast.makeText(activity.applicationContext,"BTECH",Toast.LENGTH_LONG)

        var fragment = BranchChoosingFragment.newInstance() as Fragment

        activity.supportFragmentManager.beginTransaction()
                .replace(R.id.homeFrame,fragment)
                .commit()





    }
    fun mTechButtonClicked(){

    }
}
