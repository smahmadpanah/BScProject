program;
inL l1 , l2;
inH h1, h2;
while l1 > l2 do
	if h1 > 3 then
		h2 = h2 + 1;
		l1 = h2;
		outL l1
	else
		l2 = l2 + 1;
		while l2 > 4 or false do
			h1 = h1 - 1;
			outL l2
		done
	endif;
	outL l1;
	outL l2;
done