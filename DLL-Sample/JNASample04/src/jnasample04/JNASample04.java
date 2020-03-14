/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jnasample04;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

// 参照渡しでデータを受け取る
public class JNASample04 {
    public interface Kernel32 extends Library {
        Kernel32 INSTANCE = (Kernel32) Native.load("kernel32", Kernel32.class);
        boolean GetComputerNameW(char[] lpBuffer, IntByReference lpnSize);
    }

    public static void main(String[] args) {
        IntByReference lenComputerName = new IntByReference();
        Kernel32.INSTANCE.GetComputerNameW(null, lenComputerName);
        char[] computerName = new char[lenComputerName.getValue()];
        Kernel32.INSTANCE.GetComputerNameW(computerName, lenComputerName);
        System.out.println(Native.toString(computerName));
    }
}