package org.myplanettoo.noplastic.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.myplanettoo.noplastic.database.dao.ItemDao;
import org.myplanettoo.noplastic.database.entity.Item;

@EBean
public class ItemRepository extends Repository {
    private ItemDao itemDao;

    @AfterInject
    void initUserRepository() {
        itemDao = db.itemDao();
    }

    public LiveData<Long> getCountInfo() {
        MediatorLiveData<Long> liveData = new MediatorLiveData<>();
        loadCountInfo(liveData);

        return liveData;
    }

    @Background
    void loadCountInfo(final MediatorLiveData<Long> liveData) {
        liveData.addSource(itemDao.getCountInfo(), new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long userDetail) {
                liveData.setValue(userDetail);
            }
        });
    }

    @Background
    public void insert(Item item) {
        itemDao.insert(item);
    }
}
