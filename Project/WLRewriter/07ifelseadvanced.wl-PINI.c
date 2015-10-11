#include <stdio.h> 
 
#define TRUE 1 
#define true 1 
#define FALSE 0 
#define false 0 


int main() { int l1;  //type: low 
scanf("%d", &l1);
int l2;  //type: low 
scanf("%d", &l2);
; int h1;  //type: high 
scanf("%d", &h1);
int h2;  //type: high 
scanf("%d", &h2);

;  if (!(((l1 == 0)))) { l1 = 2 + 4 + l1; printf("%dl11\n",l1); // type: low

;  if (h1 > 6) { l1 = 6;  if (((!(((l1 == 0))))) || ((!(((l1 == 0)))) && (h1 > 6) && (!(((l1 == 0)))))) { ;;} else {printf("%dl12\n",l1); // type: low
;}
; printf("%dh11\n",h1); // type: high

;}
;} else { if (l2 > 3) { l1 = l1 + 1; printf("%dl13\n",l1); // type: low

; printf("%dh21\n",h2); // type: high

;} else {l2 = 2 + h2;  if ((!((l2 > 3)) && !((!(((l1 == 0))))))) { printf("BOT\n"); // type: low
;} else {printf("%dl21\n",l2); // type: low
;}
; printf("%dl14\n",l1); // type: low

;};}
;  if (((!(((l1 == 0)))))) { ;;} else {printf("%dl15\n",l1); // type: low
;}
;  if ((!((l2 > 3)) && !((!(((l1 == 0))))))) { printf("BOT\n"); // type: low
;} else {printf("%dl22\n",l2); // type: low
;}
return 0;}