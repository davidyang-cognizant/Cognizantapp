package com.example.cognizantapp;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView name;
    private TextView reply;
    private ImageView imageView;
    private ListView listView;
    public static String LOG_TAG = MainActivity.class.getSimpleName();

    private ArrayList<String> listItems = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView) findViewById(R.id.enter_name);
        reply = (TextView) findViewById(R.id.reply);
        imageView = (ImageView) findViewById(R.id.imageView);
        listView = (ListView) findViewById(R.id.listView);

        Log.d(LOG_TAG, "------");
        Log.d(LOG_TAG, "onCreate");

        /* Used for saving the state of a reply when activity refreshes*/
        if (savedInstanceState != null) {
            Log.d(LOG_TAG, "On Create: Loading saved instance state...");
            String reply_text = savedInstanceState.getString("reply");
            reply.setText(reply_text);
        }

        /* Added adapter for adding names to a listview */
        adapter = new ArrayAdapter<String>(this, R.layout.list_view_layout, R.id.textView, listItems);
        listView.setAdapter(adapter);

        /* Example of sending intents with Parcables
        User user = new User(1, "David", 22);
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        i.putExtra("user", user);
        startActivity(i);
        */
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "onStart");
//        throw new NullPointerException("onStart NullPointerException");
    }

    public void add(int a, int b){
        int c = a  + b;
        b += c;
        a = c;
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("reply", reply.getText().toString());
    }


    public void clickHandler(View view) {
        String stringName = name.getText().toString();
        Toast.makeText(this, stringName, Toast.LENGTH_SHORT).show();
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("EXTRA_MESSAGE", stringName);
        startActivityIntent.launch(homeIntent);
    }

    public ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        assert data != null;
                        reply.setText(data.getStringExtra("REPLY"));
                    }
                }

            });

    public void handleCamera(View view) {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePicture, 1);
        } catch (ActivityNotFoundException e) {
            Log.d("MainActivity", "Unable to take picture");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    public void handleAdd(View view) {
        if(!name.getText().toString().equals("")){
            adapter.add(name.getText().toString());
        }
    }
}