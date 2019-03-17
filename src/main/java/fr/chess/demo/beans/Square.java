package fr.chess.demo.beans;

import fr.chess.demo.enums.ChessPieceColorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Square.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Square {

    /**
     * The Position.
     */
    private Position position;

    /**
     * The Opponent chess piece.
     */
    private ChessPiece opponentChessPiece;

    /**
     * The Chess piece.
     */
    private ChessPiece chessPiece;

    /**
     * The Piece already move on.
     */
    private boolean pieceAlreadyMoveOn;

    /**
     * Instantiates a new Square.
     *
     * @param position the position
     */
    public Square(Position position) {
        this.position = position;
        this.pieceAlreadyMoveOn = false;
    }

    /**
     * Has opponent chess piece boolean.
     *
     * @return the boolean
     */
    public boolean hasOpponentChessPiece() {
        return opponentChessPiece != null;
    }

    /**
     * Gets color of opponent chess piece.
     *
     * @return the color of opponent chess piece
     */
    public ChessPieceColorEnum getColorOfOpponentChessPiece() {
        return opponentChessPiece != null ? opponentChessPiece.getColor() : null;
    }

}
