package fr.chess.demo.beans;

import fr.chess.demo.enums.ChessPieceTypeEnum;
import fr.chess.demo.utils.ChessUtils;
import fr.chess.demo.enums.ChessPieceColorEnum;
import fr.chess.demo.enums.ChessPieceDirectionEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * The type Chess piece.
 */
@Log4j2
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ChessPiece {

    /**
     * The Color.
     */
    protected ChessPieceColorEnum color;

    /**
     * The Type.
     */
    protected ChessPieceTypeEnum type;

    /**
     * The Actual position.
     */
    protected Position actualPosition;

    /**
     * The Initial position.
     */
    protected Position initialPosition;

    /**
     * The Map of movements.
     */
    protected Map<ChessPieceDirectionEnum, Movement> mapOfMovements;

    /**
     * The List of moves.
     */
    protected List<String> listOfMoves;

    /**
     * Instantiates a new Chess piece.
     *
     * @param color the color
     * @param type the type
     * @param position the position
     */
    public ChessPiece(ChessPieceColorEnum color, ChessPieceTypeEnum type, Position position) {
        this.color = color;
        this.type = type;
        this.actualPosition = position;
        this.initialPosition = new Position(position);
        this.mapOfMovements = initMapOfMovements();
        this.listOfMoves = new ArrayList<>();
    }

    /**
     * Init map of movements map.
     *
     * @return the map
     */
    protected abstract Map<ChessPieceDirectionEnum, Movement> initMapOfMovements();

    /**
     * Generate possible moves.
     */
    public void generatePossibleMoves() {

        if (mapOfMovements != null) {

            mapOfMovements.forEach((direction, m) -> {

                boolean shouldChangeDirection = false;

                while (!shouldChangeDirection) {

                    ChessUtils.displayChessBoard();

                    Integer newCoordX = actualPosition.getCoordX() + m.getTranslateX();
                    Integer newCoordY = actualPosition.getCoordY() + m.getTranslateY();

                    boolean shouldMove = true;
                    boolean shoudInit = false;
                    boolean hasEat = false;

                    if (ChessUtils.isAnAuthorizedMove(newCoordX, newCoordY)) {

                        String oldPosition = actualPosition.toString();
                        String newPosition = Position.toString(newCoordX, newCoordY);
                        Square newSquare = ChessBoard.get().getMapOfSquares().get(newPosition);

                        if (newSquare.hasOpponentChessPiece()) {
                            shoudInit = true;
                            if (newSquare.getColorOfOpponentChessPiece().equals(color)) {
                                shouldChangeDirection = true;
                                shouldMove = false;
                            } else {
                                hasEat = true;
                                shouldChangeDirection = true;
                            }
                        }

                        if (hasEat) {
                            ChessBoard.get().eatChessPiece(newPosition);
                        }

                        if (shouldMove) {
                            ChessBoard.get()
                                    .changeChessPieceCoordinate(this, new Position(newPosition),
                                            new Position(oldPosition), false);
                            listOfMoves.add(
                                    String.format("%s%s%s%s", type.getAbbreviation(),
                                            initialPosition.toString(),
                                            hasEat ? "x" : "-", actualPosition.toString())
                            );
                        }

                        if (shoudInit) {
                            ChessBoard.get()
                                    .changeChessPieceCoordinate(this, initialPosition,
                                            actualPosition, true);
                        }

                    } else {
                        shouldChangeDirection = true;
                        ChessBoard.get()
                                .changeChessPieceCoordinate(this, initialPosition, actualPosition,
                                        true);
                    }
                }

            });
        }
    }

    /**
     * Init to initial position.
     */
    public void initToInitialPosition() {
        actualPosition = initialPosition;
    }

}
