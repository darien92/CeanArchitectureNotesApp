package com.example.notes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lib.data.Note
import com.example.notes.R
import java.sql.Date
import java.text.SimpleDateFormat

class NotesListAdapter (var  notes: ArrayList<Note>): RecyclerView.Adapter<NotesListAdapter.NoteViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
    )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int)  = holder.bind(notes[position])

    override fun getItemCount(): Int = notes.size


    fun updateNodes(newNotes: List<Note>){
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }
    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val layout: CardView = view.findViewById(R.id.note_layout)
        private val title: TextView = view.findViewById(R.id.card_title)
        private val content: TextView = view.findViewById(R.id.card_content)
        private val modifiedDate: TextView = view.findViewById(R.id.card_date)

        fun bind(note: Note){
            title.text = note.title
            content.text = note.content
            val sdf = SimpleDateFormat("MMM dd HH:mm:ss")
            val resultDate = Date(note.updateTime)
            modifiedDate.text = "Last Updated: ${sdf.format(resultDate)}"
        }
    }
}