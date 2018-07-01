package com.gb.students.crm_task_manager.model.repos;


import com.gb.students.crm_task_manager.model.entity.types.Types;

import io.reactivex.Observable;

public interface TypesRepo {
    Observable<Types> loadTypes();
    Observable<Boolean> saveTypes(Types type);
    Observable<Boolean> clearTypes();

}
