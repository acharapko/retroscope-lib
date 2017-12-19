// Output created by jacc on Sun Dec 10 18:12:36 EST 2017


package retroscope.rql.parser;

import retroscope.rql.syntaxtree.*;
import retroscope.rql.syntaxtree.expression.EvalExpression;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.FuncCall;
import retroscope.rql.syntaxtree.expression.arithmetic.*;
import retroscope.rql.syntaxtree.expression.assign.AssignExpression;
import retroscope.rql.syntaxtree.expression.assign.DeclareExpression;
import retroscope.rql.syntaxtree.expression.assign.OutExpression;
import retroscope.rql.syntaxtree.expression.compare.*;
import retroscope.rql.syntaxtree.expression.condition.Condition;
import retroscope.rql.syntaxtree.expression.condition.Ternary;
import retroscope.rql.syntaxtree.expression.literals.*;
import retroscope.rql.syntaxtree.expression.logical.And;
import retroscope.rql.syntaxtree.expression.logical.NotExpression;
import retroscope.rql.syntaxtree.expression.logical.Or;
import retroscope.rql.syntaxtree.expression.sets.*;
import retroscope.rql.syntaxtree.expression.stackedexpression.Exists;
import retroscope.rql.syntaxtree.expression.stackedexpression.ForAll;
import retroscope.rql.syntaxtree.expression.stackedexpression.ForEach;

import java.util.ArrayList;

public class rqlParser implements mTokens {
    private int yyss = 100;
    private int yytok;
    private int yysp = 0;
    private int[] yyst;
    protected int yyerrno = (-1);
    private Object[] yysv;
    private Object yyrv;

    public boolean parse() {
        int yyn = 0;
        yysp = 0;
        yyst = new int[yyss];
        yysv = new Object[yyss];
        yytok = (lexer.token
                 );
    loop:
        for (;;) {
            switch (yyn) {
                case 0:
                    yyst[yysp] = 0;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 163:
                    yyn = yys0();
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 164:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = 326;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 2:
                    yyst[yysp] = 2;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 165:
                    yyn = yys2();
                    continue;

                case 3:
                    yyst[yysp] = 3;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 166:
                    yyn = yys3();
                    continue;

                case 4:
                    yyst[yysp] = 4;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 167:
                    yyn = yys4();
                    continue;

                case 5:
                    yyst[yysp] = 5;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 168:
                    yyn = yys5();
                    continue;

                case 6:
                    yyst[yysp] = 6;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 169:
                    yyn = yys6();
                    continue;

                case 7:
                    yyst[yysp] = 7;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 170:
                    yyn = yys7();
                    continue;

                case 8:
                    yyst[yysp] = 8;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 171:
                    switch (yytok) {
                        case ';':
                            yyn = 55;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 9:
                    yyst[yysp] = 9;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 172:
                    yyn = yys9();
                    continue;

                case 10:
                    yyst[yysp] = 10;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 173:
                    yyn = yys10();
                    continue;

                case 11:
                    yyst[yysp] = 11;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 174:
                    yyn = yys11();
                    continue;

                case 12:
                    yyst[yysp] = 12;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 175:
                    yyn = yys12();
                    continue;

                case 13:
                    yyst[yysp] = 13;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 176:
                    yyn = yys13();
                    continue;

                case 14:
                    yyst[yysp] = 14;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 177:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 57;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 15:
                    yyst[yysp] = 15;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 178:
                    switch (yytok) {
                        case SELECT:
                            yyn = 24;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 16:
                    yyst[yysp] = 16;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 179:
                    yyn = yys16();
                    continue;

                case 17:
                    yyst[yysp] = 17;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 180:
                    yyn = yys17();
                    continue;

                case 18:
                    yyst[yysp] = 18;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 181:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 57;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 19:
                    yyst[yysp] = 19;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 182:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 57;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 20:
                    yyst[yysp] = 20;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 183:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 61;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 21:
                    yyst[yysp] = 21;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 184:
                    yyn = yys21();
                    continue;

                case 22:
                    yyst[yysp] = 22;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 185:
                    yyn = yys22();
                    continue;

                case 23:
                    yyst[yysp] = 23;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 186:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 67;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 24:
                    yyst[yysp] = 24;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 187:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 70;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 25:
                    yyst[yysp] = 25;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 188:
                    yyn = yys25();
                    continue;

                case 26:
                    yyst[yysp] = 26;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 189:
                    yyn = yys26();
                    continue;

                case 27:
                    yyst[yysp] = 27;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 190:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 71;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 28:
                    yyst[yysp] = 28;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 191:
                    yyn = yys28();
                    continue;

                case 29:
                    yyst[yysp] = 29;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 192:
                    yyn = yys29();
                    continue;

                case 30:
                    yyst[yysp] = 30;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 193:
                    yyn = yys30();
                    continue;

                case 31:
                    yyst[yysp] = 31;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 194:
                    yyn = yys31();
                    continue;

                case 32:
                    yyst[yysp] = 32;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 195:
                    yyn = yys32();
                    continue;

                case 33:
                    yyst[yysp] = 33;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 196:
                    yyn = yys33();
                    continue;

                case 34:
                    yyst[yysp] = 34;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 197:
                    yyn = yys34();
                    continue;

                case 35:
                    yyst[yysp] = 35;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 198:
                    yyn = yys35();
                    continue;

                case 36:
                    yyst[yysp] = 36;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 199:
                    yyn = yys36();
                    continue;

                case 37:
                    yyst[yysp] = 37;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 200:
                    yyn = yys37();
                    continue;

                case 38:
                    yyst[yysp] = 38;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 201:
                    yyn = yys38();
                    continue;

                case 39:
                    yyst[yysp] = 39;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 202:
                    yyn = yys39();
                    continue;

                case 40:
                    yyst[yysp] = 40;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 203:
                    yyn = yys40();
                    continue;

                case 41:
                    yyst[yysp] = 41;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 204:
                    yyn = yys41();
                    continue;

                case 42:
                    yyst[yysp] = 42;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 205:
                    yyn = yys42();
                    continue;

                case 43:
                    yyst[yysp] = 43;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 206:
                    yyn = yys43();
                    continue;

                case 44:
                    yyst[yysp] = 44;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 207:
                    yyn = yys44();
                    continue;

                case 45:
                    yyst[yysp] = 45;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 208:
                    yyn = yys45();
                    continue;

                case 46:
                    yyst[yysp] = 46;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 209:
                    yyn = yys46();
                    continue;

                case 47:
                    yyst[yysp] = 47;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 210:
                    yyn = yys47();
                    continue;

                case 48:
                    yyst[yysp] = 48;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 211:
                    yyn = yys48();
                    continue;

                case 49:
                    yyst[yysp] = 49;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 212:
                    yyn = yys49();
                    continue;

                case 50:
                    yyst[yysp] = 50;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 213:
                    yyn = yys50();
                    continue;

                case 51:
                    yyst[yysp] = 51;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 214:
                    yyn = yys51();
                    continue;

                case 52:
                    yyst[yysp] = 52;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 215:
                    switch (yytok) {
                        case '(':
                            yyn = 97;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 53:
                    yyst[yysp] = 53;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 216:
                    yyn = yys53();
                    continue;

                case 54:
                    yyst[yysp] = 54;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 217:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr1();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 55:
                    yyst[yysp] = 55;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 218:
                    yyn = yys55();
                    continue;

                case 56:
                    yyst[yysp] = 56;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 219:
                    switch (yytok) {
                        case IN:
                            yyn = 99;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 57:
                    yyst[yysp] = 57;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 220:
                    yyn = yys57();
                    continue;

                case 58:
                    yyst[yysp] = 58;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 221:
                    switch (yytok) {
                        case ';':
                            yyn = 101;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 59:
                    yyst[yysp] = 59;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 222:
                    switch (yytok) {
                        case IN:
                            yyn = 102;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 60:
                    yyst[yysp] = 60;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 223:
                    switch (yytok) {
                        case IN:
                            yyn = 103;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 61:
                    yyst[yysp] = 61;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 224:
                    yyn = yys61();
                    continue;

                case 62:
                    yyst[yysp] = 62;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 225:
                    yyn = yys62();
                    continue;

                case 63:
                    yyst[yysp] = 63;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 226:
                    yyn = yys63();
                    continue;

                case 64:
                    yyst[yysp] = 64;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 227:
                    yyn = yys64();
                    continue;

                case 65:
                    yyst[yysp] = 65;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 228:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 107;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 66:
                    yyst[yysp] = 66;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 229:
                    switch (yytok) {
                        case '.':
                            yyn = 108;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 67:
                    yyst[yysp] = 67;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 230:
                    yyn = yys67();
                    continue;

                case 68:
                    yyst[yysp] = 68;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 231:
                    switch (yytok) {
                        case ',':
                            yyn = 109;
                            continue;
                        case FROM:
                            yyn = yyr20();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 69:
                    yyst[yysp] = 69;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 232:
                    switch (yytok) {
                        case FROM:
                            yyn = 110;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 70:
                    yyst[yysp] = 70;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 233:
                    switch (yytok) {
                        case '.':
                            yyn = 111;
                            continue;
                        case FROM:
                        case ',':
                            yyn = yyr21();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 71:
                    yyst[yysp] = 71;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 234:
                    yyn = yys71();
                    continue;

                case 72:
                    yyst[yysp] = 72;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 235:
                    yyn = yys72();
                    continue;

                case 73:
                    yyst[yysp] = 73;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 236:
                    yyn = yys73();
                    continue;

                case 74:
                    yyst[yysp] = 74;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 237:
                    yyn = yys74();
                    continue;

                case 75:
                    yyst[yysp] = 75;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 238:
                    yyn = yys75();
                    continue;

                case 76:
                    yyst[yysp] = 76;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 239:
                    switch (yytok) {
                        case '}':
                            yyn = 114;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 77:
                    yyst[yysp] = 77;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 240:
                    yyn = yys77();
                    continue;

                case 78:
                    yyst[yysp] = 78;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 241:
                    yyn = yys78();
                    continue;

                case 79:
                    yyst[yysp] = 79;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 242:
                    yyn = yys79();
                    continue;

                case 80:
                    yyst[yysp] = 80;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 243:
                    yyn = yys80();
                    continue;

                case 81:
                    yyst[yysp] = 81;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 244:
                    yyn = yys81();
                    continue;

                case 82:
                    yyst[yysp] = 82;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 245:
                    yyn = yys82();
                    continue;

                case 83:
                    yyst[yysp] = 83;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 246:
                    yyn = yys83();
                    continue;

                case 84:
                    yyst[yysp] = 84;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 247:
                    yyn = yys84();
                    continue;

                case 85:
                    yyst[yysp] = 85;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 248:
                    yyn = yys85();
                    continue;

                case 86:
                    yyst[yysp] = 86;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 249:
                    yyn = yys86();
                    continue;

                case 87:
                    yyst[yysp] = 87;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 250:
                    yyn = yys87();
                    continue;

                case 88:
                    yyst[yysp] = 88;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 251:
                    yyn = yys88();
                    continue;

                case 89:
                    yyst[yysp] = 89;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 252:
                    yyn = yys89();
                    continue;

                case 90:
                    yyst[yysp] = 90;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 253:
                    yyn = yys90();
                    continue;

                case 91:
                    yyst[yysp] = 91;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 254:
                    yyn = yys91();
                    continue;

                case 92:
                    yyst[yysp] = 92;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 255:
                    yyn = yys92();
                    continue;

                case 93:
                    yyst[yysp] = 93;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 256:
                    yyn = yys93();
                    continue;

                case 94:
                    yyst[yysp] = 94;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 257:
                    yyn = yys94();
                    continue;

                case 95:
                    yyst[yysp] = 95;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 258:
                    yyn = yys95();
                    continue;

                case 96:
                    yyst[yysp] = 96;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 259:
                    yyn = yys96();
                    continue;

                case 97:
                    yyst[yysp] = 97;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 260:
                    yyn = yys97();
                    continue;

                case 98:
                    yyst[yysp] = 98;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 261:
                    yyn = yys98();
                    continue;

                case 99:
                    yyst[yysp] = 99;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 262:
                    yyn = yys99();
                    continue;

                case 100:
                    yyst[yysp] = 100;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 263:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 57;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 101:
                    yyst[yysp] = 101;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 264:
                    yyn = yys101();
                    continue;

                case 102:
                    yyst[yysp] = 102;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 265:
                    yyn = yys102();
                    continue;

                case 103:
                    yyst[yysp] = 103;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 266:
                    yyn = yys103();
                    continue;

                case 104:
                    yyst[yysp] = 104;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 267:
                    yyn = yys104();
                    continue;

                case 105:
                    yyst[yysp] = 105;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 268:
                    switch (yytok) {
                        case ')':
                            yyn = 120;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 106:
                    yyst[yysp] = 106;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 269:
                    yyn = yys106();
                    continue;

                case 107:
                    yyst[yysp] = 107;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 270:
                    yyn = yys107();
                    continue;

                case 108:
                    yyst[yysp] = 108;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 271:
                    switch (yytok) {
                        case INTEGER_LITERAL:
                            yyn = 121;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 109:
                    yyst[yysp] = 109;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 272:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 70;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 110:
                    yyst[yysp] = 110;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 273:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 57;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 111:
                    yyst[yysp] = 111;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 274:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 124;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 112:
                    yyst[yysp] = 112;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 275:
                    yyn = yys112();
                    continue;

                case 113:
                    yyst[yysp] = 113;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 276:
                    yyn = yys113();
                    continue;

                case 114:
                    yyst[yysp] = 114;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 277:
                    yyn = yys114();
                    continue;

                case 115:
                    yyst[yysp] = 115;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 278:
                    yyn = yys115();
                    continue;

                case 116:
                    yyst[yysp] = 116;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 279:
                    yyn = yys116();
                    continue;

                case 117:
                    yyst[yysp] = 117;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 280:
                    yyn = yys117();
                    continue;

                case 118:
                    yyst[yysp] = 118;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 281:
                    yyn = yys118();
                    continue;

                case 119:
                    yyst[yysp] = 119;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 282:
                    yyn = yys119();
                    continue;

                case 120:
                    yyst[yysp] = 120;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 283:
                    yyn = yys120();
                    continue;

                case 121:
                    yyst[yysp] = 121;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 284:
                    yyn = yys121();
                    continue;

                case 122:
                    yyst[yysp] = 122;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 285:
                    switch (yytok) {
                        case FROM:
                            yyn = yyr19();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 123:
                    yyst[yysp] = 123;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 286:
                    switch (yytok) {
                        case COMPUTE:
                            yyn = 132;
                            continue;
                        case ON:
                        case ';':
                        case WHEN:
                        case AT:
                            yyn = yyr9();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 124:
                    yyst[yysp] = 124;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 287:
                    switch (yytok) {
                        case FROM:
                        case ',':
                            yyn = yyr22();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 125:
                    yyst[yysp] = 125;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 288:
                    switch (yytok) {
                        case '}':
                        case ')':
                            yyn = yyr78();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 126:
                    yyst[yysp] = 126;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 289:
                    switch (yytok) {
                        case ':':
                            yyn = 133;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 127:
                    yyst[yysp] = 127;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 290:
                    switch (yytok) {
                        case '(':
                            yyn = 134;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 128:
                    yyst[yysp] = 128;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 291:
                    switch (yytok) {
                        case '(':
                            yyn = 135;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 129:
                    yyst[yysp] = 129;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 292:
                    switch (yytok) {
                        case '(':
                            yyn = 136;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 130:
                    yyst[yysp] = 130;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 293:
                    switch (yytok) {
                        case WHEN:
                            yyn = 138;
                            continue;
                        case ON:
                        case ';':
                        case AT:
                            yyn = yyr11();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 131:
                    yyst[yysp] = 131;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 294:
                    switch (yytok) {
                        case ';':
                            yyn = yyr6();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 132:
                    yyst[yysp] = 132;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 295:
                    yyn = yys132();
                    continue;

                case 133:
                    yyst[yysp] = 133;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 296:
                    switch (yytok) {
                        case '(':
                            yyn = 140;
                            continue;
                        case ';':
                            yyn = 141;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 134:
                    yyst[yysp] = 134;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 297:
                    yyn = yys134();
                    continue;

                case 135:
                    yyst[yysp] = 135;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 298:
                    yyn = yys135();
                    continue;

                case 136:
                    yyst[yysp] = 136;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 299:
                    yyn = yys136();
                    continue;

                case 137:
                    yyst[yysp] = 137;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 300:
                    switch (yytok) {
                        case AT:
                            yyn = 146;
                            continue;
                        case ON:
                        case ';':
                            yyn = yyr14();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 138:
                    yyst[yysp] = 138;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 301:
                    yyn = yys138();
                    continue;

                case 139:
                    yyst[yysp] = 139;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 302:
                    yyn = yys139();
                    continue;

                case 140:
                    yyst[yysp] = 140;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 303:
                    yyn = yys140();
                    continue;

                case 141:
                    yyst[yysp] = 141;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 304:
                    yyn = yys141();
                    continue;

                case 142:
                    yyst[yysp] = 142;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 305:
                    yyn = yys142();
                    continue;

                case 143:
                    yyst[yysp] = 143;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 306:
                    yyn = yys143();
                    continue;

                case 144:
                    yyst[yysp] = 144;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 307:
                    yyn = yys144();
                    continue;

                case 145:
                    yyst[yysp] = 145;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 308:
                    switch (yytok) {
                        case ON:
                            yyn = 153;
                            continue;
                        case ';':
                            yyn = yyr16();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 146:
                    yyst[yysp] = 146;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 309:
                    switch (yytok) {
                        case ALL_NODES:
                            yyn = 154;
                            continue;
                        case NODES:
                            yyn = 155;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 147:
                    yyst[yysp] = 147;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 310:
                    yyn = yys147();
                    continue;

                case 148:
                    yyst[yysp] = 148;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 311:
                    yyn = yys148();
                    continue;

                case 149:
                    yyst[yysp] = 149;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 312:
                    yyn = yys149();
                    continue;

                case 150:
                    yyst[yysp] = 150;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 313:
                    yyn = yys150();
                    continue;

                case 151:
                    yyst[yysp] = 151;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 314:
                    yyn = yys151();
                    continue;

                case 152:
                    yyst[yysp] = 152;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 315:
                    switch (yytok) {
                        case ';':
                            yyn = yyr7();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 153:
                    yyst[yysp] = 153;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 316:
                    switch (yytok) {
                        case TIME:
                            yyn = 157;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 154:
                    yyst[yysp] = 154;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 317:
                    switch (yytok) {
                        case ON:
                        case ';':
                            yyn = yyr13();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 155:
                    yyst[yysp] = 155;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 318:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 57;
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 156:
                    yyst[yysp] = 156;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 319:
                    yyn = yys156();
                    continue;

                case 157:
                    yyst[yysp] = 157;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 320:
                    yyn = yys157();
                    continue;

                case 158:
                    yyst[yysp] = 158;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 321:
                    switch (yytok) {
                        case ON:
                        case ';':
                            yyn = yyr12();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 159:
                    yyst[yysp] = 159;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 322:
                    yyn = yys159();
                    continue;

                case 160:
                    yyst[yysp] = 160;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 323:
                    switch (yytok) {
                        case ';':
                            yyn = yyr15();
                            continue;
                    }
                    yyn = 329;
                    continue;

                case 161:
                    yyst[yysp] = 161;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 324:
                    yyn = yys161();
                    continue;

                case 162:
                    yyst[yysp] = 162;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 325:
                    yyn = yys162();
                    continue;

                case 326:
                    return true;
                case 327:
                    yyerror("stack overflow");
                case 328:
                    return false;
                case 329:
                    yyerror("syntax error");
                    return false;
            }
        }
    }

    protected void yyexpand() {
        int[] newyyst = new int[2*yyst.length];
        Object[] newyysv = new Object[2*yyst.length];
        for (int i=0; i<yyst.length; i++) {
            newyyst[i] = yyst[i];
            newyysv[i] = yysv[i];
        }
        yyst = newyyst;
        yysv = newyysv;
    }

    private int yys0() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case EXPLAIN:
                return 15;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case SELECT:
                return 24;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
            case ENDINPUT:
                return yyr2();
        }
        return 329;
    }

    private int yys2() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr24();
        }
        return 329;
    }

    private int yys3() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr27();
        }
        return 329;
    }

    private int yys4() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr30();
        }
        return 329;
    }

    private int yys5() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case ';':
                return 48;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
        }
        return 329;
    }

    private int yys6() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr28();
        }
        return 329;
    }

    private int yys7() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case EXPLAIN:
                return 15;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case SELECT:
                return 24;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
            case ENDINPUT:
                return yyr2();
        }
        return 329;
    }

    private int yys9() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr26();
        }
        return 329;
    }

    private int yys10() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr49();
        }
        return 329;
    }

    private int yys11() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr25();
        }
        return 329;
    }

    private int yys12() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr29();
        }
        return 329;
    }

    private int yys13() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr45();
        }
        return 329;
    }

    private int yys16() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr51();
        }
        return 329;
    }

    private int yys17() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr47();
        }
        return 329;
    }

    private int yys21() {
        switch (yytok) {
            case SELECT:
            case ALL_NODES:
            case OUT:
            case STRING_LITERAL:
            case TIME:
            case IDENTIFIER:
            case SAME_NODE:
            case ']':
            case COMPUTE:
            case '[':
            case VAR:
            case INTEGER_LITERAL:
            case TRUE:
            case GLOBAL:
            case EXPLAIN:
            case ENDINPUT:
            case FALSE:
            case '{':
            case FOR_EACH:
            case FROM:
            case FOR_ALL:
            case NODES:
            case IS_PROPER_SUBSET:
            case FLOAT_LITERAL:
            case '!':
            case error:
            case EXISTS:
                return 329;
            case ASSIGN:
                return 63;
            case '(':
                return 64;
            case '.':
                return 65;
        }
        return yyr54();
    }

    private int yys22() {
        switch (yytok) {
            case STRING_LITERAL:
            case TIME:
            case OUT:
            case COMPUTE:
            case VAR:
            case IDENTIFIER:
            case SAME_NODE:
            case ']':
            case FOR_EACH:
            case '[':
            case IS_PROPER_SUBSET:
            case INTEGER_LITERAL:
            case TRUE:
            case GLOBAL:
            case EXPLAIN:
            case ENDINPUT:
            case FALSE:
            case '{':
            case FLOAT_LITERAL:
            case FROM:
            case FOR_ALL:
            case NODES:
            case ASSIGN:
            case '(':
            case '!':
            case error:
            case EXISTS:
            case ALL_NODES:
            case SELECT:
                return 329;
            case '.':
                return 66;
        }
        return yyr46();
    }

    private int yys25() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr48();
        }
        return 329;
    }

    private int yys26() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr50();
        }
        return 329;
    }

    private int yys28() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys29() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys30() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys31() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
            case '}':
                return yyr80();
        }
        return 329;
    }

    private int yys32() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys33() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys34() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys35() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys36() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys37() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys38() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys39() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys40() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys41() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys42() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys43() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys44() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys45() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys46() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys47() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys48() {
        switch (yytok) {
            case STRING_LITERAL:
            case SELECT:
            case GLOBAL:
            case '{':
            case OUT:
            case EXISTS:
            case FOR_EACH:
            case '-':
            case FOR_ALL:
            case '(':
            case FLOAT_LITERAL:
            case '!':
            case VAR:
            case INTEGER_LITERAL:
            case FALSE:
            case TRUE:
            case IDENTIFIER:
            case ENDINPUT:
            case EXPLAIN:
                return yyr5();
        }
        return 329;
    }

    private int yys49() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys50() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys51() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys53() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys55() {
        switch (yytok) {
            case STRING_LITERAL:
            case SELECT:
            case GLOBAL:
            case '{':
            case OUT:
            case EXISTS:
            case FOR_EACH:
            case '-':
            case FOR_ALL:
            case '(':
            case FLOAT_LITERAL:
            case '!':
            case VAR:
            case INTEGER_LITERAL:
            case FALSE:
            case TRUE:
            case IDENTIFIER:
            case ENDINPUT:
            case EXPLAIN:
                return yyr3();
        }
        return 329;
    }

    private int yys57() {
        switch (yytok) {
            case ',':
                return 100;
            case ON:
            case ';':
            case COMPUTE:
            case WHEN:
            case IN:
            case AT:
                return yyr74();
        }
        return 329;
    }

    private int yys61() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr41();
        }
        return 329;
    }

    private int yys62() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr52();
        }
        return 329;
    }

    private int yys63() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys64() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
            case ')':
                return yyr80();
        }
        return 329;
    }

    private int yys67() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr42();
        }
        return 329;
    }

    private int yys71() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr40();
        }
        return 329;
    }

    private int yys72() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '\\':
                return 53;
            case '}':
            case '?':
            case ON:
            case ';':
            case ':':
            case ',':
            case ')':
            case WHEN:
            case TO:
            case AT:
                return yyr55();
        }
        return 329;
    }

    private int yys73() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ')':
                return 112;
        }
        return 329;
    }

    private int yys74() {
        switch (yytok) {
            case '%':
                return 43;
            case '*':
                return 44;
            case '/':
                return 47;
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case '-':
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case ')':
            case '<':
            case IS_SUBSET:
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr58();
        }
        return 329;
    }

    private int yys75() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ',':
                return 113;
            case '}':
            case ')':
                return yyr79();
        }
        return 329;
    }

    private int yys77() {
        switch (yytok) {
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '\\':
                return 53;
            case '}':
            case '?':
            case ON:
            case ';':
            case ':':
            case ',':
            case ')':
            case WHEN:
            case TO:
            case AT:
            case AND:
                return yyr56();
        }
        return 329;
    }

    private int yys78() {
        switch (yytok) {
            case INTERSECT:
                return 36;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '\\':
                return 53;
            case '}':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case NEQ:
            case ',':
            case '<':
            case LEQ:
            case '>':
            case '?':
            case ')':
            case IS_SUBSET:
            case WHEN:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr65();
        }
        return 329;
    }

    private int yys79() {
        switch (yytok) {
            case INTERSECT:
                return 36;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '\\':
                return 53;
            case '}':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case NEQ:
            case ',':
            case '<':
            case LEQ:
            case '>':
            case '?':
            case ')':
            case IS_SUBSET:
            case WHEN:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr68();
        }
        return 329;
    }

    private int yys80() {
        switch (yytok) {
            case INTERSECT:
                return 36;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '\\':
                return 53;
            case '}':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case NEQ:
            case ',':
            case '<':
            case LEQ:
            case '>':
            case '?':
            case ')':
            case IS_SUBSET:
            case WHEN:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr37();
        }
        return 329;
    }

    private int yys81() {
        switch (yytok) {
            case '%':
                return 43;
            case '*':
                return 44;
            case '/':
                return 47;
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case '-':
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case ')':
            case '<':
            case IS_SUBSET:
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr36();
        }
        return 329;
    }

    private int yys82() {
        switch (yytok) {
            case INTERSECT:
                return 36;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '\\':
                return 53;
            case '}':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case NEQ:
            case ',':
            case '<':
            case LEQ:
            case '>':
            case '?':
            case ')':
            case IS_SUBSET:
            case WHEN:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr34();
        }
        return 329;
    }

    private int yys83() {
        switch (yytok) {
            case INTERSECT:
                return 36;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '\\':
                return 53;
            case '}':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case NEQ:
            case ',':
            case '<':
            case LEQ:
            case '>':
            case '?':
            case ')':
            case IS_SUBSET:
            case WHEN:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr67();
        }
        return 329;
    }

    private int yys84() {
        switch (yytok) {
            case INTERSECT:
                return 36;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '\\':
                return 53;
            case '}':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case NEQ:
            case ',':
            case '<':
            case LEQ:
            case '>':
            case '?':
            case ')':
            case IS_SUBSET:
            case WHEN:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr66();
        }
        return 329;
    }

    private int yys85() {
        switch (yytok) {
            case INTERSECT:
                return 36;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '\\':
                return 53;
            case '}':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case NEQ:
            case ',':
            case '<':
            case LEQ:
            case '>':
            case '?':
            case ')':
            case IS_SUBSET:
            case WHEN:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr38();
        }
        return 329;
    }

    private int yys86() {
        switch (yytok) {
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '\\':
                return 53;
            case '}':
            case '?':
            case OR:
            case ON:
            case ';':
            case ':':
            case ',':
            case ')':
            case WHEN:
            case TO:
            case AT:
            case AND:
                return yyr57();
        }
        return 329;
    }

    private int yys87() {
        switch (yytok) {
            case '%':
                return 43;
            case '*':
                return 44;
            case '/':
                return 47;
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case '-':
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case ')':
            case '<':
            case IS_SUBSET:
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr35();
        }
        return 329;
    }

    private int yys88() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr63();
        }
        return 329;
    }

    private int yys89() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr62();
        }
        return 329;
    }

    private int yys90() {
        switch (yytok) {
            case '%':
                return 43;
            case '*':
                return 44;
            case '/':
                return 47;
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case '-':
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case ')':
            case '<':
            case IS_SUBSET:
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr59();
        }
        return 329;
    }

    private int yys91() {
        switch (yytok) {
            case '%':
                return 43;
            case '*':
                return 44;
            case '/':
                return 47;
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case '-':
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case ')':
            case '<':
            case IS_SUBSET:
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr60();
        }
        return 329;
    }

    private int yys92() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr61();
        }
        return 329;
    }

    private int yys93() {
        switch (yytok) {
            case INTERSECT:
                return 36;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '\\':
                return 53;
            case '}':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case NEQ:
            case ',':
            case '<':
            case LEQ:
            case '>':
            case '?':
            case ')':
            case IS_SUBSET:
            case WHEN:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr69();
        }
        return 329;
    }

    private int yys94() {
        switch (yytok) {
            case INTERSECT:
                return 36;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '\\':
                return 53;
            case '}':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case NEQ:
            case ',':
            case '<':
            case LEQ:
            case '>':
            case '?':
            case ')':
            case IS_SUBSET:
            case WHEN:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr64();
        }
        return 329;
    }

    private int yys95() {
        switch (yytok) {
            case INTERSECT:
                return 36;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '\\':
                return 53;
            case '}':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case NEQ:
            case ',':
            case '<':
            case LEQ:
            case '>':
            case '?':
            case ')':
            case IS_SUBSET:
            case WHEN:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr70();
        }
        return 329;
    }

    private int yys96() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr75();
        }
        return 329;
    }

    private int yys97() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys98() {
        switch (yytok) {
            case '%':
                return 43;
            case '*':
                return 44;
            case '/':
                return 47;
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case ';':
            case NOT_IN:
            case ':':
            case EQ:
            case '-':
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case ')':
            case '<':
            case IS_SUBSET:
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr39();
        }
        return 329;
    }

    private int yys99() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys101() {
        switch (yytok) {
            case STRING_LITERAL:
            case SELECT:
            case GLOBAL:
            case '{':
            case OUT:
            case EXISTS:
            case FOR_EACH:
            case '-':
            case FOR_ALL:
            case '(':
            case FLOAT_LITERAL:
            case '!':
            case VAR:
            case INTEGER_LITERAL:
            case FALSE:
            case TRUE:
            case IDENTIFIER:
            case ENDINPUT:
            case EXPLAIN:
                return yyr4();
        }
        return 329;
    }

    private int yys102() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys103() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys104() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case '}':
            case ON:
            case ';':
            case ':':
            case ',':
            case ')':
            case WHEN:
            case TO:
            case AT:
                return yyr43();
        }
        return 329;
    }

    private int yys106() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr53();
        }
        return 329;
    }

    private int yys107() {
        switch (yytok) {
            case STRING_LITERAL:
            case TIME:
            case OUT:
            case COMPUTE:
            case VAR:
            case IDENTIFIER:
            case SAME_NODE:
            case ']':
            case FOR_EACH:
            case '[':
            case IS_PROPER_SUBSET:
            case INTEGER_LITERAL:
            case TRUE:
            case GLOBAL:
            case EXPLAIN:
            case ENDINPUT:
            case FALSE:
            case '{':
            case FLOAT_LITERAL:
            case FROM:
            case FOR_ALL:
            case NODES:
            case ASSIGN:
            case '(':
            case '!':
            case error:
            case EXISTS:
            case ALL_NODES:
            case SELECT:
                return 329;
            case '.':
                return 65;
        }
        return yyr54();
    }

    private int yys112() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr23();
        }
        return 329;
    }

    private int yys113() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
            case '}':
            case ')':
                return yyr80();
        }
        return 329;
    }

    private int yys114() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr71();
        }
        return 329;
    }

    private int yys115() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ')':
                return 126;
        }
        return 329;
    }

    private int yys116() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ':':
                return 127;
        }
        return 329;
    }

    private int yys117() {
        switch (yytok) {
            case ON:
            case ';':
            case COMPUTE:
            case WHEN:
            case IN:
            case AT:
                return yyr73();
        }
        return 329;
    }

    private int yys118() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ':':
                return 128;
        }
        return 329;
    }

    private int yys119() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ':':
                return 129;
        }
        return 329;
    }

    private int yys120() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr44();
        }
        return 329;
    }

    private int yys121() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr72();
        }
        return 329;
    }

    private int yys132() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys134() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys135() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys136() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys138() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys139() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ON:
            case ';':
            case WHEN:
            case AT:
                return yyr8();
        }
        return 329;
    }

    private int yys140() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys141() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr77();
        }
        return 329;
    }

    private int yys142() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ')':
                return 149;
        }
        return 329;
    }

    private int yys143() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ')':
                return 150;
        }
        return 329;
    }

    private int yys144() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ')':
                return 151;
        }
        return 329;
    }

    private int yys147() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ON:
            case ';':
            case AT:
                return yyr10();
        }
        return 329;
    }

    private int yys148() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ')':
                return 156;
        }
        return 329;
    }

    private int yys149() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr32();
        }
        return 329;
    }

    private int yys150() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr33();
        }
        return 329;
    }

    private int yys151() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr31();
        }
        return 329;
    }

    private int yys156() {
        switch (yytok) {
            case '}':
            case '\\':
            case GEQ:
            case OR:
            case '=':
            case ON:
            case NOT_IN:
            case ':':
            case '/':
            case EQ:
            case NEQ:
            case ',':
            case '+':
            case LEQ:
            case '-':
            case '*':
            case ')':
            case IS_SUBSET:
            case ';':
            case '<':
            case '%':
            case '>':
            case '?':
            case INTERSECT:
            case WHEN:
            case UNION:
            case IN:
            case TO:
            case AT:
            case AND:
                return yyr76();
        }
        return 329;
    }

    private int yys157() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys159() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case TO:
                return 161;
            case ';':
                return yyr18();
        }
        return 329;
    }

    private int yys161() {
        switch (yytok) {
            case EXISTS:
                return 14;
            case FALSE:
                return 16;
            case FLOAT_LITERAL:
                return 17;
            case FOR_ALL:
                return 18;
            case FOR_EACH:
                return 19;
            case GLOBAL:
                return 20;
            case IDENTIFIER:
                return 21;
            case INTEGER_LITERAL:
                return 22;
            case OUT:
                return 23;
            case STRING_LITERAL:
                return 25;
            case TRUE:
                return 26;
            case VAR:
                return 27;
            case '!':
                return 28;
            case '(':
                return 29;
            case '-':
                return 30;
            case '{':
                return 31;
        }
        return 329;
    }

    private int yys162() {
        switch (yytok) {
            case AND:
                return 32;
            case EQ:
                return 33;
            case GEQ:
                return 34;
            case IN:
                return 35;
            case INTERSECT:
                return 36;
            case IS_SUBSET:
                return 37;
            case LEQ:
                return 38;
            case NEQ:
                return 39;
            case NOT_IN:
                return 40;
            case OR:
                return 41;
            case UNION:
                return 42;
            case '%':
                return 43;
            case '*':
                return 44;
            case '+':
                return 45;
            case '-':
                return 46;
            case '/':
                return 47;
            case '<':
                return 49;
            case '=':
                return 50;
            case '>':
                return 51;
            case '?':
                return 52;
            case '\\':
                return 53;
            case ';':
                return yyr17();
        }
        return 329;
    }

    private int yyr1() { // RQL : Mode RQL
        {}
        yysv[yysp-=2] = yyrv;
        return yypRQL();
    }

    private int yyr2() { // RQL : /* empty */
        {}
        yysv[yysp-=0] = yyrv;
        return yypRQL();
    }

    private int yypRQL() {
        switch (yyst[yysp-1]) {
            case 0: return 1;
            default: return 54;
        }
    }

    private int yyr40() { // AssignExp : VAR IDENTIFIER
        {yyrv = new DeclareExpression(new Variable(((String)yysv[yysp-1])), false);}
        yysv[yysp-=2] = yyrv;
        return 2;
    }

    private int yyr41() { // AssignExp : GLOBAL IDENTIFIER
        {yyrv = new DeclareExpression(new Variable(((String)yysv[yysp-1])), true);}
        yysv[yysp-=2] = yyrv;
        return 2;
    }

    private int yyr42() { // AssignExp : OUT IDENTIFIER
        {yyrv = new OutExpression(new Variable(((String)yysv[yysp-1])));}
        yysv[yysp-=2] = yyrv;
        return 2;
    }

    private int yyr43() { // AssignExp : IDENTIFIER ASSIGN Expression
        {yyrv = new AssignExpression(new Variable(((String)yysv[yysp-3])), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 2;
    }

    private int yyr12() { // AtNodes : AT NODES IdList
        {yyrv = new AtNodes(((IdentifierList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 145;
    }

    private int yyr13() { // AtNodes : AT ALL_NODES
        {yyrv = new AtNodes();}
        yysv[yysp-=2] = yyrv;
        return 145;
    }

    private int yyr14() { // AtNodes : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 145;
    }

    private int yyr17() { // AtTimeExtended : TO Expression
        {yyrv = ((Expression)yysv[yysp-1]);}
        yysv[yysp-=2] = yyrv;
        return 160;
    }

    private int yyr18() { // AtTimeExtended : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 160;
    }

    private int yyr64() { // CmpExpression : Expression '=' Expression
        {yyrv = new EQExpression(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 3;
    }

    private int yyr65() { // CmpExpression : Expression EQ Expression
        {yyrv = new EQExpression(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 3;
    }

    private int yyr66() { // CmpExpression : Expression NEQ Expression
        {yyrv = new NEQExpression(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 3;
    }

    private int yyr67() { // CmpExpression : Expression LEQ Expression
        {yyrv = new LEQExpression(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 3;
    }

    private int yyr68() { // CmpExpression : Expression GEQ Expression
        {yyrv = new GEQExpression(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 3;
    }

    private int yyr69() { // CmpExpression : Expression '<' Expression
        {yyrv = new LessThanExpression(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 3;
    }

    private int yyr70() { // CmpExpression : Expression '>' Expression
        {yyrv = new GreaterThanExpression(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 3;
    }

    private int yyr8() { // Compute : COMPUTE Expression
        {yyrv = ((Expression)yysv[yysp-1]);}
        yysv[yysp-=2] = yyrv;
        return 130;
    }

    private int yyr9() { // Compute : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 130;
    }

    private int yyr75() { // Cond : Expression '?' Ternary
        {yyrv = new Condition(((Expression)yysv[yysp-3]), ((Ternary)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 4;
    }

    private int yyr53() { // DotID : '.' VarID
        {yyrv = yysv[yysp-1];}
        yysv[yysp-=2] = yyrv;
        return 62;
    }

    private int yyr54() { // DotID : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 62;
    }

    private int yyr23() { // Expression : '(' Expression ')'
        {yyrv = ((Expression)yysv[yysp-2]);}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr24() { // Expression : AssignExp
        {yyrv = yysv[yysp-1];}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr25() { // Expression : SetExp
        {yyrv = yysv[yysp-1];}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr26() { // Expression : ArithmeticExp
        {yyrv = yysv[yysp-1];}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr27() { // Expression : CmpExpression
        {yyrv = ((Expression)yysv[yysp-1]);}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr28() { // Expression : LogicalExp
        {yyrv = yysv[yysp-1];}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr29() { // Expression : VarExp
        {yyrv = yysv[yysp-1];}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr30() { // Expression : Cond
        {}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr31() { // Expression : FOR_EACH IdList IN Expression ':' '(' Expression ')'
        {yyrv = new ForEach(((IdentifierList)yysv[yysp-7]), ((Expression)yysv[yysp-5]), ((Expression)yysv[yysp-2]));}
        yysv[yysp-=8] = yyrv;
        return yypExpression();
    }

    private int yypExpression() {
        switch (yyst[yysp-1]) {
            case 161: return 162;
            case 157: return 159;
            case 140: return 148;
            case 138: return 147;
            case 136: return 144;
            case 135: return 143;
            case 134: return 142;
            case 132: return 139;
            case 103: return 119;
            case 102: return 118;
            case 99: return 116;
            case 97: return 115;
            case 63: return 104;
            case 53: return 98;
            case 51: return 95;
            case 50: return 94;
            case 49: return 93;
            case 47: return 92;
            case 46: return 91;
            case 45: return 90;
            case 44: return 89;
            case 43: return 88;
            case 42: return 87;
            case 41: return 86;
            case 40: return 85;
            case 39: return 84;
            case 38: return 83;
            case 37: return 82;
            case 36: return 81;
            case 35: return 80;
            case 34: return 79;
            case 33: return 78;
            case 32: return 77;
            case 30: return 74;
            case 29: return 73;
            case 28: return 72;
            case 7: return 5;
            case 0: return 5;
            default: return 75;
        }
    }

    private int yyr78() { // ExpressionList : Expression ',' ExpressionList
        {yyrv = new ExpressionList(((Expression)yysv[yysp-3]), ((ExpressionList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpressionList();
    }

    private int yyr79() { // ExpressionList : Expression
        {yyrv = new ExpressionList(((Expression)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpressionList();
    }

    private int yyr80() { // ExpressionList : /* empty */
        {yyrv = new ExpressionList();}
        yysv[yysp-=0] = yyrv;
        return yypExpressionList();
    }

    private int yypExpressionList() {
        switch (yyst[yysp-1]) {
            case 64: return 105;
            case 31: return 76;
            default: return 125;
        }
    }

    private int yyr73() { // IdList : IDENTIFIER ',' IdList
        {yyrv = new IdentifierList(((String)yysv[yysp-3]), ((IdentifierList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypIdList();
    }

    private int yyr74() { // IdList : IDENTIFIER
        {yyrv = new IdentifierList(((String)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypIdList();
    }

    private int yypIdList() {
        switch (yyst[yysp-1]) {
            case 110: return 123;
            case 100: return 117;
            case 19: return 60;
            case 18: return 59;
            case 14: return 56;
            default: return 158;
        }
    }

    private int yyr55() { // LogicalExp : '!' Expression
        {yyrv = new NotExpression(((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return 6;
    }

    private int yyr56() { // LogicalExp : Expression AND Expression
        {yyrv = new And(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 6;
    }

    private int yyr57() { // LogicalExp : Expression OR Expression
        {yyrv = new Or(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 6;
    }

    private int yyr3() { // Mode : Query ';'
        {queries.add(((Query)yysv[yysp-2]));}
        yysv[yysp-=2] = yyrv;
        return 7;
    }

    private int yyr4() { // Mode : EXPLAIN Query ';'
        {((Query)yysv[yysp-2]).explain(); queries.add(((Query)yysv[yysp-2]));}
        yysv[yysp-=3] = yyrv;
        return 7;
    }

    private int yyr5() { // Mode : Expression ';'
        {yyrv = new EvalExpression(((Expression)yysv[yysp-2]));}
        yysv[yysp-=2] = yyrv;
        return 7;
    }

    private int yyr15() { // OnTime : ON TIME Expression AtTimeExtended
        {yyrv = new OnTime(((Expression)yysv[yysp-2]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=4] = yyrv;
        return 152;
    }

    private int yyr16() { // OnTime : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 152;
    }

    private int yyr21() { // Param : IDENTIFIER
        {yyrv = new Param(((String)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return 68;
    }

    private int yyr22() { // Param : IDENTIFIER '.' IDENTIFIER
        {yyrv = new Param(((String)yysv[yysp-3]), ((String)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 68;
    }

    private int yyr19() { // ParamList : Param ',' ParamList
        {yyrv = new ParamList(((Param)yysv[yysp-3]), ((ParamList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypParamList();
    }

    private int yyr20() { // ParamList : Param
        {yyrv = new ParamList(((Param)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypParamList();
    }

    private int yypParamList() {
        switch (yyst[yysp-1]) {
            case 24: return 69;
            default: return 122;
        }
    }

    private int yyr6() { // Query : SELECT ParamList FROM IdList QueryEnd
        {yyrv = new Query(((ParamList)yysv[yysp-4]), ((IdentifierList)yysv[yysp-2]), ((QueryEnd)yysv[yysp-1]));}
        yysv[yysp-=5] = yyrv;
        switch (yyst[yysp-1]) {
            case 15: return 58;
            default: return 8;
        }
    }

    private int yyr7() { // QueryEnd : Compute When AtNodes OnTime
        {yyrv = new QueryEnd(((Expression)yysv[yysp-4]), ((When)yysv[yysp-3]), ((AtNodes)yysv[yysp-2]), ((OnTime)yysv[yysp-1]));}
        yysv[yysp-=4] = yyrv;
        return 131;
    }

    private int yyr58() { // ArithmeticExp : '-' Expression
        {yyrv = new Minus(((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return 9;
    }

    private int yyr59() { // ArithmeticExp : Expression '+' Expression
        {yyrv = new Plus(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 9;
    }

    private int yyr60() { // ArithmeticExp : Expression '-' Expression
        {yyrv = new Minus(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 9;
    }

    private int yyr61() { // ArithmeticExp : Expression '/' Expression
        {yyrv = new Divide(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 9;
    }

    private int yyr62() { // ArithmeticExp : Expression '*' Expression
        {yyrv = new Multiply(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 9;
    }

    private int yyr63() { // ArithmeticExp : Expression '%' Expression
        {yyrv = new Mod(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 9;
    }

    private int yyr71() { // Set : '{' ExpressionList '}'
        {yyrv = new SetLiteral(((ExpressionList)yysv[yysp-2]));}
        yysv[yysp-=3] = yyrv;
        return 10;
    }

    private int yyr72() { // Set : INTEGER_LITERAL '.' '.' INTEGER_LITERAL
        {yyrv = null;}
        yysv[yysp-=4] = yyrv;
        return 10;
    }

    private int yyr32() { // SetExp : EXISTS IdList IN Expression ':' '(' Expression ')'
        {yyrv = new Exists(((IdentifierList)yysv[yysp-7]), ((Expression)yysv[yysp-5]), ((Expression)yysv[yysp-2]));}
        yysv[yysp-=8] = yyrv;
        return 11;
    }

    private int yyr33() { // SetExp : FOR_ALL IdList IN Expression ':' '(' Expression ')'
        {yyrv = new ForAll(((IdentifierList)yysv[yysp-7]), ((Expression)yysv[yysp-5]), ((Expression)yysv[yysp-2]));}
        yysv[yysp-=8] = yyrv;
        return 11;
    }

    private int yyr34() { // SetExp : Expression IS_SUBSET Expression
        {yyrv = new IsSubset(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 11;
    }

    private int yyr35() { // SetExp : Expression UNION Expression
        {yyrv = new Union(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 11;
    }

    private int yyr36() { // SetExp : Expression INTERSECT Expression
        {yyrv = new Intersect(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 11;
    }

    private int yyr37() { // SetExp : Expression IN Expression
        {yyrv = new InOperator(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 11;
    }

    private int yyr38() { // SetExp : Expression NOT_IN Expression
        {yyrv = new NotInOperator(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 11;
    }

    private int yyr39() { // SetExp : Expression '\\' Expression
        {yyrv = new SetDifference(((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 11;
    }

    private int yyr76() { // Ternary : '(' Expression ')' ':' '(' Expression ')'
        {yyrv = new Ternary(((Expression)yysv[yysp-6]), ((Expression)yysv[yysp-2]));}
        yysv[yysp-=7] = yyrv;
        return 96;
    }

    private int yyr77() { // Ternary : '(' Expression ')' ':' ';'
        {yyrv = new Ternary(((Expression)yysv[yysp-4]));}
        yysv[yysp-=5] = yyrv;
        return 96;
    }

    private int yyr44() { // VarExp : IDENTIFIER '(' ExpressionList ')'
        {yyrv = new FuncCall(((String)yysv[yysp-4]), ((ExpressionList)yysv[yysp-2]));}
        yysv[yysp-=4] = yyrv;
        return 12;
    }

    private int yyr45() { // VarExp : VarID
        {yyrv = yysv[yysp-1];}
        yysv[yysp-=1] = yyrv;
        return 12;
    }

    private int yyr46() { // VarExp : INTEGER_LITERAL
        {yyrv = new IntegerLiteral(((Long)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return 12;
    }

    private int yyr47() { // VarExp : FLOAT_LITERAL
        {yyrv = new FloatLiteral(((Double)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return 12;
    }

    private int yyr48() { // VarExp : STRING_LITERAL
        {yyrv = new StringLiteral(((String)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return 12;
    }

    private int yyr49() { // VarExp : Set
        {yyrv = yysv[yysp-1];}
        yysv[yysp-=1] = yyrv;
        return 12;
    }

    private int yyr50() { // VarExp : TRUE
        {yyrv = new IntegerLiteral(1);}
        yysv[yysp-=1] = yyrv;
        return 12;
    }

    private int yyr51() { // VarExp : FALSE
        {yyrv = new IntegerLiteral(0);}
        yysv[yysp-=1] = yyrv;
        return 12;
    }

    private int yyr52() { // VarID : IDENTIFIER DotID
        {yyrv = new Variable(((String)yysv[yysp-2]), ((Variable)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        switch (yyst[yysp-1]) {
            case 65: return 106;
            default: return 13;
        }
    }

    private int yyr10() { // When : WHEN Expression
        {yyrv = new When(((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return 137;
    }

    private int yyr11() { // When : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 137;
    }

    protected String[] yyerrmsgs = {
    };



/* code in the parser class*/

private Scanner lexer;
private ArrayList<Query> queries = new ArrayList<>();
/* constructor registering a lexer for lang */
public rqlParser(Scanner lexer){
    this.lexer=lexer;
}

public rqlParser(){
}

/* implementation of the nextToken() using lexer.yylex() that throws an
exception
*/

public void setScanner(Scanner lexer) {
    this.lexer = lexer;
}

private int nextToken(){
      try{
          return lexer.yylex();
       }catch(java.io.IOException e){System.out.println("IO exception from lexer!");e.printStackTrace();}
       return 0;
}

private void yyerror(String msg) {
    System.out.println(
      "ERROR "+ msg + "\n" +
      " at line   " +(lexer.line() + 1) + "\n" +
      " at column " +(lexer.column() + 1) + "\n" +
      " with token <<" + lexer.semanticValue + ">>"); }


public ArrayList<Query> getQueries() {
    return this.queries;
}

public void resetQueries() {
    queries.clear();
}

}
