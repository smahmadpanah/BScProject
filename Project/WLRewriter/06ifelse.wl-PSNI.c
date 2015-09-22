#include <stdio.h> 
 
#define TRUE 1 
#define true 1 
#define FALSE 0 
#define false 0 


int main() { int l1;  //type: low 
scanf("%d", &l1);
; int h1;  //type: high 
scanf("%d", &h1);

;  if (h1 == l1) { l1 = l1 + 1;  if (TRUE) { ;;} else {printf("%d\n",l1); // type: low
;}
;} else {l1 = l1 - 2;  if (TRUE) { ;;} else {printf("%d\n",l1); // type: low
;}
;}
; printf("%d\n",h1); // type: high

;  if (TRUE) { ;;} else {printf("%d\n",l1); // type: low
;}
; l1 = 3
; printf("%d\n",l1); // type: low

return 0;}