program; 
inL l1, l2; 
inH h1, h2; 
 if !(l1 == 0) and l1 > 0 then 
l1 = 2 + l1; 
l2 = h1; 
 if l2 > 5 then 
l1 = l2 else 
l1 = l2 + 2 endif  endif; 
if ( (!(l1 == 0) and l1 > 0)  and  (l2 > 5) and (!(l1 == 0) and l1 > 0) ) or ( (!(l1 == 0) and l1 > 0) ) or ( (!(l1 == 0) and l1 > 0)  and  !(l2 > 5) and (!(l1 == 0) and l1 > 0) ) or ( (!(l1 == 0) and l1 > 0) )  then 
	 NOP 
 else 
	 outL l1 
endif
