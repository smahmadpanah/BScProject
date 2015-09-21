#include <stdio.h> 
 
#define TRUE 1 
#define true 1 
#define FALSE 0 
#define false 0 


int main() { int l1;  //type: low 
scanf("%d", &l1);
; int h1;  //type: high 
scanf("%d", &h1);

; l1 = h1
;  if (TRUE) { printf("BOT\n"); // type: low
;} else {printf("%d\n",l1); // type: low
;}
; printf("%d\n",h1); // type: high

return 0;}