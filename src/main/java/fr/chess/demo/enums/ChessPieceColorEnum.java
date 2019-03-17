package fr.chess.demo.enums;

import lombok.Getter;

/**
 * The enum Chess piece color enum.
 */
@Getter
public enum ChessPieceColorEnum {

    /**
     * White chess piece color enum.
     */
    WHITE("W"),
    /**
     * Black chess piece color enum.
     */
    BLACK("B");

    /**
     * The abbreviation.
     */
    private String abbreviation;

    /**
     * Instantiates a new Chess piece color enum.
     *
     * @param abbreviation the abbreviation
     */
    ChessPieceColorEnum(String abbreviation) {
        this.abbreviation = abbreviation;
    }

}
