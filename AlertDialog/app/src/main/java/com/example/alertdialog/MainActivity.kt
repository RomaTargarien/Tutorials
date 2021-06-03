package com.example.alertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addContactDialog = AlertDialog.Builder(this)
                .setTitle("Add contact")
                .setMessage("Do you want mister Poop to your contact list")
                .setIcon(R.drawable.ic_add_contact)
                .setPositiveButton("YES") { _, _ ->
                    Toast.makeText(this, "You added mister Poop", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("NO") { _, _ ->
                    Toast.makeText(this, "You didn't added mister Poop", Toast.LENGTH_SHORT).show()
                }.create()
        bnDialog1.setOnClickListener {
            addContactDialog.show()
        }

        val options = arrayOf("First item", "Second item", "Third item")
        val singleChoiceDialog = AlertDialog.Builder(this)
                .setSingleChoiceItems(options, 0) { dialog, i ->
                    Toast.makeText(this, "You clicked on ${options[i]}", Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("Accept") { _, _ ->
                    Toast.makeText(this, "Yoy accepted", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Decline") { _, _ ->
                    Toast.makeText(this, "You declined", Toast.LENGTH_SHORT).show()
                }.create()
        bnDialog2.setOnClickListener {
            singleChoiceDialog.show()
        }

        val multiChoiceDialog = AlertDialog.Builder(this)
                .setMultiChoiceItems(options, booleanArrayOf(false,false,false)) {
                    _, which, isChecked ->
                    if (isChecked){
                        Toast.makeText(this,"You checked ${options[which]}",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"You unchecked ${options[which]}",Toast.LENGTH_SHORT).show()
                    }
                }
                .setPositiveButton("Accept") { _, _ ->
                    Toast.makeText(this, "Yoy accepted multi", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Decline") { _, _ ->
                    Toast.makeText(this, "You declined multi", Toast.LENGTH_SHORT).show()
                }.create()

        bnDialog3.setOnClickListener {
            multiChoiceDialog.show()
        }
    }
}