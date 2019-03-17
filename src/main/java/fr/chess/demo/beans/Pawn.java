package fr.chess.demo.beans;

import fr.chess.demo.enums.ChessPieceColorEnum;
import fr.chess.demo.enums.ChessPieceDirectionEnum;
import fr.chess.demo.enums.ChessPieceTypeEnum;
import fr.chess.demo.utils.ChessUtils;
import java.util.Map;

/**
 * The type Pawn.
 */
public class Pawn extends ChessPiece {

    /**
     * Instantiates a new Pawn.
     *
     * @param color the color
     * @param position the position
     */
    public Pawn(ChessPieceColorEnum color, Position position) {
        super(color, ChessPieceTypeEnum.PAWN, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<ChessPieceDirectionEnum, Movement> initMapOfMovements() {
        return ChessUtils.collectPawntMovements();
    }

}
