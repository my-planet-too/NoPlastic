package org.myplanettoo.noplastic.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import org.myplanettoo.noplastic.database.entity.Item;

@Dao
public interface ItemDao {
    @Insert
    Long insert(Item item);

    @Query("SELECT COUNT(*) FROM item;")
    LiveData<Long> getCountInfo();
}
