program;
inH h1, h2;
inL l1, l2;
if l1 > 0 then
	l1 = l1 - 1;
	if l2 == 10 then
		if h2 > 9 then
			l1 = l1 + 2
		else 
			l2 = 2
		endif
	else
		l2 = h1
	endif;	
		outL l1
endif;
l1 = 2;
outL l1;
outL l2