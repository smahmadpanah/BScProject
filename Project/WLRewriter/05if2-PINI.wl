program; 
inH h1, h2; 
inL l1, l2; 
 if l1 > 0 then 
l1 = l1 - 1; 
 if l2 == 10 then 
 if h2 > 9 then 
l1 = l1 + 2 else 
l2 = 2 endif  else 
l2 = h1 endif ; 
if ( (l2 == 10) and (l1 > 0) )  then 
	 NOP 
 else 
	 outL l1 
endif
 endif; 
l1 = 2; 
outL l1; 
if ( !(l2 == 10) and (l1 > 0) ) or ( (l2 == 10) and (l1 > 0) )  then 
	 NOP 
 else 
	 outL l2 
endif
