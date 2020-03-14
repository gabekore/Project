#pragma once

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



