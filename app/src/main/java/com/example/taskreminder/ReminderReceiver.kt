package com.example.taskreminder

import DatabaseHelper
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title")
        Toast.makeText(context, "com.example.taskreminder.Reminder: $title", Toast.LENGTH_SHORT).show()

        // Delete the reminder after sending the notification
        val databaseHelper = DatabaseHelper(context)
        val reminderList = databaseHelper.getReminders()
        val reminder = reminderList.find { it.title == title }
        if (reminder != null) {
            databaseHelper.deleteReminder(reminder.id)
        }
    }
}
