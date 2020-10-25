package com.example.npuzzle;

import android.content.Context;
import android.widget.Button;

import com.example.npuzzle.hwk7.BoardArray2D;

public class ButtonGame extends Button {
    private int row;
    private int column;

    public ButtonGame(Context context) {
        super(context);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    public boolean movePossible(BoardArray2D game_board){
        if (game_board.moveIfPossible(row, column)) {
            System.out.println("bOARD\n\n\n" + game_board);
            return true;
        } else {
            return false;
        }
    }
}
