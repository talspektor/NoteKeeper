package com.talspektor.notekeeper

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import android.widget.LinearLayout

import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val activityIntent = Intent(this, NoteActivity::class.java)
            startActivity(activityIntent)
        }

        listItems.layoutManager = LinearLayoutManager(this)// Layout for recyclerView

        listItems.adapter = NoteRecyclerAdapter(this, DataManager.notes) // Set the recycler adapter
    }
    // Update the list using the adapter
    override fun onResume() {
        super.onResume()
        listItems.adapter?.notifyDataSetChanged() // Refresh the recycler data
    }

}
