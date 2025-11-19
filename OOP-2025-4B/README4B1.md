# 問題4B-1

実行時の引数で指定されたテキストファイルの行数と出現する単語数、さらにユニークな単語数を表示するように`main`メソッドを実装せよ。
テキストファイルは（半角の）英数字とピリオド、コンマ、コロン、セミコロンと空白および改行で構成されているものとする（括弧は含まない）。これら以外の文字種に関しては対応してもよいが評価の対象とはしない。
出力は以下の三行の形式とする。

```
line num: xx
word num: yy
unique word num: zz
```

xx, yy, zz は指定されたテキストファイルに応じて適切な値を表示させること。行数は「文の数」ではなく「改行の数」であり、単語数は「存在する単語の種類」ではなく「スペース等で区切られた英数字の数」である。ユニーク語数は大文字小文字を区別せずに重複を除いた単語数である。

注意：`String#split(String)`は「区切り文字のみで構成される文字列」には要素数0の配列を返すが、「空文字列」には要素数1の配列（中身は空文字列のみ）を返す。
また、`split`メソッドは正規表現を使って分割するが、正規表現の記述方法は[ここ](https://docs.oracle.com/javase/jp/8/docs/api/java/util/regex/Pattern.html)を参考にするとよい。

### 実行例

```
> java Main4B1 LoremIpsum.txt
line num: 5
word num: 36
unique word num: 34
> java Main4B1 Entrance.txt
line num: 11
word num: 115
unique word num: 78
```

なお、Lorem ipsumとは組版（文書における文や図の配置を決定すること）でサンプル文として用いられる「意味のない文章（アルファベットの羅列）」である。
また、`Entrance.txt`は https://www.isct.ac.jp/en/news/79vfw6rxhao5 より引用した（第三段落までを文単位で区切ったもの）。

