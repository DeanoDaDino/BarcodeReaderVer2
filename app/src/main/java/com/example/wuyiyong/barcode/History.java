package com.example.wuyiyong.barcode;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class History extends AppCompatActivity {

    TextView codes;
    EditText input_code;
    Button delete,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        input_code = (EditText) findViewById(R.id.code);
        search = (Button) findViewById(R.id.search);
        codes = (TextView) findViewById(R.id.textView);
        codes.setText(readFile("save.txt"));
        delete = (Button) findViewById(R.id.remove);


        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view) {
                remove("save.txt");
                codes.setText(readFile("save.txt"));
            }
        });
        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view) {
                results(input_code);
            }
        });


    }

    public void results(EditText something) {
        Uri uri = Uri.parse("http://www.google.com/#q=" + something.getText());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    //Reads the file and sets the text view
    public String readFile(String file) {
        String text = "";
        try {
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return text;
    }
    public void remove(String file) {
        try {
            FileOutputStream fos = openFileOutput(file, Context.MODE_PRIVATE);
            String text = "";
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(History.this, "CLEARED", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

