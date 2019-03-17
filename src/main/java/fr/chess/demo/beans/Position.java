package fr.chess.demo.beans;

import fr.chess.demo.utils.ChessUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * The type Position.
 */
@Getter
@Setter
@Log4j2
@AllArgsConstructor
public class Position {

    /**
     * The Coord x.
     */
    private Integer coordX;

    /**
     * The Coord y.
     */
    private Integer coordY;

    /**
     * Instantiates a new Position.
     *
     * @param position the position
     */
    public Position(Position position) {
        this.coordX = position.getCoordX();
        this.coordY = position.getCoordY();
    }

    /**
     * Instantiates a new Position.
     *
     * @param position the position
     */
    public Position(String position) {
        if (position == null || position.length() != 2 || !position.matches("^[a-z][0-9]")) {
            log.error("The actualPosition %s is incorrect", position);
        } else {
            this.coordX = ChessUtils.getCoordinateFromChar(position.charAt(0));
            this.coordY = Integer.valueOf(String.valueOf(position.charAt(1))) - 1;
        }
    }

    /**
     * To string string.
     *
     * @param coordX the coord x
     * @param coordY the coord y
     * @return the string
     */
    public static String toString(Integer coordX, Integer coordY) {
        return String.format("%s%s", ChessUtils.getCoordinateInString(coordX), coordY + 1);
    }

    @Override
    public String toString() {
        return String.format("%s%s", ChessUtils.getCoordinateInString(coordX), coordY + 1);
    }

}
