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
6) Now, you can run the YYParser.java correctly.