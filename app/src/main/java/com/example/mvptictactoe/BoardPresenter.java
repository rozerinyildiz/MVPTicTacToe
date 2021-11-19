package com.example.mvptictactoe;

import android.view.View;

public class BoardPresenter implements BoardListener{

    BoardView view;
    Board board;
    public BoardPresenter(BoardView boardView) {
        this.view = boardView;
        this.board = new Board(this);

    }
    public void move(byte row, byte col){
        board.move(row,col);
    }
    static class CellClickListener implements View.OnClickListener {
        BoardPresenter presenter;
        byte row;
        byte col;
        public CellClickListener(BoardPresenter presenter, byte row, byte col) {
            this.presenter=presenter;
            this.row=row;
            this.col=col;
        }

        @Override
        public void onClick(View view) {
            presenter.move(row,col);
        }
    }

    @Override
    public void playedAt(byte player, byte row, byte col) {

        if (player == BoardListener.PLAYER_1){
            view.putSymbol(BoardView.PLAYER_1_SYMBOL,row,col);
        }else if(player == BoardListener.PLAYER_2){
            view.putSymbol(BoardView.PLAYER_2_SYMBOL,row,col);
        }

    }

    @Override
    public void gameEnded(byte winner) {

        switch (winner){
            case BoardListener.NO_ONE:
                view.gameEnded(BoardView.DRAW);
                break;
            case BoardListener.PLAYER_1:
                view.gameEnded(BoardView.PLAYER_1_WINNER);
                break;
            case BoardListener.PLAYER_2:
                view.gameEnded(BoardView.PLAYER_2_WINNER);
                break;
        }
    }

    @Override
    public void invalidPlay(byte row, byte col) {
        view.invalidPlay(row,col);
    }


}
