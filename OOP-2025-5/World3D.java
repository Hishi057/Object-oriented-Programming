public class World3D extends World {
    public World3D(Rule rule, int size, boolean wrap) {
        super(rule, size, wrap);
    }

    @Override
    protected int countNeighbors(int x, int y, int z) {
        int count = 0;
        
        // FACES6近傍: 面接触する6方向 (±x, ±y, ±z)
        int[][] offsets = {
            {-1, 0, 0}, {1, 0, 0},  // x方向
            {0, -1, 0}, {0, 1, 0},  // y方向
            {0, 0, -1}, {0, 0, 1}   // z方向
        };
        
        for (int[] offset : offsets) {
            int nx = x + offset[0];
            int ny = y + offset[1];
            int nz = z + offset[2];
            
            if (wrap) {
                nx = wrap(nx);
                ny = wrap(ny);
                nz = wrap(nz);
                if (grid[nx][ny][nz] == 1) {
                    count += 1;
                }
            } else {
                if (isValid(nx, ny, nz) && grid[nx][ny][nz] == 1) {
                    count += 1;
                }
            }
        }
        
        return count;
    }

    @Override
    protected int getSizeZ() {
        return sizeX; // 3Dなのでsizeと同じ
    }

    @Override
    protected String output() {
        StringBuilder output = new StringBuilder();
        
        // SIZE層 × SIZE行、層間は空行で区切る
        for (int z = 0; z < sizeZ; z++) {
            if (z > 0) {
                output.append("\n"); // 層の区切り
            }
            for (int y = 0; y < sizeY; y++) {
                for (int x = 0; x < sizeX; x++) {
                    output.append((grid[x][y][z] == 1) ? "O" : ".");
                }
                output.append("\n");
            }
        }
        return output.toString();
    }
}