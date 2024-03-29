package com.visionarytech.eros.Dialog;

import android.annotation.SuppressLint;
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

import com.visionarytech.eros.R;

import static com.visionarytech.eros.R.layout.dialog_about_me;

public class AboutMeDialog extends AppCompatDialogFragment {
    private EditText editTextDialogBio, editTextDialogViews, editTextDialogLocation;
    private Spinner spinnerDialogGender;
    private AboutDialogListener listener;
    private SharedPreferences sharedPref = null;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Storing Data In Shared Preferences.
        Context context = getActivity();
        assert context != null;
        sharedPref = context.getSharedPreferences(getString(R.string.shared_preferences_of_user), Context.MODE_PRIVATE);

//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View view = inflater.inflate(dialog_about_me, null);

//        attaching view references to variables.
        editTextDialogBio = view.findViewById(R.id.editTextDialogBio);
        editTextDialogViews = view.findViewById(R.id.editTextDialogViews);
        spinnerDialogGender = view.findViewById(R.id.spinnerDialogGender);
        editTextDialogLocation = view.findViewById(R.id.editTextDialogLocation);

//        creating array adapter from string values, GENDER.
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.GENDER_VALUES)
        );

//        drop down layout style - list view with radio button
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDialogGender.setAdapter(genderAdapter);

//         setting values for view preferences
        if (sharedPref.contains("Bio")) {
            editTextDialogBio.setText(sharedPref.getString("Bio", ""));
        }

        if (sharedPref.contains("Views")) {
            editTextDialogViews.setText(sharedPref.getString("Views", ""));
        }
//
//        if(sharedPref.contains("Gender")){
////            editTextDialogGender.setText(sharedPref.getString("Gender",""));
//
//        }

        if (sharedPref.contains("Location")) {
            editTextDialogLocation.setText(sharedPref.getString("Location", ""));
        }


//        adding to view ro alertDialog
        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                      Creating Editor For Shared Preferences.
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Bio", editTextDialogBio.getText().toString());
                editor.putString("Views", editTextDialogViews.getText().toString());
                editor.putString("Gender", String.valueOf(spinnerDialogGender.getSelectedItem()));
                editor.putString("Location", editTextDialogLocation.getText().toString());
//                      Saving New User Preferences.
                editor.apply();
                listener.updateProgressBar(20);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (AboutDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement aboutDialogListener");
        }
    }

    public interface AboutDialogListener {
        void updateProgressBar(int progressNumber);
    }
}
