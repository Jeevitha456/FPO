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
import com.example.fpo.R
import java.text.SimpleDateFormat
import java.util.*


class AddMemberFragment : Fragment() {
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var linearLayout: LinearLayout
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var rootview = inflater.inflate(R.layout.fragment_add_member, container, false)
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

        setEventHandler()
        return rootview
    }

    private fun setEventHandler() {
        basicprofile.setOnClickListener({
            basicprofile.visibility = View.GONE
            basicprofileDetails.visibility = View.VISIBLE
        })
        basicprofile_uparrow.setOnClickListener({
            basicprofile.visibility = View.VISIBLE
            basicprofileDetails.visibility = View.GONE
        })
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


        edt_dob!!.setOnClickListener(object : View.OnClickListener {
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
        edt_gender?.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                it,
                R.layout.support_simple_spinner_dropdown_item,
                genderOptions
            )
        } as SpinnerAdapter
        edt_gender?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        edt_smartphone?.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                it,
                R.layout.support_simple_spinner_dropdown_item,
                smartPhoneOptions
            )
        } as SpinnerAdapter
        edt_smartphone?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        edt_dob!!.setText(sdf.format(cal.time))
    }

}
