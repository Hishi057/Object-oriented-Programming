# 問題3B-3

タスク管理プログラムを作成する。以下の二つのクラスを定義せよ。
挙がっているメソッドやコンストラクタはすべて可視範囲を`public`とする。また、書かれていないフィールドやメソッドは好きに定義して良い。

`Task`クラス

1. `Comparable<Task>`インタフェース https://docs.oracle.com/javase/jp/8/docs/api/java/lang/Comparable.html の実装（`int compareTo(Task)`メソッドの実装）
    - `compareTo`メソッドは、優先度が小さい方を「小さい」ものとして扱う。優先度が同じ場合については順序を付けても良いし付けなくても良い。
2. 2引数コンストラクタ`Task(int, String)` : 第1引数が優先度（1以上9以下と仮定して良い）、第2引数がタスクの内容を指す文字列である。
3. `String toString()`（オーバーライド） ： `"優先度 タスクの内容"`（例：`"2 Homework"`）を返す。

`TaskList`クラス

1. 無引数コンストラクタ。初期ではタスクが空であること。
2. `void addTask(Task)` : 引数の`Task`を一覧に追加する。既存のタスクと同一のタスク（に見えるもの）があっても追加すること。
3. `void printList()` : 優先度が小さい方から順に、番号とタスクを1行ずつ表示する（詳細な表示形式は実行例参照）。同じ優先度が複数ある場合、その全てを表示すること（同じ優先度のタスクの順序は問わない）。
4. `boolean removeTask(int)` : `printList`で表示した場合の番号を引数にとり、その番号のタスクをリストから削除する。削除が出来た場合は`true`を返し、その番号がタスクの総数より大きいか0以下である場合は`false`を返す。なお、`removeTask`の直前に`printList`が呼び出されていない場合、どのタスクが削除されるかは特に規定しない（高々一つしか削除せず、削除したら`true`を返すこと）。

並べ替えには`java.util.Collections`クラスの`sort`メソッド（ https://docs.oracle.com/javase/jp/8/docs/api/java/util/Collections.html#sort-java.util.List- ) を用いるのが容易であり、このメソッドのために`Task`を格納するコンテナは`List`インタフェースを実装したクラス（ https://docs.oracle.com/javase/jp/8/docs/api/java/util/List.html の「既知のすべての実装クラス」からたどると楽である）を選ぶと良い。



### 実行例

    Task list
    1: 1 Do homework
    2: 1 Do homework
    3: 1 Rating
    4: 2 Check theses
    5: 4 Attend meeting
    Task 3 is deleted
    Task list
    1: 1 Do homework
    2: 1 Do homework
    3: 2 Check theses
    4: 4 Attend meeting
    No task is deleted
    Task list
    1: 1 Do homework
    2: 1 Do homework
    3: 2 Check theses
    4: 4 Attend meeting
    5: 7 Game

RatingタスクとDo homeworkタスクの順序については`comapreTo`の実装に依存する。順序が異なる場合は（番号が変わるため）削除されるタスクが異なることもある。
