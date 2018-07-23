package com.gb.students.crm_task_manager.model.entity

import com.gb.students.crm_task_manager.model.entity.contact.Contact
import java.util.*

class Task {
    var title: String? = null
    var complete: Boolean? = false
    var creationDate: Date? = null
    var expDate: Date? = null

    var type: String? = null
    private var subtasks: MutableList<Subtask> = ArrayList()
    var contacts: List<Contact>? = null

    var note: String? = null

    fun addSubtask(subtaskName: String) {
        subtasks.add(Subtask(subtaskName))
    }

    fun getSubtasks(): List<Subtask> {
        return subtasks
    }

    fun setSubtasks(subtasks: MutableList<Subtask>) {
        this.subtasks = subtasks
    }
}