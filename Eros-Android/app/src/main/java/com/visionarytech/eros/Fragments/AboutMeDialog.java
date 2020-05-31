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

public class AboutMeDialog extends AppCompatDialogFragment {
    private EditText editTextDialogBio;
    private EditText editTextDialogViews;
    private AboutDialogListener listener;
    private static String BASE_URL = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_about_me, null);

//        attaching view references to variables.
        editTextDialogBio = view.findViewById(R.id.editTextDialogBio);
        editTextDialogViews = view.findViewById(R.id.editTextDialogViews);

//        adding to view ro alertDialog
        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }}).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                      Storing Data In Shared Preferences.
                    Context context = getActivity();
                    SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.shared_preferences_of_user), context.MODE_PRIVATE);
//                      Creating Editor For Shared Preferences.
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Bio", editTextDialogBio.getText().toString());
                    editor.putString("Views", editTextDialogViews.getText().toString());
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
            listener = (AboutDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement aboutDialogListener");
        }
    }

    public interface AboutDialogListener{
        void updateProgressBar(int progressNumber);
    }
}
