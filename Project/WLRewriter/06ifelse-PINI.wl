program; 
inL l1; 
inH h1; 
 if h1 == l1 then 
l1 = l1 + 1; 
if TRUE then 
	 NOP 
 else 
	 outL l1 
endif
 else 
l1 = l1 - 2; 
if TRUE then 
	 NOP 
 else 
	 outL l1 
endif
 endif ; 
outH h1; 
if TRUE then 
	 NOP 
 else 
	 outL l1 
endif;
 
l1 = 3; 
outL l1