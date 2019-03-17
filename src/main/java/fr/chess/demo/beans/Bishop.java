package fr.chess.demo.beans;

import fr.chess.demo.enums.ChessPieceTypeEnum;
import fr.chess.demo.utils.ChessUtils;
import fr.chess.demo.enums.ChessPieceColorEnum;
import fr.chess.demo.enums.ChessPieceDirectionEnum;
import java.util.Map;

/**
 * The type Bishop.
 */
public class Bishop extends ChessPiece {

    /**
     * Instantiates a new Bishop.
     *
     * @param color the color
     * @param position the position
     */
    public Bishop(ChessPieceColorEnum color, Position position) {
        super(color, ChessPieceTypeEnum.BISHOP, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<ChessPieceDirectionEnum, Movement> initMapOfMovements() {
        return ChessUtils.collectBishopMovements();
    }

}
