#include <stdio.h> 
 
#define TRUE 1 
#define true 1 
#define FALSE 0 
#define false 0 


int main() { int h1;  //type: high 
scanf("%d", &h1);
int h2;  //type: high 
scanf("%d", &h2);
; int l1;  //type: low 
scanf("%d", &l1);
int l2;  //type: low 
scanf("%d", &l2);
int l3;  //type: low 
scanf("%d", &l3);

;  if (l1 <= 0) { l2 = h1; printf("%d\n",l1); // type: low

; printf("%d\n",l2); // type: low

;} else { if (h2 == h1) { l3 = 0; printf("%d\n",l1); // type: low

;} else {l1 = 1;}; printf("%d\n",l1); // type: low

;  if (l3 == 0) { l3 = 1;}
;}
; printf("%d\n",l3); // type: low

return 0;}