package com.gb.students.crm_task_manager.model.cache.paper;

import com.gb.students.crm_task_manager.model.entity.types.Types;
import com.gb.students.crm_task_manager.model.repos.TypesRepo;

import io.paperdb.Paper;
import io.reactivex.Observable;
import timber.log.Timber;

public class PaperTypesRepo implements TypesRepo{
    @Override
    public Observable<Types> loadTypes() {
        return Observable.fromCallable(() -> {
          Types types =readFromPaper();
            Timber.d("Types loaded from memory");
            return types;
        });
    }

    @Override
    public Observable<Boolean> saveTypes(Types type) {
        return Observable.fromCallable(() -> {
            Paper.book("types").write("all", type);
            return true;
        });

    }

    @Override
    public Observable<Boolean> clearTypes() {
        return Observable.fromCallable(() -> {
            Paper.book("types").destroy();
            return true;
        });
    }

    private Types readFromPaper(){
        Types types = Paper.book("types").read("all");
        if (types == null) {
            types = new Types();
        }
        return types;
    }
}
