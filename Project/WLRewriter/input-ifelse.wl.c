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

;  if ((h1 == 0)) { l1 = l2; l1 = h1
; printf("%d\n",l1); // type: low

;} else { if (l2 > 3) { l2 = h1 + 1; printf("%d\n",h1); // type: high

; printf("%d\n",l2); // type: low

;};}
; printf("%d\n",l1); // type: low

; printf("%d\n",l2); // type: low

return 0;}