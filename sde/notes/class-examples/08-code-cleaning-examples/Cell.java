public class Cell {
    public static final int CELL_STATUS = 0,
        CELL_ROW = 1,
        CELL_COLUMN = 2,
        CELL_MINE_FLAG = 3,
        CELL_ADJACENCY = 4;

    public static final int STATUS_HIDDEN = 0,
            STATUS_REVEALED_NUMBER = 1,
            STATUS_REVEALED_BLANK = 2,
            STATUS_REVEALED_MINE = 3,
            STATUS_FLAGGED = 4;

    private int[] cellInformation;

    public Cell(int[] cellInformation) {
        this.cellInformation = cellInformation;
    }

    public boolean isCellFlagged() {
        return cellInformation[CELL_STATUS] == STATUS_FLAGGED;
    }
}
