#include <stdio.h> 
 
#define TRUE 1 
#define true 1 
#define FALSE 0 
#define false 0 


int main() { int l1;  //type: low 
scanf("%d", &l1);
; int h1;  //type: high 
scanf("%d", &h1);
int h2;  //type: high 
scanf("%d", &h2);

;  if (h1 > 0) { l1 = l1 + 1; printf("%d\n",h2); // type: high

;}
; printf("%d\n",l1); // type: low

; l1 = h2 - 2
; printf("%d\n",l1); // type: low

return 0;}