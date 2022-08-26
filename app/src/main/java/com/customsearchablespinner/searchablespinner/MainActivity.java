package com.customsearchablespinner.searchablespinner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Initialize variable
    TextView textview;
    ArrayList<String> arrayList;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign variable
        textview=findViewById(R.id.testView);

        // initialize array list
        arrayList=new ArrayList<>();

        // set value in array list
        arrayList.add("Honduras");
        arrayList.add("United Arab Emirates");
        arrayList.add("Djibouti");
        arrayList.add("Seychelles");
        arrayList.add("Antigua and Barbuda");
        arrayList.add("Vietnam");
        arrayList.add("Hungary");
        arrayList.add("Tajikistan");

        textview.setOnClickListener(v -> {
            // Initialize dialog
            dialog=new Dialog(MainActivity.this);

            // set custom dialog
            dialog.setContentView(R.layout.dialog_search_spinner);

            // set custom height and width
            dialog.getWindow().setLayout(650,800);

            // set transparent background
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            // show dialog
            dialog.show();

            // Initialize and assign variable
            EditText editText=dialog.findViewById(R.id.edit_text);
            ListView listView=dialog.findViewById(R.id.list_view);

            // Initialize array adapter
            ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,arrayList);

            // set adapter
            listView.setAdapter(adapter);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            listView.setOnItemClickListener((parent, view, position, id) -> {
                // when item selected from list
                // set selected item on textView
                textview.setText(adapter.getItem(position));

                // Dismiss dialog
                dialog.dismiss();
            });
        });
    }
}