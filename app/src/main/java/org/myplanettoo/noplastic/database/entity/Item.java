package org.myplanettoo.noplastic.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity
public class Item {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    public Date time;

    @NonNull
    public Long typePlastic;

    public static Item create(Long typePlastic) {
        Item item = new Item();
        item.time = new Date();
        item.typePlastic = typePlastic;

        return item;
    }

    public static class TypePlastic {
        public static final long BOTTLE = 1;
        public static final long PLASTIC_BAG = 2;
        public static final long OTHER_PLASTIC = 3;
    }
}
