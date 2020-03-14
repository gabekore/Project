using System;
using System.Runtime.InteropServices;
using System.Text;

namespace DllCallerCS
{
    class Program
    {
        [DllImport("ExampleDll.dll")]
        private static extern int Example();

        [DllImport("ExampleDll.dll")]
        private static extern double Sample01(int a);

        [DllImport("ExampleDll.dll")]
        private static extern void Sample02(int a, string str);

        [DllImport("ExampleDll.dll")]
        private static extern void Sample03(int a, StringBuilder str);


        [DllImport("ExampleDll.dll")]
        private static extern void Sample04(SampleStruct st);

        // C++側と合わせている
        [StructLayout(LayoutKind.Sequential)]
        private struct SampleStruct
        {
            /// <summary>
            /// 4 バイト符号付整数
            /// </summary>
            [MarshalAs(UnmanagedType.I4)]
            public int index;

            /// <summary>
            /// 固定長文字配列 (SizeConst は配列のサイズを示す)
            /// </summary>
            [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 128)]
            public string name;

            /// <summary>
            /// 固定長配列 (SizeConst は配列の要素数を示す)
            /// </summary>
            [MarshalAs(UnmanagedType.ByValArray, SizeConst = 50)]
            public int[] data;
        }



        [DllImport("ExampleDll.dll")]
        private static extern void Sample05(IntPtr st);



        [DllImport("ExampleDll.dll")]
        private static extern void Sample06(IntPtr st);

        [StructLayout(LayoutKind.Sequential)]
        private struct SampleStruct2
        {
            public int length;
            public IntPtr data;
        }



        static void Main(string[] args)
        {
            Console.WriteLine(Example());

            Console.WriteLine(Sample01(10));

            var sample02_a = "string 型で文字列を渡すことができます。";
            Sample02(2, sample02_a);

            var sample03_a = new System.Text.StringBuilder(256);
            sample03_a.Append("文字列のバッファを渡す場合は StringBuilder クラスで受け渡します。");
            Sample03(3, sample03_a);
            Console.WriteLine(sample03_a);


            var sample04_a = new SampleStruct()
            {
                index = 4,
                name = "構造体サンプル",
                data = new int[50],
            };
            sample04_a.data[0] = 11;
            sample04_a.data[1] = 22;
            sample04_a.data[2] = 33;
            Sample04(sample04_a);




            var sample05_a = Marshal.AllocHGlobal(Marshal.SizeOf(typeof(SampleStruct)));
            try
            {
                Sample05(sample05_a);
                var sample05_b = (SampleStruct)Marshal.PtrToStructure(sample05_a, typeof(SampleStruct));
                Console.WriteLine("index = " + sample05_b.index);
                Console.WriteLine("name = " + sample05_b.name);
                Console.WriteLine("data[0] = {0}, data[1] = {1}, data[2] = {2}, data[3] = {3}", sample05_b.data[0], sample05_b.data[1], sample05_b.data[2], sample05_b.data[3]);
                Console.WriteLine("DLL 側できちんと初期化していないと data[3] に値が現れることに注意する必要がある。");
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine(ex);
            }
            finally
            {
                // 必ずメモリを解放するようにする
                Marshal.FreeHGlobal(sample05_a);
            }



            Console.WriteLine("sssssss");


            var sample06_a = Marshal.AllocHGlobal(Marshal.SizeOf(typeof(SampleStruct2)));
            try
            {
                Sample06(sample06_a);
                var sample06_b = (SampleStruct2)Marshal.PtrToStructure(sample06_a, typeof(SampleStruct2));
                for (var i = 0; i < sample06_b.length; i++)
                {
                    var v = Marshal.ReadInt64(sample06_b.data, i * sizeof(double));
                    Console.WriteLine("data[{0}] = {1}", i, BitConverter.Int64BitsToDouble(v));
                }
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine(ex);
            }
            finally
            {
                // 必ずメモリを解放するようにする
                Marshal.FreeHGlobal(sample06_a);
            }


            Console.WriteLine("rrrrr");





            Console.ReadLine();
        }
    }
}
