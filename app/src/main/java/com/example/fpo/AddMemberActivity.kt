package com.example.fpo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ScrollView
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.viewpager.widget.ViewPager
import com.example.fpo.fragment.AddMemberFragment.AddBODFragment
import com.example.fpo.fragment.AddMemberFragment.AddMemberFragment
import com.example.fpo.fragment.AddMemberFragment.AddStaffFragment
import com.example.fpo.fragment.MyPageAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout

class AddMemberActivity : AppCompatActivity() {
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var toolbar: Toolbar
    private lateinit var image: ImageView
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member)

        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar)
        image = findViewById(R.id.imagegreen)
        toolbar = findViewById(R.id.toolbar)
        appBarLayout = findViewById(R.id.appbar)
        viewPager = findViewById(R.id.addmember_viewpager)
        tabs = findViewById(R.id.tabs)
        collapsingToolbarLayout.setContentScrimColor(Color.GREEN)
        val adapter = MyPageAdapter(supportFragmentManager)
        adapter.addFragment(AddMemberFragment(), "Members")
        adapter.addFragment(AddBODFragment(), "BOD")
        adapter.addFragment(AddStaffFragment(), "Staffs")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.setSelectedTabIndicatorColor(Color.WHITE)

    }
}