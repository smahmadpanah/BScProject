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
/* Line 2 of "YYParser.y"  */
package wlrewriter;

  import java.io.*;
  import java.lang.*;
  import java.util.Vector; 


/**
 * A Bison parser, automatically generated from <tt>YYParser.y</tt>.
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
  public static final int ASSIGN_KW = 261;
  /** Token number, to be returned by the scanner.  */
  public static final int IF_KW = 262;
  /** Token number, to be returned by the scanner.  */
  public static final int THEN_KW = 263;
  /** Token number, to be returned by the scanner.  */
  public static final int ELSE_KW = 264;
  /** Token number, to be returned by the scanner.  */
  public static final int ENDIF_KW = 265;
  /** Token number, to be returned by the scanner.  */
  public static final int WHILE_KW = 266;
  /** Token number, to be returned by the scanner.  */
  public static final int DO_KW = 267;
  /** Token number, to be returned by the scanner.  */
  public static final int DONE_KW = 268;
  /** Token number, to be returned by the scanner.  */
  public static final int NOP_KW = 269;
  /** Token number, to be returned by the scanner.  */
  public static final int BOT_KW = 270;
  /** Token number, to be returned by the scanner.  */
  public static final int INL_KW = 271;
  /** Token number, to be returned by the scanner.  */
  public static final int INH_KW = 272;
  /** Token number, to be returned by the scanner.  */
  public static final int OUTL_KW = 273;
  /** Token number, to be returned by the scanner.  */
  public static final int OUTH_KW = 274;
  /** Token number, to be returned by the scanner.  */
  public static final int PLUS_KW = 275;
  /** Token number, to be returned by the scanner.  */
  public static final int MINUS_KW = 276;
  /** Token number, to be returned by the scanner.  */
  public static final int LT_KW = 277;
  /** Token number, to be returned by the scanner.  */
  public static final int LE_KW = 278;
  /** Token number, to be returned by the scanner.  */
  public static final int EQ_KW = 279;
  /** Token number, to be returned by the scanner.  */
  public static final int GT_KW = 280;
  /** Token number, to be returned by the scanner.  */
  public static final int GE_KW = 281;
  /** Token number, to be returned by the scanner.  */
  public static final int INTEGER_NUMBER = 282;
  /** Token number, to be returned by the scanner.  */
  public static final int BOOL_CONSTANT = 283;
  /** Token number, to be returned by the scanner.  */
  public static final int IDENTIFIER = 284;
  /** Token number, to be returned by the scanner.  */
  public static final int p = 285;



  

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
/* Line 279 of "YYParser.y"  */
    {
	writer.print("\t program -> PROGRAM_KW IDENTIFIER ';' clist \n") ;
	writer.print("###Hooray! - Your program is syntactically correct###");
	System.out.println("###Hooray! - Your program is syntactically correct###");
	};
  break;
    

  case 3:
  if (yyn == 3)
    
/* Line 351 of lalr1.java  */
/* Line 286 of "YYParser.y"  */
    {
	writer.print("\t clist -> c \n") ;
	};
  break;
    

  case 4:
  if (yyn == 4)
    
/* Line 351 of lalr1.java  */
/* Line 290 of "YYParser.y"  */
    {
	writer.print("\t clist -> clist ; M c \n") ;
	};
  break;
    

  case 5:
  if (yyn == 5)
    
/* Line 351 of lalr1.java  */
/* Line 295 of "YYParser.y"  */
    {
		writer.print("\t exp -> b \n") ;
	};
  break;
    

  case 6:
  if (yyn == 6)
    
/* Line 351 of lalr1.java  */
/* Line 299 of "YYParser.y"  */
    {
		writer.print("\t exp -> n \n") ;
	};
  break;
    

  case 7:
  if (yyn == 7)
    
/* Line 351 of lalr1.java  */
/* Line 303 of "YYParser.y"  */
    {
		writer.print("\t exp -> x \n") ;
	};
  break;
    

  case 8:
  if (yyn == 8)
    
/* Line 351 of lalr1.java  */
/* Line 307 of "YYParser.y"  */
    {
		writer.print("\t exp -> exp EQ_KW exp \n") ;
	};
  break;
    

  case 9:
  if (yyn == 9)
    
/* Line 351 of lalr1.java  */
/* Line 311 of "YYParser.y"  */
    {
		writer.print("\t exp -> exp LT_KW exp \n") ;
	};
  break;
    

  case 10:
  if (yyn == 10)
    
/* Line 351 of lalr1.java  */
/* Line 315 of "YYParser.y"  */
    {
		writer.print("\t exp -> exp LE_KW exp \n") ;
	};
  break;
    

  case 11:
  if (yyn == 11)
    
/* Line 351 of lalr1.java  */
/* Line 319 of "YYParser.y"  */
    {
		writer.print("\t exp -> exp GE_KW exp \n") ;
	};
  break;
    

  case 12:
  if (yyn == 12)
    
/* Line 351 of lalr1.java  */
/* Line 323 of "YYParser.y"  */
    {
		writer.print("\t exp -> exp GT_KW exp \n") ;
	};
  break;
    

  case 13:
  if (yyn == 13)
    
/* Line 351 of lalr1.java  */
/* Line 327 of "YYParser.y"  */
    {
		writer.print("\t exp -> exp PLUS_KW exp \n") ;
	};
  break;
    

  case 14:
  if (yyn == 14)
    
/* Line 351 of lalr1.java  */
/* Line 331 of "YYParser.y"  */
    {
		writer.print("\t exp -> exp MINUS_KW exp \n") ;
	};
  break;
    

  case 15:
  if (yyn == 15)
    
/* Line 351 of lalr1.java  */
/* Line 335 of "YYParser.y"  */
    {
		writer.print("\t exp -> exp AND_KW exp \n") ;
	};
  break;
    

  case 16:
  if (yyn == 16)
    
/* Line 351 of lalr1.java  */
/* Line 339 of "YYParser.y"  */
    {
		writer.print("\t exp -> exp OR_KW exp \n") ;
	};
  break;
    

  case 17:
  if (yyn == 17)
    
/* Line 351 of lalr1.java  */
/* Line 344 of "YYParser.y"  */
    {
		writer.print("\t c -> NOP_KW \n") ;
	};
  break;
    

  case 18:
  if (yyn == 18)
    
/* Line 351 of lalr1.java  */
/* Line 348 of "YYParser.y"  */
    {
		writer.print("\t c -> x ASSIGN_KW exp \n") ;
	};
  break;
    

  case 19:
  if (yyn == 19)
    
/* Line 351 of lalr1.java  */
/* Line 352 of "YYParser.y"  */
    {
		writer.print("\t c -> INL_KW varlist \n") ;
	};
  break;
    

  case 20:
  if (yyn == 20)
    
/* Line 351 of lalr1.java  */
/* Line 356 of "YYParser.y"  */
    {
		writer.print("\t c -> INH_KW varlist \n") ;
	};
  break;
    

  case 21:
  if (yyn == 21)
    
/* Line 351 of lalr1.java  */
/* Line 360 of "YYParser.y"  */
    {
		writer.print("\t c -> OUTL_KW x \n") ;
	};
  break;
    

  case 22:
  if (yyn == 22)
    
/* Line 351 of lalr1.java  */
/* Line 364 of "YYParser.y"  */
    {
		writer.print("\t c -> OUTH_KW x \n") ;
	};
  break;
    

  case 23:
  if (yyn == 23)
    
/* Line 351 of lalr1.java  */
/* Line 368 of "YYParser.y"  */
    {
		writer.print("\t c -> OUTL_KW BOT_KW \n") ;
	};
  break;
    

  case 24:
  if (yyn == 24)
    
/* Line 351 of lalr1.java  */
/* Line 372 of "YYParser.y"  */
    {
		writer.print("\t c -> OUTH_KW BOT_KW \n") ;
	};
  break;
    

  case 25:
  if (yyn == 25)
    
/* Line 351 of lalr1.java  */
/* Line 376 of "YYParser.y"  */
    {
		writer.print("\t c -> IF_KW exp THEN_KW M clist ENDIF_KW \n") ;
	};
  break;
    

  case 26:
  if (yyn == 26)
    
/* Line 351 of lalr1.java  */
/* Line 380 of "YYParser.y"  */
    {
		writer.print("\t c -> IF_KW exp THEN_KW M clist ELSE_KW N M clist ENDIF_KW \n") ;
	};
  break;
    

  case 27:
  if (yyn == 27)
    
/* Line 351 of lalr1.java  */
/* Line 384 of "YYParser.y"  */
    {
		writer.print("\t c -> WHILE_KW exp DO_KW M clist DONE_KW \n") ;
	};
  break;
    

  case 28:
  if (yyn == 28)
    
/* Line 351 of lalr1.java  */
/* Line 389 of "YYParser.y"  */
    {
		writer.print("\t varlist -> x \n") ;
	};
  break;
    

  case 29:
  if (yyn == 29)
    
/* Line 351 of lalr1.java  */
/* Line 393 of "YYParser.y"  */
    {
		writer.print("\t varlist -> x , varlist \n") ;
	};
  break;
    

  case 30:
  if (yyn == 30)
    
/* Line 351 of lalr1.java  */
/* Line 398 of "YYParser.y"  */
    {
		writer.print("\t b -> BOOL_CONSTANT \n") ;
	};
  break;
    

  case 31:
  if (yyn == 31)
    
/* Line 351 of lalr1.java  */
/* Line 403 of "YYParser.y"  */
    {
		writer.print("\t n -> INTEGER_NUMBER \n") ;
	};
  break;
    

  case 32:
  if (yyn == 32)
    
/* Line 351 of lalr1.java  */
/* Line 408 of "YYParser.y"  */
    {
		writer.print("\t x -> IDENTIFIER \n") ;
	};
  break;
    

  case 33:
  if (yyn == 33)
    
/* Line 351 of lalr1.java  */
/* Line 413 of "YYParser.y"  */
    {
	
	};
  break;
    

  case 34:
  if (yyn == 34)
    
/* Line 351 of lalr1.java  */
/* Line 418 of "YYParser.y"  */
    {
	
	};
  break;
    



/* Line 351 of lalr1.java  */
/* Line 703 of "YYParser.java"  */
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
  private static final byte yypact_ninf_ = -25;
  private static final byte yypact_[] =
  {
         5,   -17,    13,   -13,   -25,    74,    19,    19,   -25,    -9,
      -9,    -1,     0,   -25,    20,   -25,    18,   -25,   -25,    45,
     -25,   -25,   -25,    52,   -25,    22,   -25,   -25,   -25,   -25,
     -25,   -25,    19,   -25,   -25,   -25,    19,    19,    19,    19,
      19,    19,    19,   -25,    -9,    74,    75,    19,    19,    74,
     -25,   -25,   100,     1,    93,   100,     1,    74,   -25,   -25,
      86,    86,     7,    14,   -25,   -25,   -25,   -25,    74,    -8,
     -25
  };

  /* YYDEFACT[S] -- default reduction number in state S.  Performed when
     YYTABLE doesn't specify something else to do.  Zero means the
     default is an error.  */
  private static final byte yydefact_[] =
  {
         0,     0,     0,     0,     1,     0,     0,     0,    17,     0,
       0,     0,     0,    32,     2,     3,     0,    31,    30,     0,
       5,     6,     7,     0,    19,    28,    20,    23,    21,    24,
      22,    33,     0,    33,    33,    33,     0,     0,     0,     0,
       0,     0,     0,    33,     0,     0,    18,     0,     0,     0,
      13,    14,     9,    10,     8,    12,    11,     0,    29,     4,
      15,    16,     0,     0,    34,    25,    27,    33,     0,     0,
      26
  };

  /* YYPGOTO[NTERM-NUM].  */
  private static final byte yypgoto_[] =
  {
       -25,   -25,    37,    -6,   -20,    -7,   -25,   -25,    -5,   -24,
     -25
  };

  /* YYDEFGOTO[NTERM-NUM].  */
  private static final byte
  yydefgoto_[] =
  {
        -1,     2,    14,    19,    15,    24,    20,    21,    22,    45,
      67
  };

  /* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule which
     number is the opposite.  If YYTABLE_NINF_, syntax error.  */
  private static final byte yytable_ninf_ = -1;
  private static final byte
  yytable_[] =
  {
        16,    23,    70,    26,    25,    25,    28,    30,     1,    47,
      48,    49,     3,     4,    27,    29,    64,    65,     5,    57,
      13,    36,    37,    31,    32,    59,    46,    66,    13,    13,
      50,    51,    52,    53,    54,    55,    56,    58,    31,    25,
      16,    60,    61,    68,    16,    31,    17,    18,    13,    33,
      34,    31,    16,    35,    44,     0,    33,    34,     0,     0,
       0,     0,     0,    16,    43,    36,    37,    38,    39,    40,
      41,    42,    36,    37,    38,    39,    40,    41,    42,    33,
      34,     6,     0,     0,     0,     7,    62,     0,     8,     0,
       9,    10,    11,    12,    63,    36,    37,    38,    39,    40,
      41,    42,     0,    13,     0,    69,    36,    37,    38,    39,
      40,    41,    42,    36,    37,    38,    39,     0,    41,    42,
      36,    37,     0,    39,     0,     0,    42
  };

  /* YYCHECK.  */
  private static final byte
  yycheck_[] =
  {
         5,     7,    10,    10,     9,    10,    11,    12,     3,    33,
      34,    35,    29,     0,    15,    15,     9,    10,    31,    43,
      29,    20,    21,    31,     6,    45,    32,    13,    29,    29,
      36,    37,    38,    39,    40,    41,    42,    44,    31,    44,
      45,    47,    48,    67,    49,    31,    27,    28,    29,     4,
       5,    31,    57,     8,    32,    -1,     4,     5,    -1,    -1,
      -1,    -1,    -1,    68,    12,    20,    21,    22,    23,    24,
      25,    26,    20,    21,    22,    23,    24,    25,    26,     4,
       5,     7,    -1,    -1,    -1,    11,    49,    -1,    14,    -1,
      16,    17,    18,    19,    57,    20,    21,    22,    23,    24,
      25,    26,    -1,    29,    -1,    68,    20,    21,    22,    23,
      24,    25,    26,    20,    21,    22,    23,    -1,    25,    26,
      20,    21,    -1,    23,    -1,    -1,    26
  };

  /* STOS_[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
  private static final byte
  yystos_[] =
  {
         0,     3,    34,    29,     0,    31,     7,    11,    14,    16,
      17,    18,    19,    29,    35,    37,    41,    27,    28,    36,
      39,    40,    41,    36,    38,    41,    38,    15,    41,    15,
      41,    31,     6,     4,     5,     8,    20,    21,    22,    23,
      24,    25,    26,    12,    32,    42,    36,    42,    42,    42,
      36,    36,    36,    36,    36,    36,    36,    42,    38,    37,
      36,    36,    35,    35,     9,    10,    13,    43,    42,    35,
      10
  };

  /* TOKEN_NUMBER_[YYLEX-NUM] -- Internal symbol number corresponding
     to YYLEX-NUM.  */
  private static final short
  yytoken_number_[] =
  {
         0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,    59,    44
  };

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte
  yyr1_[] =
  {
         0,    33,    34,    35,    35,    36,    36,    36,    36,    36,
      36,    36,    36,    36,    36,    36,    36,    37,    37,    37,
      37,    37,    37,    37,    37,    37,    37,    37,    38,    38,
      39,    40,    41,    42,    43
  };

  /* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
  private static final byte
  yyr2_[] =
  {
         0,     2,     4,     1,     4,     1,     1,     1,     3,     3,
       3,     3,     3,     3,     3,     4,     4,     1,     3,     2,
       2,     2,     2,     2,     2,     6,    10,     6,     1,     3,
       1,     1,     1,     0,     0
  };

  /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
     First, the terminals, then, starting at \a yyntokens_, nonterminals.  */
  private static final String yytname_[] =
  {
    "$end", "error", "$undefined", "PROGRAM_KW", "AND_KW", "OR_KW",
  "ASSIGN_KW", "IF_KW", "THEN_KW", "ELSE_KW", "ENDIF_KW", "WHILE_KW",
  "DO_KW", "DONE_KW", "NOP_KW", "BOT_KW", "INL_KW", "INH_KW", "OUTL_KW",
  "OUTH_KW", "PLUS_KW", "MINUS_KW", "LT_KW", "LE_KW", "EQ_KW", "GT_KW",
  "GE_KW", "INTEGER_NUMBER", "BOOL_CONSTANT", "IDENTIFIER", "p", "';'",
  "','", "$accept", "program", "clist", "exp", "c", "varlist", "b", "n",
  "x", "M", "N", null
  };

  /* YYRHS -- A `-1'-separated list of the rules' RHS.  */
  private static final byte yyrhs_[] =
  {
        34,     0,    -1,     3,    29,    31,    35,    -1,    37,    -1,
      35,    31,    42,    37,    -1,    39,    -1,    40,    -1,    41,
      -1,    36,    24,    36,    -1,    36,    22,    36,    -1,    36,
      23,    36,    -1,    36,    26,    36,    -1,    36,    25,    36,
      -1,    36,    20,    36,    -1,    36,    21,    36,    -1,    36,
       4,    42,    36,    -1,    36,     5,    42,    36,    -1,    14,
      -1,    41,     6,    36,    -1,    16,    38,    -1,    17,    38,
      -1,    18,    41,    -1,    19,    41,    -1,    18,    15,    -1,
      19,    15,    -1,     7,    36,     8,    42,    35,    10,    -1,
       7,    36,     8,    42,    35,     9,    43,    42,    35,    10,
      -1,    11,    36,    12,    42,    35,    13,    -1,    41,    -1,
      41,    32,    38,    -1,    28,    -1,    27,    -1,    29,    -1,
      -1,    -1
  };

  /* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
     YYRHS.  */
  private static final byte yyprhs_[] =
  {
         0,     0,     3,     8,    10,    15,    17,    19,    21,    25,
      29,    33,    37,    41,    45,    49,    54,    59,    61,    65,
      68,    71,    74,    77,    80,    83,    90,   101,   108,   110,
     114,   116,   118,   120,   121
  };

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] =
  {
         0,   278,   278,   285,   289,   294,   298,   302,   306,   310,
     314,   318,   322,   326,   330,   334,   338,   343,   347,   351,
     355,   359,   363,   367,   371,   375,   379,   383,   388,   392,
     397,   402,   407,   413,   418
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
       2,     2,     2,     2,    32,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,    31,
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
      25,    26,    27,    28,    29,    30
  };

  private static final byte yytranslate_ (int t)
  {
    if (t >= 0 && t <= yyuser_token_number_max_)
      return yytranslate_table_[t];
    else
      return yyundef_token_;
  }

  private static final int yylast_ = 126;
  private static final int yynnts_ = 11;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 4;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 33;

  private static final int yyuser_token_number_max_ = 285;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */
/* Unqualified %code blocks.  */

/* Line 927 of lalr1.java  */
/* Line 16 of "YYParser.y"  */


	/*************************************** MAIN *****************************************/
static PrintStream writer, output;
static int IntValue;
Vector<Integer> tempIntValue = new Vector<Integer>();
static double DoubleValue;
static int BoolValue;
static String IDvalue;
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
	return;
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



/* Line 927 of lalr1.java  */
/* Line 1619 of "YYParser.java"  */

}


/* Line 931 of lalr1.java  */
/* Line 423 of "YYParser.y"  */

/*************************************** eval ************************************/
class eval {

	public int quad;
	public int isBoolean;

	public String place="";
	public String name="";
	public String code="";
	public String type="";
	public String ids="";

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

