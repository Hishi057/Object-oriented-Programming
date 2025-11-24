import java.io.*;
import java.util.*;

public class PatternParser {
    private Map<String, String> header;
    private List<String> headerOrder;
    private List<String> lines;
    
    public PatternParser(String filepath) throws IOException {
        header = new HashMap<>();
        headerOrder = new ArrayList<>();
        lines = new ArrayList<>();
        parse(filepath);
    }
    
    private void parse(String filepath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            boolean inBody = false;
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    inBody = true;
                    continue;
                }
                
                if (line.startsWith("#")) continue;
                
                if (!inBody && line.contains(":")) {
                    String[] parts = line.split(":", 2);
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    header.put(key, value);
                    headerOrder.add(key);
                } else {
                    lines.add(line);
                }
            }
        }
    }
    
    public String getHeader(String key) {
        return header.get(key);
    }
    
    public String getHeaderOrDefault(String key, String defaultValue) {
        return header.getOrDefault(key, defaultValue);
    }
    
    public List<String> getLines() {
        return lines;
    }
    
    public String getHeaderText() {
        StringBuilder sb = new StringBuilder();
        for (String key : headerOrder) {
            sb.append(key).append(": ").append(header.get(key)).append("\n");
        }
        return sb.toString();
    }
    
    public int[][][] createGrid(int size, int sizeZ) {
        int[][][] grid = new int[size][size][sizeZ];
        int lineIdx = 0;
        for (int y = 0; y < size && lineIdx < lines.size(); y++) {
            String line = lines.get(lineIdx++);
            for (int x = 0; x < Math.min(size, line.length()); x++) {
                char c = line.charAt(x);
                grid[x][y][0] = (c == 'O') ? 1 : 0;
            }
        }
        return grid;
    }
}
