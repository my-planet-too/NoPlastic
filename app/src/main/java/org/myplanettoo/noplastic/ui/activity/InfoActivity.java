package org.myplanettoo.noplastic.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.myplanettoo.noplastic.R;

@EActivity(R.layout.activity_info)
public class InfoActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_INFO_TEXT = "EXTRA_INFO_TEXT";

    @Extra(EXTRA_TITLE)
    String title;

    @Extra(EXTRA_INFO_TEXT)
    String infoText;

    @ViewById
    TextView textViewTitle;

    @ViewById
    TextView textViewInfoText;

    @AfterViews
    void afterViews() {
        textViewTitle.setText(title);
        textViewInfoText.setText(infoText);
    }
}

