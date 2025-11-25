import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AutoTester {
    
    private static class TestResult {
        String testName;
        boolean passed;
        String message;
        
        TestResult(String testName, boolean passed, String message) {
            this.testName = testName;
            this.passed = passed;
            this.message = message;
        }
    }
    
    public static void main(String[] args) {
        String referenceDir = "oop/reference";
        List<TestResult> results = new ArrayList<>();
        
        try {
            // referenceディレクトリ内の全.patternファイルを取得
            File dir = new File(referenceDir);
            if (!dir.exists() || !dir.isDirectory()) {
                System.err.println("Error: " + referenceDir + " directory not found");
                return;
            }
            
            File[] files = dir.listFiles((d, name) -> 
                name.endsWith(".pattern") && !name.endsWith("_next.pattern")
            );
            
            if (files == null || files.length == 0) {
                System.out.println("No test patterns found");
                return;
            }
            
            Arrays.sort(files, Comparator.comparing(File::getName));
            
            System.out.println("Running tests...\n");
            
            for (File inputFile : files) {
                String testName = inputFile.getName().replace(".pattern", "");
                String expectedFile = referenceDir + "/" + testName + "_next.pattern";
                String outputFile = "test_output_" + testName + ".pattern";
                
                TestResult result = runTest(inputFile.getPath(), expectedFile, outputFile);
                results.add(result);
                
                // 結果を表示
                if (result.passed) {
                    System.out.println("✓ " + result.testName + ": PASSED");
                } else {
                    System.out.println("✗ " + result.testName + ": FAILED");
                    System.out.println("  " + result.message);
                }
            }
            
            // サマリーを表示
            System.out.println("\n" + "=".repeat(50));
            long passed = results.stream().filter(r -> r.passed).count();
            long failed = results.size() - passed;
            System.out.println("Total: " + results.size() + " tests");
            System.out.println("Passed: " + passed);
            System.out.println("Failed: " + failed);
            
            if (failed == 0) {
                System.out.println("\nAll tests passed! ✓");
            }
            
        } catch (Exception e) {
            System.err.println("Error running tests: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static TestResult runTest(String inputFile, String expectedFile, String outputFile) {
        String testName = new File(inputFile).getName().replace(".pattern", "");
        
        try {
            // 期待される出力ファイルが存在するかチェック
            File expected = new File(expectedFile);
            if (!expected.exists()) {
                return new TestResult(testName, false, 
                    "Expected output file not found: " + expectedFile);
            }
            
            // Main5を実行
            String[] command = {
                "java", "Main5",
                "--pattern", inputFile,
                "--steps", "1",
                "--dump-final", outputFile
            };
            
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            
            // 出力を読み取る（デバッグ用）
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            
            int exitCode = process.waitFor();
            
            if (exitCode != 0) {
                return new TestResult(testName, false, 
                    "Main5 exited with code " + exitCode + "\n" + output.toString());
            }
            
            // 出力ファイルと期待ファイルを比較
            String actualContent = readFile(outputFile);
            String expectedContent = readFile(expectedFile);
            
            if (actualContent.equals(expectedContent)) {
                // テスト成功後、出力ファイルを削除
                new File(outputFile).delete();
                return new TestResult(testName, true, "");
            } else {
                return new TestResult(testName, false, 
                    "Output mismatch. Check " + outputFile + " vs " + expectedFile);
            }
            
        } catch (Exception e) {
            return new TestResult(testName, false, 
                "Exception: " + e.getMessage());
        }
    }
    
    private static String readFile(String filepath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }
}
