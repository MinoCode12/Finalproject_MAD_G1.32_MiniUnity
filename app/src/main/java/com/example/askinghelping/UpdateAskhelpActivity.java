package com.example.askinghelping;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateAskhelpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextDesc;
    private EditText editTextLoc;
    private EditText editTextTel;


    private FirebaseFirestore db;

    private askhelp askhelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_askhelp);

        askhelp = (askhelp) getIntent().getSerializableExtra("product");
        db = FirebaseFirestore.getInstance();

        editTextDesc = findViewById(R.id.edittext_desc);
        editTextLoc = findViewById(R.id.edittext_location);
        editTextTel = findViewById(R.id.edittext_telNo);


        editTextDesc.setText(askhelp.getDescription());
        editTextLoc.setText(askhelp.getLocation());
        editTextTel.setText(askhelp.getTelNo());



        findViewById(R.id.button_update).setOnClickListener(this);
        findViewById(R.id.button_delete).setOnClickListener(this);
    }

    private boolean hasValidationErrors(String desc, String loc, String tel) {
        if (desc.isEmpty()) {
            editTextDesc.setError("Description required");
            editTextDesc.requestFocus();
            return true;
        }

        if (loc.isEmpty()) {
            editTextLoc.setError("Address required");
            editTextLoc.requestFocus();
            return true;
        }

        if (tel.isEmpty()) {
            editTextTel.setError("Phone number required");
            editTextTel.requestFocus();
            return true;
        }

        return false;
    }


    private void updateProduct() {
        String desc = editTextDesc.getText().toString().trim();
        String loc = editTextLoc.getText().toString().trim();
        String tel = editTextTel.getText().toString().trim();


        if (!hasValidationErrors(desc, loc, tel)) {

            askhelp = new askhelp(
                    desc, loc, tel
            );


            db.collection("products").document(askhelp.getId())
                    .update(
                            "description", askhelp.getDescription(),
                            "location", askhelp.getLocation(),
                            "telephone No", askhelp.getTelNo()
                    )
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(UpdateAskhelpActivity.this, "Product Updated", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    private void deleteProduct() {
        db.collection("products").document(askhelp.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UpdateAskhelpActivity.this, "Product deleted", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(UpdateAskhelpActivity.this, askhelpsActivity.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_update:
                updateProduct();
                break;
            case R.id.button_delete:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are you sure about this?");
                builder.setMessage("Deletion is permanent...");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteProduct();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();

                break;
        }
    }
}
