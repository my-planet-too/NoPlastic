package org.myplanettoo.noplastic.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.myplanettoo.noplastic.R;
import org.myplanettoo.noplastic.database.entity.Item;
import org.myplanettoo.noplastic.repository.ItemRepository;
import org.myplanettoo.noplastic.ui.viewmodel.MainActivityViewModel;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    TextView textViewCount;

    @Bean
    ItemRepository itemRepository;

    MainActivityViewModel viewModel;

    @AfterViews
    void afterViews() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.setItemRepository(itemRepository);
        viewModel.getCountInfo().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long count) {
                textViewCount.setText(String.valueOf(100 - count));
            }
        });
    }

    @Click(R.id.imageViewOtherPlastic)
    void onClickOtherPlastic() {
        insertItem(Item.TypePlastic.OTHER_PLASTIC);
    }

    @Click(R.id.imageViewBottle)
    void onClickBottle() {
        insertItem(Item.TypePlastic.BOTTLE);
    }

    @Click(R.id.imageViewPlasticBag)
    void onClickPlasticBag() {
        insertItem(Item.TypePlastic.PLASTIC_BAG);
    }

    private void insertItem(long plasticBag) {
        Item item = Item.create(plasticBag);
        itemRepository.insert(item);
    }
}

