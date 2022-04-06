package Model.Pieces;

import Model.Move;
import Model.Team;
import Model.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Felix Kaasa
 */

public abstract class Piece implements IPiece, Cloneable{
    public final Type type;
    public final Team team;
    private int position;
    public int value;

    public Piece(Team team, Type type, int value){
        this.team = team;
        this.type = type;
        this.value = value;
    }

    @Override
    public Team getTeam(){
        return this.team;
    }

    @Override
    public Type getPiece(){
        return type;
    }

    @Override
    public int getPosition(){
        return position;
    }

    @Override
    public void setPosition(int Id){
        this.position = Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return position == piece.position && type == piece.type && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, team, position);
    }

    @Override
    public Piece clone() {
        try {
            Piece clone = (Piece) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
