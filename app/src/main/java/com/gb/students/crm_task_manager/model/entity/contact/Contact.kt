package com.gb.students.crm_task_manager.model.entity.contact

import com.gb.students.crm_task_manager.model.entity.Task
import java.util.*

class Contact(var name: String?) {
    var birth: Date? = null
    var relations: List<Relation>? = null
    var pets: List<Pet>? = null
    var contactInfos: List<ContactInfo>? = null
    var adresses: List<Address>? = null


    val tasks: List<Task>? = null
    var aqt: Acquaintance? = null
    var work: Work? = null
    var leisures: List<Leisure>? = null
    var notifications: List<Notification>? = null
    var gifts: List<Gift>? = null

    var credits: List<Credit>? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val contact = o as Contact?
        return o.name == this.name
    }

}
