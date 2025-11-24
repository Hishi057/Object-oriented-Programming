public class World2D extends World {
    private boolean useMoore = true;
    
    public World2D(Rule rule, int size, boolean useMoore) {
        super(rule, size);
        this.useMoore = useMoore;
    }

    @Override
    protected int countNeighbors(int x, int y, int z) {
        int count = 0;
        for(int dx = -1; dx <= 1; dx++){
            for(int dy = -1; dy <= 1; dy++){
                // 隅
                if(Math.abs(dx) + Math.abs(dy) == 2 && !useMoore) continue;
                // 中心
                if(dx == 0 && dy == 0) continue;
                
                int nx = x + dx;
                int ny = y + dy;
                if(isValid(nx, ny, z)){
                    count += grid[nx][ny][z];
                }
            }
        }
        return count;
    }

    @Override
    protected int getSizeZ() {
        return 1;
    }

    @Override
    protected String output() {
        String output = "";

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                output += (grid[x][y][0] == 1) ? "O" : ".";
            }
            output += "\n";
        }
        return output;
    }
}