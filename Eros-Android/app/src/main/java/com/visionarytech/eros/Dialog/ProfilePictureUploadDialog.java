package com.visionarytech.eros.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.visionarytech.eros.R;

public class ProfilePictureUploadDialog extends AppCompatDialogFragment implements View.OnClickListener {
    private ImageView photoPreview;
    private Button chooseImageButton, uploadImageButton;
    private ProfilePictureUploadDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_profile_photo, null);


        photoPreview = view.findViewById(R.id.photoPreview);

        chooseImageButton = view.findViewById(R.id.choosePictureButton);
        chooseImageButton.setOnClickListener(this);

        uploadImageButton = view.findViewById(R.id.uploadPictureButton);
        uploadImageButton.setOnClickListener(this);

//        adding to view ro alertDialog
        builder.setView(view);
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }}).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        listener.updateProgressBar(20);
//                    }
//        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ProfilePictureUploadDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ProfilePictureUploadDialogListener");
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case (R.id.choosePictureButton):
                // code block
                break;
            case (R.id.uploadPictureButton):
                // code block

                break;
            default:
                // code block
        }

    }

    public interface ProfilePictureUploadDialogListener {
        void updateProgressBar(int progressNumber);
    }
}
