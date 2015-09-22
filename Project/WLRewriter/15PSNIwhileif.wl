program;
inH h1;
inL l2;
while h1 >= l2 do
	h1 = h1 - 1;
	l2 = l2 + 1;
	if l2 > 0 then
		l2 = l2 - 2
	endif
done;
outL l2
