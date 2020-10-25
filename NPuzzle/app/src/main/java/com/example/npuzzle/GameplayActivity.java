package com.example.npuzzle;
import com.example.npuzzle.hwk7.BoardArray2D;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.Button;
import android.widget.TextView;

public class GameplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        final Integer Columns;
        final Integer Rows;
        final GridLayout GameBoardLayout = findViewById(R.id.GameBoardLayout);
        final TextView numberOfMovesTextView = findViewById(R.id.numberOfMovesTextView);
        final Button tryAgainButton = findViewById(R.id.try_again_button);
        DisplayMetrics display_metrics = new DisplayMetrics();
        final int HEIGHT = 1500;
        getWindowManager().getDefaultDisplay().getMetrics( display_metrics );

        if( getIntent().hasExtra("com.example.npuzzle.Columns") && getIntent().hasExtra("com.example.npuzzle.Rows") ){
            Columns = getIntent().getExtras().getInt( "com.example.npuzzle.Columns" );
            Rows = getIntent().getExtras().getInt( "com.example.npuzzle.Rows" );
            GameBoardLayout.setRowCount( Rows );
            GameBoardLayout.setColumnCount( Columns );
            final BoardArray2D game_board = new BoardArray2D( Rows, Columns );

            final ButtonGame[][] cells = new ButtonGame[Rows][Columns];

            tryAgainButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent main_menu_intent = new Intent( getApplicationContext(), MainActivity.class );
                    startActivity( main_menu_intent );
                }
            });
            for( int i = 0; i<Rows; ++i)
                for( int j=0; j<Columns; ++j) {
                    cells[i][j] = new ButtonGame(this);
                    GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                    cells[i][j].setRow(i);
                    cells[i][j].setColumn(j);
                    cells[i][j].setEnabled(true);
                    param.width = display_metrics.widthPixels / Columns;
                    if( Columns >= Rows )
                        param.height = param.width;

                    if( Columns < Rows ){
                        param.height = HEIGHT/ Rows;
                        param.width = param.height;
                        if( param.width*Columns > display_metrics.widthPixels ){
                            param.width = ( param.width*Columns - ( param.width*Columns - display_metrics.widthPixels ) ) /Columns;
                            param.height = param.width;
                        }
                        int padding = display_metrics.widthPixels - param.width * Columns;
                        padding /= 2;
                        GameBoardLayout.setPadding(padding, 0, 0, 0);


                    }


                    cells[i][j].setLayoutParams( param );
                    if( game_board.cell(i,j) != -1 )
                        cells[i][j].setText(Integer.toString(game_board.cell(i,j)));
                    GameBoardLayout.addView(cells[i][j]);
                    cells[i][j].setGravity(Gravity.CENTER);
                    final int row = i;
                    final int col = j;
                    cells[i][j].setOnClickListener(new ButtonGame.OnClickListener() {
                        public void onClick(View v) {
                            ButtonGame clicked_button = cells[row][col];
                            int empty_row = new Integer(game_board.getEmptyRow() );
                            int empty_col = new Integer(game_board.getEmptyCol() );
                            if( clicked_button.movePossible( game_board ) ){
                                String temp_text = (String)clicked_button.getText();
                                System.out.println("Empty row is " + empty_row + " col" + empty_col );
                                System.out.println("Clicked button ro: " + clicked_button.getRow() + " col" + clicked_button.getColumn());
                                clicked_button.setText("");
                                cells[empty_row][empty_col].setText(temp_text);
                                numberOfMovesTextView.setText(game_board.NumberOfMoves()+"");
                                if( game_board.isSolved() ){
                                    Intent winner_activity = new Intent( getApplicationContext(), WinnerAcitivity.class );
                                    winner_activity.putExtra( "com.example.npuzzle.numberOfMoves", game_board.NumberOfMoves() );
                                    startActivity( winner_activity );
                                }
                            }
                            else
                                System.out.println("Clicked on button with text " + clicked_button.getText() + "row" + clicked_button.getRow() + " col" + clicked_button.getColumn() + "   board: " + game_board.cell(clicked_button.getRow(), clicked_button.getColumn()));
                        }
                    });
                }
        }//end of getIntent check
    }
}
