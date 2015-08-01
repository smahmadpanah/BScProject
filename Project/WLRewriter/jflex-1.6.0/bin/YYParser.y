
%{package wlrewriter;

  import java.io.*;
  import java.lang.*;
  import java.util.Vector; 

%}

%type <eval> program exp c clist varlist b n x M N

%token <eval> PROGRAM_KW AND_KW OR_KW ASSIGN_KW IF_KW THEN_KW ELSE_KW ENDIF_KW WHILE_KW DO_KW DONE_KW NOP_KW BOT_KW INL_KW INH_KW OUTL_KW OUTH_KW PLUS_KW MINUS_KW LT_KW LE_KW EQ_KW GT_KW GE_KW 
%token <eval> INTEGER_NUMBER 
%token <eval> BOOL_CONSTANT
%token <eval> IDENTIFIER

%code {

/*************************************** MAIN *****************************************/
static PrintStream writer;
static String stmt;
private int nodeCounter=0;


public static void main(String args[]) throws IOException, FileNotFoundException {
		YYParser yyparser;
		final Yylex lexer;		


	writer = new PrintStream (new File("reduction.txt"));
	lexer = new Yylex(new InputStreamReader(new FileInputStream("input.wl")));

	
	
	yyparser = new YYParser(new Lexer() {
	
	@Override
	public int yylex () {
	  int yyl_return = -1;
	  try {   
	
	   yyl_return = lexer.yylex();
	  }
	  catch (IOException e) {
	   System.err.println("IO error : " + e);
	  }
	  return yyl_return;
	}
	
	@Override
	public void yyerror (String error) {
		//System.err.println ("Error : " + error);
		System.err.println("**Error: Line "+ lexer.getYyline() +" near token '"+ lexer.yytext() +"' --> Message: "+ error +" **");
		writer.print("**Error: Line "+ lexer.getYyline() +" near token '"+ lexer.yytext() +"' --> Message: "+ error +" **");

	}
	
	@Override
	public Object getLVal() {
		return null;
	}

});
	yyparser.parse();
	writer.close();
}

/*-------------------------------------------------------------------------------------------*/
	
/*-------------------------------------------------------------------------------------------*/	
}

%left AND_KW OR_KW
%right ASSIGN_KW
%left EQ_KW
%left LT_KW GT_KW
%left LE_KW GE_KW
%left PLUS_KW MINUS_KW
%left THEN_KW
%nonassoc p
%nonassoc ELSE_KW

%%	

program : PROGRAM_KW ';' clist
	{
		writer.print("\t program -> PROGRAM_KW ';' clist \n") ;
		writer.print("###Hooray! - Your program is syntactically correct### \n");
		System.out.println("###Hooray! - Your program is syntactically correct###");
	
		$$ = new eval();
		((eval)$$).stmt += "program; " + ((eval)$3).stmt;
		writer.print(((eval)$$).stmt+ "\n");
		((eval)$$).node = new Node(nodeCounter++, "START");
		((eval)$$).list = new LinkedList(((eval)$$).node);
		((eval)$$).list.merge(((eval)$3).list);
		((eval)$$).list.merge(new LinkedList(new Node(nodeCounter++, "STOP")));
        System.out.println("the CFG is created.");

	};

clist: c
	{
		writer.print("\t clist -> c \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt;
		writer.print(((eval)$$).stmt+ "\n");
		((eval)$$).list = ((eval)$1).list;
	};
	| clist ';' M c
	{
		writer.print("\t clist -> clist ; M c \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + "; " + ((eval)$4).stmt;
		writer.print(((eval)$$).stmt+ "\n");
		((eval)$$).list = ((eval)$1).list;
		((eval)$$).list.merge(((eval)$4).list);
		
	};
		
exp : b
	{
	
		writer.print("\t exp -> b \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt;
	writer.print(((eval)$$).stmt+ "\n");
	};
	| n
	{
		writer.print("\t exp -> n \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt;
	writer.print(((eval)$$).stmt+ "\n");
	};
	| x
	{
		writer.print("\t exp -> x \n") ;
	$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt;

	writer.print(((eval)$$).stmt+ "\n");
	};
	| exp EQ_KW exp
	{
		writer.print("\t exp -> exp EQ_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " == "+ ((eval)$3).stmt;

		writer.print(((eval)$$).stmt+ "\n");
	};
	| exp LT_KW exp
	{
		writer.print("\t exp -> exp LT_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " < "+ ((eval)$3).stmt;

		writer.print(((eval)$$).stmt+ "\n");
	};
	| exp LE_KW exp
	{
		writer.print("\t exp -> exp LE_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " <= "+ ((eval)$3).stmt;

		writer.print(((eval)$$).stmt+ "\n");
	};
	| exp GE_KW exp
	{
		writer.print("\t exp -> exp GE_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " >= "+ ((eval)$3).stmt;

		writer.print(((eval)$$).stmt+ "\n");
	};
	| exp GT_KW exp
	{
		writer.print("\t exp -> exp GT_KW exp \n") ;
	$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " > "+ ((eval)$3).stmt;

	writer.print(((eval)$$).stmt+ "\n");
	};
	| exp PLUS_KW exp
	{
		writer.print("\t exp -> exp PLUS_KW exp \n") ;
	$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " + "+ ((eval)$3).stmt;

	writer.print(((eval)$$).stmt+ "\n");
	};
	| exp MINUS_KW exp
	{
		writer.print("\t exp -> exp MINUS_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " - "+ ((eval)$3).stmt;
	writer.print(((eval)$$).stmt+ "\n");
	};
	| exp AND_KW M exp
	{
		writer.print("\t exp -> exp AND_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " and "+ ((eval)$4).stmt;
	writer.print(((eval)$$).stmt+ "\n");
	};
	| exp OR_KW M exp
	{
		writer.print("\t exp -> exp OR_KW exp \n") ;
			$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " or "+ ((eval)$4).stmt;
	
writer.print(((eval)$$).stmt+ "\n");	

	};

c : NOP_KW
	{
		writer.print("\t c -> NOP_KW \n") ;
		$$=new eval();
		((eval)$$).stmt += "NOP";
		writer.print(((eval)$$).stmt+ "\n");	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
		((eval)$$).list = new LinkedList(((eval)$$).node);

	};
	| x ASSIGN_KW exp
	{
		writer.print("\t c -> x ASSIGN_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " = " + ((eval)$3).stmt;
		writer.print(((eval)$$).stmt+ "\n");	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
		((eval)$$).list = new LinkedList(((eval)$$).node);

	};
	| INL_KW varlist
	{
		writer.print("\t c -> INL_KW varlist \n") ;
		$$=new eval();
		((eval)$$).stmt += "inL "+((eval)$2).stmt;	
		writer.print(((eval)$$).stmt+ "\n");	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
		((eval)$$).list = new LinkedList(((eval)$$).node);

	};
	| INH_KW varlist
	{
		writer.print("\t c -> INH_KW varlist \n") ;
		$$=new eval();
		((eval)$$).stmt += "inH "+((eval)$2).stmt;
		writer.print(((eval)$$).stmt+ "\n");	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
		((eval)$$).list = new LinkedList(((eval)$$).node);

	};
	| OUTL_KW x
	{
		writer.print("\t c -> OUTL_KW x \n") ;
		$$=new eval();
		((eval)$$).stmt += "outL " + ((eval)$2).stmt;
		writer.print(((eval)$$).stmt+ "\n");	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
		((eval)$$).list = new LinkedList(((eval)$$).node);

	};
	| OUTH_KW x
	{
		writer.print("\t c -> OUTH_KW x \n") ;
		$$=new eval();
		((eval)$$).stmt += "outH " + ((eval)$2).stmt;
		writer.print(((eval)$$).stmt+ "\n");	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
		((eval)$$).list = new LinkedList(((eval)$$).node);

	};
	| OUTL_KW BOT_KW
	{
		writer.print("\t c -> OUTL_KW BOT_KW \n") ;
		$$=new eval();
		((eval)$$).stmt += "outL BOT";
		writer.print(((eval)$$).stmt+ "\n");	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
		((eval)$$).list = new LinkedList(((eval)$$).node);
	
	};
	| OUTH_KW BOT_KW
	{
		writer.print("\t c -> OUTH_KW BOT_KW \n") ;	
		$$=new eval();
		((eval)$$).stmt += "outH BOT";
		writer.print(((eval)$$).stmt+ "\n");	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
		((eval)$$).list = new LinkedList(((eval)$$).node);
	
	};
	| IF_KW exp THEN_KW M clist ENDIF_KW %prec p
	{
		writer.print("\t c -> IF_KW exp THEN_KW M clist ENDIF_KW \n") ;
		$$=new eval();
		((eval)$$).stmt += " if " + ((eval)$2).stmt + " then " + ((eval)$5).stmt + " endif";
		writer.print(((eval)$$).stmt+ "\n");
		
		((eval)$$).node = new Node(nodeCounter++, ((eval)$2).stmt);//condition expression node
		Node dummy = new Node(nodeCounter++, "dummy");//dummy node for last node of if
		((eval)$$).list = new LinkedList(((eval)$$).node);
		((eval)$$).list.getLast().setNextPointer2(dummy);//if false
		((eval)$$).list.merge(((eval)$5).list);//if true
		((eval)$$).list.merge(new LinkedList(dummy));	
	};
	| IF_KW exp THEN_KW M clist ELSE_KW N M clist ENDIF_KW
	{
		writer.print("\t c -> IF_KW exp THEN_KW M clist ELSE_KW N M clist ENDIF_KW \n") ;
		$$=new eval();
		((eval)$$).stmt += " if " + ((eval)$2).stmt + " then " + ((eval)$5).stmt + " else " + ((eval)$9).stmt + " endif ";
		writer.print(((eval)$$).stmt+ "\n");

		((eval)$$).node = new Node(nodeCounter++, ((eval)$2).stmt);//condition expression node
		Node dummy = new Node(nodeCounter++, "dummy");//dummy node for last node of if
		LinkedList dummyList = new LinkedList(dummy);
		((eval)$$).list = new LinkedList(((eval)$$).node);
		((eval)$$).list.getLast().setNextPointer2(((eval)$9).list.getFirst());//if false - else section
		((eval)$9).list.merge(dummyList);
		((eval)$$).list.merge(((eval)$5).list);//if true
		((eval)$$).list.merge(dummyList);	
	};
	| WHILE_KW exp DO_KW M clist DONE_KW
	{
		writer.print("\t c -> WHILE_KW exp DO_KW M clist DONE_KW \n") ;
		$$=new eval();
		((eval)$$).stmt += "while " + ((eval)$2).stmt + " do " + ((eval)$5).stmt + " done ";
		writer.print(((eval)$$).stmt+ "\n");	
	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$2).stmt);//condition expression node
		Node dummy = new Node(nodeCounter++, "dummy");//dummy node for last node of if
		LinkedList dummyList = new LinkedList(dummy);
		((eval)$$).list = new LinkedList(((eval)$$).node);
		((eval)$$).list.getLast().setNextPointer2(dummy);//while condition false
		((eval)$$).list.getLast().setNextPointer1(((eval)$5).list.getFirst()); //while condition true (loop)
		((eval)$5).list.getLast().setNextPointer1(((eval)$$).list.getFirst());
		((eval)$$).list.setLast(dummy);
	};
	
varlist : x
	{
		writer.print("\t varlist -> x \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt;
		writer.print(((eval)$$).stmt+ "\n");
	};
	| x ',' varlist
	{
		writer.print("\t varlist -> x , varlist \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + ", " + ((eval)$3).stmt;
		writer.print(((eval)$$).stmt+ "\n");
	};
	
b : BOOL_CONSTANT
	{
		writer.print("\t b -> BOOL_CONSTANT \n") ;
		$$=new eval();
		((eval)$$).stmt += this.stmt;
		writer.print(((eval)$$).stmt+ "\n");
	};
	
n : INTEGER_NUMBER
	{
		writer.print("\t n -> INTEGER_NUMBER \n") ;
		$$=new eval();
		((eval)$$).stmt += this.stmt;
		writer.print(((eval)$$).stmt+ "\n");
	};

x : IDENTIFIER
	{
		writer.print("\t x -> IDENTIFIER \n") ;
		$$=new eval();
		((eval)$$).stmt += this.stmt;
		writer.print(((eval)$$).stmt+ "\n");
	};
	
M : //lambda
	{
	
	};

N : //lambda
	{
	
	};	


%%

/*************************************** eval ************************************/
class eval {
	
	public String stmt="";
	
	public Node node;
	public LinkedList list;

}
