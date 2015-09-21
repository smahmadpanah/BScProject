program;
inH h1;
inL l1;
inH h2;
inL l2;
while h1 < 4 do 
	l1 = l1 + 2;
	outL l2;
	while l2 > 3 do
		h2 = h2 + l2;
		l2 = l2 + 1
	done
done;
outL l1
		