package fr.chess.demo.beans;

import fr.chess.demo.utils.ChessUtils;
import fr.chess.demo.enums.ChessPieceColorEnum;
import fr.chess.demo.enums.ChessPieceDirectionEnum;
import fr.chess.demo.enums.ChessPieceTypeEnum;
import java.util.Map;

/**
 * The type King.
 */
public class King extends ChessPiece {

    /**
     * Instantiates a new King.
     *
     * @param color the color
     * @param position the position
     */
    public King(ChessPieceColorEnum color, Position position) {
        super(color, ChessPieceTypeEnum.KING, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<ChessPieceDirectionEnum, Movement> initMapOfMovements() {
        return ChessUtils.collectKingMovements();
    }

}
