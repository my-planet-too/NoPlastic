package org.myplanettoo.noplastic.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import org.myplanettoo.noplastic.repository.ItemRepository;

public class MainActivityViewModel extends ViewModel {
    private LiveData<Long> countInfo;
    private ItemRepository itemRepository;

    public MainActivityViewModel() {
        countInfo = null;
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        countInfo = itemRepository.getCountInfo();
    }

    public LiveData<Long> getCountInfo() {
        return countInfo;
    }
}
