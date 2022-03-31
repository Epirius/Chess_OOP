package Model;

import Controller.IMovable;
import Model.Pieces.King;
import Model.Pieces.Pawn;
import Model.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Model implements IMovable {
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
            if (piece.type == Type.PAWN){
                Pawn pawn = (Pawn) piece;
                allThreatMoves.addAll(pawn.getPossibleThreats(piece.getPosition(), board));
            } else {
                allThreatMoves.addAll(piece.getPossibleMoves(piece.getPosition(), board));
            }
        }
        return allThreatMoves;
    }

    @Override
    public boolean kingInCheck(){
        List<Move> threats = getPossibleThreats();
        int king = (board.isCurrentPlayerIsWhite() ? getKing(Team.WHITE).getPosition() : getKing(Team.BLACK).getPosition());

        for (Move threat : threats){
            if (threat.to == king){
                return true;
            }
        }
        return false;
    }


    @Override
    public List<Move> getLegalMoves(){
        List<Move> moves = getPossibleMoves();
        List<Move> threats = getPossibleThreats();
        List<Move> illegalMoves = new ArrayList<>();
        List<Move> kingInCheck = new ArrayList<>();
        List<Integer> threatSquares = new ArrayList<>();
        List<Integer> pinnedPieces = getPinnedPieces();

        for (Move move : threats){ threatSquares.add(move.to);}

        int king = (board.isCurrentPlayerIsWhite() ? getKing(Team.WHITE).getPosition() : getKing(Team.BLACK).getPosition());

        // checking if king is attacked.
        for (Move threat : threats){
            System.out.println(threat); //TODO DELETE
            if (threat.to == king){
                kingInCheck.add(threat);
            }
        }

        for (Move move : moves){
             // checking if the king is moving into an attacked square.
            if (move.from == king && threatSquares.contains(move.to)){
                //TODO this does not check if the king is moving in to a square defended by a pawn (maybe other pieces aswell?)
                illegalMoves.add(move);
                continue;
            }

            // Checking if the king is attacking a piece that is defended.
            if (move.from == king && !board.getSquare(move.to).isEmpty() && board.getPiece(king).team != board.getPiece(move.to).team){
                Piece enemyPiece = board.getPiece(move.to);
                Piece friendlyKing = board.getPiece(move.from);

                // making the move and checking if the king is in check
                board.getSquare(move.from).removePiece();
                board.getSquare(move.to).setPiece(friendlyKing);

                if (kingInCheck()){
                    illegalMoves.add(move);
                    board.getSquare(move.from).setPiece(friendlyKing);
                    board.getSquare(move.to).setPiece(enemyPiece);
                    continue;
                }
                // resetting the board to what it was before i tested the move
                board.getSquare(move.from).setPiece(friendlyKing);
                board.getSquare(move.to).setPiece(enemyPiece);
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
                    continue;
                }
            }

            // Pawns
            if (board.getPiece(move.from).type == Type.PAWN){
                // White
                if (board.getPiece(move.from).team == Team.WHITE){
                    // checking if double move is blocked
                    if (move.to ==  move.from + 16 && board.getPiece(move.from + 8) != null){
                        illegalMoves.add(move);
                        continue;
                    }
                }

                // Black
                if (board.getPiece(move.from).team == Team.BLACK){
                    // checking if double move is blocked
                    if (move.to ==  move.from - 16 && board.getPiece(move.from - 8) != null){
                        illegalMoves.add(move);
                        continue;
                    }
                }
            }


            // Checking for pinned pieces
            if (pinnedPieces.size() > 0 && pinnedPieces.contains(move.from)){
                illegalMoves.add(move);
            }

        }

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

    public Team getTeam(){
        return board.getTeam();
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
    public Piece getPiece(int id) {
        return board.getPiece(id);
    }

    @Override
    public void doMove(Move move) {

        updateCastlingLegality(move);
        board.doMove(move);
    }

    //@Override TODO interface maybe?
    public void upgradePawn(Type type){
        board.upgradePawn(type);
    }

    private void updateCastlingLegality(Move move){
        King king = (King) getKing();

        if (!king.castleKingSide && !king.castleQueenSide){
            return;
        }

        // Setting castling to illegal if the king is moved.
        if (board.getPiece(move.from).type == Type.KING){
            king.setCastleKingSideToFalse();
            king.setCastleQueenSideToFalse();
        }

        // Checking if the rooks have moved
        if(move.from == 0 && board.getPiece(0).type == Type.ROOK){
            king.setCastleQueenSideToFalse();
        }
        if(move.from == 7 && board.getPiece(7).type == Type.ROOK){
            king.setCastleKingSideToFalse();
        }
        if(move.from == 56 && board.getPiece(56).type == Type.ROOK){
            king.setCastleQueenSideToFalse();
        }
        if(move.from == 63 && board.getPiece(63).type == Type.ROOK){
            king.setCastleKingSideToFalse();
        }

    }

    /**
     * a method that finds all friendly pieces that are pinned
     * @return a list of square id's for all friendly pinned pieces
     */
    private List<Integer> getPinnedPieces(){
        int kingPosition = getKing().getPosition();
        Team team = (board.isCurrentPlayerIsWhite() ? Team.WHITE : Team.BLACK);
        List<Piece> enemyPieceList = (Team.WHITE == team ? board.blackPieces : board.whitePieces);
        List<Integer> pinnedPiecesList = new ArrayList<>();

        for (Piece piece : enemyPieceList){
            if (!( piece.type == Type.BISHOP || piece.type == Type.QUEEN || piece.type == Type.ROOK)){
                // only bishop, queen or rook can pin pieces. so if the piece is something else: continue.
                continue;
            }
            List<Square> squaresBetween = board.squaresBetween(kingPosition, piece.getPosition());

            // making sure the piece can attack in the direction of the king.
            int direction = 0;
            if (squaresBetween.size() > 1){
                direction = squaresBetween.get(0).getSquareId() - squaresBetween.get(1).getSquareId();
            }
            if (piece.type == Type.BISHOP){
                if ( direction == 8 || direction == -8 || direction == 1 || direction == -1){
                    continue;
                }
            }
            // making sure the piece can attack in the direction of the king.
            if (piece.type == Type.ROOK){
                if (direction == 9 || direction == 7 || direction == -7 || direction == -9){
                    continue;
                }
            }

            // Checking if there is one (and only one) piece between attacker and king.
            int numberOfPiecesBetween = 0;
            Piece candidatePinnedPiece = null;
            for (Square square : squaresBetween){
                if (square.isEmpty()){
                    continue;
                }
                numberOfPiecesBetween++;
                candidatePinnedPiece = square.getPiece();
            }
            if (numberOfPiecesBetween == 1 && candidatePinnedPiece.team == team){
                pinnedPiecesList.add(candidatePinnedPiece.getPosition());
            }
        }
        return pinnedPiecesList;
    }

}
