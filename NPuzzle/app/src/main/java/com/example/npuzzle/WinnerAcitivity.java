package com.example.npuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinnerAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_acitivity);
        TextView winner_message = findViewById(R.id.winner_message);
        Button playAgainBtn = findViewById(R.id.playAgainButton);
        if( getIntent().hasExtra("com.example.npuzzle.numberOfMoves") ) {
            int num_of_moves = getIntent().getExtras().getInt("com.example.npuzzle.numberOfMoves");
            winner_message.setText("Congratulatoins you have sloved the NPuzzle game with " + num_of_moves + " moves");
        }
        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_activity = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( main_activity );
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent main_activity = new Intent( getApplicationContext(), MainActivity.class );
        startActivity( main_activity);
        return;
    }
}
