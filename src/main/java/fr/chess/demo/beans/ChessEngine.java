package fr.chess.demo.beans;

import lombok.extern.log4j.Log4j2;

/**
 * The type Chess engine.
 */
@Log4j2
public class ChessEngine {

    /**
     * Instantiates a new Chess engine.
     */
    public ChessEngine() {
        ChessBoard.get().initChessboard();
    }

    /**
     * Start.
     */
    public void start() {
        ChessBoard.get().getChessPieceToMove().generatePossibleMoves();
        ChessBoard.get().getChessPieceToMove().getListOfMoves().forEach(log::info);
    }

}
