package com.leosland.edgar.hospitalito;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static int RESULT_LOAD = 1;
    String img_Decodable_Str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.Medicos);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Medicos, R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                img_Decodable_Str = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView1);
                // Set the Image in ImageView after decoding the String
                Log.d("IMAGE", "IMAGE IS SETTING");
                imgView.setImageURI(selectedImage);

                //imgView.setImageBitmap(BitmapFactory
                //        .decodeFile(img_Decodable_Str));

            } else {
                Toast.makeText(this, "Hey pick your image first",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went embrassing", Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void  toastEvent(View view){
        String apoinment;
        EditText name_patient = (EditText) findViewById(R.id.name_patient);
        EditText number_patient = (EditText) findViewById(R.id.number_patient);
        EditText email_patient = (EditText) findViewById(R.id.email_patient);
        EditText phone_patient = (EditText) findViewById(R.id.phone_patient);
        EditText postal_patient = (EditText) findViewById(R.id.postal_patient);
        EditText ink_patient = (EditText) findViewById(R.id.ink_patient);
        EditText date_date = (EditText) findViewById(R.id.date_date);
        EditText hour_date = (EditText) findViewById(R.id.hour_date);
        Spinner Medicos = (Spinner) findViewById(R.id.Medicos);

        apoinment = "" + (name_patient.getText())
                + "\n"+(number_patient.getText())
                + "\n"+(email_patient.getText())
                + "\n"+(phone_patient.getText())
                + "\n"+(postal_patient.getText())
                + "\n"+(ink_patient.getText())
                + "\n"+(date_date.getText())
                + "\n"+(hour_date.getText())
                + "\n"+(Medicos.getSelectedItem());

        Toast.makeText(this, apoinment,
                Toast.LENGTH_LONG).show();
    }
}
