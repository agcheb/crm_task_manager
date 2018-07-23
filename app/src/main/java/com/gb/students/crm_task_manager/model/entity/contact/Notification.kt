package com.gb.students.crm_task_manager.model.entity.contact

import java.util.*

class Notification {
    var label: String? = null
    var date: Date? = null
    val creationDate = Date()
    var isRepeatable: Boolean? = null
    var repeatInterval: Date? = null

}