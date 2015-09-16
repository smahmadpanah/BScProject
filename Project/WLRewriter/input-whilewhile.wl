program;
inH k , r;
inL l1 , l2;
while l2 > 0 do
	l1 = l1 + 1
done;
while k>0 do 
	while r>9 and l1<=3 do
		r = 2 + k;
		outH r;
		outL l1
	done;
	k = k - r;
	outH r
done
