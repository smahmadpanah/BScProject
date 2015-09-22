program; 
inL l1, l2; 
inH h1, h2; 
 if (h1 == 0) then 
l1 = l2; 
l1 = h1; 
if TRUE then 
	 NOP 
 else 
	 outL l1 
endif
 else 
 if l2 > 3 then 
l2 = h1 + 1; 
outH h1; 
if TRUE then 
	 NOP 
 else 
	 outL l2 
endif
 endif endif ; 
if TRUE then 
	 NOP 
 else 
	 outL l1 
endif;
 
if TRUE then 
	 NOP 
 else 
	 outL l2 
endif
