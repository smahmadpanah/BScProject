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

; while (l1 > 0) { l1 = h2 + l1;
}
; while (h1 > l1) { l1 = l1 + 3; printf("%d:9\n",l1); // type: low

;
}
; printf("%d:11\n",l1); // type: low

; printf("%d:12\n",h1); // type: high

return 0;}