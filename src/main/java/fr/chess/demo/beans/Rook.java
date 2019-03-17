package fr.chess.demo.beans;

import fr.chess.demo.enums.ChessPieceColorEnum;
import fr.chess.demo.enums.ChessPieceDirectionEnum;
import fr.chess.demo.enums.ChessPieceTypeEnum;
import fr.chess.demo.utils.ChessUtils;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Rook.
 */
@Getter
@Setter
public class Rook extends ChessPiece {

    /**
     * Instantiates a new Rook.
     *
     * @param color the color
     * @param position the position
     */
    public Rook(ChessPieceColorEnum color, Position position) {
        super(color, ChessPieceTypeEnum.ROOK, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<ChessPieceDirectionEnum, Movement> initMapOfMovements() {
        return ChessUtils.collectRookMovements();
    }

}
