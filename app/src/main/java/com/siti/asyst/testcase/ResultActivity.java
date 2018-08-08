package com.siti.asyst.testcase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.siti.asyst.testcase.utility.Constant;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView winner_teamTV, winner_team_scoreTV;
    Button button_back;

    String winnerTeam;
    int scoreWinnerTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        winner_teamTV = findViewById(R.id.winner_team_textview);
        winner_team_scoreTV = findViewById(R.id.winner_team_score_textview);
        button_back = findViewById(R.id.back_button);

        button_back.setOnClickListener(this);

        if (getIntent().getExtras() != null) {
            winnerTeam = getIntent().getExtras().getString(Constant.KEY_WINNER_TEAM);
            scoreWinnerTeam = getIntent().getExtras().getInt(Constant.KEY_WINNER_SCORE);
        }

        winner_teamTV.setText(winnerTeam + "");
        winner_team_scoreTV.setText(scoreWinnerTeam + "");

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
