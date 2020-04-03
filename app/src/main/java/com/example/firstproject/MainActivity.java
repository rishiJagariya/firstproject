package com.example.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btn,fetch;

//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference demoRef = database.getReference("demo");
    DatabaseReference demoRef,rootRef;
    TextView demoValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edText);
        btn = (Button) findViewById(R.id.button2);
        demoValue = (TextView)findViewById(R.id.tvValue);
        fetch = (Button) findViewById(R.id.button3);

        rootRef = FirebaseDatabase.getInstance().getReference();

        demoRef = rootRef.child("demo");


        btn.setOnClickListener(new View.OnClickListener() {
            int abc=0;
            @Override
            public void onClick(View v) {

                String value = editText.getText().toString();
                demoRef.child("value"+abc).setValue(value);
                abc++;

            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            int abc=0;
            @Override
            public void onClick(View v) {

                demoRef.child("value"+abc).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        demoValue.setText(value);
                        abc++;
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });


    }


}
