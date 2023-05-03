package com.example.crud_operations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText name,roll,course;
    Button bt,bt2;
    TextView delete,update;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.sname);
        roll=findViewById(R.id.sroll);
        course=findViewById(R.id.scourse);
        bt=findViewById(R.id.sbutton);
        bt2=findViewById(R.id.rbutton);
        delete=findViewById(R.id.del);
        update=findViewById(R.id.upd);
        db=FirebaseFirestore.getInstance();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString().trim();
                String roll1 = roll.getText().toString().trim();
                String course1 = course.getText().toString().trim();

                Map<String, Object> user = new HashMap<>();
                user.put("Name", name1);
                user.put("Rollno", roll1);
                user.put("course", course1);

                db.collection("users")
                        .add(user)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(MainActivity.this, "Student details stored", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Data not stored", Toast.LENGTH_SHORT).show();
                            }
                        });
                bt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,Read.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Read Data", Toast.LENGTH_SHORT).show();
                    }
                });
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(MainActivity.this,Update.class);
                        startActivity(intent);
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(MainActivity.this,Delete.class);
                        startActivity(intent);
                    }
                });

            }
        });

    }
}