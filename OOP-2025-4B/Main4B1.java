import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Main4B1 {
    // 行の数は厳密に\nの数を数える。
    // InputStreamとか使うとなんかちゃんと数えられないから。
    public static long countNewlinesInFile(Path path) throws IOException {
        String content = Files.readString(path);
        return content.chars().filter(c -> c == '\n').count();
    }

    public static void main(String[] args) throws IOException {

        Path path = Paths.get(args[0]);

        // 行数を数える
        int line_num = 0;
        try (Stream<String> lineStream = Files.lines(path)) {
            line_num = (int)countNewlinesInFile(path);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // 文字列の読み込み
        InputStream inputStream = new FileInputStream(args[0]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String str = "";
        String line;
        while ((line = reader.readLine()) != null) {
        //    System.out.println("---");
        //    System.out.println(line);
            if (!line.isEmpty()) {
                str += line + " ";
            }
        }
        // スペース等で区切られた英数字の数 "等"ってなんですか？
        String[] words = str.split("[.,\'’:;\\s]+"); //.  \'← 💢💢💢💢

        // 文字列の処理
        int word_num = 0;
        int unique_word_num = 0;

        Set<String> unique_words = new HashSet<>();
        for(String w : words){
            w = w.toLowerCase();
            w = w.replace(",", "");
            w = w.replace(".", "");
            w = w.replace("!", "");
            w = w.replace("！", ""); // ← 💢
            
            // System.out.println(w);
            if(!w.isEmpty()) {
                word_num++;
                unique_words.add(w);
            }
        }

        // System.out.println(unique_words);
        unique_word_num = unique_words.size();

        System.out.println("line num: " + line_num);
        System.out.println("word num: " + word_num);
        System.out.println("unique word num: " + unique_word_num);

        reader.close();
    }
}