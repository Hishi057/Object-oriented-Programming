# 問題4A-2

`Main4A2`の`main`メソッドを、以下のように動作するよう定義せよ。

1. 標準入力から1行読む。
2. 入力が空行（空白・改行文字のみの場合も含む）だった場合は終了する。
3. 読み取った行を、先頭に"INPUT: "とつけた上で標準出力に出力する。
4. 1から3を繰り返す。

空白文字を表す正規表現は`\s`（ソースコード上に書くときは`\\s`）、行頭を表す正規表現は`^`、行末を表す正規表現は`$`である。Javaにおける正規表現については https://docs.oracle.com/javase/jp/8/docs/api/java/util/regex/Pattern.html を参照。

入出力に伴う`IOException`に関しては、発生時点で上記の仕様に基づく必要はない。そのため、`try-catch`文で処理しても良いし、`main`に`throws`を付けても良い。



### 実行例

コマンド間の空行は上記の2により終了するための入力である。

     > java Main4A2
     foo
     INPUT: foo
     
     > java Main4A2
     hoge
     INPUT: hoge
     fuga
     INPUT: fuga
     
     > java Main4A2
     foo
     INPUT: foo
     bar baz
     INPUT: bar baz
     
