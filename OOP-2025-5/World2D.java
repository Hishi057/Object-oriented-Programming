public class World2D extends World {
    private boolean useMoore;
    
    public World2D(Rule rule, int size, boolean wrap, boolean useMoore) {
        super(rule, size, wrap);
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
                if(wrap){
                    nx = wrap(nx);
                    ny = wrap(ny);
                    if(grid[nx][ny][0] == 1){
                        count += 1;
                    }
                } else {
                    if(isValid(nx, ny, 0) && grid[nx][ny][0] == 1){
                        count += 1;
                    }
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