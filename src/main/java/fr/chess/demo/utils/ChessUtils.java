package fr.chess.demo.utils;

import static fr.chess.demo.enums.ChessPieceDirectionEnum.DOWN;
import static fr.chess.demo.enums.ChessPieceDirectionEnum.DOWN_LEFT;
import static fr.chess.demo.enums.ChessPieceDirectionEnum.DOWN_RIGHT;
import static fr.chess.demo.enums.ChessPieceDirectionEnum.LEFT;
import static fr.chess.demo.enums.ChessPieceDirectionEnum.RIGHT;
import static fr.chess.demo.enums.ChessPieceDirectionEnum.UP;
import static fr.chess.demo.enums.ChessPieceDirectionEnum.UP_LEFT;
import static fr.chess.demo.enums.ChessPieceDirectionEnum.UP_RIGHT;
import static fr.chess.demo.enums.ChessPieceDirectionEnum.values;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import fr.chess.demo.beans.ChessBoard;
import fr.chess.demo.beans.Pawn;
import fr.chess.demo.beans.Rook;
import fr.chess.demo.beans.Bishop;
import fr.chess.demo.beans.ChessPiece;
import fr.chess.demo.beans.King;
import fr.chess.demo.beans.Knight;
import fr.chess.demo.beans.Movement;
import fr.chess.demo.beans.Position;
import fr.chess.demo.beans.Queen;
import fr.chess.demo.beans.Square;
import fr.chess.demo.enums.ChessPieceColorEnum;
import fr.chess.demo.enums.ChessPieceDirectionEnum;
import fr.chess.demo.enums.ChessPieceTypeEnum;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;
import org.fusesource.jansi.Ansi;

/**
 * The type Chess utils.
 */
@Log4j2
public final class ChessUtils {

    /**
     * The constant CHESS_BOARD_TEXT.
     */
    private static final String CHESS_BOARD_TEXT = "Actual Chess Board:";

    /**
     * The constant SPACE.
     */
    private static final String SPACE = " ";

    /**
     * The constant CHESS_BOARD_HEADER.
     */
    private static final String CHESS_BOARD_HEADER = Ansi.ansi().fgBlue()
            .a("    a  b  c  d  e  f  g  h    ").toString();

    /**
     * The Coord in string map.
     */
    private static final BiMap<Integer, String> COORD_IN_STRING_MAP;

    static {
        COORD_IN_STRING_MAP = ImmutableBiMap.<Integer, String>builder()
                .put(0, "a")
                .put(1, "b")
                .put(2, "c")
                .put(3, "d")
                .put(4, "e")
                .put(5, "f")
                .put(6, "g")
                .put(7, "h")
                .build();
    }

    /**
     * Private constructor.
     */
    private ChessUtils() {
        // Do nothing
    }

    /**
     * Gets coordinate in string.
     *
     * @param coord the coord
     * @return the coordinate in string
     */
    public static String getCoordinateInString(Integer coord) {
        return COORD_IN_STRING_MAP.get(coord);
    }

    /**
     * Gets coordinate from char.
     *
     * @param coord the coord
     * @return the coordinate from char
     */
    public static Integer getCoordinateFromChar(Character coord) {
        return COORD_IN_STRING_MAP.inverse().get(String.valueOf(coord).toLowerCase());
    }

    /**
     * Create chess piece chess piece.
     *
     * @param color the color
     * @param type the type
     * @param position the position
     * @return the chess piece
     */
    public static ChessPiece createChessPiece(ChessPieceColorEnum color, ChessPieceTypeEnum type,
            Position position) {
        ChessPiece newChessPiece = null;
        switch (type) {
            case KING:
                newChessPiece = new King(color, position);
                break;
            case PAWN:
                newChessPiece = new Pawn(color, position);
                break;
            case QUEEN:
                newChessPiece = new Queen(color, position);
                break;
            case BISHOP:
                newChessPiece = new Bishop(color, position);
                break;
            case KNIGHT:
                newChessPiece = new Knight(color, position);
                break;
            case ROOK:
                newChessPiece = new Rook(color, position);
                break;
            default:
                log.warn("The type of new chess piece to create is unknown");
                break;
        }
        return newChessPiece;
    }

    /**
     * Display chess board.
     */
    public static void displayChessBoard() {

        if (!ChessBoard.get().getMapOfSquares().isEmpty()) {

            StringBuilder sbChessBoard = new StringBuilder();

            sbChessBoard.append(CHESS_BOARD_TEXT);
            sbChessBoard.append(System.lineSeparator());
            sbChessBoard.append(CHESS_BOARD_HEADER);
            sbChessBoard.append(System.lineSeparator());

            for (int y = ChessBoard.NUMBER_OF_LINES - 1; y >= 0; y--) {
                sbChessBoard.append(Ansi.ansi().fgBlue().a(y + 1).toString()).append(SPACE)
                        .append(SPACE)
                        .append(SPACE);
                for (int x = 0; x < ChessBoard.NUMBER_OF_COLUMNS; x++) {
                    Square square = ChessBoard.get().getMapOfSquares().get(Position.toString(x, y));
                    if (square.getOpponentChessPiece() != null) {
                        sbChessBoard
                                .append(getAsciiCodeOfChessPiece(square.getOpponentChessPiece()));
                        sbChessBoard.append(SPACE).append(SPACE);
                    } else if (square.getChessPiece() != null) {
                        sbChessBoard.append(getAsciiCodeOfChessPiece(square.getChessPiece()));
                        sbChessBoard.append(SPACE).append(SPACE);
                    } else if (square.isPieceAlreadyMoveOn()) {
                        sbChessBoard.append(Ansi.ansi().fgBrightBlack().a("X").toString());
                        sbChessBoard.append(SPACE).append(SPACE);
                    } else {
                        sbChessBoard.append(SPACE).append(SPACE).append(SPACE);
                    }

                }
                sbChessBoard.append(SPACE).append(Ansi.ansi().fgBlue().a(y + 1).toString());
                sbChessBoard.append(System.lineSeparator());
            }

            sbChessBoard.append(CHESS_BOARD_HEADER);

            log.debug(sbChessBoard.toString());

        } else {
            log.warn("The chess board has no square");
        }

    }

    /**
     * Is correct position boolean.
     *
     * @param chessPiece the chess piece
     * @return the boolean
     */
    public static boolean isNotCorrectPosition(ChessPiece chessPiece) {
        return !isAnAuthorizedMove(chessPiece.getActualPosition().getCoordX(),
                chessPiece.getActualPosition().getCoordY());
    }

    /**
     * Is an authorized move boolean.
     *
     * @param newCoordX the new coord x
     * @param newCoordY the new coord y
     * @return the boolean
     */
    public static boolean isAnAuthorizedMove(Integer newCoordX, Integer newCoordY) {
        return newCoordX >= 0
                && newCoordX < ChessBoard.NUMBER_OF_COLUMNS
                && newCoordY >= 0
                && newCoordY < ChessBoard.NUMBER_OF_LINES;
    }

    private static String getAsciiCodeOfChessPiece(ChessPiece chessPiece) {
        String asciiCode = null;
        if (ChessPieceColorEnum.WHITE.equals(chessPiece.getColor())) {
            switch (chessPiece.getType()) {
                case PAWN:
                    asciiCode = Ansi.ansi().fgGreen().a("♙").toString();
                    break;
                case ROOK:
                    asciiCode = Ansi.ansi().fgGreen().a("♖").toString();
                    break;
                case KNIGHT:
                    asciiCode = Ansi.ansi().fgGreen().a("♘").toString();
                    break;
                case KING:
                    asciiCode = Ansi.ansi().fgGreen().a("♔").toString();
                    break;
                case QUEEN:
                    asciiCode = Ansi.ansi().fgGreen().a("♕").toString();
                    break;
                case BISHOP:
                    asciiCode = Ansi.ansi().fgGreen().a("♗").toString();
                    break;
                default:
                    break;
            }
        } else {
            switch (chessPiece.getType()) {
                case KING:
                    asciiCode = Ansi.ansi().fgRed().a("♚").toString();
                    break;
                case QUEEN:
                    asciiCode = Ansi.ansi().fgRed().a("♛").toString();
                    break;
                case ROOK:
                    asciiCode = Ansi.ansi().fgRed().a("♜").toString();
                    break;
                case BISHOP:
                    asciiCode = Ansi.ansi().fgRed().a("♝").toString();
                    break;
                case KNIGHT:
                    asciiCode = Ansi.ansi().fgRed().a("♞").toString();
                    break;
                case PAWN:
                    asciiCode = Ansi.ansi().fgRed().a("♟").toString();
                    break;
                default:
                    break;
            }
        }
        return asciiCode;
    }

    /**
     * Collect bishop movements map.
     *
     * @return the map
     */
    @SuppressWarnings("Duplicates")
    public static Map<ChessPieceDirectionEnum, Movement> collectBishopMovements() {
        return Arrays.stream(values())
                .filter(d -> d != UP
                        && d != DOWN
                        && d != LEFT
                        && d != RIGHT)
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                ChessUtils::getBishopMovementFromDirection
                        ));
    }

    /**
     * Collect king movements map.
     *
     * @return the map
     */
    public static Map<ChessPieceDirectionEnum, Movement> collectKingMovements() {
        return Stream.of(values()).collect(
                Collectors.toMap(
                        Function.identity(),
                        ChessUtils::getKingMovementFromDirection
                ));
    }

    /**
     * Collect knight movements map.
     *
     * @return the map
     */
    @SuppressWarnings("Duplicates")
    public static Map<ChessPieceDirectionEnum, Movement> collectKnightMovements() {
        return Arrays.stream(values())
                .filter(d -> d != UP
                        && d != DOWN
                        && d != LEFT
                        && d != RIGHT)
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                ChessUtils::getKnightMovementFromDirection
                        ));
    }

    /**
     * Collect pawnt movements map.
     *
     * @return the map
     */
    public static Map<ChessPieceDirectionEnum, Movement> collectPawntMovements() {
        return Stream.of(values()).collect(
                Collectors.toMap(
                        Function.identity(),
                        ChessUtils::getPawnMovementFromDirection
                ));
    }

    /**
     * Collect queen movements map.
     *
     * @return the map
     */
    public static Map<ChessPieceDirectionEnum, Movement> collectQueenMovements() {
        return Stream.of(values()).collect(
                Collectors.toMap(
                        Function.identity(),
                        ChessUtils::getQueenMovementFromDirection
                ));
    }

    /**
     * Collect rook movements map.
     *
     * @return the map
     */
    @SuppressWarnings("Duplicates")
    public static Map<ChessPieceDirectionEnum, Movement> collectRookMovements() {
        return Arrays.stream(values())
                .filter(d -> d != UP_LEFT
                        && d != UP_RIGHT
                        && d != DOWN_LEFT
                        && d != DOWN_RIGHT)
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                ChessUtils::getRookMovementFromDirection
                        ));
    }

    /**
     * Gets bishop movement from direction.
     *
     * @param direction the direction
     * @return the bishop movement from direction
     */
    private static Movement getBishopMovementFromDirection(ChessPieceDirectionEnum direction) {
        Movement movement = null;
        switch (direction) {
            case UP_LEFT:
                movement = new Movement(-1, 1);
                break;
            case UP_RIGHT:
                movement = new Movement(1, 1);
                break;
            case DOWN_LEFT:
                movement = new Movement(-1, -1);
                break;
            case DOWN_RIGHT:
                movement = new Movement(1, -1);
                break;
            default:
                break;
        }
        return movement;
    }

    /**
     * Gets king movement from direction.
     *
     * @param direction the direction
     * @return the king movement from direction
     */
    private static Movement getKingMovementFromDirection(ChessPieceDirectionEnum direction) {
        Movement movement = null;
        switch (direction) {
            case UP:
                movement = new Movement(0, 1);
                break;
            case DOWN:
                movement = new Movement(0, -1);
                break;
            case LEFT:
                movement = new Movement(-1, 0);
                break;
            case RIGHT:
                movement = new Movement(1, 0);
                break;
            case UP_LEFT:
                movement = new Movement(-1, 1);
                break;
            case UP_RIGHT:
                movement = new Movement(1, 1);
                break;
            case DOWN_LEFT:
                movement = new Movement(-1, -1);
                break;
            case DOWN_RIGHT:
                movement = new Movement(1, -1);
                break;
            default:
                break;
        }
        return movement;
    }

    /**
     * Gets queen movement from direction.
     *
     * @param direction the direction
     * @return the queen movement from direction
     */
    private static Movement getQueenMovementFromDirection(final ChessPieceDirectionEnum direction) {
        return getKingMovementFromDirection(direction);
    }

    /**
     * Gets pawn movement from direction.
     *
     * @param direction the direction
     * @return the pawn movement from direction
     */
    private static Movement getPawnMovementFromDirection(final ChessPieceDirectionEnum direction) {
        return getKingMovementFromDirection(direction);
    }

    /**
     * Gets knight movement from direction.
     *
     * @param direction the direction
     * @return the knight movement from direction
     */
    private static Movement getKnightMovementFromDirection(
            final ChessPieceDirectionEnum direction) {
        Movement movement = null;
        switch (direction) {
            case UP_LEFT:
                movement = new Movement(-1, 2);
                break;
            case UP_RIGHT:
                movement = new Movement(1, 2);
                break;
            case DOWN_LEFT:
                movement = new Movement(-1, -2);
                break;
            case DOWN_RIGHT:
                movement = new Movement(1, -2);
                break;
            default:
                break;
        }
        return movement;
    }

    /**
     * Gets rook movement from direction.
     *
     * @param direction the direction
     * @return the rook movement from direction
     */
    private static Movement getRookMovementFromDirection(final ChessPieceDirectionEnum direction) {
        Movement movement = null;
        switch (direction) {
            case UP:
                movement = new Movement(0, 1);
                break;
            case DOWN:
                movement = new Movement(0, -1);
                break;
            case LEFT:
                movement = new Movement(-1, 0);
                break;
            case RIGHT:
                movement = new Movement(1, 0);
                break;
            default:
                break;
        }
        return movement;
    }

}
