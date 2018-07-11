package com.gb.students.crm_task_manager.model.entity.contact

import com.gb.students.crm_task_manager.custom.StringHelper
import java.util.*

class Relation {
    var type: String? = null
    var name: String? = null
    var birth: Date? = null
    var note: String? = null

    override fun toString(): String {
        return "$name, $type, ${StringHelper.getDateFormat(StringHelper.Pattern.DOT_NUMERIC).format(birth)}\n$note"
    }
}