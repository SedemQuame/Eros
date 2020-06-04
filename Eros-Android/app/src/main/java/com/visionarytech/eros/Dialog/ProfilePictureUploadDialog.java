package com.visionarytech.eros.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.visionarytech.eros.R;

import java.io.File;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class ProfilePictureUploadDialog extends AppCompatDialogFragment implements View.OnClickListener {
    private ImageView photoPreview;
    private ProfilePictureUploadDialogListener listener;
    private String FILE_URL = "";
    private static final int GALLERY_REQUEST_CODE = 1;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference mStorageRef;
    private Uri selectedImage;
    private static final String TAG = "ProfilePictureUploadDia";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_profile_photo, null);
//        initialize views
        photoPreview = view.findViewById(R.id.photoPreviewImageView);
        photoPreview.setOnClickListener(this);

//        adding to view ro alertDialog
        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                    todo: do nothing here, just dismiss Dialog Button.
                }}).setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                    todo: upload image
                    listener.updateProgressBar(20);
                }
        });

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
            case (R.id.photoPreviewImageView):
//                todo: Choose new image here
                pickFromGallery();
                break;
            default:
                // code block
        }

    }

    public interface ProfilePictureUploadDialogListener {
        void updateProgressBar(int progressNumber);
    }

    private void pickFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
//        setting type as image to ensure that only components of type image are selected.
        intent.setType("image/*");
//        Array with accepted mime types.
        String [] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
//        Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    private void uploadImageToFireBaseStorage(){
        mStorageRef = storage.getReference().child("images/" + UUID.randomUUID().toString());

        if(selectedImage != null){
            mStorageRef.putFile(selectedImage)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                           @Override
                           public void onSuccess(Uri uri) {
                               //Do what you want with the url
                                FILE_URL = uri.toString();
                               Log.d(TAG, "FILE_URL: " + FILE_URL);
                           }
                       });
                        Toast.makeText(getContext(), "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onUpload: Successfully uploaded.");
                        Log.d(TAG, "FILE_URL: " + FILE_URL);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(getContext(), "Image Uploaded Failed", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure: " + exception.getMessage());
                    }
                });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Result code is RESULT_OK only if user selects image successfully.
        if(requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            switch(requestCode){
                case GALLERY_REQUEST_CODE:
//                    Returns the content URI for the selected Image
                    selectedImage = data.getData();
                    photoPreview.setImageURI(selectedImage);
                    Log.d(TAG, "Image URL: " + selectedImage.toString());
//                    Picasso.with(MainActivity.this).load(selectedImageURI).noPlaceholder().centerCrop().fit()
//                            .into((ImageView)
                    uploadImageToFireBaseStorage();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + requestCode);
            }
        }

    }
}
