package fr.chess.demo.beans;

import fr.chess.demo.utils.ChessUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;

/**
 * The type Chess board.
 */
@Data
@Log4j2
public class ChessBoard {

    /**
     * The constant NUMBER_OF_COLUMNS.
     */
    public static final Integer NUMBER_OF_COLUMNS = 8;

    /**
     * The constant NUMBER_OF_LINES.
     */
    public static final Integer NUMBER_OF_LINES = 8;

    /**
     * The constant NUMBER_OF_CASES.
     */
    public static final Integer NUMBER_OF_CASES = NUMBER_OF_LINES * NUMBER_OF_COLUMNS;

    /**
     * The constant instance.
     */
    private static ChessBoard instance;

    /**
     * The Map of squares.
     */
    private Map<String, Square> mapOfSquares;

    /**
     * The Chess piece to move.
     */
    private ChessPiece chessPieceToMove;

    private ChessBoard() {
        super();
        this.mapOfSquares = new HashMap<>();
    }

    /**
     * Get chess board.
     *
     * @return the chess board
     */
    public static ChessBoard get() {
        if (instance == null) {
            instance = new ChessBoard();
        }
        return instance;
    }

    /**
     * Init chessboard.
     */
    void initChessboard() {
        log.debug("Initialization of the Chess Board");
        for (int y = 0; y < NUMBER_OF_LINES; y++) {
            for (int x = 0; x < NUMBER_OF_COLUMNS; x++) {
                Position position = new Position(x, y);
                mapOfSquares.put(position.toString(), new Square(position));
            }
        }
    }

    /**
     * Add chess piece to move.
     *
     * @param chessPiece the chess piece
     */
    public void addChessPieceToMove(ChessPiece chessPiece) {
        addChessPieceToMoveOnSquare(chessPiece);
    }

    /**
     * Add list of chess pieces.
     *
     * @param listOfChessPieces the list of chess pieces
     */
    public void addListOfChessPieces(List<ChessPiece> listOfChessPieces) {
        if (CollectionUtils.isNotEmpty(listOfChessPieces)) {
            listOfChessPieces.forEach(this::addOpponentChessPiece);
        }
    }

    /**
     * Eat chess piece.
     *
     * @param position the position
     */
    void eatChessPiece(String position) {
        mapOfSquares.get(position).setOpponentChessPiece(null);
    }


    /**
     * Change chess piece coordinate.
     *
     * @param chessPiece the chess piece
     * @param newPosition the new position
     * @param oldPosition the old position
     * @param shouldInit the should init
     */
    void changeChessPieceCoordinate(ChessPiece chessPiece, Position newPosition,
            Position oldPosition, boolean shouldInit) {
        if (shouldInit) {
            chessPiece.initToInitialPosition();
            changeChessPieceSquare(chessPiece, newPosition, oldPosition);
        } else {
            chessPiece.setActualPosition(newPosition);
            changeChessPieceSquare(chessPiece, newPosition, oldPosition);
        }
    }

    /**
     * Add chess piece on square.
     *
     * @param chessPiece the chess piece
     * @param isOpponent the is opponent
     */
    private void addChessPieceOnSquare(ChessPiece chessPiece, boolean isOpponent) {
        if (ChessUtils.isNotCorrectPosition(chessPiece)) {
            log.error("Unable to add chess piece at [%, %] actualPosition",
                    chessPiece.getActualPosition().getCoordX(),
                    chessPiece.getActualPosition().getCoordY());
        } else {
            if (isOpponent) {
                mapOfSquares.get(chessPiece.getActualPosition().toString())
                        .setOpponentChessPiece(chessPiece);
            } else {
                mapOfSquares.get(chessPiece.getActualPosition().toString())
                        .setPieceAlreadyMoveOn(true);
                mapOfSquares.get(chessPiece.getActualPosition().toString())
                        .setChessPiece(chessPiece);
            }
        }
    }

    /**
     * Add chess piece to move on square.
     *
     * @param chessPiece the chess piece
     */
    private void addChessPieceToMoveOnSquare(ChessPiece chessPiece) {
        if (ChessUtils.isNotCorrectPosition(chessPiece)) {
            log.error("Unable to add chess piece at [%, %] actualPosition",
                    chessPiece.getActualPosition().getCoordX(),
                    chessPiece.getActualPosition().getCoordY());
        } else {
            chessPieceToMove = chessPiece;
            addChessPieceOnSquare(chessPieceToMove, false);
        }
    }

    /**
     * Change chess piece square.
     *
     * @param chessPiece the chess piece
     * @param newPosition the new position
     * @param oldPosition the old position
     */
    private void changeChessPieceSquare(ChessPiece chessPiece, Position newPosition,
            Position oldPosition) {
        ChessBoard.get().getMapOfSquares().get(oldPosition.toString()).setChessPiece(null);
        ChessBoard.get().getMapOfSquares().get(newPosition.toString()).setChessPiece(chessPiece);
        ChessBoard.get().getMapOfSquares().get(newPosition.toString()).setPieceAlreadyMoveOn(true);
    }

    /**
     * Add opponent chess piece.
     *
     * @param chessPiece the chess piece
     */
    private void addOpponentChessPiece(ChessPiece chessPiece) {
        addChessPieceOnSquare(chessPiece, true);
    }

}
