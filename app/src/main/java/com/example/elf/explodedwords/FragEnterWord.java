package com.example.elf.explodedwords;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FragEnterWord extends Fragment implements View.OnClickListener {
    public static final String QUIZE_WORD = "QUIZE_WORD";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View enterWordView = inflater.inflate(R.layout.frag_enter_word, container, false);

        //Set text uppercase
        EditText quizeWordEdit = (EditText) enterWordView.findViewById(R.id.quizeWord);
        quizeWordEdit.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        //Set a on click listener to the button to switch on GameActivity
        Button enterWordBtn = (Button) enterWordView.findViewById(R.id.btnEnterWord);
        enterWordBtn.setOnClickListener(this);

        return enterWordView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Button to enter a new quize word
     *
     * @param v - button itself
     */
    @Override
    public void onClick(View v) {
        EditText wordEditText = (EditText) getActivity().findViewById(R.id.quizeWord);

        //if all is allright with wordEditText and text is not empty go to GameActivity
        if (wordEditText == null || wordEditText.getText().toString().equals("")) {
            Toast.makeText(getActivity(), R.string.quize_word_msg, Toast.LENGTH_LONG).show();
            return;
        }

        //Go to GameActivity
        Intent singleGameIntent = new Intent(getActivity(), GameActivity.class);
        singleGameIntent.putExtra(QUIZE_WORD, wordEditText.getText().toString());
        startActivity(singleGameIntent);

        getActivity().finish();
    }
}
