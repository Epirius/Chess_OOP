package Model;

import Model.Pieces.King;
import Model.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Model {
    //TODO move some stuff from Model.Board to this file
    private Board board = new Board();
    private boolean currentPlayerIsWhite = true; // TODO move to Model.Model


    /////////////////////////////////////////////////////////////////////////////////////

    protected void changeCurrentPlayer(){currentPlayerIsWhite = !currentPlayerIsWhite;}

    private List<Move> getPossibleMoves(){
        // TODO move to Model.Model
        List<Piece> teamList = (currentPlayerIsWhite ? board.whitePieces : board.blackPieces);
        List<Move> allPossibleMoves = new ArrayList<>();
        for (Piece piece : teamList){
            allPossibleMoves.addAll(piece.getPossibleMoves(piece.getPosition(), board));
        }
        return allPossibleMoves;
    }

    private List<Move> getPossibleThreats(){
        // TODO move to Model.Model
        List<Piece> enemyList = (currentPlayerIsWhite ? board.blackPieces : board.whitePieces);
        List<Move> allThreatMoves = new ArrayList<>();
        for (Piece piece : enemyList){
            allThreatMoves.addAll(piece.getPossibleMoves(piece.getPosition(), board));
        }
        return allThreatMoves;
    }

    /**
     * finds all legal move for the current player.
     * @return List<Model.Move>
     */
    public List<Move> getLegalMoves(){
        // TODO move to Model.Model
        List<Move> moves = getPossibleMoves();
        List<Move> threats = getPossibleThreats();
        List<Integer> threatSquares = new ArrayList<>();
        for (Move move : threats){ threatSquares.add(move.to);}
        List<Move> illegalMoves = new ArrayList<>();
        List<Move> kingInCheck = new ArrayList<>();
        int king = (currentPlayerIsWhite ? getKing(Team.WHITE).getPosition() : getKing(Team.BLACK).getPosition());

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
            }

            // checking if king is attacked by a piece, and the move does not kill that piece.
            if (kingInCheck.size() == 1 && move.to != kingInCheck.get(0).from && move.to != king){
                // if the attacking piece is not a queen rook or bishop:
                Type attacker = board.getSquare(kingInCheck.get(0).from).getPiece().type;
                if (!(attacker == Type.QUEEN || attacker == Type.ROOK || attacker == Type.BISHOP)){
                    // then we can't block.
                    illegalMoves.add(move);
                } else if (!board.squaresBetween(king, kingInCheck.get(0).from).contains(board.getSquare(move.to))){
                    // if you can block but didn't:
                    illegalMoves.add(move);
                }
            }
            // checking for double check.
            if (kingInCheck.size() > 1){
                if (move.from != king){
                    illegalMoves.add(move);
                }
            }
        }

        // TODO check for pinned pieces and add their moves to illegalMoves.

        // checking if castling is illegal
        King kingPiece = (King) getKing();
        // WHITE
        if (king == 4 ) {
            // Model.Pieces.King side
            if (threatSquares.contains(4) || threatSquares.contains(5) || threatSquares.contains(6) ||
                    !kingPiece.castleKingSide || board.getSquare(5).getPiece() != null ||
                    board.getSquare(6).getPiece() != null) {
                illegalMoves.add(new Move(4, 6, true));
            }
            // Model.Pieces.Queen side
            if (threatSquares.contains(4) || threatSquares.contains(3) || threatSquares.contains(2) ||
                    !kingPiece.castleQueenSide || board.getSquare(2).getPiece() != null ||
                    board.getSquare(3).getPiece() != null) {
                illegalMoves.add(new Move(4, 2, true));
            }
        }

        // BLACK
        if (king == 60 ) {
            // Model.Pieces.King side
            if (threatSquares.contains(60) || threatSquares.contains(61) || threatSquares.contains(62) ||
                    !kingPiece.castleKingSide || board.getSquare(61).getPiece() != null ||
                    board.getSquare(62).getPiece() != null) {
                illegalMoves.add(new Move(60, 62, true));
            }
            // Model.Pieces.Queen side
            if (threatSquares.contains(4) || threatSquares.contains(3) || threatSquares.contains(2) ||
                    !kingPiece.castleQueenSide || board.getSquare(58).getPiece() != null ||
                    board.getSquare(59).getPiece() != null) {
                illegalMoves.add(new Move(60, 58, true));
            }
        }
        moves.removeAll(illegalMoves);
        return moves;
    }

    // helper function only used in getLegalMoves()
    private Piece getKing(){
        List<Piece> teamList = (currentPlayerIsWhite ? board.whitePieces : board.blackPieces);
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

}
