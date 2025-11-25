import oop.ex5.*;

import java.io.*;
import java.util.*;

public class Main5 {

    private static class InputParams {
        String in;
        String out;
        int steps;
        PatternParser parser;
        String worldType;
        int size;
        String rule;
        String neighborhood;
        boolean wrap;
    }

    private static final String[] allowedWorldTypes = {
        "WORLD_2D",
        "WORLD_HEX",
        "WORLD_3D"
    };
    private static final String[] allowedNeighborhoods = {
        "MOORE8",
        "VONN4",
        "HEX6",
        "FACES6"
    };

    private static InputParams input(String[] args) throws IOException {
        InputParams params = new InputParams();
        
        // 入力(必須)
        Map<String,String> cli = Util.parseArgs(args);
        params.in = cli.get("--pattern");
        String stepsS = cli.get("--steps");
        params.out = cli.get("--dump-final");
        if (params.in == null || stepsS == null || params.out == null) {
            usage("Missing: --pattern --steps --dump-final");
        }

        // パターンファイルを読み込み
        try {
            params.parser = new PatternParser(params.in);
        } catch (IOException e) {
            fail("Failed to read pattern file: " + e.getMessage());
            return null;
        }
        
        // パラメータを取得（CLI > ヘッダ > デフォルト）
        // world
        params.worldType = cli.get("--world");
        if (params.worldType == null) params.worldType = params.parser.getHeader("WORLD");
        else params.parser.updateHeader("WORLD", params.worldType);
        if (!Arrays.asList(allowedWorldTypes).contains(params.worldType)) throw new IllegalArgumentException("未知のworldです: " + params.worldType);
        
        // size
        String sizeStr = cli.get("--size");
        params.size = sizeStr != null ? Integer.parseInt(sizeStr) : Integer.parseInt(params.parser.getHeader("SIZE"));
        if (sizeStr != null) params.parser.updateHeader("SIZE", sizeStr);
        
        // rule
        params.rule = cli.get("--rule");
        if (params.rule == null) params.rule = params.parser.getHeaderOrDefault("RULE", "B3/S23");
        else params.parser.updateHeader("RULE", params.rule);
        
        // neighborhood
        params.neighborhood = cli.get("--neighborhood");
        if (params.neighborhood == null) {
            params.neighborhood = params.parser.getHeader("NEIGHBORHOOD");
            if (params.neighborhood == null) {
                // デフォルト値
                if (params.worldType.equals("WORLD_2D")) params.neighborhood = "MOORE8";
                else if (params.worldType.equals("WORLD_HEX")) params.neighborhood = "HEX6";
                else if (params.worldType.equals("WORLD_3D")) params.neighborhood = "FACES6";
                params.parser.updateHeader("NEIGHBORHOOD", params.neighborhood);
            }
        } else {
            params.parser.updateHeader("NEIGHBORHOOD", params.neighborhood);
        }

        if (!Arrays.asList(allowedNeighborhoods).contains(params.neighborhood)) {
            throw new IllegalArgumentException("未知のneighborhoodです: " + params.neighborhood);
        }
        
        // wrap
        String wrapStr = cli.get("--wrap");
        params.wrap = false;
        if (wrapStr != null) {
            params.wrap = Boolean.parseBoolean(wrapStr);
            params.parser.updateHeader("WRAP", String.valueOf(params.wrap));
        } else {
            String headerWrap = params.parser.getHeader("WRAP");
            if (headerWrap != null) params.wrap = Boolean.parseBoolean(headerWrap);
        }
        
        // steps
        params.steps = Integer.parseInt(stepsS);
        
        return params;
    }

    public static void main(String[] args) {
        try {
            // 入力処理
            InputParams params = input(args);
            if (params == null) return;
            
            // Worldオブジェクトを作成
            Rule rule = new Rule(params.rule);
            World world;

            if(params.worldType.equals("WORLD_2D")){
                if(params.neighborhood.equals("MOORE8")){
                    world = new World2D(rule, params.size, params.wrap, true);
                } else if(params.neighborhood.equals("VONN4")){
                    world = new World2D(rule, params.size, params.wrap, false);
                } else {
                    fail("WORLD_2Dに対して不正なneighborhoodです " + params.neighborhood);
                    return;
                }
            } else if(params.worldType.equals("WORLD_HEX")){
                if(params.neighborhood.equals("HEX6")){
                    world = new WorldHEX(rule, params.size, params.wrap);
                } else {
                    fail("WORLD_HEXに対して不正なneighborhoodです " + params.neighborhood);
                    return;
                }
            } else if(params.worldType.equals("WORLD_3D")){
                if(params.neighborhood.equals("FACES6")){
                    world = new World3D(rule, params.size, params.wrap);
                } else {
                    fail("WORLD_3Dに対して不正なneighborhoodです " + params.neighborhood);
                    return;
                }
            } else {
                fail("不正なworldです: " + params.worldType);
                return;
            }
            
            // グリッドにパターンを設定
            int[][][] initialGrid = params.parser.createGrid(params.size, world.getSizeZ());
            world.setGrid(initialGrid);
            
            // ステップ数実行
            for (int i = 0; i < params.steps; i++) {
                world.update();
            }

            // 出力結果を表示
            // String outputHeader = params.parser.getHeaderText();
            String outputHeader = "";
            outputHeader += "WORLD: " + params.worldType + "\n";
            outputHeader += "SIZE: " + params.size + "\n";
            outputHeader += "RULE: " + params.rule + "\n";
            outputHeader += "NEIGHBORHOOD: " + params.neighborhood + "\n";
            outputHeader += "WRAP: " + params.wrap + "\n";
            
            String outputBody = world.output();
            String output = outputHeader + "\n" + outputBody;
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(params.out))) {
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
