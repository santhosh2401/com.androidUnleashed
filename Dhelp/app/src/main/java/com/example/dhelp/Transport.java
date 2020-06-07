package com.example.dhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Transport extends AppCompatActivity {

    EditText an,cn,ag,ca,w,wh;
    Button s;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    FirebaseUser user;
    String name,num,age,address,wt,wht;
    boolean check = false;


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Transport.this,DashBoard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);


        an = findViewById(R.id.an2);
        cn = findViewById(R.id.cn2);
        ag = findViewById(R.id.ag2);
        ca = findViewById(R.id.ca2);
        w = findViewById(R.id.w2);
        wh = findViewById(R.id.wh2);

        s = findViewById(R.id.s2);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        fstore = FirebaseFirestore.getInstance();


        if(check){
            Intent intent = new Intent(Transport.this,Applied.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check = true;

                name = an.getText().toString();
                num = cn.getText().toString();
                age = ag.getText().toString();
                address = ca.getText().toString();
                wt = w.getText().toString();
                wht = wh.getText().toString();

                DocumentReference documentReference = fstore.collection("Users").document(user.getUid()).collection("Documents").document("Transport Request");
                Map<String,Object> user1 = new HashMap<>();
                user1.put("applicant",name);
                user1.put("number",num);
                user1.put("age",age);
                user1.put("address",address);
                user1.put("months",wt);
                user1.put("type",wht);
                documentReference.set(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Debug","User is added");
                        Toast.makeText(Transport.this,"Applied!",Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent(Transport.this,Applied.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
    }

}
