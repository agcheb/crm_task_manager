package com.gb.students.crm_task_manager.model.entity.types;

import java.util.List;

public class AbstractTypes {
    private List<String> types;

    public List<String> getAll() {
        return types;
    }

    public void addType(String type) {
        types.add(type);
    }

    public void removeType(String remTtype) {
        for (String type : types) {
            if (type.equals(remTtype)) {
                types.remove(type);
            }
        }
    }
}
