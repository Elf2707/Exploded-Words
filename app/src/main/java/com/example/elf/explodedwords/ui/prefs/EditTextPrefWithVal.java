package com.example.elf.explodedwords.ui.prefs;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.elf.explodedwords.R;

/**
 * Created by Elf on 07.08.2015.
 */
public class EditTextPrefWithVal extends EditTextPreference {
    TextView textValue;

    public EditTextPrefWithVal(Context context) {
        super(context);
        setLayoutResource(R.layout.edittextpref_with_value);
    }

    public EditTextPrefWithVal(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutResource(R.layout.edittextpref_with_value);
    }

    public EditTextPrefWithVal(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayoutResource(R.layout.edittextpref_with_value);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        textValue = (TextView) view.findViewById(R.id.preference_value);
        if (textValue != null) {
            textValue.setText(getText());
        }
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        if (textValue != null) {
            textValue.setText(getText());
        }
    }
}
