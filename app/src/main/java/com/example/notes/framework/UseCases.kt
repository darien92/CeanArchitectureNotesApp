package com.example.notes.framework

import com.example.lib.usecases.*

data class UseCases(
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount
)