package com.siti.asyst.listview.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.siti.asyst.listview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {

    EditText nameET;
    Button addButton;
    int position;
    OnSubmitButtonListener listener;

    public InputBottomSheet() {
        // Required empty public constructor
    }

    public static InputBottomSheet newInstance(String name, int position) {
        InputBottomSheet fragment = new InputBottomSheet();

        Bundle bundle = new Bundle();
        bundle.putString("nama", name);
        bundle.putInt("position", position);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_input_bottom_sheet, container, false);

        nameET = v.findViewById(R.id.input_name_edittext);

        addButton = v.findViewById(R.id.add_button);
        addButton.setOnClickListener(this);

        if (getArguments() != null) {
            //buat Ngambil Datanya
            nameET.setText(getArguments().getString("nama", ""));
            position = getArguments().getInt("position", 0);
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button:
                listener.onSubmitButton(nameET.getText().toString(), position);
                dismiss();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof InputBottomSheet.OnSubmitButtonListener) {
            listener = (InputBottomSheet.OnSubmitButtonListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Activity harus implement OnSubmitButtonListener");
        }

    }

    public interface OnSubmitButtonListener {
        void onSubmitButton(String name, int position);
    }

}
