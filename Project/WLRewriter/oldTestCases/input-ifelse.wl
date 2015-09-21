program;
inL l1, l2;
inH h1, h2;
if (h1 == 0) then
	l1 = l2;
	l1 = h1;
	outL l1
else 
	if l2 > 3 then
	l2 = h1+1;
	outH h1;
	outL l2
	endif
endif;
outL l1;
outL l2
