import oop.ex5.*;

import java.io.*;
import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        try {
            // 入力(必須)
            Map<String,String> cli = Util.parseArgs(args);
            String in   = cli.get("--pattern");
            String stepsS = cli.get("--steps");
            String out  = cli.get("--dump-final");
            if (in == null || stepsS == null || out == null) usage("Missing: --pattern --steps --dump-final");

            // 入力(任意)
            String ruleStr = cli.get("--rule");
            String neighborhood = cli.get("--neighborhood");
            String worldType = cli.get("--world");
            String sizeStr = cli.get("--size");
            String wrapStr = cli.get("--wrap");

            // パターンファイルを読み込み
            PatternParser parser;
            try {
                parser = new PatternParser(in);
            } catch (IOException e) {
                fail("Failed to read pattern file: " + e.getMessage());
                return;
            }
            
            // ヘッダからパラメータを取得
            String finalWorldType = worldType != null ? worldType : parser.getHeader("WORLD");
            int size = sizeStr != null ? Integer.parseInt(sizeStr) : Integer.parseInt(parser.getHeader("SIZE"));
            String finalRule = ruleStr != null ? ruleStr : parser.getHeaderOrDefault("RULE", "B3/S23");
            
            Rule rule = new Rule(finalRule);
            World world = new World2D(rule, size, true);
            
            // グリッドにパターンを設定
            int[][][] initialGrid = parser.createGrid(size, world.getSizeZ());
            world.setGrid(initialGrid);
            
            // ステップ数実行
            int steps = Integer.parseInt(stepsS);
            for (int i = 0; i < steps; i++) {
                world.update();
            }

            // 出力結果を表示
            String outputHeader = parser.getHeaderText();
            String outputBody = world.output();
            String output = outputHeader + "\n" + outputBody;
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(out))) {
                writer.write(output);
            } catch (IOException e) {
                fail("Failed to write output file: " + e.getMessage());
            }
            
        } catch (Throwable t) {
            fail(t.getMessage() == null ? t.toString() : t.getMessage());
        }
    }

    private static void usage(String msg) {
        System.err.println(msg);
        System.err.println("Usage: java Main5 --pattern <input.pattern> --steps <N> --dump-final <output.pattern> "
                + "[--rule B3/S23] [--neighborhood MOORE8|VONN4|HEX6|FACES6] [--world WORLD_2D|WORLD_HEX|WORLD_3D] [--size N] [--wrap true|false]");
        System.exit(2);
    }
    private static void fail(String msg){ System.err.println("Error: " + msg); System.exit(1); }
}
