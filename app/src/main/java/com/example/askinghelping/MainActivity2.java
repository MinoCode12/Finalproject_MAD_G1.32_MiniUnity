package com.example.askinghelping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    EditText editTextDesc;
    EditText editTextLocation;
    EditText editTextTelNo;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=FirebaseFirestore.getInstance();

        editTextDesc=(EditText) findViewById(R.id.edittext_desc);
        editTextLocation=(EditText) findViewById(R.id.edittext_location);
        editTextTelNo=(EditText) findViewById(R.id.edittext_telNo);

        findViewById(R.id.btnSend).setOnClickListener(this);
        findViewById(R.id.showBtn).setOnClickListener(this);

    }
    private boolean ValidateInputs(String desc, String loc, String tel) {
        if (desc.isEmpty()) {
            editTextDesc.setError("Description required");
            editTextDesc.requestFocus();
            return true;
        }

        if (loc.isEmpty()) {
            editTextLocation.setError("Address required");
            editTextLocation.requestFocus();
            return true;
        }

        if (tel.isEmpty()) {
            editTextTelNo.setError("Mobile number required");
            editTextTelNo.requestFocus();
            return true;
        }
        return false;
    }




    @Override
    public void onClick(View v) {

        String desc = editTextDesc.getText().toString().trim();
        String loc = editTextLocation.getText().toString().trim();
        String tel = editTextTelNo.getText().toString().trim();



        if(!ValidateInputs(desc,loc,tel)){

            CollectionReference askHelp=db.collection("askHelp");

            askhelp ah=new askhelp(desc,loc,tel);

            askHelp.add(ah).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(MainActivity2.this,"Msg was Sended",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity2.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            });



        }

    }
}
