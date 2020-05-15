package com.visionarytech.eros.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.visionarytech.eros.R;

public class PreferenceDialog extends AppCompatDialogFragment {
    private EditText editTextDialogGender;
    private EditText editTextDialogLookingFor;
    private EditText editTextDialogAgeRange;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_preferences, null);

//        adding to view ro alertDialog
        builder.setView(view);


//        attaching view references to variables.
        editTextDialogGender = view.findViewById(R.id.editTextDialogGender);
        editTextDialogLookingFor = view.findViewById(R.id.editTextDialogLookingFor);
        editTextDialogAgeRange = view.findViewById(R.id.editTextDialogAgeRange);

        return builder.create();
    }
}
