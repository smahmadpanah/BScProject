package wlrewriter;

import java.lang.*;
  
%%

%byaccj


LETTER = [a-zA-Z]
DIGIT = [0-9]
NONZERO_DIGIT = [1-9]

PROGRAM_KW = (program)

AND_KW = (and)
OR_KW = (or)

ASSIGN_KW = [=]

IF_KW = (if)
THEN_KW = (then)
ELSE_KW = (else)
ENDIF_KW = (endif)

WHILE_KW = (while)
DO_KW = (do)
DONE_KW = (done)

NOP_KW = (NOP)

BOT_KW = (BOT)

INL_KW = (inL)
INH_KW = (inH)
OUTL_KW = (outL)
OUTH_KW = (outH)

PLUS_KW =[+]
MINUS_KW =[-]

LT_KW =[<]
LE_KW =(<=)
EQ_KW =(==)
GT_KW =[>]
GE_KW =(>=)

INTEGER_NUMBER ="0"|({NONZERO_DIGIT}{DIGIT}*)
BOOL_CONSTANT ="true"|"false"
IDENTIFIER ={LETTER}+|{LETTER}({LETTER}|{DIGIT})*
	
LineTerminator = \r|\n|\r\n

%%

{LineTerminator} {yyline++;}

{PROGRAM_KW} {
	return YYParser.PROGRAM_KW;
}

{AND_KW} {
	return YYParser.AND_KW;
}
{OR_KW} {
	return YYParser.OR_KW;
}

{ASSIGN_KW} {
	return YYParser.ASSIGN_KW;
}

{IF_KW} {
	return YYParser.IF_KW;
}
{THEN_KW} {
	return YYParser.THEN_KW;
}
{ELSE_KW} {
	return YYParser.ELSE_KW;
}
{ENDIF_KW} {
	return YYParser.ENDIF_KW;
}

{WHILE_KW} {
	return YYParser.WHILE_KW;
}
{DO_KW} {
	return YYParser.DO_KW;
}
{DONE_KW} {
	return YYParser.DONE_KW;
}

{NOP_KW} {
	return YYParser.NOP_KW;
}

{BOT_KW} {
	return YYParser.BOT_KW;
}

{INL_KW} {
	return YYParser.INL_KW;
}
{INH_KW} {
	return YYParser.INH_KW;
}
{OUTL_KW} {
	return YYParser.OUTL_KW;
}
{OUTH_KW} {
	return YYParser.OUTH_KW;
}

{PLUS_KW} {
	return YYParser.PLUS_KW;
}
{MINUS_KW} {
	return YYParser.MINUS_KW;
}

{LT_KW} {
	return YYParser.LT_KW;
}
{LE_KW} {
	return YYParser.LE_KW;
}
{EQ_KW} {
	return YYParser.EQ_KW;
}
{GT_KW} {
	return YYParser.GT_KW;
}
{GE_KW} {
	return YYParser.GE_KW;
}

{INTEGER_NUMBER} {
	YYParser.IntValue=Integer.parseInt(yytext());
	return YYParser.INTEGER_NUMBER;
}
{BOOL_CONSTANT} {
     
     String s=yytext();
     if(s.compareTo("true")==0)
     	YYParser.BoolValue=1;
     else
     	YYParser.BoolValue=0;
	return YYParser.BOOL_CONSTANT;
}

{IDENTIFIER} {
	YYParser.IDvalue = yytext();	
	return YYParser.IDENTIFIER;
}

"," {
	return ',';
	}
";" {
	return ';';
}

. {
}
