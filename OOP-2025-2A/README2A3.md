# 問題2A-3

`Main2A3`クラスには3つのメソッド`m1`、`m2`、`m3`が定義されているが、中身はダミーである。
各メソッドについて、以下のように動作するように各メソッド本体を定義せよ。

1. `m1`は実行すると`ArithmeticException`が送出される。
2. `m2`は実行すると`NullPointerException`が送出される。
3. `m3`は0未満が引数として与えられると`NegativeArraySizeException`が送出される。それ以外の場合は正常に終了し、0が返される。

ただし、本問題では`main`メソッドについては変更してはならず、また`throw`を用いて直接例外を送出してはならない。
`try-catch`文については`main`メソッドに記載されたものを用いること。

各例外のドキュメントのリンクを以下に示すので、その説明を基に生成する方法を考えること。
+ https://docs.oracle.com/javase/jp/8/docs/api/java/lang/ArithmeticException.html
+ https://docs.oracle.com/javase/jp/8/docs/api/java/lang/NullPointerException.html
+ https://docs.oracle.com/javase/jp/8/docs/api/java/lang/NegativeArraySizeException.html

なお、`main`メソッドは実行時引数なしで実行すると`ArrayIndexOutOfBoundsException`が、実行時引数として数以外を表す文字列を渡すと`NumberFormatException`が発生する。
これらの例外を`catch`の対象にしていないが、これらを含め本問題に出現する例外は非チェック例外（`RuntimeException`のサブクラス）であるため、送出されても`main`メソッドに`throws`節を追加する必要はない。



### 実行例

    > java Main2A3 1
    From m1
    java.lang.ArithmeticException: / by zero
        at Main2A3.m1(Main2A3.java:6)
        at Main2A3.main(Main2A3.java:23)
    > java Main2A3 2
    From m2
    java.lang.NullPointerException: Cannot invoke "String.length()" because "null" is null
        at Main2A3.m2(Main2A3.java:10)
        at Main2A3.main(Main2A3.java:27)
    > java Main2A3 -1
    From m3
    java.lang.NegativeArraySizeException: -1
        at Main2A3.m3(Main2A3.java:14)
        at Main2A3.main(Main2A3.java:31)
    > java Main2A3 3
    0

なお上の表記では表現できていないが、出力された文字列は最後の例を除き全て標準エラー出力のもの（IntelliJ IDEA上では赤字で表示）である。
また、上記の例はあくまで参考であり、例外の後ろのメッセージや行番号などの出力が同様になるとは限らないことに注意せよ。
