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
import com.example.fpo.model.UserData

class FragmentMembers : Fragment() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_members, container, false)



        val users = ArrayList<UserData>()

        //adding some dummy data to the list
        users.add(UserData("Rahul", "001","R","Female","Madurai","10","Wheat"))
        users.add(UserData("Jeevitha", "002","J","Male","Chennai","11","Rice"))
        users.add(UserData("Rashmi", "003","R","Male","Badami","12","Corn"))
        users.add(UserData("Anush", "004","A","Female","Tumkur","13","Ragi"))
        users.add(UserData("Lavanya", "005","L","Male","Chennai","14","Maidha"))
        users.add(UserData("Vinith", "006","V","Male","Chennai","15","Flux seed"))
        users.add(UserData("Kusum", "007","K","Male","Chennai","16","Sugarcane"))
        users.add(UserData("Pavan", "008","P","Male","Chennai","17","Pumkin"))



        recyclerView = rootView.findViewById(R.id.recycler_view) as RecyclerView // Add this
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = CustomAdapter(users)
        return rootView

    }
}