package com.gb.students.crm_task_manager.contacts.profile;

import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.entity.types.Types;

import java.util.List;


public interface ProfileActivityHelper {
    List<Task> getTask();
    Types getTypes();

    void saveTypes( Types types);
    void saveTasks( List<Task> tasks);
}
