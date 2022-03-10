package Model;

import Controller.IMovable;
import Model.Pieces.King;
import Model.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Model implements IMovable {
    //TODO move some stuff from Model.Board to this file
    private Board board = new Board();


    /////////////////////////////////////////////////////////////////////////////////////

    private List<Move> getPossibleMoves(){
        List<Piece> teamList = (board.isCurrentPlayerIsWhite() ? board.whitePieces : board.blackPieces);
        List<Move> allPossibleMoves = new ArrayList<>();
        for (Piece piece : teamList){
            allPossibleMoves.addAll(piece.getPossibleMoves(piece.getPosition(), board));
        }
        return allPossibleMoves;
    }

    private List<Move> getPossibleThreats(){
        List<Piece> enemyList = (board.isCurrentPlayerIsWhite() ? board.blackPieces : board.whitePieces);
        List<Move> allThreatMoves = new ArrayList<>();
        for (Piece piece : enemyList){
            allThreatMoves.addAll(piece.getPossibleMoves(piece.getPosition(), board));
        }
        return allThreatMoves;
    }


    @Override
    public List<Move> getLegalMoves(){
        List<Move> moves = getPossibleMoves();
        List<Move> threats = getPossibleThreats();
        List<Move> illegalMoves = new ArrayList<>();
        List<Move> kingInCheck = new ArrayList<>();
        List<Integer> threatSquares = new ArrayList<>();

        for (Move move : threats){ threatSquares.add(move.to);}

        int king = (board.isCurrentPlayerIsWhite() ? getKing(Team.WHITE).getPosition() : getKing(Team.BLACK).getPosition());

        // checking if king is attacked.
        for (Move threat : threats){
            if (threat.to == king){
                kingInCheck.add(threat);
            }
        }

        for (Move move : moves){
             // checking if the king is moving into an attacked square.
            if (move.from == king && threatSquares.contains(move.to)){
                illegalMoves.add(move);
                continue;
            }

            // checking if king is attacked by a piece, and the move does not kill that piece.
            if (kingInCheck.size() == 1 && move.to != kingInCheck.get(0).from && move.from != king){
                // if the attacking piece is not a queen rook or bishop:
                Type attacker = board.getSquare(kingInCheck.get(0).from).getPiece().type;
                if (!(attacker == Type.QUEEN || attacker == Type.ROOK || attacker == Type.BISHOP)){
                    // then we can't block.
                    illegalMoves.add(move);
                    continue;
                } else if (!board.squaresBetween(king, kingInCheck.get(0).from).contains(board.getSquare(move.to))){
                    // if you can block but didn't:
                    illegalMoves.add(move);
                    continue;
                }
            }
            // checking for double check of the king.
            if (kingInCheck.size() > 1){
                if (move.from != king){
                    illegalMoves.add(move);
                }
            }

            // Pawns
            if (board.getPiece(move.from).type == Type.PAWN){
                // White
                if (board.getPiece(move.from).team == Team.WHITE){
                    // checking if double move is blocked
                    if (move.to ==  move.from + 16 && board.getPiece(move.from + 8) != null){
                        illegalMoves.add(move);
                    }
                }

                // Black
                if (board.getPiece(move.from).team == Team.BLACK){
                    // checking if double move is blocked
                    if (move.to ==  move.from - 16 && board.getPiece(move.from - 8) != null){
                        illegalMoves.add(move);
                    }
                }
            }
        }

        // TODO check for pinned pieces and add their moves to illegalMoves.

        // checking if castling is illegal
        King kingPiece = (King) getKing();
        // WHITE
        if (king == 4 ) {
            // King side
            if (threatSquares.contains(4) || threatSquares.contains(5) || threatSquares.contains(6) ||
                    !kingPiece.castleKingSide || board.getSquare(5).getPiece() != null ||
                    board.getSquare(6).getPiece() != null) {
                illegalMoves.add(new Move(4, 6, new Move(7, 5)));
            }
            // Queen side
            if (threatSquares.contains(4) || threatSquares.contains(3) || threatSquares.contains(2) ||
                    !kingPiece.castleQueenSide || board.getSquare(1).getPiece() != null ||
                    board.getSquare(2).getPiece() != null || board.getSquare(3).getPiece() != null) {
                illegalMoves.add(new Move(4, 2, new Move(0, 3)));
            }
        }

        // BLACK
        if (king == 60 ) {
            // King side
            if (threatSquares.contains(60) || threatSquares.contains(61) || threatSquares.contains(62) ||
                    !kingPiece.castleKingSide || board.getSquare(61).getPiece() != null ||
                    board.getSquare(62).getPiece() != null) {
                illegalMoves.add(new Move(60, 62, new Move(63, 61)));
            }
            // Queen side
            if (threatSquares.contains(60) || threatSquares.contains(59) || threatSquares.contains(58) ||
                    !kingPiece.castleQueenSide || board.getSquare(57).getPiece() != null ||
                    board.getSquare(58).getPiece() != null || board.getSquare(59).getPiece() != null) {
                illegalMoves.add(new Move(60, 58, new Move(56, 59)));
            }
        }
        moves.removeAll(illegalMoves);
        return moves;
    }

    // helper function only used in getLegalMoves()
    private Piece getKing(){
        List<Piece> teamList = (board.isCurrentPlayerIsWhite() ? board.whitePieces : board.blackPieces);
        Piece king = null;
        for (Piece piece : teamList){
            if (piece.type == Type.KING){king = piece;}
        }
        return king;
    }

    // helper function only used in getLegalMoves()
    private Piece getKing(Team team){
        List<Piece> teamList = (team == Team.WHITE ? board.whitePieces : board.blackPieces);
        Piece king = null;
        for (Piece piece : teamList){
            if (piece.type == Type.KING){king = piece;}
        }
        return king;
    }

    /**
     * helper function to pass info from board to controller.
     */
    public boolean isSquareFriendly(int squareId){return board.isSquareFriendly(squareId);}


    @Override
    public List<Piece> getAllPieces(){
        List<Piece> output = new ArrayList<>();
        output.addAll(board.blackPieces);
        output.addAll(board.whitePieces);
        return output;
    }

    @Override
    public void doMove(Move move) {
        board.doMove(move);
    }

}
