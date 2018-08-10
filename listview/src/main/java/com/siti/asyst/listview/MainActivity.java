package com.siti.asyst.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.siti.asyst.listview.Fragment.InputBottomSheet;
import com.siti.asyst.listview.adapter.PersonAdapter;
import com.siti.asyst.listview.model.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemLongClickListener, View.OnCreateContextMenuListener, InputBottomSheet.OnSubmitButtonListener {

    ListView listView;
    EditText inputET;
    Button addButton;

    ArrayList<String> listNama = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    ArrayList<Person> listPerson = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputET = findViewById(R.id.input_name_edittext);
        addButton = findViewById(R.id.add_button);

        listView = findViewById(R.id.listview);
        listNama.add("Siti Afiyah");
        listNama.add("Alfina");
        listNama.add("Lala");
        listNama.add("Lili");


        for (int i = 0; i < 10; i++) {
            Person person = new Person("Nama Ke " + i, "Alamat Ke " + i);
            listPerson.add(person);
        }

        PersonAdapter personAdapter = new PersonAdapter(this, listPerson);

        //arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listNama);

        listView.setAdapter(personAdapter);

        //listenernya listview
        //listView.setOnItemClickListener(this);
        //addButton.setOnClickListener(this);
        //listView.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(MainActivity.this, listNama.get(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_button:
                if (!inputET.getText().toString().equalsIgnoreCase("")) {
                    listNama.add(inputET.getText().toString());
                    inputET.setText("");
                    arrayAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "Isikan Nama", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        InputBottomSheet inputBottomSheet = InputBottomSheet.newInstance(listNama.get(position), position);
        inputBottomSheet.show(getSupportFragmentManager(), "");
        //bottomSheetDialog.show();
        return true;
    }

    @Override
    public void onSubmitButton(String name, int position) {
        listNama.set(position, name);
        arrayAdapter.notifyDataSetChanged(); //ngasihtau adapter bila ada perubahn
    }

}
