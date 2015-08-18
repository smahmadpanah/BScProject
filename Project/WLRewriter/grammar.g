program ::= PROGRAM IDENTIFIER ';' clist
clist ::= c | clist ; c
exp ::= b | n | x | exp == exp | exp < exp | exp <= exp | exp >= exp | exp > exp
		| exp + exp | exp - exp | exp or exp | exp and exp | ! exp
c ::= NOP | x = exp | inL varlist | inH varlist | outL x | outH x | outL BOT | outH BOT 
	  | if exp then clist endif | if exp then clist else clist endif
	  | while exp do clist done
varlist ::= x | x, varlist
b ::= BOOL_CONSTANT
n ::= INTEGER_NUMBER
x ::= IDENTIFIER
