# 問題4B-3

前置記法の電卓を考える。機能として加算、比較、場合分けが利用できる。例えば以下の文字列

    if < 10 + 5 4 then 59 else 100

は「10が5+4より小さいなら59，そうでないなら100」のような式を表す。（Schemeでいうならば`(if (< 10 (+ 5 4)) 59 100)`である）

このような言語に対して何らかの処理を実装するには、InterpreterパターンやVisitorパターンが用いられる。

Interpreterパターンは以下のような形式で式を表すクラスを定義し、その上に処理を実装するパターンである。
電卓で利用できる式を表す抽象クラス`Expression`を定義し、そこから継承して具体的な要素を表すクラスを作る。例えば`Number`クラスは数を表し（上の例でいえば10や5, 4, 59, 100）、`IfThenElse`クラスは`if`から始まる式を表す。
（値であることを表す`Value`クラスや二項演算子を表す`BinaryOperator`クラスは具体的な要素ではないが、具体的な要素を定義するのに利用する。）
`Expression`に処理を定義し、部分式の処理はフィールドとして定義される要素に委譲、それらの結果を基に自身の結果を返すことで、言語上の処理を実現する。
ここでは、式を評価するメソッド`evaluate`をInterpretorパターンで実装した。

一方、Visitorパターンは、式を表すクラスに「`Visitor`（インターフェースを実装したインスタンス）からの要求を許可する」ことを表す`accept`メソッドを実装する。このメソッドは要求を出した`Visitor`に「自身が実際に何の式を表すものか」を示すため自身を引数に対応するメソッドを呼び出す（ここではオーバーロードを使って特定の`visit`メソッドを呼び出している）。`Visitor`インターフェースには具体的な要素についてのメソッドが列挙されており、それぞれについて実装することで（`instanceof`などのキャストを利用することなしに）それぞれの式に対する処理を実現している。
ここでは、インターフェースを実装した`StepVisitor`クラスを定義し、「式の計算に必要な最大ステップ数」を計算している。

本課題ではそれぞれ逆の機能を実装する。つまり、以下の二つを実装せよ。

1. `StepVisitor`クラスを参考に、`Expression`クラスにその式の評価に必要なステップ数を計算する`int Expression#calcSteps()`メソッドを定義し、各実体クラス（`Number`、`Bool`、`Addition`、`LessThan`、`IfThenElse`クラス）に`calcSteps`を実装せよ。
2. `Expression`クラスに定義されている`evaluate`メソッドを参考に、`Visitor`を実装して式の評価を行う`EvaluateVisitor`クラスを定義せよ。`EvaluateVisitor`クラスの`evaluate`メソッド（すでに実装されている）を呼び出すことで評価が行われるようにする。なお、`Value`クラスの`getBool`メソッドや`getNumber`メソッドは利用してよいが、`Expression`クラス（およびその子クラス）の`evaluate`メソッドは一切呼び出さないこと。

実行例には`main`メソッドのコメントアウトされているコードを有効にしたものを表示している。提出時にはコメントアウトを外した状態にすること。
なお、文字列からその式の表すインスタンスを生成するメソッドを`Expression.getExpression`として実装した。


### 実行例

    Step checking
    Check by StepVisitor
        10 is calculated within 1 steps
        false is calculated within 1 steps
        if < 10 + 4 5 then + 10 49 else 100 is calculated within 9 steps
        if true then 10 else < + 1 5 1 is calculated within 7 steps
        < 10 + false 2 is calculated within 5 steps
    Check by calcSteps method
        10 is calculated within 1 steps
        false is calculated within 1 steps
        if < 10 + 4 5 then + 10 49 else 100 is calculated within 9 steps
        if true then 10 else < + 1 5 1 is calculated within 7 steps
        < 10 + false 2 is calculated within 5 steps

    Evaluation
    Evaluate by evaluate method
        10 is evaluated to 10
        false is evaluated to false
        if < 10 + 4 5 then + 10 49 else 100 is evaluated to 100
        if true then 10 else < + 1 5 1 is evaluated to 10
        < 10 + false 2 cannot be evaluated
    Evaluate by EvaluateVisitor
        10 is evaluated to 10
        false is evaluated to false
        if < 10 + 4 5 then + 10 49 else 100 is evaluated to 100
        if true then 10 else < + 1 5 1 is evaluated to 10
        < 10 + false 2 cannot be evaluated


ほかの数式が気になるのであれば配列に要素を追加してもよい（が、既存の式は消去しないこと）。

Interpretorパターンは式の種類を増やす際には新たにクラスを定義するだけでよい一方、処理を追加する際に編集する箇所が多くなる（すべての式を表すクラスを編集する必要がある）という問題がある。逆に、Visitorパターンは処理を追加する際には新たなクラス（Visitor）を定義するだけでよい一方、式の種類を追加するときに編集する箇所が多くなる（すべての処理を実装しているVisitorを編集する必要がある）という問題がある。本問題では処理の追加について体験してもらっている。
（もしこの利点欠点について他のパラダイムや言語機構でのありようについて興味があれば、Expression Problemというキーワードで調べると良い）