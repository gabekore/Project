/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jnasample02;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;


// 文字列を渡す
public class JNASample02 {
    public interface User32 extends Library {
        User32 INSTANCE = (User32) Native.load("user32", User32.class);
        // A/Wの区別がある場合はAを付ける
        int MessageBoxA(Pointer hWnd, String lpText, String lpCaption, int uType);
    }

    public static void main(String[] args) {
        User32.INSTANCE.MessageBoxA(null, "テスト", "タイトル", 0);
    }
}