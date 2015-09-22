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

; while (l1 > l2) {  if (h1 > 3) { h2 = h2 + 1; l1 = h2
; printf("%d\n",l1); // type: low

;} else {l2 = l2 + 1; while (l2 > 4 || false) { h1 = h1 - 1; printf("%d\n",l2); // type: low

;
}
;}; printf("%d\n",l1); // type: low

; printf("%d\n",l2); // type: low

;
}
return 0;}