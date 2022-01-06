package com.example.noteappvm

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.util.*


class AddEditNoteActivity : AppCompatActivity() {
    lateinit var noteTitleET : EditText
    lateinit var noteDesET : EditText
    lateinit var updateAddBTN : Button
    lateinit var viewModel : NoteViewModel
    var noteID = -1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        noteTitleET = findViewById(R.id.idETNoteTitle)
        noteDesET = findViewById(R.id.idETNoteDes)
        updateAddBTN = findViewById(R.id.idBtnUpdate)
        viewModel = ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("")
            val noteDes = intent.getStringExtra("")
            noteID = intent.getIntExtra("", -1)
            updateAddBTN.setText("Update Note")
            noteTitleET.setText(noteTitle)
            noteDesET.setText(noteDes)
        }else {
            updateAddBTN.setText("Save Note")
        }
        updateAddBTN.setOnClickListener {
            val noteTitle = noteTitleET.text.toString()
            val noteDes = noteDesET.text.toString()
            if (noteType.equals("Edit")){
                if (noteTitle.isNotEmpty() && noteDes.isNotEmpty()){
//                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentData:String = "dd,MM,yyy"//sdf.format(Date())
                    val updateNote = Note(noteTitle,noteDes , currentData)
                    updateNote.id = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this , "Note Updated ..", Toast.LENGTH_SHORT).show()
                } else {
                    if (noteTitle.isNotEmpty() && noteDes.isNotEmpty()){
//                        val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
//                        val currentDate: String = sdf.format(Date())
//                        viewModel.addNote(Note(noteTitle,noteDes,currentDate))
                        Toast.makeText(this , "Note Added ..", Toast.LENGTH_SHORT).show()
                    }
                }
                startActivity(Intent(applicationContext, MainActivity::class.java))
                this.finish()
            }

        }
    }
}