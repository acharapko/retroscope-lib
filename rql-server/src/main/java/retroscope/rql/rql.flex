//java -jar JFlex.jar D:\Dropbox\ubuntu_vms_share\retroscope-lib\rql-server\src\main\java\retroscope\rql\rql.flex
package retroscope.rql;
%%

%class Scanner
%implements mTokens
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


//keywords
"EXPLAIN"			{return token=EXPLAIN;}
"SELECT"			{return token=SELECT;}
"FROM"  			{return token=FROM;}

"WHEN"  			{return token=WHEN;}
"AT"  		    	{return token=AT;}
"TIME"              {return token=TIME;}
"TO"                {return token=TO;}
"ON"                {return token=ON;}
"LINK"              {return token=LINK;}
"BETWEEN"           {return token=BETWEEN;}
"AFTER"             {return token=AFTER;}
"BEFORE"            {return token=BEFORE;}
"NODES"             {return token=NODES;}
"ALL_NODES"         {return token=ALL_NODES;}
"TRUE"              {return token=TRUE;}
"FALSE"             {return token=FALSE;}


//logical operators
"AND"	    		{return token=AND;}
"OR"			    {return token=OR;}

//functions
"SUM"			    {return token=SUM;}
"COUNT"		        {return token=COUNT;}


{IntegerLiteral}	{semanticValue=Integer.parseInt(yytext());return token=INTEGER_LITERAL;}
([0-9]+|([0-9]*\.[0-9]+)([eE][-+]?[0-9]+)?) {semanticValue=Double.parseDouble(yytext());return FLOAT_LITERAL;}

L?\"(\.|[^\"])*\"	{semanticValue=yytext(); return STRING_LITERAL;}

{Identifier}		{semanticValue=yytext();return token=IDENTIFIER;}

//operators
\+         	        {return token='+';}
\%         	        {return token='%';}
-          	        {return token='-';}
\*           	  	{return token='*';}
\/          	   	{return token='/';}
\^					{return token='^';}
\(      	        {return token='(';}
\)       	        {return token=')';}
\{			        {return token='{';}
\}			        {return token='}';}
\[			        {return token='[';}
\]		            {return token=']';}
\!			        {return token='!';}
"&&"			    {return token=AND;}
"||"			    {return token=OR;}
"AND"	    		{return token=AND;}
"OR"			    {return token=OR;}
"!="			    {return token=NEQ;}
"=="			    {return token=EQ;}
"<="			    {return token=LEQ;}
">="			    {return token=GEQ;}
=			        {return token='=';}
\<			        {return token='<';}
\>			        {return token='>';}
\|			        {return token='|';}
\.			        {return token='.';}
:			        {return token=':';}
;               	{return token=';';}
,			        {return token=',';}



{Space}			    {}
{Comment}		    {}
{LineTerminator}	{}
<<EOF>>			    {return token=ENDINPUT;}
.			        {throw new Error("unexpected"+yytext());}


