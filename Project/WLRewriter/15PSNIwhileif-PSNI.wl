program; 
inH h1; 
inL l2; 
if (l2 <= 0) then 
while h1 >= l2 do 
h1 = h1 - 1; 
l2 = l2 + 1; 
 if l2 > 0 then 
l2 = l2 - 2 endif done 
endif; 
 if TRUE then 
NOP else 
outL l2 endif 