program;
inL l1;
inH h1 , h2;
while l1 > 0 do
	l1 = h2 + l1;
	outL l1
done;
while h1 > l1 do
		l1 = l1 + 3;
		outL l1
done;
outL l1;
outH h1
