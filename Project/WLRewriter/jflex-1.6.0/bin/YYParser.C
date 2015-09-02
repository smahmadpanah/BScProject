/* A Bison parser, made by GNU Bison 2.5.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java
   
      Copyright (C) 2007-2011 Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* First part of user declarations.  */

/* Line 32 of lalr1.java  */
/* Line 1 of "YYParser.Y"  */

package wlrewriter;

  import java.io.*;
  import java.lang.*;
  import java.util.*; 



/**
 * A Bison parser, automatically generated from <tt>YYParser.Y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
class YYParser
{
    /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "2.5";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";


  /** True if verbose error messages are enabled.  */
  public boolean errorVerbose = false;



  /** Token returned by the scanner to signal the end of its input.  */
  public static final int EOF = 0;

/* Tokens.  */
  /** Token number, to be returned by the scanner.  */
  public static final int PROGRAM_KW = 258;
  /** Token number, to be returned by the scanner.  */
  public static final int AND_KW = 259;
  /** Token number, to be returned by the scanner.  */
  public static final int OR_KW = 260;
  /** Token number, to be returned by the scanner.  */
  public static final int NEG_KW = 261;
  /** Token number, to be returned by the scanner.  */
  public static final int LPAR_KW = 262;
  /** Token number, to be returned by the scanner.  */
  public static final int RPAR_KW = 263;
  /** Token number, to be returned by the scanner.  */
  public static final int ASSIGN_KW = 264;
  /** Token number, to be returned by the scanner.  */
  public static final int IF_KW = 265;
  /** Token number, to be returned by the scanner.  */
  public static final int THEN_KW = 266;
  /** Token number, to be returned by the scanner.  */
  public static final int ELSE_KW = 267;
  /** Token number, to be returned by the scanner.  */
  public static final int ENDIF_KW = 268;
  /** Token number, to be returned by the scanner.  */
  public static final int WHILE_KW = 269;
  /** Token number, to be returned by the scanner.  */
  public static final int DO_KW = 270;
  /** Token number, to be returned by the scanner.  */
  public static final int DONE_KW = 271;
  /** Token number, to be returned by the scanner.  */
  public static final int NOP_KW = 272;
  /** Token number, to be returned by the scanner.  */
  public static final int BOT_KW = 273;
  /** Token number, to be returned by the scanner.  */
  public static final int INL_KW = 274;
  /** Token number, to be returned by the scanner.  */
  public static final int INH_KW = 275;
  /** Token number, to be returned by the scanner.  */
  public static final int OUTL_KW = 276;
  /** Token number, to be returned by the scanner.  */
  public static final int OUTH_KW = 277;
  /** Token number, to be returned by the scanner.  */
  public static final int PLUS_KW = 278;
  /** Token number, to be returned by the scanner.  */
  public static final int MINUS_KW = 279;
  /** Token number, to be returned by the scanner.  */
  public static final int LT_KW = 280;
  /** Token number, to be returned by the scanner.  */
  public static final int LE_KW = 281;
  /** Token number, to be returned by the scanner.  */
  public static final int EQ_KW = 282;
  /** Token number, to be returned by the scanner.  */
  public static final int GT_KW = 283;
  /** Token number, to be returned by the scanner.  */
  public static final int GE_KW = 284;
  /** Token number, to be returned by the scanner.  */
  public static final int INTEGER_NUMBER = 285;
  /** Token number, to be returned by the scanner.  */
  public static final int BOOL_CONSTANT = 286;
  /** Token number, to be returned by the scanner.  */
  public static final int IDENTIFIER = 287;
  /** Token number, to be returned by the scanner.  */
  public static final int q = 288;
  /** Token number, to be returned by the scanner.  */
  public static final int p = 289;



  

  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>YYParser</tt>.
   */
  public interface Lexer {
    

    /**
     * Method to retrieve the semantic value of the last scanned token.
     * @return the semantic value of the last scanned token.  */
    Object getLVal ();

    /**
     * Entry point for the scanner.  Returns the token identifier corresponding
     * to the next token and prepares to return the semantic value
     * of the token.
     * @return the token identifier corresponding to the next token. */
    int yylex () throws java.io.IOException;

    /**
     * Entry point for error reporting.  Emits an error
     * in a user-defined way.
     *
     * 
     * @param s The string for the error message.  */
     void yyerror (String s);
  }

  /** The object doing lexical analysis for us.  */
  private Lexer yylexer;
  
  



  /**
   * Instantiates the Bison-generated parser.
   * @param yylexer The scanner that will supply tokens to the parser.
   */
  public YYParser (Lexer yylexer) {
    this.yylexer = yylexer;
    
  }

  private java.io.PrintStream yyDebugStream = System.err;

  /**
   * Return the <tt>PrintStream</tt> on which the debugging output is
   * printed.
   */
  public final java.io.PrintStream getDebugStream () { return yyDebugStream; }

  /**
   * Set the <tt>PrintStream</tt> on which the debug output is printed.
   * @param s The stream that is used for debugging output.
   */
  public final void setDebugStream(java.io.PrintStream s) { yyDebugStream = s; }

  private int yydebug = 0;

  /**
   * Answer the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   */
  public final int getDebugLevel() { return yydebug; }

  /**
   * Set the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   * @param level The verbosity level for debugging output.
   */
  public final void setDebugLevel(int level) { yydebug = level; }

  private final int yylex () throws java.io.IOException {
    return yylexer.yylex ();
  }
  protected final void yyerror (String s) {
    yylexer.yyerror (s);
  }

  

  protected final void yycdebug (String s) {
    if (yydebug > 0)
      yyDebugStream.println (s);
  }

  private final class YYStack {
    private int[] stateStack = new int[16];
    
    private Object[] valueStack = new Object[16];

    public int size = 16;
    public int height = -1;

    public final void push (int state, Object value			    ) {
      height++;
      if (size == height)
        {
	  int[] newStateStack = new int[size * 2];
	  System.arraycopy (stateStack, 0, newStateStack, 0, height);
	  stateStack = newStateStack;
	  

	  Object[] newValueStack = new Object[size * 2];
	  System.arraycopy (valueStack, 0, newValueStack, 0, height);
	  valueStack = newValueStack;

	  size *= 2;
	}

      stateStack[height] = state;
      
      valueStack[height] = value;
    }

    public final void pop () {
      pop (1);
    }

    public final void pop (int num) {
      // Avoid memory leaks... garbage collection is a white lie!
      if (num > 0) {
	java.util.Arrays.fill (valueStack, height - num + 1, height + 1, null);
        
      }
      height -= num;
    }

    public final int stateAt (int i) {
      return stateStack[height - i];
    }

    public final Object valueAt (int i) {
      return valueStack[height - i];
    }

    // Print the state stack on the debug stream.
    public void print (java.io.PrintStream out)
    {
      out.print ("Stack now");

      for (int i = 0; i <= height; i++)
        {
	  out.print (' ');
	  out.print (stateStack[i]);
        }
      out.println ();
    }
  }

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return success (<tt>true</tt>).  */
  public static final int YYACCEPT = 0;

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return failure (<tt>false</tt>).  */
  public static final int YYABORT = 1;

  /**
   * Returned by a Bison action in order to start error recovery without
   * printing an error message.  */
  public static final int YYERROR = 2;

  // Internal return codes that are not supported for user semantic
  // actions.
  private static final int YYERRLAB = 3;
  private static final int YYNEWSTATE = 4;
  private static final int YYDEFAULT = 5;
  private static final int YYREDUCE = 6;
  private static final int YYERRLAB1 = 7;
  private static final int YYRETURN = 8;

  private int yyerrstatus_ = 0;

  /**
   * Return whether error recovery is being done.  In this state, the parser
   * reads token until it reaches a known state, and then restarts normal
   * operation.  */
  public final boolean recovering ()
  {
    return yyerrstatus_ == 0;
  }

  private int yyaction (int yyn, YYStack yystack, int yylen) 
  {
    Object yyval;
    

    /* If YYLEN is nonzero, implement the default value of the action:
       `$$ = $1'.  Otherwise, use the top of the stack.

       Otherwise, the following line sets YYVAL to garbage.
       This behavior is undocumented and Bison
       users should not rely upon it.  */
    if (yylen > 0)
      yyval = yystack.valueAt (yylen - 1);
    else
      yyval = yystack.valueAt (0);

    yy_reduce_print (yyn, yystack);

    switch (yyn)
      {
	  case 2:
  if (yyn == 2)
    
/* Line 351 of lalr1.java  */
/* Line 107 of "YYParser.Y"  */
    {
		writer.print("\t program -> PROGRAM_KW ';' clist \n") ;
		writer.print("###Hooray! - Your program is syntactically correct### \n");
		System.out.println("###Hooray! - Your program is syntactically correct###");
	
		yyval = new eval();
		((eval)yyval).stmt += "program; " + ((eval)((eval)(yystack.valueAt (3-(3))))).stmt;
		((eval)yyval).cSourceCode += "#include<stdio.h> \n \n void main() { " + ((eval)((eval)(yystack.valueAt (3-(3))))).cSourceCode + "}"; 
		writer.print(((eval)yyval).stmt+ "\n");
		
		//((eval)$$).variables.addAll(((eval)$3).variables);
		
		((eval)yyval).node = new Node(nodeCounter++, "START");
		//System.out.println(((eval)$3).nodeIdAndStmt);
		((eval)yyval).nodeIdAndStmt += "#" + ((eval)yyval).node.getNodeID() + ":" + "program; \n";
		((eval)yyval).nodeIdAndStmt += ((eval)((eval)(yystack.valueAt (3-(3))))).nodeIdAndStmt;
		((eval)yyval).node.setNodeIdAndStmt(((eval)yyval).nodeIdAndStmt);
		/*for(String str : ((eval)$$).variables){
					for(Variable varvar : symbolTableOfVariables){
						if(str.equals(varvar.name)){
							((eval)$$).node.addToVariablesOfNode(varvar);
							break;
						}
		
					}
				}
		*/
		
		
		((eval)yyval).list = new MyLinkedList(((eval)yyval).node);
		((eval)yyval).list.merge(((eval)((eval)(yystack.valueAt (3-(3))))).list);
		((eval)yyval).list.merge(new MyLinkedList(new Node(nodeCounter++, "STOP")));
		System.out.println("the CFG is created.");

		PDGBuilder pdg = new PDGBuilder(((eval)yyval).list); //the CFG is input to build the Forward Dominance Tree and after that, CFG and DDG that make PDG! :)
		PINIRewriter pini = new PINIRewriter(pdg.getPDG());
        PSNIRewriter psni = new PSNIRewriter(pini);
		
		
	};
  break;
    

  case 3:
  if (yyn == 3)
    
/* Line 351 of lalr1.java  */
/* Line 149 of "YYParser.Y"  */
    {
		writer.print("\t clist -> c \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (1-(1))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (1-(1))))).cSourceCode + ";";
		writer.print(((eval)yyval).stmt+ "\n");
		
		((eval)yyval).nodeIdAndStmt += ((eval)((eval)(yystack.valueAt (1-(1))))).nodeIdAndStmt;
		//((eval)$$).variables.addAll(((eval)$1).variables);
		
		((eval)yyval).list = ((eval)((eval)(yystack.valueAt (1-(1))))).list;
	};
  break;
    

  case 4:
  if (yyn == 4)
    
/* Line 351 of lalr1.java  */
/* Line 162 of "YYParser.Y"  */
    {
		writer.print("\t clist -> clist ; M c \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (4-(1))))).stmt + "; " + ((eval)((eval)(yystack.valueAt (4-(4))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (4-(1))))).cSourceCode + "; " + ((eval)((eval)(yystack.valueAt (4-(4))))).cSourceCode + ";";
		writer.print(((eval)yyval).stmt+ "\n");
		
		((eval)yyval).nodeIdAndStmt += ((eval)((eval)(yystack.valueAt (4-(1))))).nodeIdAndStmt + "; \n" + ((eval)((eval)(yystack.valueAt (4-(4))))).nodeIdAndStmt;
		
		//((eval)$$).variables.addAll(((eval)$1).variables);
		//((eval)$$).variables.addAll(((eval)$4).variables);
		
		
		((eval)yyval).list = ((eval)((eval)(yystack.valueAt (4-(1))))).list;
		((eval)yyval).list.merge(((eval)((eval)(yystack.valueAt (4-(4))))).list);
		
	};
  break;
    

  case 5:
  if (yyn == 5)
    
/* Line 351 of lalr1.java  */
/* Line 181 of "YYParser.Y"  */
    {
	
		writer.print("\t exp -> b \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (1-(1))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (1-(1))))).cSourceCode;
	writer.print(((eval)yyval).stmt+ "\n");
	};
  break;
    

  case 6:
  if (yyn == 6)
    
/* Line 351 of lalr1.java  */
/* Line 190 of "YYParser.Y"  */
    {
		writer.print("\t exp -> n \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (1-(1))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (1-(1))))).cSourceCode;
	writer.print(((eval)yyval).stmt+ "\n");
	};
  break;
    

  case 7:
  if (yyn == 7)
    
/* Line 351 of lalr1.java  */
/* Line 198 of "YYParser.Y"  */
    {
		writer.print("\t exp -> x \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (1-(1))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (1-(1))))).cSourceCode;

		((eval)yyval).variables.add(((eval)((eval)(yystack.valueAt (1-(1))))).stmt);
		
	writer.print(((eval)yyval).stmt+ "\n");
	
		boolean check = false;
		for(Variable v : symbolTableOfVariables){
			if(((eval)((eval)(yystack.valueAt (1-(1))))).stmt.equals(v.name)){
				check = true;
				break;
			}
		}
		
		if(!check){
			System.err.println("undefined variable!");
			System.err.println("\t"+((eval)yyval).stmt);
			System.exit(0);
		}
	};
  break;
    

  case 8:
  if (yyn == 8)
    
/* Line 351 of lalr1.java  */
/* Line 223 of "YYParser.Y"  */
    {
		writer.print("\t exp -> exp EQ_KW exp \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (3-(1))))).stmt + " == "+ ((eval)((eval)(yystack.valueAt (3-(3))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (3-(1))))).cSourceCode + " == "+ ((eval)((eval)(yystack.valueAt (3-(3))))).cSourceCode;

		writer.print(((eval)yyval).stmt+ "\n");
	
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(1))))).variables);
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(3))))).variables);
	};
  break;
    

  case 9:
  if (yyn == 9)
    
/* Line 351 of lalr1.java  */
/* Line 235 of "YYParser.Y"  */
    {
		writer.print("\t exp -> exp LT_KW exp \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (3-(1))))).stmt + " < "+ ((eval)((eval)(yystack.valueAt (3-(3))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (3-(1))))).cSourceCode + " < "+ ((eval)((eval)(yystack.valueAt (3-(3))))).cSourceCode;

		writer.print(((eval)yyval).stmt+ "\n");
	
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(1))))).variables);
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(3))))).variables);
	};
  break;
    

  case 10:
  if (yyn == 10)
    
/* Line 351 of lalr1.java  */
/* Line 247 of "YYParser.Y"  */
    {
		writer.print("\t exp -> exp LE_KW exp \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (3-(1))))).stmt + " <= "+ ((eval)((eval)(yystack.valueAt (3-(3))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (3-(1))))).cSourceCode + " <= "+ ((eval)((eval)(yystack.valueAt (3-(3))))).cSourceCode;

		writer.print(((eval)yyval).stmt+ "\n");
	
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(1))))).variables);
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(3))))).variables);
	};
  break;
    

  case 11:
  if (yyn == 11)
    
/* Line 351 of lalr1.java  */
/* Line 259 of "YYParser.Y"  */
    {
		writer.print("\t exp -> exp GE_KW exp \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (3-(1))))).stmt + " >= "+ ((eval)((eval)(yystack.valueAt (3-(3))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (3-(1))))).cSourceCode + " >= "+ ((eval)((eval)(yystack.valueAt (3-(3))))).cSourceCode;

		writer.print(((eval)yyval).stmt+ "\n");
	
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(1))))).variables);
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(3))))).variables);
	};
  break;
    

  case 12:
  if (yyn == 12)
    
/* Line 351 of lalr1.java  */
/* Line 271 of "YYParser.Y"  */
    {
		writer.print("\t exp -> exp GT_KW exp \n") ;
	yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (3-(1))))).stmt + " > "+ ((eval)((eval)(yystack.valueAt (3-(3))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (3-(1))))).cSourceCode + " > "+ ((eval)((eval)(yystack.valueAt (3-(3))))).cSourceCode;

	writer.print(((eval)yyval).stmt+ "\n");
	
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(1))))).variables);
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(3))))).variables);
	};
  break;
    

  case 13:
  if (yyn == 13)
    
/* Line 351 of lalr1.java  */
/* Line 283 of "YYParser.Y"  */
    {
		writer.print("\t exp -> exp PLUS_KW exp \n") ;
	yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (3-(1))))).stmt + " + "+ ((eval)((eval)(yystack.valueAt (3-(3))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (3-(1))))).cSourceCode + " + "+ ((eval)((eval)(yystack.valueAt (3-(3))))).cSourceCode;

	writer.print(((eval)yyval).stmt+ "\n");
	
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(1))))).variables);
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(3))))).variables);
	};
  break;
    

  case 14:
  if (yyn == 14)
    
/* Line 351 of lalr1.java  */
/* Line 295 of "YYParser.Y"  */
    {
		writer.print("\t exp -> exp MINUS_KW exp \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (3-(1))))).stmt + " - "+ ((eval)((eval)(yystack.valueAt (3-(3))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (3-(1))))).cSourceCode + " - "+ ((eval)((eval)(yystack.valueAt (3-(3))))).cSourceCode;
	writer.print(((eval)yyval).stmt+ "\n");
	
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(1))))).variables);
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(3))))).variables);
	};
  break;
    

  case 15:
  if (yyn == 15)
    
/* Line 351 of lalr1.java  */
/* Line 306 of "YYParser.Y"  */
    {
		writer.print("\t exp -> exp AND_KW exp \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (4-(1))))).stmt + " and "+ ((eval)((eval)(yystack.valueAt (4-(4))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (4-(1))))).cSourceCode + " && "+ ((eval)((eval)(yystack.valueAt (4-(4))))).cSourceCode;
	writer.print(((eval)yyval).stmt+ "\n");

	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (4-(1))))).variables);
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (4-(4))))).variables);	
	};
  break;
    

  case 16:
  if (yyn == 16)
    
/* Line 351 of lalr1.java  */
/* Line 317 of "YYParser.Y"  */
    {
		writer.print("\t exp -> exp OR_KW exp \n") ;
			yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (4-(1))))).stmt + " or "+ ((eval)((eval)(yystack.valueAt (4-(4))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (4-(1))))).cSourceCode + " || "+ ((eval)((eval)(yystack.valueAt (4-(4))))).cSourceCode;
	
writer.print(((eval)yyval).stmt+ "\n");	


	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (4-(1))))).variables);
	((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (4-(4))))).variables);
	};
  break;
    

  case 17:
  if (yyn == 17)
    
/* Line 351 of lalr1.java  */
/* Line 330 of "YYParser.Y"  */
    {
		writer.print("\t exp -> NEG_KW exp \n") ;
		yyval=new eval();
		((eval)yyval).stmt += "!("+ ((eval)((eval)(yystack.valueAt (2-(2))))).stmt + ")";
		((eval)yyval).cSourceCode += "!("+ ((eval)((eval)(yystack.valueAt (2-(2))))).cSourceCode + ")";
	
		writer.print(((eval)yyval).stmt+ "\n");	

		((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (2-(2))))).variables);
	};
  break;
    

  case 18:
  if (yyn == 18)
    
/* Line 351 of lalr1.java  */
/* Line 341 of "YYParser.Y"  */
    {
		writer.print("\t exp -> LPAR_KW exp RPAR_KW \n") ;
		yyval=new eval();
		((eval)yyval).stmt += "("+ ((eval)((eval)(yystack.valueAt (3-(2))))).stmt + ")";
		((eval)yyval).cSourceCode += "("+ ((eval)((eval)(yystack.valueAt (3-(2))))).cSourceCode + ")";
		writer.print(((eval)yyval).stmt+ "\n");	

		((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(2))))).variables);
	
	
	};
  break;
    

  case 19:
  if (yyn == 19)
    
/* Line 351 of lalr1.java  */
/* Line 354 of "YYParser.Y"  */
    {
		writer.print("\t c -> NOP_KW \n") ;
		yyval=new eval();
		((eval)yyval).stmt += "NOP";
		((eval)yyval).cSourceCode += ";";
		writer.print(((eval)yyval).stmt+ "\n");	
		((eval)yyval).node = new Node(nodeCounter++, ((eval)yyval).stmt);
		((eval)yyval).nodeIdAndStmt += "#" + ((eval)yyval).node.getNodeID() + ":" + ((eval)yyval).stmt;
		((eval)yyval).node.setNodeIdAndStmt(((eval)yyval).nodeIdAndStmt);
		((eval)yyval).list = new MyLinkedList(((eval)yyval).node);

	};
  break;
    

  case 20:
  if (yyn == 20)
    
/* Line 351 of lalr1.java  */
/* Line 367 of "YYParser.Y"  */
    {
		writer.print("\t c -> x ASSIGN_KW exp \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (3-(1))))).stmt + " = " + ((eval)((eval)(yystack.valueAt (3-(3))))).stmt;
		((eval)yyval).cSourceCode += ((eval)((eval)(yystack.valueAt (3-(1))))).cSourceCode + " = " + ((eval)((eval)(yystack.valueAt (3-(3))))).cSourceCode;
		writer.print(((eval)yyval).stmt+ "\n");	
				
		((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(3))))).variables);
		
		boolean check = false;
		for(Variable v : symbolTableOfVariables){
			if(((eval)((eval)(yystack.valueAt (3-(1))))).stmt.equals(v.name)){
				((eval)yyval).node = new Node(nodeCounter++, ((eval)yyval).stmt);
				((eval)yyval).nodeIdAndStmt += "#" + ((eval)yyval).node.getNodeID() + ":" + ((eval)((eval)(yystack.valueAt (3-(1))))).stmt + " = " + ((eval)((eval)(yystack.valueAt (3-(3))))).stmt;
				((eval)yyval).node.setNodeIdAndStmt(((eval)yyval).nodeIdAndStmt);
				
				for(String str : ((eval)yyval).variables){
					for(Variable varvar : symbolTableOfVariables){
						if(str.equals(varvar.name)){
							((eval)yyval).node.addToVariablesOfNode(varvar);
							break;
						}
					}
				}
				
				for(Variable varvar : symbolTableOfVariables){
					if(varvar.name.equals(((eval)((eval)(yystack.valueAt (3-(1))))).stmt)){
							((eval)yyval).node.setAssignedVariable(varvar);
							break;
					}
				}
				
				
				((eval)yyval).list = new MyLinkedList(((eval)yyval).node);
				check = true;
				break;
			}
		}
		
		if(!check){
			System.err.println("undefined variable can not be assigned:");
			System.err.println("\t"+((eval)yyval).stmt);
			System.exit(0);
		}
		
		((eval)yyval).variables.add(((eval)((eval)(yystack.valueAt (3-(1))))).stmt); //not necessary

	};
  break;
    

  case 21:
  if (yyn == 21)
    
/* Line 351 of lalr1.java  */
/* Line 416 of "YYParser.Y"  */
    {
		writer.print("\t c -> INL_KW varlist \n") ;
		yyval=new eval();
		((eval)yyval).stmt += "inL "+((eval)((eval)(yystack.valueAt (2-(2))))).stmt;	
		((eval)yyval).cSourceCode += "int "+((eval)((eval)(yystack.valueAt (2-(2))))).cSourceCode;
		writer.print(((eval)yyval).stmt+ "\n");	
		
		((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (2-(2))))).variables);
		
		
		boolean first = true;
		for(String i : ((eval)((eval)(yystack.valueAt (2-(2))))).variables){
			Variable currentVar = new Variable(i);
			
			for(Variable v : symbolTableOfVariables){
				if(v.name.equals(currentVar.name)){
					v.type = "low";
					currentVar.type = "low";
				}
			}
			
			((eval)yyval).node = new Node(nodeCounter++, currentVar.name);
			((eval)yyval).node.setAssignedVariable(currentVar);
			//((eval)$$).node.addToVariablesOfNode(currentVar);
			
			if(first){
				((eval)yyval).list = new MyLinkedList(((eval)yyval).node);
				first = false;
			}
			else{
				((eval)yyval).list.merge(new MyLinkedList(((eval)yyval).node));
			}
		}
		
			((eval)yyval).nodeIdAndStmt += "#" + ((eval)yyval).node.getNodeID() + ":" + ((eval)yyval).stmt;
			((eval)yyval).node.setNodeIdAndStmt(((eval)yyval).nodeIdAndStmt);
		
		

	};
  break;
    

  case 22:
  if (yyn == 22)
    
/* Line 351 of lalr1.java  */
/* Line 457 of "YYParser.Y"  */
    {
		writer.print("\t c -> INH_KW varlist \n") ;
		yyval=new eval();
		((eval)yyval).stmt += "inH "+((eval)((eval)(yystack.valueAt (2-(2))))).stmt;
		((eval)yyval).cSourceCode += "int "+((eval)((eval)(yystack.valueAt (2-(2))))).cSourceCode;
		writer.print(((eval)yyval).stmt+ "\n");
		
		((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (2-(2))))).variables);
		
		
		boolean first = true;
		for(String i : ((eval)((eval)(yystack.valueAt (2-(2))))).variables){
			Variable currentVar = new Variable(i);
			
			for(Variable v : symbolTableOfVariables){
				if(v.name.equals(currentVar.name)){
					v.type = "high";
					currentVar.type = "high";
				}
			}
			
			((eval)yyval).node = new Node(nodeCounter++, currentVar.name);
			((eval)yyval).node.setAssignedVariable(currentVar);
			//((eval)$$).node.addToVariablesOfNode(currentVar);
			
			if(first){
				((eval)yyval).list = new MyLinkedList(((eval)yyval).node);
				first = false;
			}
			else{
				((eval)yyval).list.merge(new MyLinkedList(((eval)yyval).node));
			}
		}
			((eval)yyval).nodeIdAndStmt += "#" + ((eval)yyval).node.getNodeID() + ":" + ((eval)yyval).stmt;
			((eval)yyval).node.setNodeIdAndStmt(((eval)yyval).nodeIdAndStmt);
		
		

	};
  break;
    

  case 23:
  if (yyn == 23)
    
/* Line 351 of lalr1.java  */
/* Line 497 of "YYParser.Y"  */
    {
		writer.print("\t c -> OUTL_KW x \n") ;
		yyval=new eval();
		((eval)yyval).stmt += "outL " + ((eval)((eval)(yystack.valueAt (2-(2))))).stmt;
		((eval)yyval).cSourceCode += "printf(\"%d\n\","+((eval)((eval)(yystack.valueAt (2-(2))))).cSourceCode+")";
		writer.print(((eval)yyval).stmt+ "\n");	
		
		((eval)yyval).variables.add(((eval)((eval)(yystack.valueAt (2-(2))))).stmt);
		
		boolean check = false;
		for(Variable v : symbolTableOfVariables){
			if(((eval)((eval)(yystack.valueAt (2-(2))))).stmt.equals(v.name) && v.type.equals("low")){
				((eval)yyval).node = new Node(nodeCounter++, ((eval)yyval).stmt);
				((eval)yyval).nodeIdAndStmt += "#" + ((eval)yyval).node.getNodeID() + ":" + ((eval)yyval).stmt;
				((eval)yyval).node.setNodeIdAndStmt(((eval)yyval).nodeIdAndStmt);
				
				for(String str : ((eval)yyval).variables){
					for(Variable varvar : symbolTableOfVariables){
						if(str.equals(varvar.name)){
							((eval)yyval).node.addToVariablesOfNode(varvar);
							break;
						}
		
					}
				}
		
				
				((eval)yyval).list = new MyLinkedList(((eval)yyval).node);
				check = true;
				break;
			}
		}
		
		if(!check){
			System.err.println("undefined variable!");
			System.err.println("\t"+((eval)yyval).stmt);
			System.exit(0);
		}
		
		

	};
  break;
    

  case 24:
  if (yyn == 24)
    
/* Line 351 of lalr1.java  */
/* Line 540 of "YYParser.Y"  */
    {
		writer.print("\t c -> OUTH_KW x \n") ;
		yyval=new eval();
		((eval)yyval).stmt += "outH " + ((eval)((eval)(yystack.valueAt (2-(2))))).stmt;
		((eval)yyval).cSourceCode += "printf(\"%d\n\","+((eval)((eval)(yystack.valueAt (2-(2))))).cSourceCode+")";
		writer.print(((eval)yyval).stmt+ "\n");
		
		((eval)yyval).variables.add(((eval)((eval)(yystack.valueAt (2-(2))))).stmt);
		
		boolean check = false;
		for(Variable v : symbolTableOfVariables){
			if(((eval)((eval)(yystack.valueAt (2-(2))))).stmt.equals(v.name) && v.type.equals("high")){
				((eval)yyval).node = new Node(nodeCounter++, ((eval)yyval).stmt);
				((eval)yyval).nodeIdAndStmt += "#" + ((eval)yyval).node.getNodeID() + ":" + ((eval)yyval).stmt;
				((eval)yyval).node.setNodeIdAndStmt(((eval)yyval).nodeIdAndStmt);
				
				for(String str : ((eval)yyval).variables){
					for(Variable varvar : symbolTableOfVariables){
						if(str.equals(varvar.name)){
							((eval)yyval).node.addToVariablesOfNode(varvar);
							break;
						}
		
					}
				}
				
				((eval)yyval).list = new MyLinkedList(((eval)yyval).node);
				check = true;
				break;
			}
		}
		
		if(!check){
			System.err.println("undefined variable!");
			System.err.println("\t"+((eval)yyval).stmt);
			System.exit(0);
		}
		

	};
  break;
    

  case 25:
  if (yyn == 25)
    
/* Line 351 of lalr1.java  */
/* Line 581 of "YYParser.Y"  */
    {
		writer.print("\t c -> OUTL_KW BOT_KW \n") ;
		yyval=new eval();
		((eval)yyval).stmt += "outL BOT";
		((eval)yyval).cSourceCode += "printf(\"BOT\n\")";
		writer.print(((eval)yyval).stmt+ "\n");	
		((eval)yyval).node = new Node(nodeCounter++, ((eval)yyval).stmt);
		((eval)yyval).nodeIdAndStmt += "#" + ((eval)yyval).node.getNodeID() + ":" + ((eval)yyval).stmt;
		((eval)yyval).node.setNodeIdAndStmt(((eval)yyval).nodeIdAndStmt);
		
		((eval)yyval).list = new MyLinkedList(((eval)yyval).node);
	
	};
  break;
    

  case 26:
  if (yyn == 26)
    
/* Line 351 of lalr1.java  */
/* Line 595 of "YYParser.Y"  */
    {
		writer.print("\t c -> OUTH_KW BOT_KW \n") ;	
		yyval=new eval();
		((eval)yyval).stmt += "outH BOT";
		((eval)yyval).cSourceCode += "printf(\"BOT\n\")";
		writer.print(((eval)yyval).stmt+ "\n");	
		((eval)yyval).node = new Node(nodeCounter++, ((eval)yyval).stmt);
		((eval)yyval).nodeIdAndStmt += "#" + ((eval)yyval).node.getNodeID() + ":" + ((eval)yyval).stmt;
		((eval)yyval).node.setNodeIdAndStmt(((eval)yyval).nodeIdAndStmt);
		((eval)yyval).list = new MyLinkedList(((eval)yyval).node);
	
	};
  break;
    

  case 27:
  if (yyn == 27)
    
/* Line 351 of lalr1.java  */
/* Line 608 of "YYParser.Y"  */
    {
		writer.print("\t c -> IF_KW exp THEN_KW M clist ENDIF_KW \n") ;
		yyval=new eval();
		((eval)yyval).stmt += " if " + ((eval)((eval)(yystack.valueAt (6-(2))))).stmt + " then " + ((eval)((eval)(yystack.valueAt (6-(5))))).stmt + " endif";
		((eval)yyval).cSourceCode += " if (" + ((eval)((eval)(yystack.valueAt (6-(2))))).cSourceCode + ") { " + ((eval)((eval)(yystack.valueAt (6-(5))))).cSourceCode + "}";
		writer.print(((eval)yyval).stmt+ "\n");
		
		((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (6-(2))))).variables);
		//((eval)$$).variables.addAll(((eval)$5).variables);
		
		((eval)yyval).node = new Node(nodeCounter++, ((eval)((eval)(yystack.valueAt (6-(2))))).stmt);//condition expression node
		((eval)yyval).nodeIdAndStmt += " if " + "#" + ((eval)yyval).node.getNodeID() + ":" + ((eval)((eval)(yystack.valueAt (6-(2))))).stmt + " then \n" + ((eval)((eval)(yystack.valueAt (6-(5))))).nodeIdAndStmt + " endif";
		((eval)yyval).node.setNodeIdAndStmt(((eval)yyval).nodeIdAndStmt);
		
		for(String str : ((eval)yyval).variables){
			for(Variable v : symbolTableOfVariables){
				if(str.equals(v.name)){
					((eval)yyval).node.addToVariablesOfNode(v);
					break;
				}
		
			}
		}
		
		((eval)yyval).list = new MyLinkedList(((eval)yyval).node);

		Node dummy = new Node(nodeCounter++, "dummy");//dummy node for last node of if
		
		((eval)yyval).list.getLast().setNextPointer2(dummy);//if false
		dummy.addPreviousPointer(((eval)yyval).list.getLast()); //backward pointer
		
		((eval)yyval).list.merge(((eval)((eval)(yystack.valueAt (6-(5))))).list);//if true
		((eval)yyval).list.merge(new MyLinkedList(dummy));	
	};
  break;
    

  case 28:
  if (yyn == 28)
    
/* Line 351 of lalr1.java  */
/* Line 643 of "YYParser.Y"  */
    {
		writer.print("\t c -> IF_KW exp THEN_KW M clist ELSE_KW N M clist ENDIF_KW \n") ;
		yyval=new eval();
		((eval)yyval).stmt += " if " + ((eval)((eval)(yystack.valueAt (10-(2))))).stmt + " then " + ((eval)((eval)(yystack.valueAt (10-(5))))).stmt + " else " + ((eval)((eval)(yystack.valueAt (10-(9))))).stmt + " endif ";
		((eval)yyval).cSourceCode += " if (" + ((eval)((eval)(yystack.valueAt (10-(2))))).cSourceCode + ") { " + ((eval)((eval)(yystack.valueAt (10-(5))))).cSourceCode + "} else {" + ((eval)((eval)(yystack.valueAt (10-(9))))).cSourceCode + "} ";
		writer.print(((eval)yyval).stmt+ "\n");
		
		((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (10-(2))))).variables);
		//((eval)$$).variables.addAll(((eval)$5).variables);
		//((eval)$$).variables.addAll(((eval)$9).variables);

		((eval)yyval).node = new Node(nodeCounter++, ((eval)((eval)(yystack.valueAt (10-(2))))).stmt);//condition expression node
		((eval)yyval).nodeIdAndStmt += " if " + "#" + ((eval)yyval).node.getNodeID() + ":" + ((eval)((eval)(yystack.valueAt (10-(2))))).stmt + " then \n" + ((eval)((eval)(yystack.valueAt (10-(5))))).nodeIdAndStmt + " else \n" + ((eval)((eval)(yystack.valueAt (10-(9))))).nodeIdAndStmt + " endif ";
		((eval)yyval).node.setNodeIdAndStmt(((eval)yyval).nodeIdAndStmt);
		
		for(String str : ((eval)yyval).variables){
			for(Variable v : symbolTableOfVariables){
				if(str.equals(v.name)){
					((eval)yyval).node.addToVariablesOfNode(v);
					break;
				}
		
			}
		}
		
		((eval)yyval).list = new MyLinkedList(((eval)yyval).node);
		
		Node dummy = new Node(nodeCounter++, "dummy");//dummy node for last node of if
		MyLinkedList dummyList = new MyLinkedList(dummy);
		
		((eval)yyval).list.getLast().setNextPointer2(((eval)((eval)(yystack.valueAt (10-(9))))).list.getFirst());//if false - else section
		((eval)((eval)(yystack.valueAt (10-(9))))).list.getFirst().addPreviousPointer(((eval)yyval).list.getLast()); //backward pointer
		((eval)yyval).list.getNodeSet().addAll(((eval)((eval)(yystack.valueAt (10-(9))))).list.getNodeSet());

		
		
		((eval)((eval)(yystack.valueAt (10-(9))))).list.merge(dummyList);
		((eval)yyval).list.merge(((eval)((eval)(yystack.valueAt (10-(5))))).list);//if true
		((eval)yyval).list.merge(dummyList);	
	};
  break;
    

  case 29:
  if (yyn == 29)
    
/* Line 351 of lalr1.java  */
/* Line 684 of "YYParser.Y"  */
    {
		writer.print("\t c -> WHILE_KW exp DO_KW M clist DONE_KW \n") ;
		yyval=new eval();
		((eval)yyval).stmt += "while " + ((eval)((eval)(yystack.valueAt (6-(2))))).stmt + " do " + ((eval)((eval)(yystack.valueAt (6-(5))))).stmt + " done ";
		((eval)yyval).cSourceCode += "while (" + ((eval)((eval)(yystack.valueAt (6-(2))))).cSourceCode + ") { " + ((eval)((eval)(yystack.valueAt (6-(5))))).cSourceCode + "\n} ";
		writer.print(((eval)yyval).stmt+ "\n");
		
		((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (6-(2))))).variables);
	//	((eval)$$).variables.addAll(((eval)$5).variables);
	
		((eval)yyval).node = new Node(nodeCounter++, ((eval)((eval)(yystack.valueAt (6-(2))))).stmt);//condition expression node
		((eval)yyval).nodeIdAndStmt += "while " + "#" + ((eval)yyval).node.getNodeID() + ":" + ((eval)((eval)(yystack.valueAt (6-(2))))).stmt + " do \n" + ((eval)((eval)(yystack.valueAt (6-(5))))).nodeIdAndStmt + " done ";
		((eval)yyval).node.setNodeIdAndStmt(((eval)yyval).nodeIdAndStmt);
		
		for(String str : ((eval)yyval).variables){
			for(Variable v : symbolTableOfVariables){
				if(str.equals(v.name)){
					((eval)yyval).node.addToVariablesOfNode(v);
					break;
				}
		
			}
		}
		
		
		((eval)yyval).list = new MyLinkedList(((eval)yyval).node);
		
		Node dummy = new Node(nodeCounter++, "dummy");//dummy node for last node of if
		MyLinkedList dummyList = new MyLinkedList(dummy);
		
		((eval)yyval).list.getLast().setNextPointer2(dummy);//while condition false
		dummy.addPreviousPointer(((eval)yyval).list.getLast()); //backward pointer	
		((eval)yyval).list.getNodeSet().add(dummy);

		((eval)yyval).list.merge(((eval)((eval)(yystack.valueAt (6-(5))))).list); //while condition true (loop)
		
		((eval)((eval)(yystack.valueAt (6-(5))))).list.getLast().setNextPointer1(((eval)yyval).list.getFirst());
		((eval)yyval).list.getFirst().addPreviousPointer(((eval)((eval)(yystack.valueAt (6-(5))))).list.getLast());
		
		((eval)yyval).list.setLast(dummy);
	};
  break;
    

  case 30:
  if (yyn == 30)
    
/* Line 351 of lalr1.java  */
/* Line 727 of "YYParser.Y"  */
    {
		writer.print("\t varlist -> x \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (1-(1))))).stmt;
		((eval)yyval).cSourceCode += ((eval)yyval).stmt;
		writer.print(((eval)yyval).stmt+ "\n");
		
		Variable tempVar = new Variable(((eval)((eval)(yystack.valueAt (1-(1))))).stmt);
		
		boolean flag = true;
		for(int i = 0; i < symbolTableOfVariables.size(); i++){
			if(symbolTableOfVariables.get(i).name.equals(tempVar.name)){
				flag = false;
				break;
			}
		}
		
		if(flag == true){
			symbolTableOfVariables.add(tempVar);
			((eval)yyval).variables.add(((eval)((eval)(yystack.valueAt (1-(1))))).stmt);
		}
		else{
			System.err.println("The variable " + ((eval)((eval)(yystack.valueAt (1-(1))))).stmt + " is already declared!");
			System.exit(0);
		}
	};
  break;
    

  case 31:
  if (yyn == 31)
    
/* Line 351 of lalr1.java  */
/* Line 754 of "YYParser.Y"  */
    {
		writer.print("\t varlist -> x , varlist \n") ;
		yyval=new eval();
		((eval)yyval).stmt += ((eval)((eval)(yystack.valueAt (3-(1))))).stmt + ", " + ((eval)((eval)(yystack.valueAt (3-(3))))).stmt;
		((eval)yyval).cSourceCode += ((eval)yyval).stmt;
		writer.print(((eval)yyval).stmt+ "\n");
		
		Variable tempVar = new Variable(((eval)((eval)(yystack.valueAt (3-(1))))).stmt);
		
		boolean flag = true;
		for(int i = 0; i < symbolTableOfVariables.size(); i++){
			if(symbolTableOfVariables.get(i).name.equals(tempVar.name)){
				flag = false;
				break;
			}
		}
		
		
		if(flag == true){
			symbolTableOfVariables.add(tempVar);
			((eval)yyval).variables.add(((eval)((eval)(yystack.valueAt (3-(1))))).stmt);
			((eval)yyval).variables.addAll(((eval)((eval)(yystack.valueAt (3-(3))))).variables);
		}
		else{
			System.err.println("The variable " + ((eval)((eval)(yystack.valueAt (3-(1))))).stmt + " is already declared!");
		}
	};
  break;
    

  case 32:
  if (yyn == 32)
    
/* Line 351 of lalr1.java  */
/* Line 783 of "YYParser.Y"  */
    {
		writer.print("\t b -> BOOL_CONSTANT \n") ;
		yyval=new eval();
		((eval)yyval).stmt += this.stmt;
		((eval)yyval).cSourceCode += ((eval)yyval).stmt;
		writer.print(((eval)yyval).stmt+ "\n");
	};
  break;
    

  case 33:
  if (yyn == 33)
    
/* Line 351 of lalr1.java  */
/* Line 792 of "YYParser.Y"  */
    {
		writer.print("\t n -> INTEGER_NUMBER \n") ;
		yyval=new eval();
		((eval)yyval).stmt += this.stmt;
		((eval)yyval).cSourceCode += ((eval)yyval).stmt;
		writer.print(((eval)yyval).stmt+ "\n");
	};
  break;
    

  case 34:
  if (yyn == 34)
    
/* Line 351 of lalr1.java  */
/* Line 801 of "YYParser.Y"  */
    {
		writer.print("\t x -> IDENTIFIER \n") ;
		yyval=new eval();
		((eval)yyval).stmt += this.stmt;
		((eval)yyval).cSourceCode += ((eval)yyval).stmt;
		writer.print(((eval)yyval).stmt+ "\n");
	};
  break;
    

  case 35:
  if (yyn == 35)
    
/* Line 351 of lalr1.java  */
/* Line 810 of "YYParser.Y"  */
    {
	
	};
  break;
    

  case 36:
  if (yyn == 36)
    
/* Line 351 of lalr1.java  */
/* Line 815 of "YYParser.Y"  */
    {
	
	};
  break;
    



/* Line 351 of lalr1.java  */
/* Line 1296 of "YYParser.C"  */
	default: break;
      }

    yy_symbol_print ("-> $$ =", yyr1_[yyn], yyval);

    yystack.pop (yylen);
    yylen = 0;

    /* Shift the result of the reduction.  */
    yyn = yyr1_[yyn];
    int yystate = yypgoto_[yyn - yyntokens_] + yystack.stateAt (0);
    if (0 <= yystate && yystate <= yylast_
	&& yycheck_[yystate] == yystack.stateAt (0))
      yystate = yytable_[yystate];
    else
      yystate = yydefgoto_[yyn - yyntokens_];

    yystack.push (yystate, yyval);
    return YYNEWSTATE;
  }

  /* Return YYSTR after stripping away unnecessary quotes and
     backslashes, so that it's suitable for yyerror.  The heuristic is
     that double-quoting is unnecessary unless the string contains an
     apostrophe, a comma, or backslash (other than backslash-backslash).
     YYSTR is taken from yytname.  */
  private final String yytnamerr_ (String yystr)
  {
    if (yystr.charAt (0) == '"')
      {
        StringBuffer yyr = new StringBuffer ();
        strip_quotes: for (int i = 1; i < yystr.length (); i++)
          switch (yystr.charAt (i))
            {
            case '\'':
            case ',':
              break strip_quotes;

            case '\\':
	      if (yystr.charAt(++i) != '\\')
                break strip_quotes;
              /* Fall through.  */
            default:
              yyr.append (yystr.charAt (i));
              break;

            case '"':
              return yyr.toString ();
            }
      }
    else if (yystr.equals ("$end"))
      return "end of input";

    return yystr;
  }

  /*--------------------------------.
  | Print this symbol on YYOUTPUT.  |
  `--------------------------------*/

  private void yy_symbol_print (String s, int yytype,
			         Object yyvaluep				 )
  {
    if (yydebug > 0)
    yycdebug (s + (yytype < yyntokens_ ? " token " : " nterm ")
	      + yytname_[yytype] + " ("
	      + (yyvaluep == null ? "(null)" : yyvaluep.toString ()) + ")");
  }

  /**
   * Parse input from the scanner that was specified at object construction
   * time.  Return whether the end of the input was reached successfully.
   *
   * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
   *          imply that there were no syntax errors.
   */
  public boolean parse () throws java.io.IOException
  {
    /// Lookahead and lookahead in internal form.
    int yychar = yyempty_;
    int yytoken = 0;

    /* State.  */
    int yyn = 0;
    int yylen = 0;
    int yystate = 0;

    YYStack yystack = new YYStack ();

    /* Error handling.  */
    int yynerrs_ = 0;
    

    /// Semantic value of the lookahead.
    Object yylval = null;

    int yyresult;

    yycdebug ("Starting parse\n");
    yyerrstatus_ = 0;


    /* Initialize the stack.  */
    yystack.push (yystate, yylval);

    int label = YYNEWSTATE;
    for (;;)
      switch (label)
      {
        /* New state.  Unlike in the C/C++ skeletons, the state is already
	   pushed when we come here.  */
      case YYNEWSTATE:
        yycdebug ("Entering state " + yystate + "\n");
        if (yydebug > 0)
          yystack.print (yyDebugStream);

        /* Accept?  */
        if (yystate == yyfinal_)
          return true;

        /* Take a decision.  First try without lookahead.  */
        yyn = yypact_[yystate];
        if (yy_pact_value_is_default_ (yyn))
          {
            label = YYDEFAULT;
	    break;
          }

        /* Read a lookahead token.  */
        if (yychar == yyempty_)
          {
	    yycdebug ("Reading a token: ");
	    yychar = yylex ();
            
            yylval = yylexer.getLVal ();
          }

        /* Convert token to internal form.  */
        if (yychar <= EOF)
          {
	    yychar = yytoken = EOF;
	    yycdebug ("Now at end of input.\n");
          }
        else
          {
	    yytoken = yytranslate_ (yychar);
	    yy_symbol_print ("Next token is", yytoken,
			     yylval);
          }

        /* If the proper action on seeing token YYTOKEN is to reduce or to
           detect an error, take that action.  */
        yyn += yytoken;
        if (yyn < 0 || yylast_ < yyn || yycheck_[yyn] != yytoken)
          label = YYDEFAULT;

        /* <= 0 means reduce or error.  */
        else if ((yyn = yytable_[yyn]) <= 0)
          {
	    if (yy_table_value_is_error_ (yyn))
	      label = YYERRLAB;
	    else
	      {
	        yyn = -yyn;
	        label = YYREDUCE;
	      }
          }

        else
          {
            /* Shift the lookahead token.  */
	    yy_symbol_print ("Shifting", yytoken,
			     yylval);

            /* Discard the token being shifted.  */
            yychar = yyempty_;

            /* Count tokens shifted since error; after three, turn off error
               status.  */
            if (yyerrstatus_ > 0)
              --yyerrstatus_;

            yystate = yyn;
            yystack.push (yystate, yylval);
            label = YYNEWSTATE;
          }
        break;

      /*-----------------------------------------------------------.
      | yydefault -- do the default action for the current state.  |
      `-----------------------------------------------------------*/
      case YYDEFAULT:
        yyn = yydefact_[yystate];
        if (yyn == 0)
          label = YYERRLAB;
        else
          label = YYREDUCE;
        break;

      /*-----------------------------.
      | yyreduce -- Do a reduction.  |
      `-----------------------------*/
      case YYREDUCE:
        yylen = yyr2_[yyn];
        label = yyaction (yyn, yystack, yylen);
	yystate = yystack.stateAt (0);
        break;

      /*------------------------------------.
      | yyerrlab -- here on detecting error |
      `------------------------------------*/
      case YYERRLAB:
        /* If not already recovering from an error, report this error.  */
        if (yyerrstatus_ == 0)
          {
            ++yynerrs_;
            if (yychar == yyempty_)
              yytoken = yyempty_;
            yyerror (yysyntax_error (yystate, yytoken));
          }

        
        if (yyerrstatus_ == 3)
          {
	    /* If just tried and failed to reuse lookahead token after an
	     error, discard it.  */

	    if (yychar <= EOF)
	      {
	      /* Return failure if at end of input.  */
	      if (yychar == EOF)
	        return false;
	      }
	    else
	      yychar = yyempty_;
          }

        /* Else will try to reuse lookahead token after shifting the error
           token.  */
        label = YYERRLAB1;
        break;

      /*---------------------------------------------------.
      | errorlab -- error raised explicitly by YYERROR.  |
      `---------------------------------------------------*/
      case YYERROR:

        
        /* Do not reclaim the symbols of the rule which action triggered
           this YYERROR.  */
        yystack.pop (yylen);
        yylen = 0;
        yystate = yystack.stateAt (0);
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------------------.
      | yyerrlab1 -- common code for both syntax error and YYERROR.  |
      `-------------------------------------------------------------*/
      case YYERRLAB1:
        yyerrstatus_ = 3;	/* Each real token shifted decrements this.  */

        for (;;)
          {
	    yyn = yypact_[yystate];
	    if (!yy_pact_value_is_default_ (yyn))
	      {
	        yyn += yyterror_;
	        if (0 <= yyn && yyn <= yylast_ && yycheck_[yyn] == yyterror_)
	          {
	            yyn = yytable_[yyn];
	            if (0 < yyn)
		      break;
	          }
	      }

	    /* Pop the current state because it cannot handle the error token.  */
	    if (yystack.height == 1)
	      return false;

	    
	    yystack.pop ();
	    yystate = yystack.stateAt (0);
	    if (yydebug > 0)
	      yystack.print (yyDebugStream);
          }

	

        /* Shift the error token.  */
        yy_symbol_print ("Shifting", yystos_[yyn],
			 yylval);

        yystate = yyn;
	yystack.push (yyn, yylval);
        label = YYNEWSTATE;
        break;

        /* Accept.  */
      case YYACCEPT:
        return true;

        /* Abort.  */
      case YYABORT:
        return false;
      }
  }

  // Generate an error message.
  private String yysyntax_error (int yystate, int tok)
  {
    if (errorVerbose)
      {
        /* There are many possibilities here to consider:
           - Assume YYFAIL is not used.  It's too flawed to consider.
             See
             <http://lists.gnu.org/archive/html/bison-patches/2009-12/msg00024.html>
             for details.  YYERROR is fine as it does not invoke this
             function.
           - If this state is a consistent state with a default action,
             then the only way this function was invoked is if the
             default action is an error action.  In that case, don't
             check for expected tokens because there are none.
           - The only way there can be no lookahead present (in tok) is
             if this state is a consistent state with a default action.
             Thus, detecting the absence of a lookahead is sufficient to
             determine that there is no unexpected or expected token to
             report.  In that case, just report a simple "syntax error".
           - Don't assume there isn't a lookahead just because this
             state is a consistent state with a default action.  There
             might have been a previous inconsistent state, consistent
             state with a non-default action, or user semantic action
             that manipulated yychar.  (However, yychar is currently out
             of scope during semantic actions.)
           - Of course, the expected token list depends on states to
             have correct lookahead information, and it depends on the
             parser not to perform extra reductions after fetching a
             lookahead from the scanner and before detecting a syntax
             error.  Thus, state merging (from LALR or IELR) and default
             reductions corrupt the expected token list.  However, the
             list is correct for canonical LR with one exception: it
             will still contain any token that will not be accepted due
             to an error action in a later state.
        */
        if (tok != yyempty_)
          {
            // FIXME: This method of building the message is not compatible
            // with internationalization.
            StringBuffer res =
              new StringBuffer ("syntax error, unexpected ");
            res.append (yytnamerr_ (yytname_[tok]));
            int yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                /* Start YYX at -YYN if negative to avoid negative
                   indexes in YYCHECK.  In other words, skip the first
                   -YYN actions for this state because they are default
                   actions.  */
                int yyxbegin = yyn < 0 ? -yyn : 0;
                /* Stay within bounds of both yycheck and yytname.  */
                int yychecklim = yylast_ - yyn + 1;
                int yyxend = yychecklim < yyntokens_ ? yychecklim : yyntokens_;
                int count = 0;
                for (int x = yyxbegin; x < yyxend; ++x)
                  if (yycheck_[x + yyn] == x && x != yyterror_
                      && !yy_table_value_is_error_ (yytable_[x + yyn]))
                    ++count;
                if (count < 5)
                  {
                    count = 0;
                    for (int x = yyxbegin; x < yyxend; ++x)
                      if (yycheck_[x + yyn] == x && x != yyterror_
                          && !yy_table_value_is_error_ (yytable_[x + yyn]))
                        {
                          res.append (count++ == 0 ? ", expecting " : " or ");
                          res.append (yytnamerr_ (yytname_[x]));
                        }
                  }
              }
            return res.toString ();
          }
      }

    return "syntax error";
  }

  /**
   * Whether the given <code>yypact_</code> value indicates a defaulted state.
   * @param yyvalue   the value to check
   */
  private static boolean yy_pact_value_is_default_ (int yyvalue)
  {
    return yyvalue == yypact_ninf_;
  }

  /**
   * Whether the given <code>yytable_</code> value indicates a syntax error.
   * @param yyvalue   the value to check
   */
  private static boolean yy_table_value_is_error_ (int yyvalue)
  {
    return yyvalue == yytable_ninf_;
  }

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
  private static final byte yypact_ninf_ = -26;
  private static final byte yypact_[] =
  {
         6,   -25,    16,   101,   -26,    23,    23,   -26,     0,     0,
     -14,    -1,   -26,    -2,   -26,    12,    23,    23,   -26,   -26,
      38,   -26,   -26,   -26,    55,   -26,    20,   -26,   -26,   -26,
     -26,   -26,   -26,    23,   102,    67,   -26,   -26,   -26,    23,
      23,    23,    23,    23,    23,    23,   -26,     0,   101,    81,
     -26,    23,    23,   101,   -26,   -26,    -4,     3,   111,    -4,
       3,   101,   -26,   -26,   102,   102,    39,    82,   -26,   -26,
     -26,   -26,   101,   -11,   -26
  };

  /* YYDEFACT[S] -- default reduction number in state S.  Performed when
     YYTABLE doesn't specify something else to do.  Zero means the
     default is an error.  */
  private static final byte yydefact_[] =
  {
         0,     0,     0,     0,     1,     0,     0,    19,     0,     0,
       0,     0,    34,     2,     3,     0,     0,     0,    33,    32,
       0,     5,     6,     7,     0,    21,    30,    22,    25,    23,
      26,    24,    35,     0,    17,     0,    35,    35,    35,     0,
       0,     0,     0,     0,     0,     0,    35,     0,     0,    20,
      18,     0,     0,     0,    13,    14,     9,    10,     8,    12,
      11,     0,    31,     4,    15,    16,     0,     0,    36,    27,
      29,    35,     0,     0,    28
  };

  /* YYPGOTO[NTERM-NUM].  */
  private static final byte yypgoto_[] =
  {
       -26,   -26,    15,    -5,     9,    -6,   -26,   -26,    -3,   -23,
     -26
  };

  /* YYDEFGOTO[NTERM-NUM].  */
  private static final byte
  yydefgoto_[] =
  {
        -1,     2,    13,    20,    14,    25,    21,    22,    23,    48,
      71
  };

  /* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule which
     number is the opposite.  If YYTABLE_NINF_, syntax error.  */
  private static final byte yytable_ninf_ = -1;
  private static final byte
  yytable_[] =
  {
        15,    24,    74,    27,    28,    26,    26,    29,    31,     1,
       3,    34,    35,    51,    52,    53,     4,    30,    12,    39,
      40,    33,    42,    61,    32,    45,    39,    40,    49,    16,
      17,    12,    12,    32,    54,    55,    56,    57,    58,    59,
      60,    62,    36,    37,    26,    15,    64,    65,    72,    38,
      15,    68,    69,    18,    19,    12,    47,    63,    15,    36,
      37,    39,    40,    41,    42,    43,    44,    45,    66,    15,
      46,    36,    37,     0,    32,    50,    67,     0,    39,    40,
      41,    42,    43,    44,    45,    36,    37,    73,     0,     0,
      39,    40,    41,    42,    43,    44,    45,     0,    70,     0,
       0,     0,     0,     0,    39,    40,    41,    42,    43,    44,
      45,     5,     0,     0,     0,     6,     0,    32,     7,     0,
       8,     9,    10,    11,     0,    39,    40,    41,    42,    43,
      44,    45,     0,    12,    39,    40,    41,    42,     0,    44,
      45
  };

  /* YYCHECK.  */
  private static final byte
  yycheck_[] =
  {
         3,     6,    13,     9,    18,     8,     9,    10,    11,     3,
      35,    16,    17,    36,    37,    38,     0,    18,    32,    23,
      24,     9,    26,    46,    35,    29,    23,    24,    33,     6,
       7,    32,    32,    35,    39,    40,    41,    42,    43,    44,
      45,    47,     4,     5,    47,    48,    51,    52,    71,    11,
      53,    12,    13,    30,    31,    32,    36,    48,    61,     4,
       5,    23,    24,    25,    26,    27,    28,    29,    53,    72,
      15,     4,     5,    -1,    35,     8,    61,    -1,    23,    24,
      25,    26,    27,    28,    29,     4,     5,    72,    -1,    -1,
      23,    24,    25,    26,    27,    28,    29,    -1,    16,    -1,
      -1,    -1,    -1,    -1,    23,    24,    25,    26,    27,    28,
      29,    10,    -1,    -1,    -1,    14,    -1,    35,    17,    -1,
      19,    20,    21,    22,    -1,    23,    24,    25,    26,    27,
      28,    29,    -1,    32,    23,    24,    25,    26,    -1,    28,
      29
  };

  /* STOS_[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
  private static final byte
  yystos_[] =
  {
         0,     3,    38,    35,     0,    10,    14,    17,    19,    20,
      21,    22,    32,    39,    41,    45,     6,     7,    30,    31,
      40,    43,    44,    45,    40,    42,    45,    42,    18,    45,
      18,    45,    35,     9,    40,    40,     4,     5,    11,    23,
      24,    25,    26,    27,    28,    29,    15,    36,    46,    40,
       8,    46,    46,    46,    40,    40,    40,    40,    40,    40,
      40,    46,    42,    41,    40,    40,    39,    39,    12,    13,
      16,    47,    46,    39,    13
  };

  /* TOKEN_NUMBER_[YYLEX-NUM] -- Internal symbol number corresponding
     to YYLEX-NUM.  */
  private static final short
  yytoken_number_[] =
  {
         0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,    59,    44
  };

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte
  yyr1_[] =
  {
         0,    37,    38,    39,    39,    40,    40,    40,    40,    40,
      40,    40,    40,    40,    40,    40,    40,    40,    40,    41,
      41,    41,    41,    41,    41,    41,    41,    41,    41,    41,
      42,    42,    43,    44,    45,    46,    47
  };

  /* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
  private static final byte
  yyr2_[] =
  {
         0,     2,     3,     1,     4,     1,     1,     1,     3,     3,
       3,     3,     3,     3,     3,     4,     4,     2,     3,     1,
       3,     2,     2,     2,     2,     2,     2,     6,    10,     6,
       1,     3,     1,     1,     1,     0,     0
  };

  /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
     First, the terminals, then, starting at \a yyntokens_, nonterminals.  */
  private static final String yytname_[] =
  {
    "$end", "error", "$undefined", "PROGRAM_KW", "AND_KW", "OR_KW",
  "NEG_KW", "LPAR_KW", "RPAR_KW", "ASSIGN_KW", "IF_KW", "THEN_KW",
  "ELSE_KW", "ENDIF_KW", "WHILE_KW", "DO_KW", "DONE_KW", "NOP_KW",
  "BOT_KW", "INL_KW", "INH_KW", "OUTL_KW", "OUTH_KW", "PLUS_KW",
  "MINUS_KW", "LT_KW", "LE_KW", "EQ_KW", "GT_KW", "GE_KW",
  "INTEGER_NUMBER", "BOOL_CONSTANT", "IDENTIFIER", "q", "p", "';'", "','",
  "$accept", "program", "clist", "exp", "c", "varlist", "b", "n", "x", "M",
  "N", null
  };

  /* YYRHS -- A `-1'-separated list of the rules' RHS.  */
  private static final byte yyrhs_[] =
  {
        38,     0,    -1,     3,    35,    39,    -1,    41,    -1,    39,
      35,    46,    41,    -1,    43,    -1,    44,    -1,    45,    -1,
      40,    27,    40,    -1,    40,    25,    40,    -1,    40,    26,
      40,    -1,    40,    29,    40,    -1,    40,    28,    40,    -1,
      40,    23,    40,    -1,    40,    24,    40,    -1,    40,     4,
      46,    40,    -1,    40,     5,    46,    40,    -1,     6,    40,
      -1,     7,    40,     8,    -1,    17,    -1,    45,     9,    40,
      -1,    19,    42,    -1,    20,    42,    -1,    21,    45,    -1,
      22,    45,    -1,    21,    18,    -1,    22,    18,    -1,    10,
      40,    11,    46,    39,    13,    -1,    10,    40,    11,    46,
      39,    12,    47,    46,    39,    13,    -1,    14,    40,    15,
      46,    39,    16,    -1,    45,    -1,    45,    36,    42,    -1,
      31,    -1,    30,    -1,    32,    -1,    -1,    -1
  };

  /* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
     YYRHS.  */
  private static final byte yyprhs_[] =
  {
         0,     0,     3,     7,     9,    14,    16,    18,    20,    24,
      28,    32,    36,    40,    44,    48,    53,    58,    61,    65,
      67,    71,    74,    77,    80,    83,    86,    89,    96,   107,
     114,   116,   120,   122,   124,   126,   127
  };

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] =
  {
         0,   106,   106,   148,   161,   180,   189,   197,   222,   234,
     246,   258,   270,   282,   294,   305,   316,   329,   340,   353,
     366,   415,   456,   496,   539,   580,   594,   607,   642,   683,
     726,   753,   782,   791,   800,   810,   815
  };

  // Report on the debug stream that the rule yyrule is going to be reduced.
  private void yy_reduce_print (int yyrule, YYStack yystack)
  {
    if (yydebug == 0)
      return;

    int yylno = yyrline_[yyrule];
    int yynrhs = yyr2_[yyrule];
    /* Print the symbols being reduced, and their result.  */
    yycdebug ("Reducing stack by rule " + (yyrule - 1)
	      + " (line " + yylno + "), ");

    /* The symbols being reduced.  */
    for (int yyi = 0; yyi < yynrhs; yyi++)
      yy_symbol_print ("   $" + (yyi + 1) + " =",
		       yyrhs_[yyprhs_[yyrule] + yyi],
		       ((yystack.valueAt (yynrhs-(yyi + 1)))));
  }

  /* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
  private static final byte yytranslate_table_[] =
  {
         0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,    36,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,    35,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34
  };

  private static final byte yytranslate_ (int t)
  {
    if (t >= 0 && t <= yyuser_token_number_max_)
      return yytranslate_table_[t];
    else
      return yyundef_token_;
  }

  private static final int yylast_ = 140;
  private static final int yynnts_ = 11;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 4;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 37;

  private static final int yyuser_token_number_max_ = 289;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */
/* Unqualified %code blocks.  */

/* Line 927 of lalr1.java  */
/* Line 17 of "YYParser.Y"  */


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



/* Line 927 of lalr1.java  */
/* Line 2043 of "YYParser.C"  */

}


/* Line 931 of lalr1.java  */
/* Line 820 of "YYParser.Y"  */


/*************************************** eval ************************************/
class eval {
	
	public String stmt="";
	public String nodeIdAndStmt="";
	
	public String cSourceCode = "";
	
	public HashSet<String> variables = new HashSet<String>();
	
	public Node node;
	public MyLinkedList list;

}

