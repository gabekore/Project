// DllCallerCPP.cpp : このファイルには 'main' 関数が含まれています。プログラム実行の開始と終了がそこで行われます。
//

#include "pch.h"
#include <iostream>
#include <windows.h>
#include <stdio.h>
#include "dllexam.h"

using namespace std;

#pragma comment(lib, "ExampleDll.lib")

__declspec(dllimport)
int __stdcall Example(void);
double __stdcall Sample01(int a);
void __stdcall Sample02(int a, char* str);
void __stdcall Sample03(int a, char* str);
void __stdcall Sample04(SampleStruct st);
void __stdcall Sample05(SampleStruct* st);
void __stdcall Sample06(SampleStruct2* st);

int main()
{
	// Example
	int i = Example();
	std::cout << i << "\n\n";

	// 01
	double d = Sample01(6);
	std::cout << d << "\n\n";

	// 02
	char sample02_a[] = { "string 型で文字列を渡すことができます。" };
	Sample02(2, sample02_a);
	std::cout << "\n\n";

	// 03
	char sample03_a[] = { "文字列のバッファを渡す場合は C#ならStringBuilderクラス、C++ならcharポインタで受け渡します。" };
	Sample03(3, sample03_a);
	std::cout << sample03_a << "\n\n";

	// 04
	SampleStruct sample04_a;
	sample04_a.index = 4;
	strncpy_s(sample04_a.name, "構造体サンプル", 128);
	sample04_a.data[0] = 11;
	sample04_a.data[1] = 22;
	sample04_a.data[2] = 33;
	Sample04(sample04_a);
	std::cout << "\n\n";

	// 05
	SampleStruct sample05_a;
	Sample05(&sample05_a);
	std::cout << "index = " << sample05_a.index << "\n";
	std::cout << "name = " << sample05_a.name << "\n";
	std::cout << "data[0] = " << sample05_a.data[0] << ", data[1] = " << sample05_a.data[1] << ", data[2] = " << sample05_a.data[2] << ", data[3] = " << sample05_a.data[3] << "\n";
	std::cout << "DLL 側できちんと初期化していないと data[3] に値が現れることに注意する必要がある。";
	std::cout << "\n\n";

	// 06
	SampleStruct2 sample06_a;
	Sample06(&sample06_a);
	for (int i = 0; i < sample06_a.length; i++)
	{
		std::cout << "data[" << i << "] = " << sample06_a.data[i] << "\n";
	}




	return 0;
}

