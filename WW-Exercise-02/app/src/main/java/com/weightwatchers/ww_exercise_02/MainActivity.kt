package com.weightwatchers.ww_exercise_02

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.view.View
import com.weightwatchers.ww_exercise_02.adapter.ContactAdapter
import com.weightwatchers.ww_exercise_02.model.Contact

class MainActivity : AppCompatActivity() {
    private var contactAdapter: ContactAdapter? = null

    //TODO: Fix & cleanup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupViews()
    }

    override fun onStart() {
        super.onStart()

        contactAdapter = ContactAdapter()

        addFakeContact()
    }

    private fun setupViews() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView.adapter = contactAdapter

        val addContactButton = findViewById<View>(R.id.add_contact) as FloatingActionButton
        addContactButton.setOnClickListener {
            // TODO Send user to ContactActivity
        }
    }

    private fun addFakeContact() {
        val contact = Contact("Average Joe", "800-123-5542")

        contactAdapter!!.add(contact)
    }
}