program;
inH k , r;
inL l;
while k>0 do 
	if r==9 then
		r = r + 1
	else 
		k = k - 1;
		if l > 1 then
		l = k
		endif
endif;
outH k;
outL l
done