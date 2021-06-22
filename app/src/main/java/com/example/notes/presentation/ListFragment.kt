package com.example.notes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.notes.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    private lateinit var fabAddNote: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabAddNote = view.findViewById(R.id.fab_add_note)
        fabAddNote.setOnClickListener {
            goToNoteDetails(0)
        }
    }

    private fun goToNoteDetails(id:Long = 0L){
        val action: NavDirections = ListFragmentDirections.actionListFragmentToNoteFragment(id)
        Navigation.findNavController(requireView()).navigate(action)
    }
}