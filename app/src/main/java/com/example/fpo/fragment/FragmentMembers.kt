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
        users.add(UserData("Belal Khan", "Ranchi Jharkhand","J"))
        users.add(UserData("Ramiz Khan", "Ranchi Jharkhand","P"))
        users.add(UserData("Faiz Khan", "Ranchi Jharkhand","Y"))
        users.add(UserData("Yashar Khan", "Ranchi Jharkhand","K"))
        users.add(UserData("Yashar Khan", "Ranchi Jharkhand","K"))
        users.add(UserData("Yashar Khan", "Ranchi Jharkhand","K"))
        users.add(UserData("Yashar Khan", "Ranchi Jharkhand","K"))
        users.add(UserData("Yashar Khan", "Ranchi Jharkhand","K"))



        recyclerView = rootView.findViewById(R.id.recycler_view) as RecyclerView // Add this
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = CustomAdapter(users)
        return rootView

    }
}