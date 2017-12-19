//java -jar JFlex.jar D:\Dropbox\Retroscope++\retroscope-lib\RetroLogServer\src\main\java\retroscope\datamodel\parser\datamodel.flex
package retroscope.datamodel.parser;
%%

%class DataScanner
%implements dataTokens
%unicode
%int
%debug

%{
int token;
Object semanticValue;
int line(){return yyline;}
int column(){return yycolumn;}
%}

LineTerminator=\r|\n|\r\n
Space=[ \t\f]

Comment={TraditionalComment}|{EndOfLineComment}

TraditionalComment="/*" ~"*/"
EndOfLineComment="#" ~{LineTerminator}

Identifier=[:jletter:] [:jletterdigit:]*
IntegerLiteral=0|[1-9][0-9]*

%%

"null"  			{return token=NIL;}

{IntegerLiteral}	{semanticValue=Long.parseLong(yytext());return token=INTEGER_LITERAL;}
([0-9]+|([0-9]*\.[0-9]+)([eE][-+]?[0-9]+)?) {semanticValue=Double.parseDouble(yytext());return FLOAT_LITERAL;}

L?\"(\.|[^\"])*\"	{semanticValue=yytext(); return STRING_LITERAL;}

{Identifier}		{semanticValue=yytext();return token=IDENTIFIER;}

\:           	  	{return token=':';}
\;           	  	{return token=';';}
\<          	   	{return token='<';}
\>					{return token='>';}
\(      	        {return token='(';}
\)       	        {return token=')';}
\{			        {return token='{';}
\}			        {return token='}';}
\[			        {return token='[';}
\]		            {return token=']';}
\,		            {return token=',';}
\-		            {return token='-';}
\|		            {return token='|';}
"..."			    {return token=APPEND;}
"--"			    {return token=REMOVE;}



{Space}			    {}
{Comment}		    {}
{LineTerminator}	{}
<<EOF>>			    {return token=ENDINPUT;}
.			        {throw new Error("unexpected"+yytext());}


