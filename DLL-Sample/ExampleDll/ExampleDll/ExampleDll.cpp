// ExampleDll.cpp : DLL アプリケーション用にエクスポートされる関数を定義します。
//

#include "stdafx.h"
#include "ExampleDll.h"
#include <stdio.h>

#define PI		3.1415926536

typedef struct _SampleStruct
{
	int index;
	char name[128];
	int data[50];
} SampleStruct, *PSampleStruct;

typedef struct _SampleStruct2
{
	int length;
	double* data;
} SampleStruct2, *PSampleStruct2;





int __stdcall Example()
{
	return 5;
}

double __stdcall Sample01(int a)
{
	printf("--<SampleDll:Sample01>---------------\r\n");
	printf("a = %d\r\n", a);
	printf("-------------------------------------\r\n");

	return PI;
}

void __stdcall Sample02(int a, char* str)
{
	printf("--<SampleDll:Sample02>---------------\r\n");
	printf("[%d] %s\r\n", a, str);
	printf("-------------------------------------\r\n");
}

void __stdcall Sample03(int a, char* str)
{
	printf("--<SampleDll:Sample03>---------------\r\n");
	printf("[%d] %s\r\n", a, str);
	sprintf_s(str, 256, "DLL 側から文字列を返す場合は StringBuilder クラスを使用します。");
	printf("-------------------------------------\r\n");
}

void __stdcall Sample04(SampleStruct st)
{
	printf("--<SampleDll:Sample04>---------------\r\n");
	printf("index = %d\r\n", st.index);
	printf("name = %s\r\n", st.name);
	printf("data[0] = %d, data[1] = %d, data[2] = %d, data[3] = %d\r\n", st.data[0], st.data[1], st.data[2], st.data[3]);
	printf("-------------------------------------\r\n");
}


void __stdcall Sample05(SampleStruct* st)
{
	printf("--<SampleDll:Sample05>---------------\r\n");
	//memset(st, 0, sizeof(SampleStruct));
	(*st).index = 5;
	sprintf_s((*st).name, 128, "構造体ポインタサンプル");
	(*st).data[0] = 11;
	(*st).data[1] = 22;
	(*st).data[2] = 33;
	printf("-------------------------------------\r\n");
}



double g_dData[256];

void __stdcall Sample06(SampleStruct2* st)
{
	printf("--<SampleDll:Sample06>---------------\r\n");
	memset(st, 0, sizeof(SampleStruct2));
	memset(g_dData, 0, sizeof(g_dData));
	(*st).length = 10;
	(*st).data = g_dData;
	for (int i = 0; i < (*st).length; i++)
	{
		g_dData[i] = (i + 1) / 10.0;
	}
	printf("-------------------------------------\r\n");
}