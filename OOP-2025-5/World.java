public abstract class World {

    public int step;
    protected int[][][] grid;
    protected int[][][] nextGrid;
    protected Rule rule;
    protected int size, sizeX, sizeY, sizeZ;
    protected boolean wrap;

    protected abstract int countNeighbors(int x, int y, int z);
    protected abstract int getSizeZ();
    protected abstract String output();

    public World(Rule rule, int size, boolean wrap) {
        this.rule = rule;
        this.size = size;

        this.sizeX = this.size;
        this.sizeY = this.size;
        this.sizeZ = this.getSizeZ();

        this.grid = new int[sizeX][sizeY][sizeZ];
        this.nextGrid = new int[sizeX][sizeY][sizeZ];
        this.step = 0;
        this.wrap = wrap;
    }

    public void update() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (int z = 0; z < sizeZ; z++) {
                    int neighborCount = this.countNeighbors(x, y, z);
                    nextGrid[x][y][z] = rule.apply(grid[x][y][z], neighborCount);
                }
            }
        }

        int[][][] temp = grid;
        grid = nextGrid;
        nextGrid = temp;

        step++;
    }

    public void setGrid(int[][][] newGrid) {
        this.grid = newGrid;
    }

    // ラップあり
    protected int wrap(int v) {
        v = v % sizeX;
        if(v < 0) v += sizeX;
        return v;
    }

    // ラップなし
    protected boolean isValid(int x, int y, int z) {
        return x >= 0 && x < sizeX && y >= 0 && y < sizeY && z >= 0 && z < sizeZ;
    }
}
