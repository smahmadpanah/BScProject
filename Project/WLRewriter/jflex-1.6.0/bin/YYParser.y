%{
package wlrewriter;

  import java.io.*;
  import java.lang.*;
  import java.util.*; 
  import java.util.regex.Matcher;
import java.util.regex.Pattern;

%}

%type <eval> program exp c clist varlist b n x M N

%token <eval> PROGRAM_KW AND_KW OR_KW NEG_KW LPAR_KW RPAR_KW ASSIGN_KW IF_KW THEN_KW ELSE_KW ENDIF_KW WHILE_KW DO_KW DONE_KW NOP_KW BOT_KW INL_KW INH_KW OUTL_KW OUTH_KW PLUS_KW MINUS_KW LT_KW LE_KW EQ_KW GT_KW GE_KW 
%token <eval> INTEGER_NUMBER 
%token <eval> BOOL_CONSTANT
%token <eval> IDENTIFIER

%code {

/*************************************** MAIN *****************************************/
static PrintStream writer;
    static String stmt;
    private int nodeCounter = 0;
    private static String sourceCodeFileName;
	
	private String cSourceCodeOfInput="";
	public String cSourceCodeForPSNI="";
	
	private int whileID = 0;
	
	public int controlFlag = 0;
	
	public static int selectorPDGorPINIorPSNI; //0: pdg | 1: pini | 2:psni
	
	
	public ArrayList<Variable> symbolTableOfVariables = new ArrayList<Variable>(); //static bood ghablan
    
    
   // public static void main(String args[]) throws IOException, FileNotFoundException {
		public static void mainMethod(String sFileName, int selector) {
        YYParser yyparser;
        final Yylex lexer;

		selectorPDGorPINIorPSNI = selector;
		
//        System.out.println("Enter the source code file path:");
//        Scanner sc = new Scanner(System.in);
//        sourceCodeFileName = sc.next();
        sourceCodeFileName = sFileName;
//        String sourceCodeFileName = "input-while.wl";

        try {
            writer = new PrintStream(new File("reduction.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("File reduction not found.");
			GUI.terminal.appendError("File reduction not found.");
        }
		
        Yylex yylexTemp = null;
        try{
           yylexTemp = new Yylex(new InputStreamReader(new FileInputStream(sourceCodeFileName)));
        } catch (Exception ex) {
            System.err.println("Source code file not found!");
			GUI.terminal.appendError("Source code file not found!");
            //System.exit(0);
			return;
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
					GUI.terminal.appendError("IO error : " + e);
                }
                return yyl_return;
            }

            @Override
            public void yyerror(String error) {
                //System.err.println ("Error : " + error);
                System.err.println("**Error: Line " + lexer.getYyline() + " near token '" + lexer.yytext() + "' --> Message: " + error + " **");
                GUI.terminal.append("**Error: Line " + lexer.getYyline() + " near token '" + lexer.yytext() + "' --> Message: " + error + " **", Color.orange);
				writer.print("**Error: Line " + lexer.getYyline() + " near token '" + lexer.yytext() + "' --> Message: " + error + " **");

            }

            @Override
            public Object getLVal() {
                return null;
            }

        });
		try {
            yyparser.parse();
        } catch (IOException ex) {
            System.out.println("parse method is wrong.");
			GUI.terminal.appendError("parse method is wrong.");
        }
        writer.close();
    }
    
    
    public static String getSourceCodeFileName() {
        return sourceCodeFileName;
    }


/*-------------------------------------------------------------------------------------------*/
	
/*-------------------------------------------------------------------------------------------*/	
}

%left AND_KW OR_KW
%nonassoc q
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
		GUI.terminal.append("Your program is syntactically correct");
	
		$$ = new eval();
		((eval)$$).stmt += "program; " + ((eval)$3).stmt;
		((eval)$$).cSourceCode += "#include <stdio.h> \n \n#define TRUE 1 \n#define true 1 \n" 
									+ "#define FALSE 0 \n#define false 0 \n\n\n" 
									+"int main() { " + ((eval)$3).cSourceCode + "return 0;}"; 
		//System.out.print(((eval)$$).cSourceCode);
		
		cSourceCodeForPSNI = ((eval)$$).cSourceCode;
		
		//omit WhileIDs 
        String pattern = "~WhileID[0-9]+~";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(((eval)$$).cSourceCode);
        cSourceCodeOfInput = m.replaceAll("");
		pattern = "~ENDWhileID[0-9]+~";
        r = Pattern.compile(pattern);
        m = r.matcher(cSourceCodeOfInput);
        cSourceCodeOfInput = m.replaceAll("");
		//System.out.println("\n\n*******\n\n" + cSourceCodeOfInput);
		
		//**********WE CAN USE cSourceCodeOfInput TO MAKE A .c FILE FOR COMPILE IT IN C LANGUAGE.
		
		writer.print(((eval)$$).stmt+ "\n");
		
		//((eval)$$).variables.addAll(((eval)$3).variables);
		
		((eval)$$).node = new Node(nodeCounter++, "START");
		//System.out.println(((eval)$3).nodeIdAndStmt);
		((eval)$$).nodeIdAndStmt += "#" + ((eval)$$).node.getNodeID() + ":" + "program; \n";
		((eval)$$).nodeIdAndStmt += ((eval)$3).nodeIdAndStmt;
		((eval)$$).node.setNodeIdAndStmt(((eval)$$).nodeIdAndStmt);
		/*for(String str : ((eval)$$).variables){
					for(Variable varvar : symbolTableOfVariables){
						if(str.equals(varvar.name)){
							((eval)$$).node.addToVariablesOfNode(varvar);
							break;
						}
		
					}
				}
		*/
		
		
		((eval)$$).list = new MyLinkedList(((eval)$$).node);
		((eval)$$).list.merge(((eval)$3).list);
		((eval)$$).list.merge(new MyLinkedList(new Node(nodeCounter++, "STOP")));
				
		
		if(controlFlag==2){
		try{
			PrintStream writer3 = new PrintStream(new File(sourceCodeFileName+"-PSNI.c"));
			writer3.print(cSourceCodeOfInput);
		}
		catch (Exception e){
			System.out.println("ERROR in FILE.");
			GUI.terminal.appendError("ERROR in FILE.");
		}
		}
		if(controlFlag==1){
		psni.setPSNICFG(((eval)$$).list);
		try{
			PrintStream writer1 = new PrintStream(new File(sourceCodeFileName+"-PINI.c"));
			writer1.print(cSourceCodeOfInput);
		}
		catch (Exception e){
			System.out.println("ERROR in FILE.");
			GUI.terminal.appendError("ERROR in FILE.");
		}
		}
		if(controlFlag==0){
		System.out.println("Control Flow Graph is created.");
		GUI.terminal.append("Control Flow Graph is created.");
		try{
			PrintStream writer2 = new PrintStream(new File(sourceCodeFileName+".c"));
			writer2.print(cSourceCodeOfInput);
		}
		catch (Exception e){
			System.out.println("ERROR in FILE.");
			GUI.terminal.appendError("ERROR in FILE.");
		}
		PDGBuilder pdg = new PDGBuilder(((eval)$$).list); //the CFG is input to build the Forward Dominance Tree and after that, CFG and DDG that make PDG! :)
		PINIRewriter pini = null;
		if(selectorPDGorPINIorPSNI != 0){ // faghat namayeshe pdg ro nemikhad, ya pini ya psni
		pini = new PINIRewriter(pdg.getPDG());
		}
		if(selectorPDGorPINIorPSNI == 2){
        psni = new PSNIRewriter(pini);
		}
		}
		
	};

clist: c
	{
		writer.print("\t clist -> c \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode;
		
		writer.print(((eval)$$).stmt+ "\n");
		
		((eval)$$).nodeIdAndStmt += ((eval)$1).nodeIdAndStmt;
		//((eval)$$).variables.addAll(((eval)$1).variables);
		
		((eval)$$).list = ((eval)$1).list;
	};
	| clist ';' M c
	{
		writer.print("\t clist -> clist ; M c \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + "; " + ((eval)$4).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode + "; " + ((eval)$4).cSourceCode;
		((eval)$$).cSourceCode += "\n";
		
		writer.print(((eval)$$).stmt+ "\n");
		
		((eval)$$).nodeIdAndStmt += ((eval)$1).nodeIdAndStmt + "; \n" + ((eval)$4).nodeIdAndStmt;
		
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
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode;
	writer.print(((eval)$$).stmt+ "\n");
	};
	| n
	{
		writer.print("\t exp -> n \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode;
	writer.print(((eval)$$).stmt+ "\n");
	};
	| x
	{
		writer.print("\t exp -> x \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode;

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
			GUI.terminal.append("undefined variable!\n\t"+((eval)$$).stmt, Color.orange);
			System.err.println("\t"+((eval)$$).stmt);
			//System.exit(0);
			return;
		}
	};
	| exp EQ_KW exp
	{
		writer.print("\t exp -> exp EQ_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " == "+ ((eval)$3).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode + " == "+ ((eval)$3).cSourceCode;

		writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp LT_KW exp
	{
		writer.print("\t exp -> exp LT_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " < "+ ((eval)$3).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode + " < "+ ((eval)$3).cSourceCode;

		writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp LE_KW exp
	{
		writer.print("\t exp -> exp LE_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " <= "+ ((eval)$3).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode + " <= "+ ((eval)$3).cSourceCode;

		writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp GE_KW exp
	{
		writer.print("\t exp -> exp GE_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " >= "+ ((eval)$3).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode + " >= "+ ((eval)$3).cSourceCode;

		writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp GT_KW exp
	{
		writer.print("\t exp -> exp GT_KW exp \n") ;
	$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " > "+ ((eval)$3).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode + " > "+ ((eval)$3).cSourceCode;

	writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp PLUS_KW exp
	{
		writer.print("\t exp -> exp PLUS_KW exp \n") ;
	$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " + "+ ((eval)$3).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode + " + "+ ((eval)$3).cSourceCode;

	writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp MINUS_KW exp
	{
		writer.print("\t exp -> exp MINUS_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " - "+ ((eval)$3).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode + " - "+ ((eval)$3).cSourceCode;
	writer.print(((eval)$$).stmt+ "\n");
	
	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$3).variables);
	};
	| exp AND_KW M exp
	{
		writer.print("\t exp -> exp AND_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " and "+ ((eval)$4).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode + " && "+ ((eval)$4).cSourceCode;
	writer.print(((eval)$$).stmt+ "\n");

	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$4).variables);	
	};
	| exp OR_KW M exp
	{
		writer.print("\t exp -> exp OR_KW exp \n") ;
			$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " or "+ ((eval)$4).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode + " || "+ ((eval)$4).cSourceCode;
	
writer.print(((eval)$$).stmt+ "\n");	


	((eval)$$).variables.addAll(((eval)$1).variables);
	((eval)$$).variables.addAll(((eval)$4).variables);
	};
	| NEG_KW exp %prec q
	{
		writer.print("\t exp -> NEG_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += "!("+ ((eval)$2).stmt + ")";
		((eval)$$).cSourceCode += "!("+ ((eval)$2).cSourceCode + ")";
	
		writer.print(((eval)$$).stmt+ "\n");	

		((eval)$$).variables.addAll(((eval)$2).variables);
	};
	| LPAR_KW exp RPAR_KW %prec p
	{
		writer.print("\t exp -> LPAR_KW exp RPAR_KW \n") ;
		$$=new eval();
		((eval)$$).stmt += "("+ ((eval)$2).stmt + ")";
		((eval)$$).cSourceCode += "("+ ((eval)$2).cSourceCode + ")";
		writer.print(((eval)$$).stmt+ "\n");	

		((eval)$$).variables.addAll(((eval)$2).variables);
	
	
	};
	
c : NOP_KW
	{
		writer.print("\t c -> NOP_KW \n") ;
		$$=new eval();
		((eval)$$).stmt += "NOP";
		((eval)$$).cSourceCode += ";";
		writer.print(((eval)$$).stmt+ "\n");	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
		((eval)$$).nodeIdAndStmt += "#" + ((eval)$$).node.getNodeID() + ":" + ((eval)$$).stmt;
		((eval)$$).node.setNodeIdAndStmt(((eval)$$).nodeIdAndStmt);
		((eval)$$).list = new MyLinkedList(((eval)$$).node);

	};
	| x ASSIGN_KW exp
	{
		writer.print("\t c -> x ASSIGN_KW exp \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + " = " + ((eval)$3).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode + " = " + ((eval)$3).cSourceCode;
		writer.print(((eval)$$).stmt+ "\n");	
				
		((eval)$$).variables.addAll(((eval)$3).variables);
		
		boolean check = false;
		for(Variable v : symbolTableOfVariables){
			if(((eval)$1).stmt.equals(v.name)){
				((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
				((eval)$$).nodeIdAndStmt += "#" + ((eval)$$).node.getNodeID() + ":" + ((eval)$1).stmt + " = " + ((eval)$3).stmt;
				((eval)$$).node.setNodeIdAndStmt(((eval)$$).nodeIdAndStmt);
				
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
				
				
				((eval)$$).list = new MyLinkedList(((eval)$$).node);
				check = true;
				break;
			}
		}
		
		if(!check){
			System.err.println("undefined variable can not be assigned:");
			GUI.terminal.append("undefined variable can not be assigned:\n\t"+((eval)$$).stmt, Color.orange);
			System.err.println("\t"+((eval)$$).stmt);
			//System.exit(0);
			return;
		}
		
		((eval)$$).variables.add(((eval)$1).stmt); //not necessary

	};
	| INL_KW varlist
	{
		writer.print("\t c -> INL_KW varlist \n") ;
		$$=new eval();
		((eval)$$).stmt += "inL "+((eval)$2).stmt;	
		//((eval)$$).cSourceCode += "int "+((eval)$2).cSourceCode;
		for(String alpha : ((eval)$2).variables){
			((eval)$$).cSourceCode += "int " + alpha + ";  //type: low \n";
			((eval)$$).cSourceCode += "scanf(\"%d\", &" + alpha + ");\n";
		}
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
				((eval)$$).list = new MyLinkedList(((eval)$$).node);
				first = false;
			}
			else{
				((eval)$$).list.merge(new MyLinkedList(((eval)$$).node));
			}
		}
		
			((eval)$$).nodeIdAndStmt += "#" + ((eval)$$).node.getNodeID() + ":" + ((eval)$$).stmt;
			((eval)$$).node.setNodeIdAndStmt(((eval)$$).nodeIdAndStmt);
		
		

	};
	| INH_KW varlist
	{
		writer.print("\t c -> INH_KW varlist \n") ;
		$$=new eval();
		((eval)$$).stmt += "inH "+((eval)$2).stmt;
		//((eval)$$).cSourceCode += "int "+((eval)$2).cSourceCode;
		for(String alpha : ((eval)$2).variables){
			((eval)$$).cSourceCode += "int " + alpha + ";  //type: high \n";
			((eval)$$).cSourceCode += "scanf(\"%d\", &" + alpha + ");\n";
		}
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
				((eval)$$).list = new MyLinkedList(((eval)$$).node);
				first = false;
			}
			else{
				((eval)$$).list.merge(new MyLinkedList(((eval)$$).node));
			}
		}
			((eval)$$).nodeIdAndStmt += "#" + ((eval)$$).node.getNodeID() + ":" + ((eval)$$).stmt;
			((eval)$$).node.setNodeIdAndStmt(((eval)$$).nodeIdAndStmt);
		
		

	};
	| OUTL_KW x
	{
		writer.print("\t c -> OUTL_KW x \n") ;
		$$=new eval();
		((eval)$$).stmt += "outL " + ((eval)$2).stmt;
		((eval)$$).cSourceCode += "printf(\"%d\\n\","+((eval)$2).cSourceCode+")";
		writer.print(((eval)$$).stmt+ "\n");	
		
		((eval)$$).variables.add(((eval)$2).stmt);
		
		boolean check = false;
		for(Variable v : symbolTableOfVariables){
			if(((eval)$2).stmt.equals(v.name) && v.type.equals("low")){
				((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
				((eval)$$).nodeIdAndStmt += "#" + ((eval)$$).node.getNodeID() + ":" + ((eval)$$).stmt;
				((eval)$$).node.setNodeIdAndStmt(((eval)$$).nodeIdAndStmt);
				
				for(String str : ((eval)$$).variables){
					for(Variable varvar : symbolTableOfVariables){
						if(str.equals(varvar.name)){
							((eval)$$).node.addToVariablesOfNode(varvar);
							break;
						}
		
					}
				}
		
				
				((eval)$$).list = new MyLinkedList(((eval)$$).node);
				check = true;
				break;
			}
		}
		
		if(!check){
			System.err.println("undefined variable!");
			GUI.terminal.append("undefined variable!\n\t"+((eval)$$).stmt, Color.orange);
			System.err.println("\t"+((eval)$$).stmt);
			//System.exit(0);
			return;
		}
		
		

	};
	| OUTH_KW x
	{
		writer.print("\t c -> OUTH_KW x \n") ;
		$$=new eval();
		((eval)$$).stmt += "outH " + ((eval)$2).stmt;
		((eval)$$).cSourceCode += "printf(\"%d\\n\","+((eval)$2).cSourceCode+")";
		writer.print(((eval)$$).stmt+ "\n");
		
		((eval)$$).variables.add(((eval)$2).stmt);
		
		boolean check = false;
		for(Variable v : symbolTableOfVariables){
			if(((eval)$2).stmt.equals(v.name) && v.type.equals("high")){
				((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
				((eval)$$).nodeIdAndStmt += "#" + ((eval)$$).node.getNodeID() + ":" + ((eval)$$).stmt;
				((eval)$$).node.setNodeIdAndStmt(((eval)$$).nodeIdAndStmt);
				
				for(String str : ((eval)$$).variables){
					for(Variable varvar : symbolTableOfVariables){
						if(str.equals(varvar.name)){
							((eval)$$).node.addToVariablesOfNode(varvar);
							break;
						}
		
					}
				}
				
				((eval)$$).list = new MyLinkedList(((eval)$$).node);
				check = true;
				break;
			}
		}
		
		if(!check){
			System.err.println("undefined variable!");
			System.err.println("\t"+((eval)$$).stmt);
			GUI.terminal.append("undefined variable!\n\t"+((eval)$$).stmt, Color.orange);
			//System.exit(0);
			return;
		}
		

	};
	| OUTL_KW BOT_KW
	{
		writer.print("\t c -> OUTL_KW BOT_KW \n") ;
		$$=new eval();
		((eval)$$).stmt += "outL BOT";
		((eval)$$).cSourceCode += "printf(\"BOT\\n\")";
		writer.print(((eval)$$).stmt+ "\n");	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
		((eval)$$).nodeIdAndStmt += "#" + ((eval)$$).node.getNodeID() + ":" + ((eval)$$).stmt;
		((eval)$$).node.setNodeIdAndStmt(((eval)$$).nodeIdAndStmt);
		
		((eval)$$).list = new MyLinkedList(((eval)$$).node);
	
	};
	| OUTH_KW BOT_KW
	{
		writer.print("\t c -> OUTH_KW BOT_KW \n") ;	
		$$=new eval();
		((eval)$$).stmt += "outH BOT";
		((eval)$$).cSourceCode += "printf(\"BOT\\n\")";
		writer.print(((eval)$$).stmt+ "\n");	
		((eval)$$).node = new Node(nodeCounter++, ((eval)$$).stmt);
		((eval)$$).nodeIdAndStmt += "#" + ((eval)$$).node.getNodeID() + ":" + ((eval)$$).stmt;
		((eval)$$).node.setNodeIdAndStmt(((eval)$$).nodeIdAndStmt);
		((eval)$$).list = new MyLinkedList(((eval)$$).node);
	
	};
	| IF_KW exp THEN_KW M clist ENDIF_KW %prec p
	{
		writer.print("\t c -> IF_KW exp THEN_KW M clist ENDIF_KW \n") ;
		$$=new eval();
		((eval)$$).stmt += " if " + ((eval)$2).stmt + " then " + ((eval)$5).stmt + " endif";
		((eval)$$).cSourceCode += " if (" + ((eval)$2).cSourceCode + ") { " + ((eval)$5).cSourceCode + ";}";
		writer.print(((eval)$$).stmt+ "\n");
		
		((eval)$$).variables.addAll(((eval)$2).variables);
		//((eval)$$).variables.addAll(((eval)$5).variables);
		
		((eval)$$).node = new Node(nodeCounter++, ((eval)$2).stmt);//condition expression node
		((eval)$$).nodeIdAndStmt += " if " + "#" + ((eval)$$).node.getNodeID() + ":" + ((eval)$2).stmt + " then \n" + ((eval)$5).nodeIdAndStmt + " endif";
		((eval)$$).node.setNodeIdAndStmt(((eval)$$).nodeIdAndStmt);
		
		for(String str : ((eval)$$).variables){
			for(Variable v : symbolTableOfVariables){
				if(str.equals(v.name)){
					((eval)$$).node.addToVariablesOfNode(v);
					break;
				}
		
			}
		}
		
		((eval)$$).list = new MyLinkedList(((eval)$$).node);

		Node dummy = new Node(nodeCounter++, "dummy");//dummy node for last node of if
		
		((eval)$$).list.getLast().setNextPointer2(dummy);//if false
		dummy.addPreviousPointer(((eval)$$).list.getLast()); //backward pointer
		
		((eval)$$).list.merge(((eval)$5).list);//if true
		((eval)$$).list.merge(new MyLinkedList(dummy));	
	};
	| IF_KW exp THEN_KW M clist ELSE_KW N M clist ENDIF_KW
	{
		writer.print("\t c -> IF_KW exp THEN_KW M clist ELSE_KW N M clist ENDIF_KW \n") ;
		$$=new eval();
		((eval)$$).stmt += " if " + ((eval)$2).stmt + " then " + ((eval)$5).stmt + " else " + ((eval)$9).stmt + " endif ";
		((eval)$$).cSourceCode += " if (" + ((eval)$2).cSourceCode + ") { " + ((eval)$5).cSourceCode + ";} else {" + ((eval)$9).cSourceCode + ";}";
		writer.print(((eval)$$).stmt+ "\n");
		
		((eval)$$).variables.addAll(((eval)$2).variables);
		//((eval)$$).variables.addAll(((eval)$5).variables);
		//((eval)$$).variables.addAll(((eval)$9).variables);

		((eval)$$).node = new Node(nodeCounter++, ((eval)$2).stmt);//condition expression node
		((eval)$$).nodeIdAndStmt += " if " + "#" + ((eval)$$).node.getNodeID() + ":" + ((eval)$2).stmt + " then \n" + ((eval)$5).nodeIdAndStmt + " else \n" + ((eval)$9).nodeIdAndStmt + " endif ";
		((eval)$$).node.setNodeIdAndStmt(((eval)$$).nodeIdAndStmt);
		
		for(String str : ((eval)$$).variables){
			for(Variable v : symbolTableOfVariables){
				if(str.equals(v.name)){
					((eval)$$).node.addToVariablesOfNode(v);
					break;
				}
		
			}
		}
		
		((eval)$$).list = new MyLinkedList(((eval)$$).node);
		
		Node dummy = new Node(nodeCounter++, "dummy");//dummy node for last node of if
		MyLinkedList dummyList = new MyLinkedList(dummy);
		
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
		((eval)$$).node.whileID = whileID++;
		((eval)$$).cSourceCode += "~WhileID"+((eval)$$).node.whileID+"~while (" + ((eval)$2).cSourceCode + ") { " + ((eval)$5).cSourceCode + ";\n}"+"~ENDWhileID"+((eval)$$).node.whileID+"~";
		((eval)$$).nodeIdAndStmt += "while " + "#" + ((eval)$$).node.getNodeID() + ":" + ((eval)$2).stmt + " do \n" + ((eval)$5).nodeIdAndStmt + " done ";
		((eval)$$).node.setNodeIdAndStmt(((eval)$$).nodeIdAndStmt);
		
		for(String str : ((eval)$$).variables){
			for(Variable v : symbolTableOfVariables){
				if(str.equals(v.name)){
					((eval)$$).node.addToVariablesOfNode(v);
					break;
				}
		
			}
		}
		
		
		((eval)$$).list = new MyLinkedList(((eval)$$).node);
		
		Node dummy = new Node(nodeCounter++, "dummy");//dummy node for last node of if
		MyLinkedList dummyList = new MyLinkedList(dummy);
		
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
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode;
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
			GUI.terminal.append("The variable " + ((eval)$1).stmt + " is already declared!", Color.orange);
			//System.exit(0);
			return;
		}
	};
	| x ',' varlist
	{
		writer.print("\t varlist -> x , varlist \n") ;
		$$=new eval();
		((eval)$$).stmt += ((eval)$1).stmt + ", " + ((eval)$3).stmt;
		((eval)$$).cSourceCode += ((eval)$1).cSourceCode + ", " + ((eval)$3).cSourceCode;
		
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
			GUI.terminal.append("The variable " + ((eval)$1).stmt + " is already declared!", Color.orange);
		}
	};
	
b : BOOL_CONSTANT
	{
		writer.print("\t b -> BOOL_CONSTANT \n") ;
		$$=new eval();
		((eval)$$).stmt += this.stmt;
		((eval)$$).cSourceCode += ((eval)$$).stmt;
		writer.print(((eval)$$).stmt+ "\n");
	};
	
n : INTEGER_NUMBER
	{
		writer.print("\t n -> INTEGER_NUMBER \n") ;
		$$=new eval();
		((eval)$$).stmt += this.stmt;
		((eval)$$).cSourceCode += ((eval)$$).stmt;
		writer.print(((eval)$$).stmt+ "\n");
	};

x : IDENTIFIER
	{
		writer.print("\t x -> IDENTIFIER \n") ;
		$$=new eval();
		((eval)$$).stmt += this.stmt;
		((eval)$$).cSourceCode += ((eval)$$).stmt;
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
	public String nodeIdAndStmt="";
	
	public String cSourceCode = "";
	
	public HashSet<String> variables = new HashSet<String>();
	
	public Node node;
	public MyLinkedList list;

}
