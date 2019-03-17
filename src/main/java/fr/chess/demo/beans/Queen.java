package fr.chess.demo.beans;

import fr.chess.demo.enums.ChessPieceTypeEnum;
import fr.chess.demo.utils.ChessUtils;
import fr.chess.demo.enums.ChessPieceColorEnum;
import fr.chess.demo.enums.ChessPieceDirectionEnum;
import java.util.Map;

/**
 * The type Queen.
 */
public class Queen extends ChessPiece {

    /**
     * Instantiates a new Queen.
     *
     * @param color the color
     * @param position the position
     */
    public Queen(ChessPieceColorEnum color, Position position) {
        super(color, ChessPieceTypeEnum.QUEEN, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<ChessPieceDirectionEnum, Movement> initMapOfMovements() {
        return ChessUtils.collectQueenMovements();
    }

}
