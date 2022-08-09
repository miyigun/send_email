package com.example.send_email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText mailAddress, subject, content;
    Button button;
    String mailAddressText, subjectText, contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        define();
        sendMail();
    }

    public void define() {
        mailAddress = (EditText) findViewById(R.id.editTextMailAddress);
        subject = (EditText) findViewById(R.id.editTextMailSubject);
        content = (EditText) findViewById(R.id.editTextMailContent);
        button = (Button) findViewById(R.id.sendemail);
    }

    public void getData() {
        mailAddressText = mailAddress.getText().toString();
        subjectText = subject.getText().toString();
        contentText = content.getText().toString();
    }

    public void sendMail() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();

                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    //To send email to multiple email addresses, we define string array below "new String[]{mailAddressText}
                    intent.putExtra(Intent.EXTRA_EMAIL, mailAddressText);
                    intent.putExtra(Intent.EXTRA_SUBJECT, subjectText);
                    intent.putExtra(Intent.EXTRA_TEXT, contentText);

                    startActivity(Intent.createChooser(intent, "Send e-mail"));
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }

            }
        });
    }
}