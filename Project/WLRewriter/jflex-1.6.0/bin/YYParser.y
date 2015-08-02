
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
static PrintStream writer, output;
static int IntValue;
Vector<Integer> tempIntValue = new Vector<Integer>();
static double DoubleValue;
static int BoolValue;
static String IDvalue;
static String stmt;
private int nodeCounter=0;

Vector<String> tempIDValue = new Vector<String>();

public static void main(String args[]) throws IOException, FileNotFoundException {
		YYParser yyparser;
		final Yylex lexer;		


	writer = new PrintStream (new File("reduction.txt"));
	output = new PrintStream (new File("result.c"));
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
	Vector<activationRecord> records = new Vector<activationRecord>();
	Vector<Integer> offsets = new Vector<Integer>();
	
	static Vector<String> symbols=new Vector<String>();
	static Vector<String> tsymbols=new Vector<String>();

	static int tempSymbol=0;
	Vector<quad> quads = new Vector<quad>();
	int tempCount = 0;
	/*************************************** merge *****************************************/
	public Vector<Integer> merge(Vector<Integer> l1, Vector<Integer> l2) {
		Vector<Integer> res = new Vector<Integer>();
		res.addAll(l1);
		res.addAll(l2);
		return res;
	}
	/*************************************** makelist **************************************/
	public Vector<Integer> makelist(int l) {
		Vector<Integer> res = new Vector<Integer>();
		res.add(l);
		return res;
	}
	/*************************************** backpatch ************************************/
	public void backpatch(Vector<Integer> l, int i) {
	//	System.out.println("fff");
		for (int qnumber : l)
			quads.get(qnumber).fourth = ""+ i;

	}
	/*************************************** newTemp **************************************/
	public String newTemp() {
		String temp = new String("t" + tempCount);
		tempCount++;
		return temp;
	}
	
	public String newTemp(String itype) {
	
	char c=itype.charAt(0);
	String type="";
		switch (c) { 
		case 'i' : 
		type="integer";
		break;
		
		case 'r' :
		 type="real";
		  break;
		  
		case 'b' :
		 type="integer"; 
		 break;
		}
		String temp = new String("t" + tempCount);
		tempCount++;
		if(records.size()>0){
			records.get(records.size()-1).vars.add(temp);
			records.get(records.size()-1).vNames.add(temp);
			records.get(records.size()-1).vTypes.add(type);
			System.out.println(temp+ "   "+type);
			symbols.add(temp);
			tsymbols.add(type);
		}
		return temp;
	}
	/*************************************** emit ****************************************/
	public void emit(String first, String second, String third, String forth) {
		quad q = new quad(first, second, third, forth);
		quads.add(q);
	}
	
		public void emit(String first, String second, String third, int forth) {
		quad q = new quad(first, second, third, forth+"");
		quads.add(q);
	}
	
	public void emit(quad q) {
		quads.add(q);
	}

	/*************************************** nexQuad ************************************/
	public int nextQuad() {
		return quads.size();
	}
	/*************************************** lookup ************************************/
	public String lookUp(String id,activationRecord current){
		
		if(current == null)
			return null;
			
		int i=current.vNames.indexOf(id);
		System.out.println(current.vNames.size()+"id= "+id);
		if( i!=-1)
			return current.vTypes.get(i);
	
		return lookUp(id,current.father);
		
	}
	/*************************************** findproc ************************************/
	public activationRecord findProc(String id){
			int i=records.get(records.size()-1).procNames.indexOf(id);
		if(i !=-1)
			return records.get(records.size()-1).childs.get(i);
		
		if(records.get(records.size()-1).father == null)
			return null;
		else{
			i= records.get(records.size()-1).father.procNames.indexOf(id);
			return records.get(records.size()-1).father.childs.get(i);
		}
	}	
	/*************************************** insertType ************************************/
	public void insertTypeToSymbolTable(String var,String tvar){
	
		String[] newVars=var.split("#");
		System.out.println("length="+ newVars.length+"  "+ var);
		for(String ss:newVars){
			System.out.println(tvar + " added");
			tsymbols.add(tvar);
			records.get(records.size()-1).vTypes.add(tvar);	
		}

	
	}
	/*************************************** insertSymbol ************************************/
	public String insertSymbolToSymbolTable(String symbol){
		
		records.get(records.size()-1).vNames.add(symbol);
		if(symbols.indexOf(symbol)!=-1){
			records.get(records.size()-1).vars.add("d"+tempSymbol);
			symbols.add("d"+tempSymbol);
			String alpha = "d"+tempSymbol;
			tempSymbol++;
		System.out.println("place= "+("d"+tempSymbol) +" name= "+ symbol);
			return alpha;
		}
		else{
				symbols.add(symbol);
				records.get(records.size()-1).vars.add(symbol);
			System.out.println("place= "+ symbol +" name= "+ symbol);
				return symbol;
			}
		
	}
	/*************************************** printQuadToCode ************************************/
	public void printQuadToCode(){
	

	output.println("#include <stdio.h>\n\n");
	output.println("void  *stack[1024];");
	output.println("void  *ptr;");
	output.println("int  top=0,procParamNumber=0;");
	for(int i=0;i<symbols.size();i++){
		if(tsymbols.get(i).compareTo("real")==0)
			output.println("double  "+symbols.get(i)+";");
		else
			output.println("int   "+symbols.get(i)+";");
		
	}
	
	output.println("\n\nint main(){\n");
		
	for(int i = 0;i< quads.size(); i++){
	
	output.print("L"+i+" :	") ;
	if(quads.get(i).first.compareTo("goto")==0)
			output.println("goto L" + quads.get(i).fourth +";");
	else if(quads.get(i).first.compareTo("gotoptr")==0)
			output.println("goto *ptr;");
	else if(quads.get(i).first.compareTo("IfCase")==0 )
		output.println("if ( " + quads.get(i).second + " == " + quads.get(i).third + " )  goto L" + quads.get(i).fourth+";");
	
	else if(quads.get(i).first.compareTo(":=")==0 )
		output.println(quads.get(i).fourth+" = "+quads.get(i).second+";");
	else if(quads.get(i).first.compareTo("+")==0 || 
		quads.get(i).first.compareTo( "-")==0 ||
		quads.get(i).first.compareTo( "*")==0 || 
		quads.get(i).first.compareTo("/")==0 ||
		quads.get(i).first.compareTo("&&")==0 || 
		quads.get(i).first.compareTo("||")==0
		)
				output.println(quads.get(i).fourth+" = "+quads.get(i).second+" "+quads.get(i).first+" "+quads.get(i).third+";");

	else if(quads.get(i).first.compareTo("<")==0 || quads.get(i).first.compareTo("<=")==0 || quads.get(i).first.compareTo(">")==0  || quads.get(i).first.compareTo(">=")==0 
		 || quads.get(i).first.compareTo("!=")==0 || quads.get(i).first.compareTo("=")==0 || quads.get(i).first.compareTo("")==0 )
		output.println("if ( "+quads.get(i).second+" "+quads.get(i).first+" "+quads.get(i).third+" )  "+"goto L"+quads.get(i).fourth+";");
	
	}	
	output.println("L"+quads.size()+" :  "+"return 0;\n");
	output.println("}");
	output.close();
	
	}
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
		((eval)$$).node = new Node(nodeCounter++, "Start");
		((eval)$$).list = new LinkedList(((eval)$$).node);
		((eval)$$).list.merge(((eval)$3).list);

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

	public int quad;
	public int isBoolean;

	public String place="";
	public String name="";
	public String code="";
	public String type="";
	public String ids="";
	
	public String stmt="";
	
	public Node node;
	public LinkedList list;

	public Vector<Integer> true_list=new Vector<Integer>();
	public Vector<Integer> false_list=new Vector<Integer>();
	public Vector<Integer> next_list=new Vector<Integer>();

	public Vector<Integer> intNumber_case_list=new Vector<Integer>();
	public Vector<Integer> label_case_list=new Vector<Integer>();




}
/*************************************** activationRecord ************************************/
class activationRecord {
 
	public int offset;
	
	/* vars */
	public Vector<String> vars = new Vector<String>(); //place
	public Vector<String> vNames = new Vector<String>(); //name
	public Vector<String> vTypes = new Vector<String>(); 
	
	/*params*/
	public Vector<String> params = new Vector<String>();
	public Vector<String> pTypes = new Vector<String>();
	public int paramSize=0;
	public int startLabel;
	public String returnType="void";
	
	/* activation records */
	public activationRecord father;
	Vector<activationRecord> childs = new Vector<activationRecord>();
	Vector<String> procNames = new Vector<String>();

	public activationRecord(activationRecord father) {
			this.father=father;
	}
	public void addVar(String name, String type) {
		vars.add(name);
		vTypes.add(type);
	}
	public void addChild(activationRecord child,String childName){
		childs.add(child);
		procNames.add(childName);
		System.out.println("child = "+ childName);
	}
	

	public void printSymbols(){
		System.out.println(vars.size()+"----"+ vNames.size());
	for(int i=0;i<vNames.size();i++){
		System.out.println("place= "+vars.get(i) +" name= "+ vNames.get(i)+" type= "+vTypes.get(i));
		}
	}

}
/*************************************** quad ************************************/
class quad {
	public String first;
	public String second;
	public String third;
	public String fourth;

	public quad(String first, String second, String third, String forth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = forth;
	}

	public String print() {

		return null;
	}

}
