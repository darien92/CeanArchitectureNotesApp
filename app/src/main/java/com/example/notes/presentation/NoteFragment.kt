package com.example.notes.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.lib.data.Note
import com.example.notes.R
import com.example.notes.framework.viewmodels.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.coroutineScope

class NoteFragment : Fragment() {
    private var noteId: Long = 0L
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)
    private lateinit var fabConfirmAdd: FloatingActionButton
    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        //getting the NoteId
        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        fabConfirmAdd = view.findViewById(R.id.fab_confirm_add_note)
        etTitle = view.findViewById(R.id.et_note_title)
        etContent = view.findViewById(R.id.et_note_content)
        if (noteId != 0L){
            viewModel.getCurrentNote(noteId)
        }

        fabConfirmAdd.setOnClickListener {
            if (!etTitle.equals("") || !etContent.equals("")){
                val time = System.currentTimeMillis()
                currentNote.title = etTitle.text.toString()
                currentNote.content = etContent.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L){ // new note
                    currentNote.creationTime = time
                }
                viewModel.saveNote(currentNote)
            }else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.isSaved().observe(viewLifecycleOwner, {
            if(it){
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(etTitle).popBackStack()
            }else{
                Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.currentNote.observe(viewLifecycleOwner, { note ->
            note?.let { //aqui entra si note no es null
                currentNote = it
                etTitle.setText(it.title, TextView.BufferType.EDITABLE)
                etContent.setText(it.content, TextView.BufferType.EDITABLE)
            }

        })
    }

    private fun hideKeyboard(){
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(etTitle.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.delete_note -> {
                if(context != null && noteId != 0L){
                    AlertDialog.Builder(requireContext())
                        .setTitle("Delete note")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes"){ _, _ ->
                            viewModel.deleteNote(currentNote)
                        }
                        .setNegativeButton("Cancel"){ dialogInterface, _ ->
                            dialogInterface.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }
        return true
    }

}