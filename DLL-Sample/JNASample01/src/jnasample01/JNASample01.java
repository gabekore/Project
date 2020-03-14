package jnasample01;

import com.sun.jna.Library;
import com.sun.jna.Native;

// 基本形
public class JNASample01 {
    public interface Kernel32 extends Library {
        Kernel32 INSTANCE = (Kernel32) Native.load("kernel32", Kernel32.class);
        void Sleep(int dwMilliseconds);
    }

    public static void main(String[] args) {
        System.out.println("started");
        Kernel32.INSTANCE.Sleep(1000);
        System.out.println("finished");
    }
}