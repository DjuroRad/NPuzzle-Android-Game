package com.example.npuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ColumnsMinusBtn = (Button) findViewById(R.id.ColumnsMinusBtn);
        Button ColumnsPlusBtn = (Button) findViewById(R.id.ColumnsPlusBtn);
        Button RowsPlusBtn = (Button) findViewById(R.id.RowsPlusBtn);
        Button RowsMinusBtn = (Button) findViewById(R.id.RowsMinusBtn);
        Button PlayBtn = (Button) findViewById( R.id.PlayBtn );

        final TextView ColumnsTextField = (TextView) findViewById(R.id.ColumnsTextField);
        final TextView RowsTextField= (TextView) findViewById(R.id.RowsTextField);

        final int MIN_ROWS = 3, MIN_COLS = 3;
        final int MAX_ROWS = 9, MAX_COLS = 9;
        ColumnsPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String columns_text = ColumnsTextField.getText().toString();
                int columns = Integer.parseInt( columns_text );
                if( columns < MAX_COLS )
                    columns++;
                ColumnsTextField.setText( columns + "" );
            }
        });//end of ColumnsPlusBtn SetOnClickListener
        ColumnsMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String columns_text = ColumnsTextField.getText().toString();
                int columns = Integer.parseInt( columns_text );
                if( columns > MIN_COLS )
                    columns--;
                ColumnsTextField.setText( columns + "" );
            }
        });//end of ColumnsPlusBtn SetOnClickListener
        RowsPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rows_text = RowsTextField.getText().toString();
                int rows = Integer.parseInt( rows_text );
                if( rows < MAX_ROWS )
                    rows++;
                RowsTextField.setText( rows + "" );
            }
        });//end of ColumnsPlusBtn SetOnClickListener
        RowsMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rows_text = RowsTextField.getText().toString();
                int rows = Integer.parseInt( rows_text );
                if( rows > MIN_ROWS )
                    rows--;
                RowsTextField.setText( rows + "" );
            }
        });//end of ColumnsPlusBtn SetOnClickListener
        PlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_game_intent = new Intent( getApplicationContext(), GameplayActivity.class );
                start_game_intent.putExtra( "com.example.npuzzle.Columns", Integer.parseInt( ColumnsTextField.getText().toString()) );
                start_game_intent.putExtra( "com.example.npuzzle.Rows", Integer.parseInt( RowsTextField.getText().toString()) );
                startActivity( start_game_intent );
            }
        });
    }
}
