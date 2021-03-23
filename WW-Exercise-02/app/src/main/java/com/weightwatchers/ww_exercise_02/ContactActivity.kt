package com.weightwatchers.ww_exercise_02

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.BaseTransientBottomBar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.weightwatchers.ww_exercise_02.model.Contact

class ContactActivity : AppCompatActivity() {
    private var rootView: CoordinatorLayout? = null
    private var nameEditText: EditText? = null
    private var numberEditText: EditText? = null

    //TODO: Implement editing functionality

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_contact)

        setupViews()
    }

    private fun setupViews() {
        rootView = findViewById<View>(R.id.coordinator_layout) as CoordinatorLayout

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        nameEditText = findViewById<View>(R.id.name_edit_text) as EditText
        numberEditText = findViewById<View>(R.id.number_edit_text) as EditText

        val saveButton = findViewById<View>(R.id.save_button) as Button
        saveButton.setOnClickListener { saveContact() }
    }

    private fun saveContact() {
        var contactName = ""
        var contactNumber = ""

        //TODO build contact and validate
        if (nameEditText!!.text != null && numberEditText!!.text != null) {
            contactName = nameEditText!!.text.toString()
            contactNumber = numberEditText!!.text.toString()
        }

        if (!contactName.isEmpty() && !contactNumber.isEmpty() && Contact.isValidNumber(contactNumber)) {
            //TODO send back to previous screen
            val contact = Contact(contactName, contactNumber)

            val data = Intent()
            data.putExtra("Contact", contact)
            setResult(101, data)

        } else {
            Snackbar.make(rootView!!, "Unable to save contact. Please check all fields", BaseTransientBottomBar.LENGTH_LONG)
                .show()
        }
    }
}