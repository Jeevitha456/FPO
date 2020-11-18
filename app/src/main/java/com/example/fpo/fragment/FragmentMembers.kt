package com.example.fpo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fpo.R
import com.example.fpo.adapter.CustomAdapter
import com.example.fpo.adapter.OnItemClickListener
import com.example.fpo.model.UserData

class FragmentMembers : Fragment(), OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var users: ArrayList<UserData>
    private var oldvalue: Int = -1
    private var newvalue: Int = -1

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_members, container, false)

        users = ArrayList<UserData>()

        //adding some dummy data to the list
        users.add(UserData("Rahul", "001", "R", "Female", "Madurai", "10", "Wheat", false))
        users.add(UserData("Jeevitha", "002", "J", "Male", "Chennai", "11", "Rice", false))
        users.add(UserData("Rashmi", "003", "R", "Male", "Badami", "12", "Corn", false))
        users.add(UserData("Anush", "004", "A", "Female", "Tumkur", "13", "Ragi", false))
        users.add(UserData("Lavanya", "005", "L", "Male", "Chennai", "14", "Maidha", false))
        users.add(UserData("Vinith", "006", "V", "Male", "Chennai", "15", "Flux seed", false))
        users.add(UserData("Kusum", "007", "K", "Male", "Chennai", "16", "Sugarcane", false))
        users.add(UserData("Pavan", "008", "P", "Male", "Chennai", "17", "Pumkin", false))

        recyclerView = rootView.findViewById(R.id.recycler_view) as RecyclerView // Add this
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = CustomAdapter(users, this)
        return rootView

    }

    override fun onItemClick(position: Int) {
        val clickItem = users[position]
        newvalue = position

        if (!clickItem.expanded) {
            clickItem.expanded = true
            recyclerView.adapter?.notifyItemChanged(position)
            if (oldvalue != -1 && oldvalue != newvalue) {
                val clickitemOld = users[oldvalue]
                clickitemOld.expanded = false
                recyclerView.adapter?.notifyItemChanged(oldvalue)
            }
            oldvalue = newvalue
        } else {
            clickItem.expanded = false
            recyclerView.adapter?.notifyItemChanged(position)
        }
    }
}