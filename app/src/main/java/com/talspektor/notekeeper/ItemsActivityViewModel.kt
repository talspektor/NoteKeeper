package com.talspektor.notekeeper

import android.arch.lifecycle.ViewModel
import android.os.Bundle

class ItemsActivityViewModel : ViewModel() {
    var isNewlyCreated = true
    var navDrawerDisplaySelectionName =
        "com.talspektor.notekeeper.ItemsActivityViewModel.navDrawerDisplaySelection"

    var recentlyViewedNoteIdName =
        "com.talspektor.notekeeper.ItemsActivityViewModel.recentlyViewedNoteIds"

    var navDrawerDisplaySelection = R.id.nav_notes

    private  val naxRecentlyViewedNotes = 5
    val recentlyViewedNotes = ArrayList<NoteInfo>(naxRecentlyViewedNotes)

    fun addToRecentlyViewedNotes(note: NoteInfo) {
        // Check if selection is already in the list
        val existingIndex = recentlyViewedNotes.indexOf(note)
        if (existingIndex == -1) {
            // it isn't in the list
            // Add new one to beginning of the list and remove any beyond max we want to keep
            recentlyViewedNotes.add(0, note)
            for (index in recentlyViewedNotes.lastIndex downTo naxRecentlyViewedNotes)
                recentlyViewedNotes.removeAt(index)
        } else {
            //it is in the list...
            // Shift the ones above dowd the list and make it first member of the list
            for (index in (existingIndex - 1) downTo 0)
                recentlyViewedNotes[index + 1] = recentlyViewedNotes[index]
            recentlyViewedNotes[0] = note
        }
    }

    fun saveState(outState: Bundle) {
        outState.putInt(navDrawerDisplaySelectionName, navDrawerDisplaySelection)
//        val noteIds = DataManager.noteIdsAsArray(recentlyViewedNotes)
//        recentlyViewedNotesoutState.putIntegerArray(recentlyViewedNoteIdName, noteIds)
    }

    fun restoreStat(savedInstanceState: Bundle) {
        navDrawerDisplaySelection = savedInstanceState.getInt(navDrawerDisplaySelectionName)
        val noteIds = savedInstanceState.getIntArray(recentlyViewedNoteIdName)
        val noteList = DataManager.loadNotes(*noteIds)// * = spred operator
        recentlyViewedNotes.addAll(noteList)
    }
}