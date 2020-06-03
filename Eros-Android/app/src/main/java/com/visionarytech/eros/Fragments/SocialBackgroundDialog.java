package com.visionarytech.eros.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.visionarytech.eros.Networks.RequestHandler;
import com.visionarytech.eros.R;

public class SocialBackgroundDialog extends AppCompatDialogFragment {
    private EditText editTextDialogWork, editTextDialogSchool, editTextDialogReligion;
    private Spinner spinnerDialogReligion;
    private SocialBackgroundDialogListener listener;
    private static String BASE_URL = "";
//          Storing Data In Shared Preferences.
    SharedPreferences sharedPref = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Storing Data In Shared Preferences.
        Context context = getActivity();
        sharedPref = context.getSharedPreferences(getString(R.string.shared_preferences_of_user), context.MODE_PRIVATE);

//          return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_social_background, null);
//          attaching view references to variables.
        editTextDialogWork = view.findViewById(R.id.editTextDialogWork);
        editTextDialogSchool = view.findViewById(R.id.editTextDialogSchool);
//        editTextDialogReligion = view.findViewById(R.id.editTextDialogReligion);
        spinnerDialogReligion = view.findViewById(R.id.spinnerDialogReligion);


//        creating arrayadapter from string values, GENDER.
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.RELIGION_VALUES)
        );

//        drop down layout style - list view with radio button
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDialogReligion.setAdapter(genderAdapter);

//         setting values for view preferences
        if(sharedPref.contains("Work")){
            editTextDialogWork.setText(sharedPref.getString("Work",""));
        }

        if(sharedPref.contains("School")){
            editTextDialogSchool.setText(sharedPref.getString("School",""));
        }

        if(sharedPref.contains("Religion")){
//            editTextDialogReligion.setText(sharedPref.getString("Religion",""));
//            spinnerDialogReligion.se
        }

//          adding to view ro alertDialog
        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }}).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

//                      Creating Editor For Shared Preferences.
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Work", editTextDialogWork.getText().toString());
                    editor.putString("School", editTextDialogSchool.getText().toString());
                    editor.putString("Religion", String.valueOf(spinnerDialogReligion.getSelectedItem()));

//                    editor.putString("Religion", editTextDialogReligion.getText().toString());
//                      Saving New User Preferences.
                    editor.apply();
//                    Building REQUEST_URL
//                    Passing User Details to RequestHandler.
                    RequestHandler handler = new RequestHandler(getContext(), "", "");
//                      Update Registration Progress By 25%;
                    listener.updateProgressBar(25);
                }
        });
        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (SocialBackgroundDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement aboutDialogListener");
        }
    }

    public interface SocialBackgroundDialogListener{
        void updateProgressBar(int progressNumber);
    }
}
