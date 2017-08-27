package com.ktulive


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

import com.ktulive.extra.CusUtils
import com.ktulive.models.Branch
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.ktulive.extra.BranchListItemClickListener

import java.util.ArrayList

import kotlinx.android.synthetic.main.activity_branch.*

class BranchChoosingActivity : Fragment() {

    lateinit var branchArrayList: ArrayList<Branch>
    lateinit var branchdisplay: ArrayList<String>
    lateinit var branchadapter: ArrayAdapter<String>


    internal var databaseReference: DatabaseReference? = null







    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.activity_branch, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        databaseReference = CusUtils.getDatabase().reference.child("btech").child("courses")
        databaseReference!!.keepSynced(true)
        branchArrayList = ArrayList()


        branchListView.setHasFixedSize(true)
        branchListView.layoutManager = LinearLayoutManager(context  )
//        branchListView.addOnItemTouchListener({object : BranchListItemClickListener(context,BranchListItemClickListener.OnItemClickListener { view, position -> print(position) }) })




        databaseReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dataSnapshotIterable = dataSnapshot.children
                branchdisplay = ArrayList()

                Log.e("CHILDRED", dataSnapshot.childrenCount.toString() + "")
                for (branch in dataSnapshotIterable) {
                    val branch1 = branch.getValue(Branch::class.java)
                    branchArrayList.add(branch1)


                    branch1.printBranchDetails()

                    branchdisplay.add(branch1.branch_name)

                }

               var  myRecyclerAdapter = MyAdapter(branchdisplay,context)
                branchListView.adapter = myRecyclerAdapter


//

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

    }
    companion object {
        fun newInstance():BranchChoosingActivity{
            var mapFragment = BranchChoosingActivity()
            var args = Bundle()
            mapFragment.arguments = args
            return mapFragment
        }
    }

}
