1) go to "\jflex-1.6.0\lib" and run "jflex-1.6.0.jar" with "lexer.l"  (0 error, 0 warning)
2) go to "\jflex-1.6.0\bin" and run "cmd" in this folder
3) run this script: "bison -L JAVA YYParser.y"
4) Copy "Yylex.java" and "YYParser.java" to Netbeans project "src" folder (drag and drop to Netbeans)
5) To debug the error in "YYParser.java" (as Netbeans will show), open "Yylex.java" and add these following instrucions to it, around line 250:
	"
	public int getYyline() {
        return (yyline+1);
    }
	"
	and save it.
6) Open "YYParser.java" and put the following piece of code in near line 236:
	private PSNIRewriter psni;

	"
    /**
     * Instantiates the Bison-generated parser.
     *
     * @param yylexer The scanner that will supply tokens to the parser.
     */
    public YYParser(Lexer yylexer) {
        this.yylexer = yylexer;

    }
	
	// a new constructor for PSNI
    public YYParser(Lexer yylexer, PSNIRewriter p) {
        this.yylexer = yylexer;
        psni = p;
    }
	"
	
7) Now, you can run the YYParser.java correctly.
8) After run the program, enter your source code file path as the input. It will make three .png file for Control Dependence Graph, Data Dependence Graph and at last, Program Dependence Graph.
9) Check out filename-PINI.wl, the rewrited source code of the input file.

Enjoy it! :)