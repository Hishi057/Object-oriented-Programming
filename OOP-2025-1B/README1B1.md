# 問題1B-1

`Main1B1` クラスを実行すると実行時引数の数を出力し、それに続いて各行に「引数の番号と引数」実行例のように一つずつ表示するよう `main` メソッドを変更せよ。
配列の範囲外アクセスによる例外が送出されないようにすること。（初期の`Main1B1`は何も引数を与えないと範囲外アクセスによる例外 `ArrayIndexOutOfBoundsException` が送出される）

IntelliJ IDEAではファイルを右クリックし、`More Run/Debug`から`Modify Run Configuration`を選んだ画面で`Program arguments`の箇所に実行時引数を設定できる。（`run_config1.png`と`run_config2.png`を参照のこと。これは実行例の三つ目相当である。）

文字列を出力するためのメソッドは1A-1で説明しているが、複数の文字列を扱う場合には文字列に対しては`printf`メソッドで`%s`指定子を用いる他、文字列と他の要素（文字列、`int`など）を加算すると連結した文字列を作成できる（例えば`Main1B1`の3行目）ので、適宜利用するとよい。
<!-- なお、加算による文字列連結は手軽だが効率はあまり良くない。これについては後の講義で触れる。 -->



### 実行例
    > javac Main1B1.java
    > java Main1B1
    The number of arguments: 0
    > java Main1B1 foo
    The number of arguments: 1
    args[0]: foo
    > java Main1B1 hoge fuga piyo
    The number of arguments: 3
    args[0]: hoge
    args[1]: fuga
    args[2]: piyo

この実行例はシェルから実行した場合の表示になる。
`>` はシェルの入力用プロンプトを表しており、その後ろがユーザが打ち込んだコマンドである。
`javac`はJavaで書かれたプログラムからクラスファイル（今回の場合`Main1B1.class`）へのコンパイラである。
2行目以降の`java`はクラスファイルを実行するためのコマンドであり、最初の引数`Main1B1`は「プログラム開始時に実行される`main`メソッドが実装されたクラス」、残り（二つ目なら`foo`、三つ目なら`hoge`と`fuga`と`piyo`）は`Main1B1`の実行時引数を表している。

IntelliJ IDEAでは`Program arguments`の設定に応じて実行結果が変わり、空欄にすれば3行目だけが、`foo`とだけ入れれば5,6行目が、`hoge fuga piyo`と入れれば8-11行目が出力される。
最初のコンパイルは実行前に自動で行われる。

