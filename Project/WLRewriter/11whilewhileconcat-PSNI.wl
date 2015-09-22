program; 
inH h1, h2; 
inL l1, l2; 
while l1 > 0 do 
h1 = h2 + 2; 
outL l1 done ; 
while 0 <= l1 do 
while h1 > l1 do 
l1 = l1 + 3; 
 if ((0 <= l1)) or ((l1 > 0) and (0 <= l1)) or ((l1 > 0) and (0 <= l1)) or ((0 <= l1)) then 
NOP else 
outL l1 endif  done  done ; 
 if ((l1 > 0) and (0 <= l1)) or ((0 <= l1)) then 
NOP else 
outL l1 endif ; 
outH h1