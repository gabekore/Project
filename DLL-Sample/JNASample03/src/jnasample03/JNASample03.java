/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jnasample03;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;

// 文字列を渡すunicode
public class JNASample03 {
    public interface User32 extends Library {
        User32 INSTANCE = (User32) Native.load("user32", User32.class);
        // A/Wの区別がある場合はWを付ける
        int MessageBoxW(Pointer hWnd, WString lpText, WString lpCaption, int uType);
    }

    public static void main(String[] args) {
        User32.INSTANCE.MessageBoxW(null, new WString("テスト"), new WString("タイトル"), 0);
    }
}