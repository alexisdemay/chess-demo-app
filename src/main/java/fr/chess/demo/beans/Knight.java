package fr.chess.demo.beans;

import fr.chess.demo.enums.ChessPieceTypeEnum;
import fr.chess.demo.utils.ChessUtils;
import fr.chess.demo.enums.ChessPieceColorEnum;
import fr.chess.demo.enums.ChessPieceDirectionEnum;
import java.util.Map;

/**
 * The type Knight.
 */
public class Knight extends ChessPiece {

    /**
     * Instantiates a new Knight.
     *
     * @param color the color
     * @param position the position
     */
    public Knight(ChessPieceColorEnum color, Position position) {
        super(color, ChessPieceTypeEnum.KNIGHT, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<ChessPieceDirectionEnum, Movement> initMapOfMovements() {
        return ChessUtils.collectKnightMovements();
    }

}
