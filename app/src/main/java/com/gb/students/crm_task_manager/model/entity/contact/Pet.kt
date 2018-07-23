package com.gb.students.crm_task_manager.model.entity.contact


class Pet {
    var type: String? = null
    var name: String? = null
    var note: String? = null
    override fun toString(): String {
        return "$type $name\n$note"

    }
}
