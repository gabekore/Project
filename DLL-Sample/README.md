# 概要

VC++で作ったDLLを、
- VC++のEXE
- C#のEXE
- Javaのクラス
から呼び出すサンプル

内容は参考URLからコピっているので、私のオリジナルではないです

# 開発環境
- Visual Studio 2017
    - VC++、ライブラリ
    - VC++、コンソール
    - C#、コンソール
    - 全てReleaseビルドの64bitビルド（32bitは考慮してない、面倒だった・・・）
- NetBeans 8.1
    - Java Native Access (JNA) のjarファイル
    - [Java Native Access (JNA)のREADME](https://github.com/java-native-access/jna/blob/master/README.md) からダウンロード
        - jna-5.5.0.jar
        - jna-platform-5.5.0.jar

# メモ
- DLLの呼び出しは、VC++よりもC#から呼び出すほうがラクだった
- VC++からDLLを呼び出すには静的リンクの方が使い勝手良さそう
    - 但し、ビルド時にDLLとLIBファイルが必要
    - 動的だと関数の型宣言しないので、コンパイル時に型チェックできない
    - 動的だと関数名を文字で書くので、コンパイル時に型チェックできない
- Javaから呼ぶ場合
    - DLLの期待する引数がchar*なら、Javaではbyte配列を使うといい
        - char配列とかStringBuilderはうまく扱えなかった
    - 

# 参考URL

- [JNA (Java Native Access) パターン集](https://qiita.com/everylittle/items/b888cbec643f14de5ea6#%E4%BE%8B1)
    - Java Native Access (JNA)の実践的な使い方が書かれてある
- [Java Native Access (JNA) | GitHub](https://github.com/java-native-access/jna)
    - JavaからVC++のネイティブコード（DLL）にアクセスするのに必要なもの
- [C/C++ による汎用 DLL を作成する その1](http://kisuke0303.sakura.ne.jp/blog/?p=314)
- [C/C++ による汎用 DLL を作成する その2](http://kisuke0303.sakura.ne.jp/blog/?p=323)
- [C/C++ による汎用 DLL を作成する その3](http://kisuke0303.sakura.ne.jp/blog/?p=332)
- [C/C++ による汎用 DLL を作成する その4](http://kisuke0303.sakura.ne.jp/blog/?p=354)
- [C/C++ による汎用 DLL を作成する その5](http://kisuke0303.sakura.ne.jp/blog/?p=365)
- [C/C++ による汎用 DLL を作成する その6](http://kisuke0303.sakura.ne.jp/blog/?p=372)
    - このシリーズはめちゃくちゃ役に立たった
- [【C++】【C#】C++でDLLを作成してC#から呼ぶ](https://light11.hatenadiary.com/entry/2019/05/25/235638)
- [POSIX メッセージキュー（POSIXに合わせてWindows用に実装）](https://util.osdn.jp/mqueue-win32/)

# 謝意
参考URLの素晴らしい記事がものすごく勉強になりました。

ありがとうございます！！！
