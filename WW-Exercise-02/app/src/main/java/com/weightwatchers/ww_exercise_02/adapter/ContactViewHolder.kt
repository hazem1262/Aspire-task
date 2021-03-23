package com.weightwatchers.ww_exercise_02.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.weightwatchers.ww_exercise_02.R
import com.weightwatchers.ww_exercise_02.model.Contact

/**
 * A view holder that represent the contact. 3 fields are:
 * - contactNumberView = the contact's phoneNumber in the list
 * - contactNameView = the contact's name
 * - phoneNumberView = the contact's phone phoneNumber
 */
class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var contactNumberView: TextView
    var contactNameView: TextView
    var phoneNumberView: TextView

    init {
        contactNumberView = itemView.findViewById(R.id.contact_number) as TextView
        contactNameView = itemView.findViewById(R.id.contact_name) as TextView
        phoneNumberView = itemView.findViewById(R.id.contact_phone_number) as TextView
    }

    fun bind(contact: Contact) {
        //TODO Bind contact with views
    }
}