# 問題1B-3

本課題は1B-2が解答できていることを前提にしているので、順序を入れ替えるなどすると意図と異なる解答になる可能性がある。

`Person` を継承したクラス `Student` を `Student.java` に定義し、以下のコンストラクタ、フィールド、メソッドを定義せよ。ファイルは既に用意されている。

1. `String` 型のフィールド `id`
2. コンストラクタ：`String` 型の `name` と `id` を引数とし、`Person`クラスに宣言されたフィールド `name` と上のフィールド `id` を引数で初期化する
<!-- Personクラスの暗黙のコンストラクタが呼び出されているが、この説明の時期が遅いので次回以降は注意。 -->
3. メソッド`getID`：`id` を返す無引数メソッド
4. メソッド`setID`：`String`型の引数`id`をとり、フィールドの`id`に代入するメソッド（返し値は`void`）
5. メソッド`introduce`：無引数で，`name` と `id` の内容を標準出力に表示するメソッド（書式は実行例の3,4行目や7,8行目を参照）

なお、`Student` に再度 `name` や `getName` や `setName`を**宣言してはならない**。これらについては `Person` に定義されたものを用いること。

### 実行例
`Main1B3`クラスの後ろのコメントアウトを外すと上の実行例に続けて以下のように表示される。

    Name: Toko
    ID: 22B01234
    My name is Toko
    My student ID is 22B01234
    Name: Kobayashi
    ID: 03B24949
    My name is Kobayashi
    My student ID is 03B24949
    Name: Kobayashi

