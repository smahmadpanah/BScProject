program;
inL l1, l2;
inH h1;
if (h1 == 0) then
	l1 = l2;
	outL l1
else 
	NOP;
	l1 = 3;
	outH h1;
	outL l2
endif;
outL l1
