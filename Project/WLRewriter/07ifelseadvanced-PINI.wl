program; 
inL l1, l2; 
inH h1, h2; 
 if !((l1 == 0)) then 
l1 = 2 + 4 + l1; 
outL l1; 
 if h1 > 6 then 
l1 = 6; 
if ( (!((l1 == 0))) ) or ( (!((l1 == 0)))  and  (h1 > 6) and (!((l1 == 0))) )  then 
	 NOP 
 else 
	 outL l1 
endif;
 
outH h1 endif else 
 if l2 > 3 then 
l1 = l1 + 1; 
outL l1; 
outH h2 else 
l2 = 2 + h2; 
if ( !(l2 > 3) and !(!((l1 == 0))) )  then 
	 outL BOT 
 else 
	 outL l2 
endif;
 
outL l1 endif  endif ; 
if ( (!((l1 == 0))) )  then 
	 NOP 
 else 
	 outL l1 
endif;
 
if ( !(l2 > 3) and !(!((l1 == 0))) )  then 
	 outL BOT 
 else 
	 outL l2 
endif
