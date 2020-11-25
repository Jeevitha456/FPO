package com.example.fpo.fragment.AddMemberFragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fpo.R
import com.example.fpo.adapter.*
import com.example.fpo.model.KharfiData
import com.example.fpo.model.LivestockData
import com.example.fpo.model.RabiData
import com.example.fpo.model.UserData
import java.text.SimpleDateFormat
import java.util.*


class AddMemberFragment : Fragment(), OnFarmItemClickListener, OnItemClickListener, OnLivestockClickListener {
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var linearLayout: LinearLayout

    //basic profile layout
    private lateinit var edt_dob: EditText
    private lateinit var edt_gender: Spinner
    private lateinit var edt_smartphone: Spinner
    private lateinit var edt_choosefile: EditText
    private lateinit var edt_uploadaddress: EditText
    private lateinit var edt_uploadidentity: EditText
    private lateinit var basicprofile: ConstraintLayout
    private lateinit var basicprofileDetails: ConstraintLayout
    private lateinit var basicprofile_uparrow: ImageView
    var cal = Calendar.getInstance()

    //farm layout
    private lateinit var edt_member: Spinner
    private lateinit var layout_farm: ConstraintLayout
    private lateinit var layout_farmDetails: ConstraintLayout
    private lateinit var farm_uparrow: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var rabi_crops: ArrayList<RabiData>
    private lateinit var gridlayoutmanager: GridLayoutManager
    private lateinit var kharfirecyclerView: RecyclerView
    private lateinit var kharfi_crops: ArrayList<KharfiData>
    private lateinit var kharfi_gridlayoutmanager: GridLayoutManager
    private lateinit var livestockrecyclerView: RecyclerView
    private lateinit var livestock_crops: ArrayList<LivestockData>
    private lateinit var livestock_gridlayoutmanager: GridLayoutManager
    private var oldvalue: Int = -1
    private var newvalue: Int = -1
    private var farmoldvalue: Int = -1
    private var farmnewvalue: Int = -1
    private var liveoldvalue: Int = -1
    private var livestocknewvalue: Int = -1


    //livestock layout
    private lateinit var edt_livestock__member: Spinner
    private lateinit var edt_livestock__breed: Spinner
    private lateinit var edt_livestock__count: Spinner
    private lateinit var layout_livestock: ConstraintLayout
    private lateinit var layout_livestockDetails: ConstraintLayout
    private lateinit var livestock_uparrow: ImageView

    //assest layout
    private lateinit var edt_assest__member: Spinner
    private lateinit var edt_assest__type: Spinner
    private lateinit var edt_assest__status: Spinner
    private lateinit var edt_acquired_on: EditText
    private lateinit var layout_assest: ConstraintLayout
    private lateinit var layout_assestDetails: ConstraintLayout
    private lateinit var assest_uparrow: ImageView

    //household layout
    private lateinit var edt_household_member: Spinner
    private lateinit var layout_household: ConstraintLayout
    private lateinit var layout_householdDetails: ConstraintLayout
    private lateinit var household_uparrow: ImageView

    //bank layout
    private lateinit var edt_bank_member: Spinner
    private lateinit var edt_account_type: Spinner
    private lateinit var layout_bank: ConstraintLayout
    private lateinit var layout_bankDetails: ConstraintLayout
    private lateinit var bank_uparrow: ImageView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootview = inflater.inflate(R.layout.fragment_add_member, container, false)
        nestedScrollView = rootview.findViewById(R.id.nestedscroll_view) as NestedScrollView
        linearLayout = rootview.findViewById(R.id.linear_layout) as LinearLayout

        // basic profile layout
        edt_dob = rootview.findViewById(R.id.edt_dob) as EditText
        edt_gender = rootview.findViewById(R.id.edt_gender) as Spinner
        edt_smartphone = rootview.findViewById(R.id.edt_smartphone) as Spinner
        edt_choosefile = rootview.findViewById(R.id.edt_choosefile) as EditText
        edt_uploadaddress = rootview.findViewById(R.id.upload_addressfile) as EditText
        edt_uploadidentity = rootview.findViewById(R.id.upload_identityfile) as EditText
        basicprofile = rootview.findViewById(R.id.layout_basicprofile) as ConstraintLayout
        basicprofileDetails =
                rootview.findViewById(R.id.layout_basicprofiledetails) as ConstraintLayout
        basicprofile_uparrow = rootview.findViewById(R.id.basicprofile_uparrow) as ImageView
        basicprofilelayout()

        //farm layout
        edt_member = rootview.findViewById(R.id.edt_member) as Spinner
        layout_farm = rootview.findViewById(R.id.layout_farm) as ConstraintLayout
        layout_farmDetails = rootview.findViewById(R.id.layout_farmdetails) as ConstraintLayout
        farm_uparrow = rootview.findViewById(R.id.farm_uparrow) as ImageView
        recyclerView = rootview.findViewById(R.id.farm_rabi_recycler_view) as RecyclerView
        kharfirecyclerView = rootview.findViewById(R.id.farm_kharfi_recycler_view) as RecyclerView
        livestockrecyclerView = rootview.findViewById(R.id.farm_livestock_recycler_view) as RecyclerView

        farmlayout()

        //livestock
        edt_livestock__member = rootview.findViewById(R.id.edt_livestock__member) as Spinner
        edt_livestock__breed = rootview.findViewById(R.id.edt_livestock__breed) as Spinner
        edt_livestock__count = rootview.findViewById(R.id.edt_livestock__count) as Spinner
        layout_livestock = rootview.findViewById(R.id.layout_livestock) as ConstraintLayout
        layout_livestockDetails =
                rootview.findViewById(R.id.layout_livestockdetails) as ConstraintLayout
        livestock_uparrow = rootview.findViewById(R.id.livestock_uparrow) as ImageView
        livestocklayout()

        //assets
        edt_assest__member = rootview.findViewById(R.id.edt_assets__member) as Spinner
        edt_assest__type = rootview.findViewById(R.id.edt_asset_type) as Spinner
        edt_assest__status = rootview.findViewById(R.id.edt_status_type) as Spinner
        edt_acquired_on = rootview.findViewById(R.id.edt__aquired_on) as EditText
        layout_assest = rootview.findViewById(R.id.layout_assest) as ConstraintLayout
        layout_assestDetails =
                rootview.findViewById(R.id.layout_assestcarddetails) as ConstraintLayout
        assest_uparrow = rootview.findViewById(R.id.assests_uparrow) as ImageView
        assestslayout()

        //household
        edt_household_member = rootview.findViewById(R.id.edt_household__member) as Spinner
        layout_household = rootview.findViewById(R.id.layout_household) as ConstraintLayout
        layout_householdDetails =
                rootview.findViewById(R.id.layout_houholdcarddetails) as ConstraintLayout
        household_uparrow = rootview.findViewById(R.id.household_uparrow) as ImageView
        householdlayout()

        //bank
        edt_bank_member = rootview.findViewById(R.id.edt_bank__member) as Spinner
        edt_account_type = rootview.findViewById(R.id.edt_account_type) as Spinner
        layout_bank = rootview.findViewById(R.id.layout_bank) as ConstraintLayout
        layout_bankDetails = rootview.findViewById(R.id.layout_bank_carddetails) as ConstraintLayout
        bank_uparrow = rootview.findViewById(R.id.bank_uparrow) as ImageView
        banklayout()

        setEventHandler()
        return rootview
    }

    private fun setEventHandler() {
        basicprofile.setOnClickListener({
            basicprofile.visibility = View.GONE
            basicprofileDetails.visibility = View.VISIBLE
            layout_farmDetails.visibility = View.GONE
            layout_farm.visibility = View.VISIBLE
            layout_livestock.visibility = View.VISIBLE
            layout_livestockDetails.visibility = View.GONE
            layout_assest.visibility = View.VISIBLE
            layout_assestDetails.visibility = View.GONE
            layout_household.visibility = View.VISIBLE
            layout_householdDetails.visibility = View.GONE
            layout_bank.visibility = View.VISIBLE
            layout_bankDetails.visibility = View.GONE
        })
        basicprofile_uparrow.setOnClickListener({
            basicprofile.visibility = View.VISIBLE
            basicprofileDetails.visibility = View.GONE
        })

        layout_farm.setOnClickListener({
            layout_farm.visibility = View.GONE
            layout_farmDetails.visibility = View.VISIBLE
            basicprofileDetails.visibility = View.GONE
            basicprofile.visibility = View.VISIBLE
            layout_livestock.visibility = View.VISIBLE
            layout_livestockDetails.visibility = View.GONE
            layout_assest.visibility = View.VISIBLE
            layout_assestDetails.visibility = View.GONE
            layout_household.visibility = View.VISIBLE
            layout_householdDetails.visibility = View.GONE
            layout_bank.visibility = View.VISIBLE
            layout_bankDetails.visibility = View.GONE
        })
        farm_uparrow.setOnClickListener({
            layout_farm.visibility = View.VISIBLE
            layout_farmDetails.visibility = View.GONE
        })

        layout_livestock.setOnClickListener({
            layout_livestock.visibility = View.GONE
            layout_livestockDetails.visibility = View.VISIBLE
            basicprofileDetails.visibility = View.GONE
            basicprofile.visibility = View.VISIBLE
            layout_farm.visibility = View.VISIBLE
            layout_farmDetails.visibility = View.GONE
            layout_assest.visibility = View.VISIBLE
            layout_assestDetails.visibility = View.GONE
            layout_household.visibility = View.VISIBLE
            layout_householdDetails.visibility = View.GONE
            layout_bank.visibility = View.VISIBLE
            layout_bankDetails.visibility = View.GONE
        })
        livestock_uparrow.setOnClickListener({
            layout_livestock.visibility = View.VISIBLE
            layout_livestockDetails.visibility = View.GONE
        })

        layout_assest.setOnClickListener({
            layout_assest.visibility = View.GONE
            layout_assestDetails.visibility = View.VISIBLE
            basicprofileDetails.visibility = View.GONE
            basicprofile.visibility = View.VISIBLE
            layout_farm.visibility = View.VISIBLE
            layout_farmDetails.visibility = View.GONE
            layout_livestock.visibility = View.VISIBLE
            layout_livestockDetails.visibility = View.GONE
            layout_household.visibility = View.VISIBLE
            layout_householdDetails.visibility = View.GONE
            layout_bank.visibility = View.VISIBLE
            layout_bankDetails.visibility = View.GONE
        })
        assest_uparrow.setOnClickListener({
            layout_assest.visibility = View.VISIBLE
            layout_assestDetails.visibility = View.GONE
        })

        layout_household.setOnClickListener({
            layout_assest.visibility = View.GONE
            layout_householdDetails.visibility = View.VISIBLE
            basicprofileDetails.visibility = View.GONE
            basicprofile.visibility = View.VISIBLE
            layout_farm.visibility = View.VISIBLE
            layout_farmDetails.visibility = View.GONE
            layout_livestock.visibility = View.VISIBLE
            layout_livestockDetails.visibility = View.GONE
            layout_assest.visibility = View.VISIBLE
            layout_assestDetails.visibility = View.GONE
            layout_bank.visibility = View.VISIBLE
            layout_bankDetails.visibility = View.GONE
        })
        household_uparrow.setOnClickListener({
            layout_household.visibility = View.VISIBLE
            layout_householdDetails.visibility = View.GONE
        })

        layout_bank.setOnClickListener({
            layout_bank.visibility = View.GONE
            layout_bankDetails.visibility = View.VISIBLE
            basicprofileDetails.visibility = View.GONE
            basicprofile.visibility = View.VISIBLE
            layout_farm.visibility = View.VISIBLE
            layout_farmDetails.visibility = View.GONE
            layout_livestock.visibility = View.VISIBLE
            layout_livestockDetails.visibility = View.GONE
            layout_assest.visibility = View.VISIBLE
            layout_assestDetails.visibility = View.GONE
            layout_household.visibility = View.VISIBLE
            layout_householdDetails.visibility = View.GONE
        })
        bank_uparrow.setOnClickListener({
            layout_bank.visibility = View.VISIBLE
            layout_bankDetails.visibility = View.GONE
        })
    }

    private fun banklayout() {
        val bankmemberOptions = arrayOf("Ayub Khan | 9986464166", "Jeevitha | 9900765378")
        edt_bank_member.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    bankmemberOptions
            )
        } as SpinnerAdapter
        edt_bank_member.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }
        val accounttypeOptions = arrayOf("")
        edt_account_type.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    accounttypeOptions
            )
        } as SpinnerAdapter
        edt_account_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }
    }

    private fun householdlayout() {
        val householdmemberOptions = arrayOf("Ayub Khan | 9986464166", "Jeevitha | 9900765378")
        edt_household_member.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    householdmemberOptions
            )
        } as SpinnerAdapter
        edt_household_member.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }
    }

    private fun assestslayout() {
        val assestsmemberOptions = arrayOf("Ayub Khan | 9986464166", "Jeevitha | 9900765378")
        edt_assest__member.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    assestsmemberOptions
            )
        } as SpinnerAdapter
        edt_assest__member.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }

        val assettypeOptions = arrayOf("Select type")
        edt_assest__type.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    assettypeOptions
            )
        } as SpinnerAdapter
        edt_assest__type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }

        val assetstatusOptions = arrayOf("Select status")
        edt_assest__status.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    assetstatusOptions
            )
        } as SpinnerAdapter
        edt_assest__status.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                    view: DatePicker, year: Int, monthOfYear: Int,
                    dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInAssestView()
            }
        }


        edt_acquired_on.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                        requireContext(),
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

        })
    }

    private fun livestocklayout() {
        val livestockmemberOptions = arrayOf("Ayub Khan | 9986464166", "Jeevitha | 9900765378")
        edt_livestock__member.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    livestockmemberOptions
            )
        } as SpinnerAdapter
        edt_livestock__member.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        println("erreur")
                    }

                    override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                    ) {
                        val type = parent?.getItemAtPosition(position).toString()
                        println(type)
                    }

                }

        val livestockbreedOptions = arrayOf("Select type")
        edt_livestock__breed.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    livestockbreedOptions
            )
        } as SpinnerAdapter
        edt_livestock__breed.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }

        val livestockcountOptions = arrayOf("2", "3", "4")
        edt_livestock__count.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    livestockcountOptions
            )
        } as SpinnerAdapter
        edt_livestock__count.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }
    }

    private fun farmlayout() {
        val memberOptions = arrayOf("Ayub Khan | 9986464166", "Jeevitha | 9900765378")
        edt_member.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    memberOptions
            )
        } as SpinnerAdapter
        edt_member.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }
        rabi_crops = ArrayList<RabiData>()
        rabi_crops.add(RabiData(R.drawable.wheat_icon, false))
        rabi_crops.add(RabiData(R.drawable.potato_icon, false))
        rabi_crops.add(RabiData(R.drawable.pomo_icon, false))
        rabi_crops.add(RabiData(R.drawable.soyabean_icon, false))
        rabi_crops.add(RabiData(R.drawable.watermelon_icon, false))
        rabi_crops.add(RabiData(R.drawable.sugarcane_icon, false))
        rabi_crops.add(RabiData(R.drawable.paddy_icon, false))
        rabi_crops.add(RabiData(R.drawable.fruits_icon, false))
        rabi_crops.add(RabiData(R.drawable.bananna_icon, false))
        rabi_crops.add(RabiData(R.drawable.groundnut_icon, false))
        rabi_crops.add(RabiData(R.drawable.tomato_icon, false))
        rabi_crops.add(RabiData(R.drawable.vege_icon, false))
        rabi_crops.add(RabiData(R.drawable.wheat_icon, false))
        rabi_crops.add(RabiData(R.drawable.soyabean_icon, false))
        rabi_crops.add(RabiData(R.drawable.paddy_icon, false))
        rabi_crops.add(RabiData(R.drawable.groundnut_icon, false))
        rabi_crops.add(RabiData(R.drawable.potato_icon, false))
        rabi_crops.add(RabiData(R.drawable.watermelon_icon, false))
        rabi_crops.add(RabiData(R.drawable.fruits_icon, false))
        rabi_crops.add(RabiData(R.drawable.tomato_icon, false))
        rabi_crops.add(RabiData(R.drawable.pomo_icon, false))
        rabi_crops.add(RabiData(R.drawable.sugarcane_icon, false))
        rabi_crops.add(RabiData(R.drawable.bananna_icon, false))
        rabi_crops.add(RabiData(R.drawable.vege_icon, false))
        gridlayoutmanager = GridLayoutManager(context, 3, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = gridlayoutmanager
        recyclerView.adapter = RabiAdapter(rabi_crops, this)

        kharfi_crops = ArrayList<KharfiData>()
        kharfi_crops.add(KharfiData(R.drawable.wheat_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.potato_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.pomo_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.soyabean_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.watermelon_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.sugarcane_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.paddy_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.fruits_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.bananna_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.groundnut_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.tomato_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.vege_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.wheat_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.soyabean_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.paddy_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.groundnut_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.potato_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.watermelon_icon, false))
        kharfi_crops.add(KharfiData(R.drawable.fruits_icon, false))

        kharfi_gridlayoutmanager = GridLayoutManager(context, 3, LinearLayoutManager.HORIZONTAL, false)
        kharfirecyclerView.layoutManager = kharfi_gridlayoutmanager
        kharfirecyclerView.adapter = KharfiAdapter(kharfi_crops, this)

        livestock_crops = ArrayList<LivestockData>()
        livestock_crops.add(LivestockData(R.drawable.dairy_icon, false))
        livestock_crops.add(LivestockData(R.drawable.goat_icon, false))
        livestock_crops.add(LivestockData(R.drawable.fish_icon, false))
        livestock_crops.add(LivestockData(R.drawable.poultry_icon, false))
        livestock_gridlayoutmanager = GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false)
        livestockrecyclerView.layoutManager = livestock_gridlayoutmanager
        livestockrecyclerView.adapter = LiveStockAdapter(livestock_crops, this)
    }


    private fun basicprofilelayout() {
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                    view: DatePicker, year: Int, monthOfYear: Int,
                    dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }


        edt_dob.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                        requireContext(),
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

        })
        val genderOptions = arrayOf("Male", "Female")
        edt_gender.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    genderOptions
            )
        } as SpinnerAdapter
        edt_gender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }

        val smartPhoneOptions = arrayOf("Yes", "No")
        edt_smartphone.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    smartPhoneOptions
            )
        } as SpinnerAdapter
        edt_smartphone.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }

        edt_choosefile.isEnabled = false
        edt_uploadaddress.isEnabled = false
        edt_uploadidentity.isEnabled = false
    }

    private fun updateDateInView() {
        val myFormat = "d MMM, yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        edt_dob.setText(sdf.format(cal.time))
    }

    private fun updateDateInAssestView() {
        val myFormat = "MM/dd/yy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        edt_acquired_on.setText(sdf.format(cal.time))
    }

    override fun onFarmItemClick(position: Int) {
        val clickItem = kharfi_crops[position]
        farmnewvalue = position

        if (!clickItem.isSelected) {
            clickItem.isSelected = true
            kharfirecyclerView.adapter?.notifyItemChanged(position)
            if (farmoldvalue != -1 && farmoldvalue != farmnewvalue) {
                val clickitemOld = kharfi_crops[farmoldvalue]
                clickitemOld.isSelected = false
                kharfirecyclerView.adapter?.notifyItemChanged(farmoldvalue)
            }
            farmoldvalue = farmnewvalue
        } else {
            clickItem.isSelected = false
            kharfirecyclerView.adapter?.notifyItemChanged(position)
        }
    }

    override fun onItemClick(position: Int) {
        val clickItem = rabi_crops[position]
        newvalue = position

        if (!clickItem.isSelected) {
            clickItem.isSelected = true
            recyclerView.adapter?.notifyItemChanged(position)
            if (oldvalue != -1 && oldvalue != newvalue) {
                val clickitemOld = rabi_crops[oldvalue]
                clickitemOld.isSelected = false
                recyclerView.adapter?.notifyItemChanged(oldvalue)
            }
            oldvalue = newvalue
        } else {
            clickItem.isSelected = false
            recyclerView.adapter?.notifyItemChanged(position)
        }
    }

    override fun onLivestockItemClick(position: Int) {

        val clickItem = livestock_crops[position]
        livestocknewvalue = position

        if (!clickItem.isSelected) {
            clickItem.isSelected = true
            livestockrecyclerView.adapter?.notifyItemChanged(position)
            if (liveoldvalue != -1 && liveoldvalue != livestocknewvalue) {
                val clickitemOld = livestock_crops[liveoldvalue]
                clickitemOld.isSelected = false
                livestockrecyclerView.adapter?.notifyItemChanged(liveoldvalue)
            }
            liveoldvalue = livestocknewvalue
        } else {
            clickItem.isSelected = false
            livestockrecyclerView.adapter?.notifyItemChanged(position)
        }
    }


}
