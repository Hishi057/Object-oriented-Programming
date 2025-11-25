public class WorldHEX extends World {
    public WorldHEX(Rule rule, int size, boolean wrap) {
        super(rule, size, wrap);
    }

    @Override
    protected int countNeighbors(int x, int y, int z) {
        int count = 0;
        
        // 六角格子の6近傍
        // 偶数行と奇数行で近傍の位置が異なる
        int[][] evenRowOffsets = {
            {-1, -1}, {0, -1},  // 上左、上右
            {-1, 0}, {1, 0},    // 左、右
            {-1, 1}, {0, 1}     // 下左、下右
        };
        
        int[][] oddRowOffsets = {
            {0, -1}, {1, -1},   // 上左、上右
            {-1, 0}, {1, 0},    // 左、右
            {0, 1}, {1, 1}      // 下左、下右
        };
        
        int[][] offsets = (y % 2 == 0) ? evenRowOffsets : oddRowOffsets;
        
        for (int[] offset : offsets) {
            int nx = x + offset[0];
            int ny = y + offset[1];
            
            if (wrap) {
                nx = wrap(nx);
                ny = wrap(ny);
                if (grid[nx][ny][0] == 1) {
                    count += 1;
                }
            } else {
                if (isValid(nx, ny, 0) && grid[nx][ny][0] == 1) {
                    count += 1;
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