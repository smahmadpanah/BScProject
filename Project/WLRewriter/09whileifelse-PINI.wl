program; 
inL l1, l2; 
inH h1, h2; 
while l1 > l2 do 
 if h1 > 3 then 
h2 = h2 + 1; 
l1 = h2; 
if ( (h1 > 3) and (l1 > l2) ) or ( (h1 > 3) and (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2) ) or ( (l1 > l2) ) or ( (l1 > l2)  and  (h1 > 3) and (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2)  and  (h1 > 3) and (l1 > l2) ) or ( (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2) )  then 
	 NOP 
 else 
	 outL l1 
endif
 else 
l2 = l2 + 1; 
while l2 > 4 or false do 
h1 = h1 - 1; 
if ( (h1 > 3) and (l1 > l2) ) or ( (h1 > 3) and (l1 > l2)  and  !(h1 > 3) and (l1 > l2) ) or ( (h1 > 3) and (l1 > l2) ) or ( (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2) ) or ( (l1 > l2)  and  !(h1 > 3) and (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2)  and  !(h1 > 3) and (l1 > l2) ) or ( (l1 > l2) )  then 
	 NOP 
 else 
	 outL l2 
endif
 done  endif ; 
if ( (h1 > 3) and (l1 > l2) ) or ( (l1 > l2)  and  (h1 > 3) and (l1 > l2) ) or ( (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2)  and  (h1 > 3) and (l1 > l2) ) or ( (l1 > l2) ) or ( (l1 > l2)  and  (h1 > 3) and (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2)  and  (h1 > 3) and (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2) ) or ( (h1 > 3) and (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2) ) or ( (l1 > l2) )  then 
	 NOP 
 else 
	 outL l1 
endif;
 
if ( (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2) ) or ( (h1 > 3) and (l1 > l2) ) or ( (l1 > l2)  and  (h1 > 3) and (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2) ) or ( (l1 > l2) ) or ( (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2)  and  (h1 > 3) and (l1 > l2) ) or ( (h1 > 3) and (l1 > l2) ) or ( (l2 > 4 or false) and !(h1 > 3) and (l1 > l2)  and  (l1 > l2) )  then 
	 NOP 
 else 
	 outL l2 
endif
 done 