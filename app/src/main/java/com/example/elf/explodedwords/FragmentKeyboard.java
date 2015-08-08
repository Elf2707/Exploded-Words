package com.example.elf.explodedwords;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FragmentKeyboard extends Fragment implements View.OnClickListener {

    OnLetterInput lettersListener;

    public static FragmentKeyboard newInstance(String param1, String param2) {
        FragmentKeyboard fragment = new FragmentKeyboard();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentKeyboard() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keybrd_letters, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Setting up a listener for buttons
        for (int i = 0; i < ((RelativeLayout) getView()).getChildCount(); i++) {
            LinearLayout layout = (LinearLayout) ((RelativeLayout) getView()).getChildAt(i);
            for (int j = 0; j < layout.getChildCount(); j++) {
                Button button = (Button) layout.getChildAt(j);
                button.setOnClickListener(this);
            }
        }

        lettersListener = (OnLetterInput) getActivity();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if (lettersListener.onLetterInput(v)) {
            ((Button) v).getBackground().setColorFilter(getResources().getColor(R.color.clr_right), PorterDuff.Mode.MULTIPLY);

        } else {
            ((Button) v).getBackground().setColorFilter(getResources().getColor(R.color.clr_wrong), PorterDuff.Mode.MULTIPLY);
        }
    }

    /**
     * Interface fo data exchange with activity
     */
    public interface OnLetterInput {

        public boolean onLetterInput(View inputButton);
    }

}
