package com.example.taskreminder

import DatabaseHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.widget.Toast
import com.example.taskreminder.R
import com.example.taskreminder.Reminder
import com.example.taskreminder.ReminderAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddReminder: FloatingActionButton
    private lateinit var reminderAdapter: ReminderAdapter
    private lateinit var reminderList: ArrayList<Reminder>
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        reminderList = ArrayList()
        reminderAdapter = ReminderAdapter(reminderList)
        recyclerView.adapter = reminderAdapter

        databaseHelper = DatabaseHelper(this)
        loadRemindersFromDatabase()

        fabAddReminder = findViewById(R.id.fabAddReminder)
        fabAddReminder.setOnClickListener {
            // Handle FAB click to add a new reminder
            // Show a dialog or start a new activity to add a reminder
            Toast.makeText(this, "Add com.example.taskreminder.Reminder clicked", Toast.LENGTH_SHORT).show()
            addReminderToDatabase("New com.example.taskreminder.Reminder", "2023-05-01 12:00 PM")
        }
    }

    private fun loadRemindersFromDatabase() {
        val reminders = databaseHelper.getReminders()
        reminderList.clear()
        reminderList.addAll(reminders)
        reminderAdapter.notifyDataSetChanged()
    }

    private fun addReminderToDatabase(title: String, dateTime: String) {
        val id = databaseHelper.addReminder(title, dateTime)
        val reminder = Reminder(id, title, dateTime)
        reminderList.add(reminder)
        reminderAdapter.notifyDataSetChanged()
    }

    fun deleteReminderFromDatabase(reminder: Reminder) {
        val rowsDeleted = databaseHelper.deleteReminder(reminder.id)
        if (rowsDeleted > 0) {
            reminderList.remove(reminder)
            reminderAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseHelper.close()
    }
}

private operator fun Unit.compareTo(i: Int): Int {

return i}
