package dllcallerjava;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Structure;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;


public class DllCallerJAVA {
    
    
    //--------------------------------------------------------------------------
    // 構造体
    //--------------------------------------------------------------------------
    public static class SampleStruct extends Structure {
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("index", "name", "data");
        }
        
	public int    index;
	public byte[] name = new byte[128];
	public int[]  data = new int[50];
    }


    public static class SampleStruct2 extends Structure {
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("length", "data");
        }
        
	public int    length;
	public double[] data;
    }





    public interface ExampleDll extends Library {
        
        String path = new File(".").getAbsoluteFile().getParent();
        
        ExampleDll INSTANCE = (ExampleDll)Native.load(path + "./lib/ExampleDll.dll", ExampleDll.class);

        //----------------------------------------------------------------------
        // DLLが持つ関数
        //----------------------------------------------------------------------
        int Example();
        double Sample01(int a);
        void Sample02(int a, String str);
        void Sample03(int a, byte[] str);
        void Sample04(SampleStruct st);
        void Sample05(SampleStruct st);
        void Sample06(SampleStruct2 st);
    }
    
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        ExampleDll dll = ExampleDll.INSTANCE;
        
        // Example
        int i = dll.Example();
        System.out.println(i);
        System.out.println("");
        
        // 01
	double d = dll.Sample01(6);
        System.out.println(d);
        

	// 02
	String sample02_a = "string 型で文字列を渡すことができます。";
	dll.Sample02(2, sample02_a);
        
        // 03
        String sample03_a = "abcdefあいうえお01234";
        byte[] sample03_b = sample03_a.getBytes();
        dll.Sample03(3, sample03_b);
        System.out.println("→：" + Native.toString(sample03_b, "Shift_JIS"));
        
        
        // 04
        {
            SampleStruct sample04_a = new SampleStruct();
            sample04_a.index = 4;
            String s = "abc漢字も書けるカタカナ123";
            byte[] c = s.getBytes();
            for (int j = 0; j < sample04_a.name.length && j < c.length;  j++) {
                sample04_a.name[j] = c[j];
            }
            sample04_a.data[0] = 4;
            sample04_a.data[1] = 5;
            sample04_a.data[2] = 6;
            dll.Sample04(sample04_a);
        }

        // 05
        {
            SampleStruct sample05_a = new SampleStruct();
            sample05_a.index = 4;
            String s = "ABCひらがなカタカナ456";
            byte[] c = s.getBytes();
            for (int j = 0; j < sample05_a.name.length && j < c.length;  j++) {
                sample05_a.name[j] = c[j];
            }
            sample05_a.data[0] = 7;
            sample05_a.data[1] = 8;
            sample05_a.data[2] = 9;
            dll.Sample05(sample05_a);
            System.out.println("(5)index:" + sample05_a.index);
            System.out.println("(5)name :" + Native.toString(sample05_a.name, "Shift_JIS"));
            System.out.println(String.format("(5)%d, %d, %d, %d, %d, %d, %d", sample05_a.data[0], sample05_a.data[1], sample05_a.data[2], sample05_a.data[3], sample05_a.data[4], sample05_a.data[5], sample05_a.data[6]));
        }
        
        // 06
        // このパターンはやらないと思うので諦める
//        {
//            SampleStruct2 sample06_a = new SampleStruct2();
//            sample06_a.length = 34;
//            sample06_a.data = new double[]{1.23, 3.45, 5,67};
//            dll.Sample06(sample06_a);
//            System.out.println("(6)length:" + sample06_a.length);
//            System.out.println("(6)data0:" + sample06_a.data[0]);
//            System.out.println("(6)data1:" + sample06_a.data[1]);
//            System.out.println("(6)data2:" + sample06_a.data[2]);
//            System.out.println("(6)data3:" + sample06_a.data[3]);
//            System.out.println("(6)data4:" + sample06_a.data[4]);
//            System.out.println("(6)data5:" + sample06_a.data[5]);
//        }
    }
}
