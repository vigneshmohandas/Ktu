package com.ktulive.actvities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.ktulive.GeneralData
import com.ktulive.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by asnimansari on 23/12/17.
 */
class HomeFragment():Fragment(){
    var sem:Int? = null
    var branch:String? = null
    var branch_full:String? = null
//    var shared_pref_editor:SharedPreferences.Editor?=null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       var shared_pref_editor = context.getSharedPreferences("ktulive", Context.MODE_PRIVATE).edit()

        btech.setOnClickListener {
            btech.setBackgroundResource(R.drawable.button_selected_bg)
            btech.setTextColor(getResources().getColor(R.color.white,resources.newTheme()));

            mtech.setBackgroundResource(R.drawable.button_unselected_bg)
            mtech.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))

            streamcard.visibility = View.VISIBLE

        }
        mtech.setOnClickListener {
            mtech.setBackgroundResource(R.drawable.button_selected_bg)
            mtech.setTextColor(getResources().getColor(R.color.white))

            btech.setBackgroundResource(R.drawable.button_unselected_bg)
            btech.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))


            val url = "https://ktu.edu.in/eu/acd/academicRegulationsMtech.htm"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }


        cse.setOnClickListener {

            clearAllandHighlightRequiredBranches(cse)
            branch_full = "Computer Science  & Engineering"
            branch = GeneralData.getBranchCode("CSE")
            shared_pref_editor.putString("branch",branch)
            shared_pref_editor?.putString("branch_full_name",branch_full)

            shared_pref_editor.apply()


        }
        civil.setOnClickListener {

            clearAllandHighlightRequiredBranches(civil)
            branch_full = "Civil Engineering"
            branch = GeneralData.getBranchCode("CIVIL")
            shared_pref_editor.putString("branch",branch)
            shared_pref_editor?.putString("branch_full_name",branch_full)

            shared_pref_editor.apply()

        }
        mech.setOnClickListener {

            clearAllandHighlightRequiredBranches(mech)
            branch_full = "Mechanical Engineering"
            branch = GeneralData.getBranchCode("MECH")
            shared_pref_editor.putString("branch",branch)
            shared_pref_editor?.putString("branch_full_name",branch_full)

            shared_pref_editor.apply()

        }
        eee.setOnClickListener {

            clearAllandHighlightRequiredBranches(eee)
            branch_full = "Electrical & Electronics Engineering"
            branch = GeneralData.getBranchCode("EEE")
            shared_pref_editor.putString("branch",branch)
            shared_pref_editor?.putString("branch_full_name",branch_full)

            shared_pref_editor.apply()

        }
        arch.setOnClickListener {
            branch_full = "Architecture"
            clearAllandHighlightRequiredBranches(arch)

            branch = GeneralData.getBranchCode("ARCH")
            shared_pref_editor.putString("branch",branch)
            shared_pref_editor?.putString("branch_full_name",branch_full)

            shared_pref_editor.apply()

        }
        ece.setOnClickListener {
            branch_full = "Electronics & Communication"
            clearAllandHighlightRequiredBranches(ece)

            branch = GeneralData.getBranchCode("ECE")
            shared_pref_editor.putString("branch",branch)
            shared_pref_editor?.putString("branch_full_name",branch_full)

            shared_pref_editor.apply()

        }
        chem.setOnClickListener {
            branch_full = "Chemical Engineering"
            clearAllandHighlightRequiredBranches(chem)
            branch = GeneralData.getBranchCode("CHEM")
            shared_pref_editor.putString("branch",branch)
            shared_pref_editor?.putString("branch_full_name",branch_full)

            shared_pref_editor.apply()

        }

        sem1.setOnClickListener {

            shared_pref_editor.putInt("sem",1)
            shared_pref_editor.apply()
            sem = 1
            clearAllandHighlightRequiredSemester(sem1)

        }
        sem2.setOnClickListener {
            shared_pref_editor.putInt("sem",2)
            shared_pref_editor.apply()
            sem = 2
            clearAllandHighlightRequiredSemester(sem2)





        }
        sem3.setOnClickListener {
            shared_pref_editor.putInt("sem",3)
            shared_pref_editor.apply()
            sem = 3
            clearAllandHighlightRequiredSemester(sem3)




        }
        sem4.setOnClickListener {
            shared_pref_editor.putInt("sem",4)
            shared_pref_editor.apply()
            sem = 4
            clearAllandHighlightRequiredSemester(sem4)



        }
        sem5.setOnClickListener {
            shared_pref_editor.putInt("sem",5)
            shared_pref_editor.apply()
            sem = 5
            clearAllandHighlightRequiredSemester(sem5)



        }
        sem6.setOnClickListener {
            shared_pref_editor.putInt("sem",6)
            shared_pref_editor.apply()
            sem = 6
            clearAllandHighlightRequiredSemester(sem6)



        }
        sem7.setOnClickListener {
            shared_pref_editor.putInt("sem",7)
            shared_pref_editor.apply()
            sem = 7
            clearAllandHighlightRequiredSemester(sem7)



        }
        sem8.setOnClickListener {
            shared_pref_editor.putInt("sem",8)
            shared_pref_editor.apply()
            sem = 8
            clearAllandHighlightRequiredSemester(sem8)
        }



    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home,container,false)
    }
        @RequiresApi(Build.VERSION_CODES.M)
    fun clearAllandHighlightRequiredSemester(highlightsem: TextView){
        sem1.setBackgroundResource(R.drawable.button_unselected_bg)
        sem2.setBackgroundResource(R.drawable.button_unselected_bg)
        sem3.setBackgroundResource(R.drawable.button_unselected_bg)
        sem4.setBackgroundResource(R.drawable.button_unselected_bg)
        sem5.setBackgroundResource(R.drawable.button_unselected_bg)
        sem6.setBackgroundResource(R.drawable.button_unselected_bg)
        sem7.setBackgroundResource(R.drawable.button_unselected_bg)
        sem8.setBackgroundResource(R.drawable.button_unselected_bg)
        sem1.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        sem2.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        sem3.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        sem4.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        sem5.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        sem6.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        sem7.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        sem8.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        highlightsem.setBackgroundResource(R.drawable.button_selected_bg)
        highlightsem.setTextColor(resources.getColor(R.color.white,resources.newTheme()))



            var ft:FragmentTransaction = fragmentManager.beginTransaction()
            var nextFrag = SubjectChoosingFragment()
            val args = Bundle()

            Toast.makeText(context,branch+sem.toString(),Toast.LENGTH_SHORT).show()
            args.putString("branch_sem", branch+sem.toString())

            nextFrag.arguments=  args
            ft.replace(R.id.fragment_location,nextFrag)


            ft.commit()



    }

    fun clearAllandHighlightRequiredBranches(highlightbranch:TextView){
        cse.setBackgroundResource(R.drawable.button_unselected_bg)
        civil.setBackgroundResource(R.drawable.button_unselected_bg)
        mech.setBackgroundResource(R.drawable.button_unselected_bg)
        arch.setBackgroundResource(R.drawable.button_unselected_bg)
        ece.setBackgroundResource(R.drawable.button_unselected_bg)
        eee.setBackgroundResource(R.drawable.button_unselected_bg)
        chem.setBackgroundResource(R.drawable.button_unselected_bg)

        cse.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        civil.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        mech.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        arch.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        ece.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        eee.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))
        chem.setTextColor(getResources().getColor(R.color.colorPrimary,resources.newTheme()))

        highlightbranch.setBackgroundResource(R.drawable.button_selected_bg)
        highlightbranch.setTextColor(getResources().getColor(R.color.white,resources.newTheme()))

        semstercard.visibility  = View.VISIBLE
    }
}