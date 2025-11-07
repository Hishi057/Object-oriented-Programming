# 問題3B-2

`String`の連結と`StringBuilder`による連結した文字列の作成の効率を体感するために、**`String`の連結が10倍以上遅い例を作成せよ**。
なお、ここでの「遅い」とは1度の連結の速度差ではなく、文字列の構築全体を指す。
それぞれの構築を`buildString`メソッドと`buildStringByBuilder`メソッドとして実装すること。
実行ごとにブレが大きくなる場合があるので、ある程度大きなものとすることと複数回の実行を勧める。

`String`の連結と`StringBuilder`による連結は若干の差異があるため、可能な限り同等の処理となるようにせよ。
例えば文字列の連結の結果は本来順序によらないが、ここでは同一とするように実装せよ。
また、実行時間を比較しているため、意図的に時間稼ぎをするような処理を間に挟んではならない。
もし連結以外の処理が必要な場合には、`main`メソッドにその処理を追加するなどして文字列の構築自体に入らないようにすること。
既に記述されているフィールドやメソッド以外に定義を追加して利用しても良い。

StringBuilderについては https://docs.oracle.com/javase/jp/8/docs/api/java/lang/StringBuilder.html を参照すると良い。


### 実行例

	StringBuilder append
    Process time (us) : 293
    String append
    Process time (us) : 4041
    Identity check : true

実行時間はただの例であるため参考としないこと。
