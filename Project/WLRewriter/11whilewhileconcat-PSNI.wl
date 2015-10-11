program; 
inL l1; 
inH h1, h2; 
if h2 < 0 then 
while l1 > 0 do 
l1 = h2 + l1 done 
endif; 
while h1 > l1 do 
l1 = l1 + 3; 
 if TRUE then 
NOP else 
outL l1 endif  done ; 
 if TRUE then 
NOP else 
outL l1 endif ; 
outH h1