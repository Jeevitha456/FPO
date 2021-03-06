package com.example.fpo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.fpo.fragment.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var toolbar: Toolbar
    private lateinit var image: ImageView
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private lateinit var floatingActionButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar)
        image = findViewById(R.id.imagegreen)
        toolbar = findViewById(R.id.toolbar)
        searchView = findViewById(R.id.searchview)
        searchView.setBackgroundColor(Color.WHITE)
        appBarLayout = findViewById(R.id.appbar)
        viewPager = findViewById(R.id.viewpager)
        tabs = findViewById(R.id.tabs)
        collapsingToolbarLayout.setContentScrimColor(Color.GREEN)
        floatingActionButton = findViewById(R.id.floating_bar)
        val adapter = MyPageAdapter(supportFragmentManager)
        adapter.addFragment(FragmentMembers(), "Members")
        adapter.addFragment(FragmentBOD(), "BOD")
        adapter.addFragment(FragmentStaffs(), "Staffs")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.setSelectedTabIndicatorColor(Color.WHITE)

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        floatingActionButton.setOnClickListener() {
            val intent = Intent(this, AddMemberActivity::class.java)
            startActivity(intent)
        }
    }
}