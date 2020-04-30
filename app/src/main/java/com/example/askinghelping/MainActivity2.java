package com.example.askinghelping;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


    }
    private boolean hasValidationErrors(String desc, String loc, String tel) {
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





    public void saveMsg(){

        String desc = editTextDesc.getText().toString().trim();
        String loc = editTextLocation.getText().toString().trim();
        String tel = editTextTelNo.getText().toString().trim();



        if(!hasValidationErrors(desc,loc,tel)){

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
    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnSend:
                saveMsg();
                break;
            case R.id.showBtn:
                startActivity(new Intent(this, askhelpActivity.class));
                break;
        }

    }

}
