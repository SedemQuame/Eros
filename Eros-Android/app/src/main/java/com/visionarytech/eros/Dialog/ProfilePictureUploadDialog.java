package com.visionarytech.eros.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.visionarytech.eros.R;

import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class ProfilePictureUploadDialog extends AppCompatDialogFragment implements View.OnClickListener {
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final String TAG = "ProfilePictureUploadDia";
    private CardView progressBarContainer;
    private ImageView photoPreview;
    private ProgressBar progressBar;
    private ProfilePictureUploadDialogListener listener;
    private String FILE_URL = "";
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference mStorageRef;
    private Uri selectedImage;
    private SharedPreferences sharedPref = null;
    private View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //        Storing Data In Shared Preferences.
        Context context = getActivity();
        assert context != null;
        sharedPref = context.getSharedPreferences(getString(R.string.shared_preferences_of_user), Context.MODE_PRIVATE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_profile_photo, null);
//        initialize views
        CardView photoPreviewContainer = view.findViewById(R.id.photoPreviewImageView);
        photoPreviewContainer.setOnClickListener(this);

        photoPreview = view.findViewById(R.id.photoReview);
        photoPreview.setOnClickListener(this);

        progressBarContainer = view.findViewById(R.id.progressBarContainer);

        progressBar = view.findViewById(R.id.progressBar);
        Button uploadButton = view.findViewById(R.id.uploadPictureButton);
        uploadButton.setOnClickListener(this);

        builder.setView(view);
        return builder.create();
    }

    private void pickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
//        setting type as image to ensure that only components of type image are selected.
        intent.setType("image/*");
//        Array with accepted mime types.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
//        Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    private void uploadImageToFireBaseStorage() {
        mStorageRef = storage.getReference().child("images/" + UUID.randomUUID().toString());
        if (selectedImage != null) {
            mStorageRef.putFile(selectedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //Do what you want with the url
                                    FILE_URL = uri.toString();
                                    Log.d(TAG, "FILE_URL #1: " + FILE_URL);
                                    Toast.makeText(getActivity(), "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    listener.updateProgressBar(20);
                                    listener.returnFileUrl(FILE_URL);
                                }
                            });
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
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //                    Returns the content URI for the selected Image
            selectedImage = data.getData();
            photoPreview.setImageURI(selectedImage);
            Log.d(TAG, "Image URL: " + selectedImage.toString());
        }
        ViewGroup.LayoutParams params =  photoPreview.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        photoPreview.setLayoutParams(params);
    }

    public interface ProfilePictureUploadDialogListener {
        void returnFileUrl(String fileUrl);
        void updateProgressBar(int progressNumber);
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
//        ViewGroup.LayoutParams params = null;
        switch (view.getId()) {
            case (R.id.photoPreviewImageView):
                pickFromGallery();
            case (R.id.photoReview):
                pickFromGallery();
            case (R.id.uploadPictureButton):
                progressBarContainer.setVisibility(View.VISIBLE);
                uploadImageToFireBaseStorage();
                progressBarContainer.setVisibility(View.GONE);
                break;
            default:
        }

    }

}
