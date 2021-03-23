package com.weightwatchers.ww_exercise_02.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.weightwatchers.ww_exercise_02.model.Contact
import java.util.ArrayList

class ContactAdapter : RecyclerView.Adapter<ContactViewHolder>() {
    val contactList: MutableList<Contact> = ArrayList()

    /** TODO:
     * - add onClick functionality that brings user to dialer
     * - add onLongClick functionality that brings user to edit screen
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return null
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList.get(position)
        holder.bind(contact)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun add(contact: Contact) {
        contactList.add(contact)
        notifyDataSetChanged()
    }

    fun remove(contact: Contact) {
        contactList.remove(contact)
    }
}