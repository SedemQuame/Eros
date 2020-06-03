package com.visionarytech.eros.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.visionarytech.eros.Networks.RequestHandler;
import com.visionarytech.eros.R;

public class ContactInformationDialog extends AppCompatDialogFragment {
    private EditText editTextDialogEmail;
    private EditText editTextDialogPhone;
    private ContactInformationDialogListener listener;
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
        View view = inflater.inflate(R.layout.dialog_contact_information, null);
//          attaching view references to variables.
        editTextDialogEmail = view.findViewById(R.id.editTextDialogEmail);
        editTextDialogPhone = view.findViewById(R.id.editTextDialogPhone);

//         setting values for view preferences
        if(sharedPref.contains("Email")){
            editTextDialogEmail.setText(sharedPref.getString("Email",""));
        }

        if(sharedPref.contains("Phone")){
            editTextDialogPhone.setText(sharedPref.getString("Phone",""));
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
                    editor.putString("Email", editTextDialogEmail.getText().toString());
                    editor.putString("Phone", editTextDialogPhone.getText().toString());
//                      Saving New User Preferences.
                    editor.apply();
//                    Building REQUEST_URL
//                    Passing User Details to RequestHandler.
//                    RequestHandler handler = new RequestHandler(getContext(), "", "");
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
            listener = (ContactInformationDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement aboutDialogListener");
        }
    }

    public interface ContactInformationDialogListener{
        void updateProgressBar(int progressNumber);
    }
}
