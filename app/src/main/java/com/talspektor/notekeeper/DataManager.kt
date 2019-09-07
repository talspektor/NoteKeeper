package com.talspektor.notekeeper

import java.util.HashMap

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    fun loadNotes(vararg noteIds: Int): List<NoteInfo> {
//        simulateLoadDelay()
        val noteList: List<NoteInfo>

        if(noteIds.isEmpty())
            noteList = notes
        else {
            noteList = ArrayList(noteIds.size)
            for(noteId in noteIds)
                noteList.add(notes[noteId])
        }
        return noteList
    }

    fun loadNote(noteId: Int) = notes[noteId]

    fun isLastNoteId(noteId: Int) = noteId == notes.lastIndex

    fun addNote(course: CourseInfo, noteTitle: String, noteText: String): Int {
        val note = NoteInfo(course, noteTitle, noteText)
        notes.add(note)
        return notes.lastIndex
    }

    fun findNote(course: CourseInfo, noteTitle: String, noteText: String) : NoteInfo? {
        for (note in notes) {
            if (course == note.course &&
                    noteText == note.text &&
                    noteTitle == note.title)
                return note
        }
        return null
    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Programming with intents")
        courses.set(course.courseId, course)

        course = CourseInfo("android_async", "Android Async Programming and Services")
        courses.set(course.courseId, course)

        course = CourseInfo(title = "JAVA Fundementals: The Java Language", courseId = "Java_lang")
        courses.set(course.courseId, course)

        course = CourseInfo("java_core", "Java Fundementals: The Core Platform")
        courses.set(course.courseId, course)
    }

    fun initializeNotes() {
        var note = NoteInfo(title = "Android Programming with intents", text = "Android Programming with intents", course = CourseInfo(courseId = "android_intents", title = "Android Programming with intents") )
        notes.add(note)

        note = NoteInfo(title = "Android Programming with intents", text = "Android Programming with intents", course = CourseInfo(courseId = "android_intents", title = "Android Programming with intents") )
        notes.add(note)

        note = NoteInfo(title = "JAVA Fundementals: The Java Language", text = "JAVA Fundementals: The Java Language", course = CourseInfo(courseId = "android_intents", title = "Android Programming with intents") )
        notes.add(note)

        note = NoteInfo(title = "Java Fundementals: The Core Platform", text = "Java Fundementals: The Core Platform", course = CourseInfo(courseId = "android_intents", title = "Android Programming with intents") )
        notes.add(note)

        note = NoteInfo(title = "Android Programming with intents", text = "Android Programming with intents", course = CourseInfo(courseId = "android_intents", title = "Android Programming with intents") )
        notes.add(note)

        note = NoteInfo(title = "JAVA Fundementals: The Java Language", text = "JAVA Fundementals: The Java Language", course = CourseInfo(courseId = "android_intents", title = "Android Programming with intents") )
        notes.add(note)

        note = NoteInfo(title = "Java Fundementals: The Core Platform", text = "Java Fundementals: The Core Platform", course = CourseInfo(courseId = "android_intents", title = "Android Programming with intents") )
        notes.add(note)
    }
}