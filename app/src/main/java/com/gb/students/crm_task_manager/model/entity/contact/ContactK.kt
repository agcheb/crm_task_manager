package com.gb.students.crm_task_manager.model.entity.contact

import com.gb.students.crm_task_manager.model.entity.Task
import java.util.*

class ContactK(var name: String?) {
    var birth: Date? = null
    var relations: List<Relation>? = mutableListOf()
    var pets: List<Pet> = mutableListOf()
    var contactInfos: List<ContactInfo> = mutableListOf()
    var adresses: List<Address> = mutableListOf()
    var phone: String? = null
    var mail: String? = null

    var tasks: List<Task> = mutableListOf()
    var aqt: Acquaintance? = null
    var work: Work? = null
    var leisures: List<Leisure>? = mutableListOf()
    var notifications: List<Notification>? = mutableListOf()
    var gifts: List<Gift>? = null
    var imagePath: String? = null
    var credits: List<Credit>? = mutableListOf()

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val contact = o as ContactK?
        return o.name == this.name
    }

}
