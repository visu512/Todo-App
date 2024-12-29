package com.hindi.todo_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hindi.todo_app.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), NoteRvAdapter.NoteClickInterface, NoteRvAdapter.NoteClickDeleteInterface {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: NoteViewModel by viewModels()
    private lateinit var noteRvAdapter: NoteRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize adapter with an empty list
        noteRvAdapter = NoteRvAdapter(this, mutableListOf(), this, this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = noteRvAdapter
        }

        // Observe LiveData from ViewModel
        viewModel.allNotes.observe(this) { notes ->
            // Update adapter's list when data changes
            noteRvAdapter.updateList(notes)

            // Control visibility of RecyclerView and TextView
            if (notes.isEmpty()) {
                binding.recyclerView.visibility = View.GONE
                binding.emptyTextView.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.emptyTextView.visibility = View.GONE
            }
        }

        // Handle Add Task button click
        binding.AddTaskBtn.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("noteType", "Add")
            startActivity(intent)
        }
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this, AddActivity::class.java).apply {
            putExtra("noteType", "Edit")
            putExtra("noteTitle", note.notesTitle)
            putExtra("noteDescription", note.description)
            putExtra("noteID", note.id)
        }
        startActivity(intent)
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
    }
}
