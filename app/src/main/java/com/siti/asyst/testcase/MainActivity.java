package com.siti.asyst.testcase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.siti.asyst.testcase.utility.Constant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart;
    EditText teamaET, teambET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.button_start);
        teamaET = findViewById(R.id.teama_edittext);
        teambET = findViewById(R.id.teamb_edittext);

        buttonStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start:

                if (validate()) {
                    Intent intent = new Intent(this, MatchActivity.class);

                    intent.putExtra(Constant.KEY_TEAM_A_NAME, teamaET.getText().toString());
                    intent.putExtra(Constant.KEY_TEAM_B_NAME, teambET.getText().toString());

                    startActivity(intent);
                }

                break;
        }
    }

    public boolean validate() {
        if (TextUtils.isEmpty(teamaET.getText().toString()) || TextUtils.isEmpty(teambET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Nama team harus diisi", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (teamaET.getText().toString().equalsIgnoreCase(teambET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Nama team harus unik", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
