package com.igor040897.dinosaurs.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.igor040897.dinosaurs.Manifest;
import com.igor040897.dinosaurs.R;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class AddItemActivity extends AppCompatActivity {

    public static final String RESULT_NAME = "name";
    public static final String RESULT_DESCRIPTION = "description";
    public static final String RESULT_URI_IMAGE = "uri";
    public static final int RC_ADD_ITEM = 99;

    private String uriImage = "";
    private TextView add;
    private EditText name, description;
    private ImageView download;
//    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        add = findViewById(R.id.ok);
        download = findViewById(R.id.download);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                add.setEnabled(!TextUtils.isEmpty(name.getText()) &&
                        !TextUtils.isEmpty(description.getText()) &&
                        !TextUtils.isEmpty(uriImage));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        final AddItemActivity temp = this;
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddItemActivityPermissionsDispatcher.readImageWithCheck(temp);
                showFileChooser();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra(RESULT_NAME, name.getText().toString());
                result.putExtra(RESULT_DESCRIPTION, description.getText().toString());
                result.putExtra(RESULT_URI_IMAGE, uriImage);
                setResult(RESULT_OK, result);
                finish();
            }
        });

        name.addTextChangedListener(textWatcher);
        description.addTextChangedListener(textWatcher);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/jpg");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"), 1);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    uriImage = uri.getPath();



                    add.setEnabled(!TextUtils.isEmpty(name.getText()) &&
                            !TextUtils.isEmpty(description.getText()) &&
                            !TextUtils.isEmpty(uriImage));

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void readImage() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AddItemActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
    void readImageRationale(final PermissionRequest request) {
    }

    @OnNeverAskAgain(Manifest.permission.READ_EXTERNAL_STORAGE)
    void readImageNever() {
    }
}
