package com.jatin.smsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtMobile,txtMsg;
    private Button btnSend;
    private TextView txtStatus;
    private SmsManager smsManager;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMobile = findViewById(R.id.txtMobile);
        txtMsg = findViewById(R.id.txtMsg);
        txtStatus = findViewById(R.id.txtStatus);
        imageView = findViewById(R.id.imageView);
        btnSend = findViewById(R.id.btnSend);
        smsManager = SmsManager.getDefault();

        if(Build.VERSION.SDK_INT>22)
        {
            if(checkSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED){
                txtStatus.setText("Ready to send SMS");
                imageView.setImageResource(R.drawable.ic_ok);
            }
            else{
                txtStatus.setText("No Permission to send SMS");
                imageView.setImageResource(R.drawable.ic_not_ok);
                requestPermissions (new String[]{Manifest.permission.READ_SMS},111);
            }
        }
        else {
            txtStatus.setText("Ready to send SMS");
            imageView.setImageResource(R.drawable.ic_ok);
        }

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                smsManager.sendTextMessage(txtMobile.getText().toString(),
                                null,
                        txtMsg.getText().toString(),
                        pi,null);
                txtStatus.setText("Message Sent..");
            }
        });

    }
}