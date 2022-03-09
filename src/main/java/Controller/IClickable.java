package Controller;

import java.util.List;

public interface IClickable {

    /**
     * A method that updates a list in View of the legal squares that can be clicked.
     * @param legalSquares a list of legal squares, in the form of Interger id of the square.
     */
    void setLegalSquares(List<Integer> legalSquares);

}
