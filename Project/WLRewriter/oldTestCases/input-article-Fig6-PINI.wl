program; 
inH h1, h2; 
inL l1, l2, l3; 
 if l1 <= 0 then 
l2 = h1; 
outL l1; 
if ( (l1 <= 0) )  then 
	 outL BOT 
 else 
	 outL l2 
endif
 else 
 if h2 == h1 then 
l3 = 0; 
outL l1 else 
l1 = 1 endif ; 
outL l1; 
 if l3 == 0 then 
l3 = 1 endif endif ; 
if ( !(l1 <= 0) )  then 
	 NOP 
 else 
	 outL l3 
endif
