
%{package wlrewriter;

  import java.io.*;
  import java.lang.*;
  import java.util.*; 

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
    private int nodeCounter = 0;
    private static String sourceCodeFileName;
	
	private static ArrayList<Variable> symbolTableOfVariables = new ArrayList<Variable>();
    
    
    public static void main(String args[]) throws IOException, FileNotFoundException {
        YYParser yyparser;
        final Yylex lexer;

		
		System.out.println("Enter the source code file path:");
        Scanner sc = new Scanner(System.in);
        sourceCodeFileName = sc.next();
//        String sourceCodeFileName = "input-while.wl";

        writer = new PrintStream(new File("reduction.txt"));

        Yylex yylexTemp = null;
        try{
           yylexTemp = new Yylex(new InputStreamReader(new FileInputStream(sourceCodeFileName)));
        } catch (Exception ex) {
            System.err.println("Source code file not found!");
            System.exit(0);
        }

         lexer = yylexTemp;
		
        yyparser = new YYParser(new Lexer() {

            @Override
            public int yylex() {
                int yyl_return = -1;
                try {

                    yyl_return = lexer.yylex();
                } catch (IOException e) {
                    System.err.println("IO error : " + e);
                }
                return yyl_return;
            }

            @Override
            public void yyerror(String error) {
                //System.err.println ("Error : " + error);
                System.err.println("**Error: Line " + lexer.getYyline() + " near token '" + lexer.yytext() + "' --> Message: " + error + " **");
                writer.print("**Error: Line " + lexer.getYyline() + " near token '" + lexer.yytext() + "' --> Message: " + error + " **");

            }

            @Override
            public Object getLVal() {
                return null;
            }

        });
        yyparser.parse();
        writer.close();
    }
    
    
    public static String getSourceCodeFileName() {
        return sourceCodeFileName;
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
		
		//((eval)$$).variables.addAll(((eval)$3).variables);
		
		((eval)$$).node = new Node(nodeCounter++, "START");
		
		
		/*for(String str : ((eval)$$).variables){
					for(Variable varvar : symbolTableOfVariables){
						if(str.equals(varvar.name)){
							((eval)$$).node.addToVariablesOfNode(varvar);
							break;
						}
		
					}
				}
		*/
		
		
		((eval)$$).list = new LinkedList(((eval)$$).node);
		((eval)$$).list.merge(((eval)$3).list);
		((eval)$$).list.merge(new LinkedList(new Node(nodeCounter++, "STOP")));
		System.out.println("the CFG is created.");

		FDTBuilder fdt = new FDTBuilder(((eval)$$).list); //the CFG is input to build the Forward Dominance Tree
		System.out.println("the FDT is created.");
	};

clist: c
	{
		writer.print("\t clist -> c \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt;
		writer.print(((eval)$$).stmt+ "\n");
		
		//((eval)$$).variables.addAll(((eval)$1).variables);
		
		((eval)$$).list = ((eval)$1).list;
	};
	| clist ';' M c
	{
		writer.print("\t clist -> clist ; M c \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + "; " + ((eval)$4).stmt;
		writer.print(((eval)$$).stmt+ "\n");
		
		//((eval)$$).variables.addAll(((eval)$1).variables);
		//((eval)$$).variables.addAll(((eval)$4).variables);
		
		
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

		((eval)$$).variables.add(((eval)$1).stmt);
		
	writer.print(((eval)$$).stmt+ "\n");
	
		boolean check = false;
		for(Variable v : symbolTableOfVariables){
			if(((eval)$1).stmt.equals(v.name)){
				check = true;
				break;
			}
		}
		
		if(!check){
			System.err.println("undefined variable!");
			System.err.println("\t"+((eval)$$).stmt);
			System.exit(0);
		}
	};
	| exp EQ_KW exp
	{
		writer.print("\t exp -> exp EQ_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " == "+ ((eval)$3).stmt;

		writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp LT_KW exp
	{
		writer.print("\t exp -> exp LT_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " < "+ ((eval)$3).stmt;

		writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp LE_KW exp
	{
		writer.print("\t exp -> exp LE_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " <= "+ ((eval)$3).stmt;

		writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp GE_KW exp
	{
		writer.print("\t exp -> exp GE_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " >= "+ ((eval)$3).stmt;

		writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp GT_KW exp
	{
		writer.print("\t exp -> exp GT_KW exp \n") ;
	$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " > "+ ((eval)$3).stmt;

	writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp PLUS_KW exp
	{
		writer.print("\t exp -> exp PLUS_KW exp \n") ;
	$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " + "+ ((eval)$3).stmt;

	writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp MINUS_KW exp
	{
		writer.print("\t exp -> exp MINUS_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " - "+ ((eval)$3).stmt;
	writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp AND_KW M exp
	{
		writer.print("\t exp -> exp AND_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " and "+ ((eval)$4).stmt;
	writer.print(((eval)$$).stmt+ "\n");

	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$4).variables);	
	};
	| exp OR_KW M exp
	{
		writer.print("\t exp -> exp OR_KW exp \n") ;
			$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " or "+ ((eval)$4).stmt;
	
writer.print(((eval)$$).stmt+ "\n");	


	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$4).variables);
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
				
		((eval)$$).variables.addAll(((eval)$3).variables);
		
		boolean check = false;
		for(Variable v : symbolTableOfVariables){
			if(((eval)$1).stmt.equals(v.name)){
				((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
				
				for(String str : ((eval)$$).variables){
					for(Variable varvar : symbolTableOfVariables){
						if(str.equals(varvar.name)){
							((eval)$$).node.addToVariablesOfNode(varvar);
							break;
						}
					}
				}
				
				for(Variable varvar : symbolTableOfVariables){
					if(varvar.name.equals(((eval)$1).stmt)){
							((eval)$$).node.setAssignedVariable(varvar);
							break;
					}
				}
				
				
				((eval)$$).list = new LinkedList(((eval)$$).node);
				check = true;
				break;
			}
		}
		
		if(!check){
			System.err.println("undefined variable can not be assigned:");
			System.err.println("\t"+((eval)$$).stmt);
			System.exit(0);
		}
		
		((eval)$$).variables.add(((eval)$1).stmt); //not necessary

	};
	| INL_KW varlist
	{
		writer.print("\t c -> INL_KW varlist \n") ;
		$$=new eval();
		((eval)$$).stmt += "inL "+((eval)$2).stmt;	
		writer.print(((eval)$$).stmt+ "\n");	
		
		((eval)$$).variables.addAll(((eval)$2).variables);
		
		
		boolean first = true;
		for(String i : ((eval)$2).variables){
			Variable currentVar = new Variable(i);
			
			for(Variable v : symbolTableOfVariables){
				if(v.name.equals(currentVar.name)){
					v.type = "low";
					currentVar.type = "low";
				}
			}
			
			((eval)$$).node = new Node(nodeCounter++, currentVar.name);
			((eval)$$).node.setAssignedVariable(currentVar);
			//((eval)$$).node.addToVariablesOfNode(currentVar);
			
			if(first){
				((eval)$$).list = new LinkedList(((eval)$$).node);
				first = false;
			}
			else{
				((eval)$$).list.merge(new LinkedList(((eval)$$).node));
			}
		}
		
		

	};
	| INH_KW varlist
	{
		writer.print("\t c -> INH_KW varlist \n") ;
		$$=new eval();
		((eval)$$).stmt += "inH "+((eval)$2).stmt;
		writer.print(((eval)$$).stmt+ "\n");
		
		((eval)$$).variables.addAll(((eval)$2).variables);
		
		
		boolean first = true;
		for(String i : ((eval)$2).variables){
			Variable currentVar = new Variable(i);
			
			for(Variable v : symbolTableOfVariables){
				if(v.name.equals(currentVar.name)){
					v.type = "high";
					currentVar.type = "high";
				}
			}
			
			((eval)$$).node = new Node(nodeCounter++, currentVar.name);
			((eval)$$).node.setAssignedVariable(currentVar);
			//((eval)$$).node.addToVariablesOfNode(currentVar);
			
			if(first){
				((eval)$$).list = new LinkedList(((eval)$$).node);
				first = false;
			}
			else{
				((eval)$$).list.merge(new LinkedList(((eval)$$).node));
			}
		}
		
		

	};
	| OUTL_KW x
	{
		writer.print("\t c -> OUTL_KW x \n") ;
		$$=new eval();
		((eval)$$).stmt += "outL " + ((eval)$2).stmt;
		writer.print(((eval)$$).stmt+ "\n");	
		
		((eval)$$).variables.add(((eval)$2).stmt);
		
		boolean check = false;
		for(Variable v : symbolTableOfVariables){
			if(((eval)$2).stmt.equals(v.name) && v.type.equals("low")){
				((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
				
				for(String str : ((eval)$$).variables){
					for(Variable varvar : symbolTableOfVariables){
						if(str.equals(varvar.name)){
							((eval)$$).node.addToVariablesOfNode(varvar);
							break;
						}
		
					}
				}
		
				
				((eval)$$).list = new LinkedList(((eval)$$).node);
				check = true;
				break;
			}
		}
		
		if(!check){
			System.err.println("undefined variable!");
			System.err.println("\t"+((eval)$$).stmt);
			System.exit(0);
		}
		
		

	};
	| OUTH_KW x
	{
		writer.print("\t c -> OUTH_KW x \n") ;
		$$=new eval();
		((eval)$$).stmt += "outH " + ((eval)$2).stmt;
		writer.print(((eval)$$).stmt+ "\n");
		
		((eval)$$).variables.add(((eval)$2).stmt);
		
		boolean check = false;
		for(Variable v : symbolTableOfVariables){
			if(((eval)$2).stmt.equals(v.name) && v.type.equals("high")){
				((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
				
				
				for(String str : ((eval)$$).variables){
					for(Variable varvar : symbolTableOfVariables){
						if(str.equals(varvar.name)){
							((eval)$$).node.addToVariablesOfNode(varvar);
							break;
						}
		
					}
				}
				
				((eval)$$).list = new LinkedList(((eval)$$).node);
				check = true;
				break;
			}
		}
		
		if(!check){
			System.err.println("undefined variable!");
			System.err.println("\t"+((eval)$$).stmt);
			System.exit(0);
		}
		

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
		
		((eval)$$).variables.addAll(((eval)$2).variables);
		//((eval)$$).variables.addAll(((eval)$5).variables);
		
		((eval)$$).node = new Node(nodeCounter++, ((eval)$2).stmt);//condition expression node
		
		for(String str : ((eval)$$).variables){
			for(Variable v : symbolTableOfVariables){
				if(str.equals(v.name)){
					((eval)$$).node.addToVariablesOfNode(v);
					break;
				}
		
			}
		}
		
		((eval)$$).list = new LinkedList(((eval)$$).node);

		Node dummy = new Node(nodeCounter++, "dummy");//dummy node for last node of if
		
		((eval)$$).list.getLast().setNextPointer2(dummy);//if false
		dummy.addPreviousPointer(((eval)$$).list.getLast()); //backward pointer
		
		((eval)$$).list.merge(((eval)$5).list);//if true
		((eval)$$).list.merge(new LinkedList(dummy));	
	};
	| IF_KW exp THEN_KW M clist ELSE_KW N M clist ENDIF_KW
	{
		writer.print("\t c -> IF_KW exp THEN_KW M clist ELSE_KW N M clist ENDIF_KW \n") ;
		$$=new eval();
		((eval)$$).stmt += " if " + ((eval)$2).stmt + " then " + ((eval)$5).stmt + " else " + ((eval)$9).stmt + " endif ";
		writer.print(((eval)$$).stmt+ "\n");
		
		((eval)$$).variables.addAll(((eval)$2).variables);
		//((eval)$$).variables.addAll(((eval)$5).variables);
		//((eval)$$).variables.addAll(((eval)$9).variables);

		((eval)$$).node = new Node(nodeCounter++, ((eval)$2).stmt);//condition expression node
		
		for(String str : ((eval)$$).variables){
			for(Variable v : symbolTableOfVariables){
				if(str.equals(v.name)){
					((eval)$$).node.addToVariablesOfNode(v);
					break;
				}
		
			}
		}
		
		((eval)$$).list = new LinkedList(((eval)$$).node);
		
		Node dummy = new Node(nodeCounter++, "dummy");//dummy node for last node of if
		LinkedList dummyList = new LinkedList(dummy);
		
		((eval)$$).list.getLast().setNextPointer2(((eval)$9).list.getFirst());//if false - else section
		((eval)$9).list.getFirst().addPreviousPointer(((eval)$$).list.getLast()); //backward pointer
		((eval)$$).list.getNodeSet().addAll(((eval)$9).list.getNodeSet());

		
		
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
		
		((eval)$$).variables.addAll(((eval)$2).variables);
	//	((eval)$$).variables.addAll(((eval)$5).variables);
	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$2).stmt);//condition expression node
		
		for(String str : ((eval)$$).variables){
			for(Variable v : symbolTableOfVariables){
				if(str.equals(v.name)){
					((eval)$$).node.addToVariablesOfNode(v);
					break;
				}
		
			}
		}
		
		
		((eval)$$).list = new LinkedList(((eval)$$).node);
		
		Node dummy = new Node(nodeCounter++, "dummy");//dummy node for last node of if
		LinkedList dummyList = new LinkedList(dummy);
		
		((eval)$$).list.getLast().setNextPointer2(dummy);//while condition false
		dummy.addPreviousPointer(((eval)$$).list.getLast()); //backward pointer	
		((eval)$$).list.getNodeSet().add(dummy);

		((eval)$$).list.merge(((eval)$5).list); //while condition true (loop)
		
		((eval)$5).list.getLast().setNextPointer1(((eval)$$).list.getFirst());
		((eval)$$).list.getFirst().addPreviousPointer(((eval)$5).list.getLast());
		
		((eval)$$).list.setLast(dummy);
	};
	
varlist : x
	{
		writer.print("\t varlist -> x \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt;
		writer.print(((eval)$$).stmt+ "\n");
		
		Variable tempVar = new Variable(((eval)$1).stmt);
		
		boolean flag = true;
		for(int i = 0; i < symbolTableOfVariables.size(); i++){
			if(symbolTableOfVariables.get(i).name.equals(tempVar.name)){
				flag = false;
				break;
			}
		}
		
		if(flag == true){
			symbolTableOfVariables.add(tempVar);
			((eval)$$).variables.add(((eval)$1).stmt);
		}
		else{
			System.err.println("The variable " + ((eval)$1).stmt + " is already declared!");
			System.exit(0);
		}
	};
	| x ',' varlist
	{
		writer.print("\t varlist -> x , varlist \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + ", " + ((eval)$3).stmt;
		writer.print(((eval)$$).stmt+ "\n");
		
		Variable tempVar = new Variable(((eval)$1).stmt);
		
		boolean flag = true;
		for(int i = 0; i < symbolTableOfVariables.size(); i++){
			if(symbolTableOfVariables.get(i).name.equals(tempVar.name)){
				flag = false;
				break;
			}
		}
		
		
		if(flag == true){
			symbolTableOfVariables.add(tempVar);
			((eval)$$).variables.add(((eval)$1).stmt);
			((eval)$$).variables.addAll(((eval)$3).variables);
		}
		else{
			System.err.println("The variable " + ((eval)$1).stmt + " is already declared!");
		}
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
	
	public HashSet<String> variables = new HashSet<String>();
	
	public Node node;
	public LinkedList list;

}
