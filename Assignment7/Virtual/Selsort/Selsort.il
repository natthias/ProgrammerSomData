    // XXX: read the comments pls

    .method public static hidebysig 
           default void SelectionSort (int32[] arr)  cil managed 
    {
        // Method begins at RVA 0x20c8
	// Code size 72 (0x48)
	.maxstack 4         
	.locals init ( 
		int32	V_0,                // i
		int32	V_1,                // least
		int32	V_2,                // j
		int32	V_3)                // tmp
	IL_0000:  ldc.i4.0              // puts 0 on the stack
	IL_0001:  stloc.0               // takes 0 from the stack and stores it in i
	IL_0002:  br IL_003e            // jumps to the outer loop check
    // int least = i;
	    IL_0007:  ldloc.0           // puts i on the stack
	    IL_0008:  stloc.1           // stores it in least (least = i)
    // int j = i+1
	    IL_0009:  ldloc.0           // puts i on the stack
	    IL_000a:  ldc.i4.1          // puts 1 on the stack
	    IL_000b:  add               // adds the top two values (i + 1)
	    IL_000c:  stloc.2           // stores the result in j
	    IL_000d:  br IL_0023        // jumps to the inner loop check
    // arr[j] < arr[least]
	    IL_0012:  ldarg.0           // puts the array on the stack
	    IL_0013:  ldloc.2           // puts j on the stack 
	    IL_0014:  ldelem.i4         // puts arr[j] onto the stack 
	    IL_0015:  ldarg.0           // puts the array on the stack  
	    IL_0016:  ldloc.1           // puts least on the stack
	    IL_0017:  ldelem.i4         // puts arr[least] onto the stack 
	    IL_0018:  bge IL_001f       // jumps if arr[j] less than arr[least]
    // least = j;
	    IL_001d:  ldloc.2           // puts j on the stack 
	    IL_001e:  stloc.1           // stores the result in least (least = j) 
    // j++
	    IL_001f:  ldloc.2           // puts j on the stack 
	    IL_0020:  ldc.i4.1          // puts 1 on the stack 
	    IL_0021:  add               // adds 1 to j  
	    IL_0022:  stloc.2           // stores the result in j 
    // j < arr.Length;
	    IL_0023:  ldloc.2           // puts j on the stack
	    IL_0024:  ldarg.0           // puts arr on the stack 
	    IL_0025:  ldlen             // puts len of arr on stack 
	    IL_0026:  conv.i4           // converts it to int 4
	    IL_0027:  blt IL_0012       // jumps if j less than arr length
    //  int tmp = arr[i];
	    IL_002c:  ldarg.0           // puts arr on the stack
	    IL_002d:  ldloc.0           // puts i on the stack
	    IL_002e:  ldelem.i4         // puts arr[i] onto the stack
	    IL_002f:  stloc.3           // stores arr[i] in temp 
    // arr[i] = arr[least];
	    IL_0030:  ldarg.0           // puts arr on the stack 
	    IL_0031:  ldloc.0           // puts i on the stack 
	    IL_0032:  ldarg.0           // puts arr on the stack
	    IL_0033:  ldloc.1           // puts least on the stack
	    IL_0034:  ldelem.i4         // puts arr[least] onto the stack 
	    IL_0035:  stelem.i4         // stores arr[least] in arr[i]
    // arr[least] = tmp;
	    IL_0036:  ldarg.0           // puts arr on the stack
	    IL_0037:  ldloc.1           // puts least on the stack
	    IL_0038:  ldloc.3           // puts tmp on the stack
	    IL_0039:  stelem.i4         // stores tmp in arr[least]
    // i++ 
	    IL_003a:  ldloc.0           // puts i on the stack
	    IL_003b:  ldc.i4.1          // puts 1 on the stack
	    IL_003c:  add               // adds 1 to i
	    IL_003d:  stloc.0           // stores the result in i
    // ; i < arr.Length;
	    IL_003e:  ldloc.0           // puts i on the stack 
	    IL_003f:  ldarg.0           // puts arr on the stack 
	    IL_0040:  ldlen             // puts len of arr on stack 
	    IL_0041:  conv.i4           // converts it to int 4
	    IL_0042:  blt IL_0007       // jumps if the if i less than length

	IL_0047:  ret 
    } // end of method Selsort::SelectionSort
