package Model;

import java.util.Objects;

/**
 * @author Felix Kaasa
 */
public class PinnedPiece {
    public final int pinnedPiece;
    public final int pinnedFrom;

    /**
     * this class is used to track a pinned piece and where it is pinned from
     * @param pinnedPiece int id of the square where the piece that is pinned is.
     * @param pinnedFrom int id of the square where the piece that is pinning is.
     * NB: When checking for eqaulity, only pinnedPiece is checked.
     */
    public PinnedPiece(int pinnedPiece, int pinnedFrom) {
        this.pinnedPiece = pinnedPiece;
        this.pinnedFrom = pinnedFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PinnedPiece that = (PinnedPiece) o;
        return pinnedPiece == that.pinnedPiece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinnedPiece);
    }
}
