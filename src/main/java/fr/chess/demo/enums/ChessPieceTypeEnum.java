package fr.chess.demo.enums;

import lombok.Getter;

/**
 * The enum Chess piece type enum.
 */
@Getter
public enum ChessPieceTypeEnum {

    /**
     * Pawn chess piece type enum.
     */
    PAWN("P", "Pion"),
    /**
     * Rook chess piece type enum.
     */
    ROOK("T", "Tour"),
    /**
     * Knight chess piece type enum.
     */
    KNIGHT("C", "Cavalier"),
    /**
     * King chess piece type enum.
     */
    KING("R", "Roi"),
    /**
     * Queen chess piece type enum.
     */
    QUEEN("R", "Reine"),
    /**
     * Bishop chess piece type enum.
     */
    BISHOP("F", "Fou");

    /**
     * The Abbreviation.
     */
    private String abbreviation;

    /**
     * The Translation in french.
     */
    private String translationInFrench;

    /**
     * Instantiates a new Chess piece type enum.
     *
     * @param abbreviation the abbreviation
     * @param translationInFrench the translation in french
     */
    ChessPieceTypeEnum(String abbreviation, String translationInFrench) {
        this.abbreviation = abbreviation;
        this.translationInFrench = translationInFrench;
    }

}
