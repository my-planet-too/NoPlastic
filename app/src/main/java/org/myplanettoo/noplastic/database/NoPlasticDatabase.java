package org.myplanettoo.noplastic.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import org.myplanettoo.noplastic.database.converter.DateConverter;
import org.myplanettoo.noplastic.database.dao.ItemDao;
import org.myplanettoo.noplastic.database.entity.Item;

@Database(entities = {
        Item.class},
        version = 1)
@TypeConverters(DateConverter.class)
public abstract class NoPlasticDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "no_plastic_db";
    private static NoPlasticDatabase instance;

    public synchronized static NoPlasticDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(
                            context.getApplicationContext(),
                            NoPlasticDatabase.class,
                            DATABASE_NAME
                    ).build();
        }

        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    public abstract ItemDao itemDao();
}
