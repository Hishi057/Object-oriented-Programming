# 問題2A-1

`oop.ex2A1.Ticket`クラスは番号の専有を表すためのクラスであり、クラス内で有効なチケットかを管理している。
`Ticket.java` 内のメソッドおよびフィールドに `static` を適切に付与することで、`Main2A1` クラスがコンパイルでき、その`main`メソッドを実行すると実行例になるようにせよ。（本課題では初期状態でコンパイルエラーが発生するが、環境としてはそれが正常である）
編集するクラスは`oop.ex2A1.Ticket`クラスのみであり、`Main2A1` クラスを編集してはならない。
本課題と同様に次の課題（2A-2）にも初期状態でコンパイルエラーが存在するため、IntelliJ IDEA上で実行例を確かめるには次の課題もある程度進める必要があることに注意せよ。

前回の課題1B-4と同様に、これ以降課題における`Main`以外のクラスは基本的にパッケージに所属するようにし、パッケージに対応するフォルダ内にファイルを配置している。 <!-- 一般的にJavaで用いられる命名規則に基づくパッケージならば、`jp.ac.isct.comp.oop2024.ex2A1`になるだろう。本講義ではフォルダの階層が深くなるので避けている。 -->
課題毎にパッケージの位置が異なるため、パッケージの宣言を忘れたり、誤って別のファイルを編集したり、他の課題のファイルを`import`の対象にしないように注意すること。



### 実行例

    Max Reservation number is 10
    Invalid ticket number : -1
    Get Valid ticket : 0
    Get Valid ticket : 1
    Get Valid ticket : 2
    The ticket number is already owned :  3
    Get Valid ticket : 4
    The ticket number is already owned :  5
    Get Valid ticket : 6
    Get Valid ticket : 7
    Get Valid ticket : 8
    Get Valid ticket : 9
    Invalid ticket number : 10
    Valid ticket : 3
    Invalid ticket
    The ticket number is already owned :  0
    The ticket number is already owned :  1
    The ticket number is already owned :  2
    Get Valid ticket : 3
    The ticket number is already owned :  4
    The ticket number is already owned :  5
    The ticket number is already owned :  6
    The ticket number is already owned :  7
    The ticket number is already owned :  8
    The ticket number is already owned :  9
