package com.example.report;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.calculate_button);
        EditText first_number = (EditText)findViewById(R.id.first_number);
        EditText second_number = (EditText)findViewById(R.id.second_number);
        TextView result_textview = (TextView)findViewById(R.id.result_textview);

        button.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        float x1 = Float.parseFloat(first_number.getText().toString());
                        float x2 = Float.parseFloat(second_number.getText().toString());

                        float add = x1 + x2;
                        float sub = x1 - x2;
                        float mul = x1 * x2;
                        float div = x1 / x2;

                        String result = "Results:\nAddition: " + add +"\nSubtraction: " + sub +"\nMultiplication: " + mul + "\nDivision: " + div;
                        result_textview.setText(result);
                        save(v);
                    }
        }
        );
    }

    public void save(View v) {

        final String FILE_NAME = "Result.txt";
        FileOutputStream fos = null;
        TextView result_textview = (TextView)findViewById(R.id.result_textview);
        String text = result_textview.getText().toString();

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Saved to" + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}