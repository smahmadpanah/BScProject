program; 
inH h1, h2; 
inL l1; 
while h1 > l1 do 
l1 = l1 + 1; 
 if TRUE then 
NOP else 
outL l1 endif ; 
if h2 < 0 then 
h2 = h2 - 1; 
 if TRUE then 
NOP else 
outL l1 endif ; 
l1 = h2 
endif done ; 
 if TRUE then 
NOP else 
outL l1 endif 