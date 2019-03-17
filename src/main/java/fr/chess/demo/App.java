package fr.chess.demo;

import fr.chess.demo.beans.ChessBoard;
import fr.chess.demo.beans.ChessEngine;
import fr.chess.demo.beans.ChessPiece;
import fr.chess.demo.beans.Position;
import fr.chess.demo.enums.ChessPieceColorEnum;
import fr.chess.demo.enums.ChessPieceTypeEnum;
import fr.chess.demo.utils.ChessUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;

/**
 * The type App.
 */
@Log4j2
public class App {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // Load a list of chess pieces
        List<ChessPiece> listOfChessPieces = loadChessPieces();

        // Instanciate the chess engine
        ChessEngine chessEngine = new ChessEngine();

        // Add a list of chess pieces and the chess piece to move on the chess board
        ChessBoard.get().addListOfChessPieces(listOfChessPieces);
        ChessBoard.get().addChessPieceToMove(ChessUtils.createChessPiece(
                ChessPieceColorEnum.WHITE,
                ChessPieceTypeEnum.ROOK,
                new Position("d5")));

        // Start the generation of possible movements
        chessEngine.start();

    }

    /**
     * Load chess pieces list.
     *
     * @return the list
     */
    private static List<ChessPiece> loadChessPieces() {
        List<ChessPiece> listOfChessPieces = new ArrayList<>();
        listOfChessPieces.add(
                ChessUtils.createChessPiece(
                        ChessPieceColorEnum.BLACK,
                        ChessPieceTypeEnum.PAWN,
                        new Position("d7")));
        listOfChessPieces.add(
                ChessUtils.createChessPiece(
                        ChessPieceColorEnum.WHITE,
                        ChessPieceTypeEnum.KNIGHT,
                        new Position("d2")));
        listOfChessPieces.add(
                ChessUtils.createChessPiece(
                        ChessPieceColorEnum.BLACK,
                        ChessPieceTypeEnum.QUEEN,
                        new Position("a5")));
        return listOfChessPieces;
    }

}
