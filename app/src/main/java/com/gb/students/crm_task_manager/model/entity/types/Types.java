package com.gb.students.crm_task_manager.model.entity.types;

public class Types {
    private ContactTypes contactTypes;
    private LeisureTypes leisureTypes;
    private PetTypes petTypes;
    private RelationTypes relationTypes;
    private TaskTypes taskTypes;
    private WorkField workField;

    public Types() {
        this.contactTypes = new ContactTypes();
        this.leisureTypes = new LeisureTypes();
        this.petTypes = new PetTypes();
        this.relationTypes = new RelationTypes();
        this.taskTypes = new TaskTypes();
        this.workField = new WorkField();
    }

    public ContactTypes getContactTypes() {
        return contactTypes;
    }

    public LeisureTypes getLeisureTypes() {
        return leisureTypes;
    }

    public PetTypes getPetTypes() {
        return petTypes;
    }

    public RelationTypes getRelationTypes() {
        return relationTypes;
    }

    public TaskTypes getTaskTypes() {
        return taskTypes;
    }

    public WorkField getWorkField() {
        return workField;
    }

}
