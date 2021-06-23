package com.example.notes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.framework.viewmodels.ListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    private val notesListAdapter = NotesListAdapter(arrayListOf())

    private lateinit var fabAddNote: FloatingActionButton
    private lateinit var viewModel: ListViewModel
    private lateinit var rvListNotes: RecyclerView
    private lateinit var pbLoading: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvListNotes = view.findViewById(R.id.rv_notes_list)
        pbLoading = view.findViewById(R.id.pb_notes_list)
        rvListNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }

        fabAddNote = view.findViewById(R.id.fab_add_note)
        fabAddNote.setOnClickListener {
            goToNoteDetails(0)
        }

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNote()
    }

    private fun observeViewModel() {
        viewModel.notes.observe(viewLifecycleOwner, { notes ->
            pbLoading.visibility = View.GONE
            notesListAdapter.updateNodes(notes.sortedByDescending { it.updateTime })
        })
    }

    private fun goToNoteDetails(id:Long = 0L){
        val action: NavDirections = ListFragmentDirections.actionListFragmentToNoteFragment(id)
        Navigation.findNavController(requireView()).navigate(action)
    }
}