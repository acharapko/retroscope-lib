// Output created by jacc on Thu Mar 02 17:21:53 EST 2017


package retroscope.rql;
import retroscope.rql.syntaxtree.*;
import retroscope.rql.syntaxtree.link.*;
import retroscope.rql.syntaxtree.expression.*;


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
                case 130:
                    yyn = yys0();
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 131:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = 260;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 2:
                    yyst[yysp] = 2;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 132:
                    yyn = yys2();
                    continue;

                case 3:
                    yyst[yysp] = 3;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 133:
                    yyn = yys3();
                    continue;

                case 4:
                    yyst[yysp] = 4;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 134:
                    switch (yytok) {
                        case ';':
                            yyn = 32;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 5:
                    yyst[yysp] = 5;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 135:
                    yyn = yys5();
                    continue;

                case 6:
                    yyst[yysp] = 6;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 136:
                    switch (yytok) {
                        case SELECT:
                            yyn = 11;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 7:
                    yyst[yysp] = 7;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 137:
                    yyn = yys7();
                    continue;

                case 8:
                    yyst[yysp] = 8;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 138:
                    yyn = yys8();
                    continue;

                case 9:
                    yyst[yysp] = 9;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 139:
                    yyn = yys9();
                    continue;

                case 10:
                    yyst[yysp] = 10;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 140:
                    yyn = yys10();
                    continue;

                case 11:
                    yyst[yysp] = 11;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 141:
                    switch (yytok) {
                        case COUNT:
                            yyn = 41;
                            continue;
                        case IDENTIFIER:
                            yyn = 42;
                            continue;
                        case SUM:
                            yyn = 43;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 12:
                    yyst[yysp] = 12;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 142:
                    yyn = yys12();
                    continue;

                case 13:
                    yyst[yysp] = 13;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 143:
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
                case 144:
                    yyn = yys14();
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
                case 145:
                    yyn = yys15();
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
                case 146:
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
                case 147:
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
                case 148:
                    yyn = yys18();
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
                case 149:
                    yyn = yys19();
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
                case 150:
                    yyn = yys20();
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
                case 151:
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
                case 152:
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
                case 153:
                    yyn = yys23();
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
                case 154:
                    yyn = yys24();
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
                case 155:
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
                case 156:
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
                case 157:
                    yyn = yys27();
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
                case 158:
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
                case 159:
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
                case 160:
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
                case 161:
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
                case 162:
                    yyn = yys32();
                    continue;

                case 33:
                    yyst[yysp] = 33;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 163:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr1();
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 34:
                    yyst[yysp] = 34;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 164:
                    switch (yytok) {
                        case ';':
                            yyn = 61;
                            continue;
                    }
                    yyn = 263;
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
                case 165:
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
                case 166:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 64;
                            continue;
                    }
                    yyn = 263;
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
                case 167:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 65;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 38:
                    yyst[yysp] = 38;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 168:
                    switch (yytok) {
                        case ',':
                            yyn = 66;
                            continue;
                        case FROM:
                            yyn = yyr27();
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 39:
                    yyst[yysp] = 39;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 169:
                    switch (yytok) {
                        case FROM:
                            yyn = 67;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 40:
                    yyst[yysp] = 40;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 170:
                    switch (yytok) {
                        case '(':
                            yyn = 68;
                            continue;
                    }
                    yyn = 263;
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
                case 171:
                    switch (yytok) {
                        case '(':
                            yyn = yyr32();
                            continue;
                    }
                    yyn = 263;
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
                case 172:
                    switch (yytok) {
                        case '.':
                            yyn = 69;
                            continue;
                        case FROM:
                        case ',':
                            yyn = yyr28();
                            continue;
                    }
                    yyn = 263;
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
                case 173:
                    switch (yytok) {
                        case '(':
                            yyn = yyr33();
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 44:
                    yyst[yysp] = 44;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 174:
                    yyn = yys44();
                    continue;

                case 45:
                    yyst[yysp] = 45;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 175:
                    yyn = yys45();
                    continue;

                case 46:
                    yyst[yysp] = 46;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 176:
                    yyn = yys46();
                    continue;

                case 47:
                    yyst[yysp] = 47;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 177:
                    yyn = yys47();
                    continue;

                case 48:
                    yyst[yysp] = 48;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 178:
                    yyn = yys48();
                    continue;

                case 49:
                    yyst[yysp] = 49;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 179:
                    yyn = yys49();
                    continue;

                case 50:
                    yyst[yysp] = 50;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 180:
                    yyn = yys50();
                    continue;

                case 51:
                    yyst[yysp] = 51;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 181:
                    yyn = yys51();
                    continue;

                case 52:
                    yyst[yysp] = 52;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 182:
                    yyn = yys52();
                    continue;

                case 53:
                    yyst[yysp] = 53;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 183:
                    yyn = yys53();
                    continue;

                case 54:
                    yyst[yysp] = 54;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 184:
                    yyn = yys54();
                    continue;

                case 55:
                    yyst[yysp] = 55;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 185:
                    yyn = yys55();
                    continue;

                case 56:
                    yyst[yysp] = 56;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 186:
                    yyn = yys56();
                    continue;

                case 57:
                    yyst[yysp] = 57;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 187:
                    yyn = yys57();
                    continue;

                case 58:
                    yyst[yysp] = 58;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 188:
                    yyn = yys58();
                    continue;

                case 59:
                    yyst[yysp] = 59;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 189:
                    yyn = yys59();
                    continue;

                case 60:
                    yyst[yysp] = 60;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 190:
                    yyn = yys60();
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
                case 191:
                    yyn = yys61();
                    continue;

                case 62:
                    yyst[yysp] = 62;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 192:
                    yyn = yys62();
                    continue;

                case 63:
                    yyst[yysp] = 63;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 193:
                    switch (yytok) {
                        case ')':
                            yyn = 72;
                            continue;
                    }
                    yyn = 263;
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
                case 194:
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
                case 195:
                    yyn = yys65();
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
                case 196:
                    switch (yytok) {
                        case COUNT:
                            yyn = 41;
                            continue;
                        case IDENTIFIER:
                            yyn = 42;
                            continue;
                        case SUM:
                            yyn = 43;
                            continue;
                    }
                    yyn = 263;
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
                case 197:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 76;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 68:
                    yyst[yysp] = 68;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 198:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 77;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 69:
                    yyst[yysp] = 69;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 199:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 78;
                            continue;
                    }
                    yyn = 263;
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
                case 200:
                    yyn = yys70();
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
                case 201:
                    yyn = yys71();
                    continue;

                case 72:
                    yyst[yysp] = 72;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 202:
                    yyn = yys72();
                    continue;

                case 73:
                    yyst[yysp] = 73;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 203:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 80;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 74:
                    yyst[yysp] = 74;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 204:
                    switch (yytok) {
                        case FROM:
                            yyn = yyr26();
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 75:
                    yyst[yysp] = 75;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 205:
                    yyn = yys75();
                    continue;

                case 76:
                    yyst[yysp] = 76;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 206:
                    yyn = yys76();
                    continue;

                case 77:
                    yyst[yysp] = 77;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 207:
                    switch (yytok) {
                        case ')':
                            yyn = 85;
                            continue;
                        case '.':
                            yyn = 86;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 78:
                    yyst[yysp] = 78;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 208:
                    switch (yytok) {
                        case FROM:
                        case ',':
                            yyn = yyr29();
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 79:
                    yyst[yysp] = 79;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 209:
                    switch (yytok) {
                        case ')':
                            yyn = yyr64();
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 80:
                    yyst[yysp] = 80;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 210:
                    yyn = yys80();
                    continue;

                case 81:
                    yyst[yysp] = 81;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 211:
                    switch (yytok) {
                        case ';':
                            yyn = yyr6();
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 82:
                    yyst[yysp] = 82;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 212:
                    yyn = yys82();
                    continue;

                case 83:
                    yyst[yysp] = 83;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 213:
                    yyn = yys83();
                    continue;

                case 84:
                    yyst[yysp] = 84;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 214:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 76;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 85:
                    yyst[yysp] = 85;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 215:
                    switch (yytok) {
                        case FROM:
                        case ',':
                            yyn = yyr30();
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 86:
                    yyst[yysp] = 86;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 216:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 92;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 87:
                    yyst[yysp] = 87;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 217:
                    yyn = yys87();
                    continue;

                case 88:
                    yyst[yysp] = 88;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 218:
                    switch (yytok) {
                        case '(':
                            yyn = 97;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 89:
                    yyst[yysp] = 89;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 219:
                    switch (yytok) {
                        case '(':
                            yyn = 98;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 90:
                    yyst[yysp] = 90;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 220:
                    yyn = yys90();
                    continue;

                case 91:
                    yyst[yysp] = 91;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 221:
                    yyn = yys91();
                    continue;

                case 92:
                    yyst[yysp] = 92;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 222:
                    switch (yytok) {
                        case ')':
                            yyn = 99;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 93:
                    yyst[yysp] = 93;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 223:
                    switch (yytok) {
                        case AT:
                            yyn = 101;
                            continue;
                        case ';':
                        case ON:
                            yyn = yyr21();
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 94:
                    yyst[yysp] = 94;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 224:
                    yyn = yys94();
                    continue;

                case 95:
                    yyst[yysp] = 95;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 225:
                    yyn = yys95();
                    continue;

                case 96:
                    yyst[yysp] = 96;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 226:
                    switch (yytok) {
                        case '(':
                            yyn = 104;
                            continue;
                    }
                    yyn = 263;
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
                case 227:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 76;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 98:
                    yyst[yysp] = 98;
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
                            yyn = 76;
                            continue;
                    }
                    yyn = 263;
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
                case 229:
                    switch (yytok) {
                        case FROM:
                        case ',':
                            yyn = yyr31();
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 100:
                    yyst[yysp] = 100;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 230:
                    switch (yytok) {
                        case ON:
                            yyn = 108;
                            continue;
                        case ';':
                            yyn = yyr23();
                            continue;
                    }
                    yyn = 263;
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
                case 231:
                    switch (yytok) {
                        case ALL_NODES:
                            yyn = 109;
                            continue;
                        case NODES:
                            yyn = 110;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 102:
                    yyst[yysp] = 102;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 232:
                    yyn = yys102();
                    continue;

                case 103:
                    yyst[yysp] = 103;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 233:
                    yyn = yys103();
                    continue;

                case 104:
                    yyst[yysp] = 104;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 234:
                    yyn = yys104();
                    continue;

                case 105:
                    yyst[yysp] = 105;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 235:
                    switch (yytok) {
                        case ')':
                            yyn = 112;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 106:
                    yyst[yysp] = 106;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 236:
                    switch (yytok) {
                        case ')':
                            yyn = 113;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 107:
                    yyst[yysp] = 107;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 237:
                    switch (yytok) {
                        case ';':
                            yyn = yyr7();
                            continue;
                    }
                    yyn = 263;
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
                case 238:
                    switch (yytok) {
                        case TIME:
                            yyn = 114;
                            continue;
                    }
                    yyn = 263;
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
                case 239:
                    switch (yytok) {
                        case ';':
                        case ON:
                            yyn = yyr20();
                            continue;
                    }
                    yyn = 263;
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
                case 240:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 76;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 111:
                    yyst[yysp] = 111;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 241:
                    yyn = yys111();
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
                case 242:
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
                case 243:
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
                case 244:
                    yyn = yys114();
                    continue;

                case 115:
                    yyst[yysp] = 115;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 245:
                    switch (yytok) {
                        case ';':
                        case ON:
                            yyn = yyr19();
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 116:
                    yyst[yysp] = 116;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 246:
                    yyn = yys116();
                    continue;

                case 117:
                    yyst[yysp] = 117;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 247:
                    yyn = yys117();
                    continue;

                case 118:
                    yyst[yysp] = 118;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 248:
                    yyn = yys118();
                    continue;

                case 119:
                    yyst[yysp] = 119;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 249:
                    yyn = yys119();
                    continue;

                case 120:
                    yyst[yysp] = 120;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 250:
                    yyn = yys120();
                    continue;

                case 121:
                    yyst[yysp] = 121;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 251:
                    yyn = yys121();
                    continue;

                case 122:
                    yyst[yysp] = 122;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 252:
                    yyn = yys122();
                    continue;

                case 123:
                    yyst[yysp] = 123;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 253:
                    switch (yytok) {
                        case ';':
                            yyn = yyr22();
                            continue;
                    }
                    yyn = 263;
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
                case 254:
                    yyn = yys124();
                    continue;

                case 125:
                    yyst[yysp] = 125;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 255:
                    switch (yytok) {
                        case LINK:
                            yyn = 127;
                            continue;
                    }
                    yyn = 263;
                    continue;

                case 126:
                    yyst[yysp] = 126;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 256:
                    yyn = yys126();
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
                case 257:
                    yyn = yys127();
                    continue;

                case 128:
                    yyst[yysp] = 128;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 258:
                    yyn = yys128();
                    continue;

                case 129:
                    yyst[yysp] = 129;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 259:
                    yyn = yys129();
                    continue;

                case 260:
                    return true;
                case 261:
                    yyerror("stack overflow");
                case 262:
                    return false;
                case 263:
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
            case EXPLAIN:
                return 6;
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case SELECT:
                return 11;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
            case ENDINPUT:
                return yyr2();
        }
        return 263;
    }

    private int yys2() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr43();
        }
        return 263;
    }

    private int yys3() {
        switch (yytok) {
            case AND:
                return 17;
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case ';':
                return 28;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
        }
        return 263;
    }

    private int yys5() {
        switch (yytok) {
            case EXPLAIN:
                return 6;
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case SELECT:
                return 11;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
            case ENDINPUT:
                return yyr2();
        }
        return 263;
    }

    private int yys7() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr56();
        }
        return 263;
    }

    private int yys8() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr53();
        }
        return 263;
    }

    private int yys9() {
        switch (yytok) {
            case STRING_LITERAL:
            case COUNT:
            case SUM:
            case TIME:
            case error:
            case TRUE:
            case '!':
            case NODES:
            case INTEGER_LITERAL:
            case '}':
            case FLOAT_LITERAL:
            case FROM:
            case WHEN:
            case IDENTIFIER:
            case ALL_NODES:
            case '{':
            case '[':
            case FALSE:
            case ']':
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case SELECT:
                return 263;
            case '(':
                return 35;
            case '.':
                return 36;
            case ':':
                return 37;
        }
        return yyr48();
    }

    private int yys10() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr52();
        }
        return 263;
    }

    private int yys12() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr54();
        }
        return 263;
    }

    private int yys13() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr55();
        }
        return 263;
    }

    private int yys14() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys15() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys16() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys17() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys18() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys19() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys20() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys21() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys22() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys23() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys24() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys25() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys26() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys27() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys28() {
        switch (yytok) {
            case IDENTIFIER:
            case TRUE:
            case ENDINPUT:
            case STRING_LITERAL:
            case SELECT:
            case FLOAT_LITERAL:
            case FALSE:
            case '-':
            case EXPLAIN:
            case '(':
            case '!':
            case INTEGER_LITERAL:
                return yyr5();
        }
        return 263;
    }

    private int yys29() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys30() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys31() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys32() {
        switch (yytok) {
            case IDENTIFIER:
            case TRUE:
            case ENDINPUT:
            case STRING_LITERAL:
            case SELECT:
            case FLOAT_LITERAL:
            case FALSE:
            case '-':
            case EXPLAIN:
            case '(':
            case '!':
            case INTEGER_LITERAL:
                return yyr3();
        }
        return 263;
    }

    private int yys35() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
            case ')':
                return yyr66();
        }
        return 263;
    }

    private int yys44() {
        switch (yytok) {
            case AND:
                return 17;
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
            case TO:
            case BETWEEN:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case ',':
            case LINK:
            case ')':
            case AT:
            case AFTER:
                return yyr44();
        }
        return 263;
    }

    private int yys45() {
        switch (yytok) {
            case AND:
                return 17;
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
            case ')':
                return 70;
        }
        return 263;
    }

    private int yys46() {
        switch (yytok) {
            case '%':
                return 23;
            case '*':
                return 24;
            case '/':
                return 27;
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '<':
            case '=':
            case '+':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr36();
        }
        return 263;
    }

    private int yys47() {
        switch (yytok) {
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
            case TO:
            case BETWEEN:
            case AND:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case ',':
            case LINK:
            case ')':
            case AT:
            case AFTER:
                return yyr45();
        }
        return 263;
    }

    private int yys48() {
        switch (yytok) {
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '>':
                return 31;
            case TO:
            case BETWEEN:
            case AND:
            case OR:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case '=':
            case ',':
            case NEQ:
            case LINK:
            case ')':
            case EQ:
            case AT:
            case AFTER:
                return yyr58();
        }
        return 263;
    }

    private int yys49() {
        switch (yytok) {
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case ',':
            case '<':
            case '=':
            case NEQ:
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr61();
        }
        return 263;
    }

    private int yys50() {
        switch (yytok) {
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case ',':
            case '<':
            case '=':
            case NEQ:
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr60();
        }
        return 263;
    }

    private int yys51() {
        switch (yytok) {
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '>':
                return 31;
            case TO:
            case BETWEEN:
            case AND:
            case OR:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case '=':
            case ',':
            case NEQ:
            case LINK:
            case ')':
            case EQ:
            case AT:
            case AFTER:
                return yyr59();
        }
        return 263;
    }

    private int yys52() {
        switch (yytok) {
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
            case TO:
            case BETWEEN:
            case AND:
            case OR:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case ',':
            case LINK:
            case ')':
            case AT:
            case AFTER:
                return yyr46();
        }
        return 263;
    }

    private int yys53() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr41();
        }
        return 263;
    }

    private int yys54() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr40();
        }
        return 263;
    }

    private int yys55() {
        switch (yytok) {
            case '%':
                return 23;
            case '*':
                return 24;
            case '/':
                return 27;
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '<':
            case '=':
            case '+':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr37();
        }
        return 263;
    }

    private int yys56() {
        switch (yytok) {
            case '%':
                return 23;
            case '*':
                return 24;
            case '/':
                return 27;
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '<':
            case '=':
            case '+':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr38();
        }
        return 263;
    }

    private int yys57() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr39();
        }
        return 263;
    }

    private int yys58() {
        switch (yytok) {
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case ',':
            case '<':
            case '=':
            case NEQ:
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr62();
        }
        return 263;
    }

    private int yys59() {
        switch (yytok) {
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '>':
                return 31;
            case TO:
            case BETWEEN:
            case AND:
            case OR:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case '=':
            case ',':
            case NEQ:
            case LINK:
            case ')':
            case EQ:
            case AT:
            case AFTER:
                return yyr57();
        }
        return 263;
    }

    private int yys60() {
        switch (yytok) {
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case ',':
            case '<':
            case '=':
            case NEQ:
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr63();
        }
        return 263;
    }

    private int yys61() {
        switch (yytok) {
            case IDENTIFIER:
            case TRUE:
            case ENDINPUT:
            case STRING_LITERAL:
            case SELECT:
            case FLOAT_LITERAL:
            case FALSE:
            case '-':
            case EXPLAIN:
            case '(':
            case '!':
            case INTEGER_LITERAL:
                return yyr4();
        }
        return 263;
    }

    private int yys62() {
        switch (yytok) {
            case AND:
                return 17;
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
            case ',':
                return 71;
            case ')':
                return yyr65();
        }
        return 263;
    }

    private int yys64() {
        switch (yytok) {
            case ':':
                return 73;
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr49();
        }
        return 263;
    }

    private int yys65() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr51();
        }
        return 263;
    }

    private int yys70() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr42();
        }
        return 263;
    }

    private int yys71() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
            case ')':
                return yyr66();
        }
        return 263;
    }

    private int yys72() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr47();
        }
        return 263;
    }

    private int yys75() {
        switch (yytok) {
            case WHEN:
                return 83;
            case BETWEEN:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case LINK:
            case AT:
            case AFTER:
                return yyr13();
        }
        return 263;
    }

    private int yys76() {
        switch (yytok) {
            case ',':
                return 84;
            case WHEN:
            case BETWEEN:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case LINK:
            case ')':
            case AT:
            case AFTER:
                return yyr35();
        }
        return 263;
    }

    private int yys80() {
        switch (yytok) {
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case FA_LINK:
            case ON:
            case '/':
            case BEFORE:
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '*':
            case ';':
            case '<':
            case '=':
            case LINK:
            case ')':
            case LEQ:
            case '%':
            case EQ:
            case AT:
            case AFTER:
                return yyr50();
        }
        return 263;
    }

    private int yys82() {
        switch (yytok) {
            case FA_LINK:
                return 88;
            case LINK:
                return 89;
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr18();
        }
        return 263;
    }

    private int yys83() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys87() {
        switch (yytok) {
            case AFTER:
                return 94;
            case BEFORE:
                return 95;
            case BETWEEN:
                return 96;
            case ';':
            case ON:
            case AT:
                return yyr11();
        }
        return 263;
    }

    private int yys90() {
        switch (yytok) {
            case AND:
                return 17;
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
            case BETWEEN:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case LINK:
            case AT:
            case AFTER:
                return yyr12();
        }
        return 263;
    }

    private int yys91() {
        switch (yytok) {
            case WHEN:
            case BETWEEN:
            case FA_LINK:
            case ';':
            case ON:
            case BEFORE:
            case LINK:
            case ')':
            case AT:
            case AFTER:
                return yyr34();
        }
        return 263;
    }

    private int yys94() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys95() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys102() {
        switch (yytok) {
            case AND:
                return 17;
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
            case ';':
            case ON:
            case AT:
                return yyr10();
        }
        return 263;
    }

    private int yys103() {
        switch (yytok) {
            case AND:
                return 17;
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
            case ';':
            case ON:
            case AT:
                return yyr9();
        }
        return 263;
    }

    private int yys104() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys111() {
        switch (yytok) {
            case AND:
                return 17;
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
            case ',':
                return 116;
        }
        return 263;
    }

    private int yys112() {
        switch (yytok) {
            case ',':
                return 117;
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr17();
        }
        return 263;
    }

    private int yys113() {
        switch (yytok) {
            case ',':
                return 118;
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr16();
        }
        return 263;
    }

    private int yys114() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys116() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys117() {
        switch (yytok) {
            case FA_LINK:
                return 88;
            case LINK:
                return 89;
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr18();
        }
        return 263;
    }

    private int yys118() {
        switch (yytok) {
            case FA_LINK:
                return 88;
            case LINK:
                return 89;
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr18();
        }
        return 263;
    }

    private int yys119() {
        switch (yytok) {
            case AND:
                return 17;
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
            case TO:
                return 124;
            case ';':
                return yyr25();
        }
        return 263;
    }

    private int yys120() {
        switch (yytok) {
            case AND:
                return 17;
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
            case ')':
                return 125;
        }
        return 263;
    }

    private int yys121() {
        switch (yytok) {
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr15();
        }
        return 263;
    }

    private int yys122() {
        switch (yytok) {
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr14();
        }
        return 263;
    }

    private int yys124() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys126() {
        switch (yytok) {
            case AND:
                return 17;
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
            case ';':
                return yyr24();
        }
        return 263;
    }

    private int yys127() {
        switch (yytok) {
            case FALSE:
                return 7;
            case FLOAT_LITERAL:
                return 8;
            case IDENTIFIER:
                return 9;
            case INTEGER_LITERAL:
                return 10;
            case STRING_LITERAL:
                return 12;
            case TRUE:
                return 13;
            case '!':
                return 14;
            case '(':
                return 15;
            case '-':
                return 16;
        }
        return 263;
    }

    private int yys128() {
        switch (yytok) {
            case GEQ:
            case AND:
            case '>':
            case '<':
            case OR:
            case '/':
            case '-':
            case NEQ:
            case '+':
            case '=':
            case '*':
            case LEQ:
            case '%':
            case EQ:
                return yyr43();
            case ';':
            case ON:
            case AT:
                return yyr8();
        }
        return 263;
    }

    private int yys129() {
        switch (yytok) {
            case AND:
                return 17;
            case EQ:
                return 18;
            case GEQ:
                return 19;
            case LEQ:
                return 20;
            case NEQ:
                return 21;
            case OR:
                return 22;
            case '%':
                return 23;
            case '*':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
            case '/':
                return 27;
            case '<':
                return 29;
            case '=':
                return 30;
            case '>':
                return 31;
        }
        return 263;
    }

    private int yyr1() { // Statements : Statement Statements
        {}
        yysv[yysp-=2] = yyrv;
        return yypStatements();
    }

    private int yyr2() { // Statements : /* empty */
        {}
        yysv[yysp-=0] = yyrv;
        return yypStatements();
    }

    private int yypStatements() {
        switch (yyst[yysp-1]) {
            case 0: return 1;
            default: return 33;
        }
    }

    private int yyr19() { // AtNodes : AT NODES IdentifierList
        {yyrv = new AtNodes(((IdentifierList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 100;
    }

    private int yyr20() { // AtNodes : AT ALL_NODES
        {yyrv = new AtNodes();}
        yysv[yysp-=2] = yyrv;
        return 100;
    }

    private int yyr21() { // AtNodes : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 100;
    }

    private int yyr24() { // AtTimeExtended : TO Expression
        {yyrv = ((Expression)yysv[yysp-1]);}
        yysv[yysp-=2] = yyrv;
        return 123;
    }

    private int yyr25() { // AtTimeExtended : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 123;
    }

    private int yyr57() { // CmpExpression : Expression '=' Expression
        {yyrv = new EQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yyr58() { // CmpExpression : Expression EQ Expression
        {yyrv = new EQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yyr59() { // CmpExpression : Expression NEQ Expression
        {yyrv = new NEQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yyr60() { // CmpExpression : Expression LEQ Expression
        {yyrv = new LEQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yyr61() { // CmpExpression : Expression GEQ Expression
        {yyrv = new GEQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yyr62() { // CmpExpression : Expression '<' Expression
        {yyrv = new LessThanExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yyr63() { // CmpExpression : Expression '>' Expression
        {yyrv = new GreaterThanExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yypCmpExpression() {
        switch (yyst[yysp-1]) {
            case 127: return 128;
            default: return 2;
        }
    }

    private int yyr36() { // Expression : '-' Expression
        {yyrv = new Minus(currentEnv, ((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return yypExpression();
    }

    private int yyr37() { // Expression : Expression '+' Expression
        {yyrv = new Plus(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr38() { // Expression : Expression '-' Expression
        {yyrv = new Minus(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr39() { // Expression : Expression '/' Expression
        {yyrv = new Divide(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr40() { // Expression : Expression '*' Expression
        {yyrv = new Multiply(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr41() { // Expression : Expression '%' Expression
        {yyrv = new Mod(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr42() { // Expression : '(' Expression ')'
        {yyrv = ((Expression)yysv[yysp-2]);}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr43() { // Expression : CmpExpression
        {yyrv = ((Expression)yysv[yysp-1]);}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr44() { // Expression : '!' Expression
        {yyrv = new NotExpression(currentEnv, ((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return yypExpression();
    }

    private int yyr45() { // Expression : Expression AND Expression
        {yyrv = new And(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr46() { // Expression : Expression OR Expression
        {yyrv = new Or(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr47() { // Expression : IDENTIFIER '(' ExpressionList ')'
        {yyrv = new FuncCall(currentEnv, ((String)yysv[yysp-4]), ((ExpressionList)yysv[yysp-2]));}
        yysv[yysp-=4] = yyrv;
        return yypExpression();
    }

    private int yyr48() { // Expression : IDENTIFIER
        {yyrv = new Variable(((String)yysv[yysp-1]), currentEnv, true);}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr49() { // Expression : IDENTIFIER '.' IDENTIFIER
        {yyrv = new Variable(((String)yysv[yysp-3]), ((String)yysv[yysp-1]), currentEnv, true);}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr50() { // Expression : IDENTIFIER '.' IDENTIFIER ':' IDENTIFIER
        {yyrv = new Variable(((String)yysv[yysp-5]), ((String)yysv[yysp-3]), ((String)yysv[yysp-1]), currentEnv, true);}
        yysv[yysp-=5] = yyrv;
        return yypExpression();
    }

    private int yyr51() { // Expression : IDENTIFIER ':' IDENTIFIER
        {yyrv = new Variable("", ((String)yysv[yysp-3]), ((String)yysv[yysp-1]), currentEnv, true);}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr52() { // Expression : INTEGER_LITERAL
        {yyrv = new IntegerLiteral(currentEnv, ((Integer)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr53() { // Expression : FLOAT_LITERAL
        {yyrv = new FloatLiteral(currentEnv, ((Double)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr54() { // Expression : STRING_LITERAL
        {yyrv = new StringLiteral(currentEnv, ((String)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr55() { // Expression : TRUE
        {yyrv = new IntegerLiteral(currentEnv, 1);}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr56() { // Expression : FALSE
        {yyrv = new IntegerLiteral(currentEnv, 0);}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yypExpression() {
        switch (yyst[yysp-1]) {
            case 127: return 129;
            case 124: return 126;
            case 116: return 120;
            case 114: return 119;
            case 104: return 111;
            case 95: return 103;
            case 94: return 102;
            case 83: return 90;
            case 31: return 60;
            case 30: return 59;
            case 29: return 58;
            case 27: return 57;
            case 26: return 56;
            case 25: return 55;
            case 24: return 54;
            case 23: return 53;
            case 22: return 52;
            case 21: return 51;
            case 20: return 50;
            case 19: return 49;
            case 18: return 48;
            case 17: return 47;
            case 16: return 46;
            case 15: return 45;
            case 14: return 44;
            case 5: return 3;
            case 0: return 3;
            default: return 62;
        }
    }

    private int yyr64() { // ExpressionList : Expression ',' ExpressionList
        {yyrv = new ExpressionList(((Expression)yysv[yysp-3]), ((ExpressionList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpressionList();
    }

    private int yyr65() { // ExpressionList : Expression
        {yyrv = new ExpressionList(((Expression)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpressionList();
    }

    private int yyr66() { // ExpressionList : /* empty */
        {yyrv = new ExpressionList();}
        yysv[yysp-=0] = yyrv;
        return yypExpressionList();
    }

    private int yypExpressionList() {
        switch (yyst[yysp-1]) {
            case 35: return 63;
            default: return 79;
        }
    }

    private int yyr34() { // IdentifierList : IDENTIFIER ',' IdentifierList
        {yyrv = new IdentifierList(((String)yysv[yysp-3]), ((IdentifierList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypIdentifierList();
    }

    private int yyr35() { // IdentifierList : IDENTIFIER
        {yyrv = new IdentifierList(((String)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypIdentifierList();
    }

    private int yypIdentifierList() {
        switch (yyst[yysp-1]) {
            case 98: return 106;
            case 97: return 105;
            case 84: return 91;
            case 67: return 75;
            default: return 115;
        }
    }

    private int yyr14() { // Link : LINK '(' IdentifierList ')' ',' Link
        {yyrv = new Links(((Links)yysv[yysp-1]), new Link(((IdentifierList)yysv[yysp-4])));}
        yysv[yysp-=6] = yyrv;
        return yypLink();
    }

    private int yyr15() { // Link : FA_LINK '(' IdentifierList ')' ',' Link
        {yyrv = new Links(((Links)yysv[yysp-1]), new ForAllLink(((IdentifierList)yysv[yysp-4])));}
        yysv[yysp-=6] = yyrv;
        return yypLink();
    }

    private int yyr16() { // Link : LINK '(' IdentifierList ')'
        {yyrv = new Links(new Link(((IdentifierList)yysv[yysp-2])));}
        yysv[yysp-=4] = yyrv;
        return yypLink();
    }

    private int yyr17() { // Link : FA_LINK '(' IdentifierList ')'
        {yyrv = new Links(new ForAllLink(((IdentifierList)yysv[yysp-2])));}
        yysv[yysp-=4] = yyrv;
        return yypLink();
    }

    private int yyr18() { // Link : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return yypLink();
    }

    private int yypLink() {
        switch (yyst[yysp-1]) {
            case 117: return 121;
            case 82: return 87;
            default: return 122;
        }
    }

    private int yyr22() { // OnTime : ON TIME Expression AtTimeExtended
        {yyrv = new OnTime(((Expression)yysv[yysp-2]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=4] = yyrv;
        return 107;
    }

    private int yyr23() { // OnTime : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 107;
    }

    private int yyr28() { // Param : IDENTIFIER
        {yyrv = new Param(((String)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return 38;
    }

    private int yyr29() { // Param : IDENTIFIER '.' IDENTIFIER
        {yyrv = new Param(((String)yysv[yysp-3]), ((String)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 38;
    }

    private int yyr30() { // Param : Aggregator '(' IDENTIFIER ')'
        {yyrv = new Param(((String)yysv[yysp-2]));}
        yysv[yysp-=4] = yyrv;
        return 38;
    }

    private int yyr31() { // Param : Aggregator '(' IDENTIFIER '.' IDENTIFIER ')'
        {yyrv = new Param(((String)yysv[yysp-4]), ((String)yysv[yysp-2]));}
        yysv[yysp-=6] = yyrv;
        return 38;
    }

    private int yyr26() { // ParamList : Param ',' ParamList
        {yyrv = new ParamList(((Param)yysv[yysp-3]), ((ParamList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypParamList();
    }

    private int yyr27() { // ParamList : Param
        {yyrv = new ParamList(((Param)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypParamList();
    }

    private int yypParamList() {
        switch (yyst[yysp-1]) {
            case 11: return 39;
            default: return 74;
        }
    }

    private int yyr6() { // Query : SELECT ParamList FROM IdentifierList QueryEnd
        {yyrv = new Query(((ParamList)yysv[yysp-4]), ((IdentifierList)yysv[yysp-2]), ((QueryEnd)yysv[yysp-1]), currentEnv);}
        yysv[yysp-=5] = yyrv;
        switch (yyst[yysp-1]) {
            case 6: return 34;
            default: return 4;
        }
    }

    private int yyr7() { // QueryEnd : When Link TimeSearch AtNodes OnTime
        {yyrv = new QueryEnd(((When)yysv[yysp-5]), ((Links)yysv[yysp-4]), ((TimeSearch)yysv[yysp-3]), ((AtNodes)yysv[yysp-2]), ((OnTime)yysv[yysp-1]));}
        yysv[yysp-=5] = yyrv;
        return 81;
    }

    private int yyr3() { // Statement : Query ';'
        {((Query)yysv[yysp-2]).execute();}
        yysv[yysp-=2] = yyrv;
        return 5;
    }

    private int yyr4() { // Statement : EXPLAIN Query ';'
        {((Query)yysv[yysp-2]).explain();}
        yysv[yysp-=3] = yyrv;
        return 5;
    }

    private int yyr5() { // Statement : Expression ';'
        {yyrv = new EvalExpression(((Expression)yysv[yysp-2]));}
        yysv[yysp-=2] = yyrv;
        return 5;
    }

    private int yyr32() { // Aggregator : COUNT
        {yyrv = new Aggregator(AggregatorType.COUNT);}
        yysv[yysp-=1] = yyrv;
        return 40;
    }

    private int yyr33() { // Aggregator : SUM
        {yyrv = new Aggregator(AggregatorType.SUM);}
        yysv[yysp-=1] = yyrv;
        return 40;
    }

    private int yyr8() { // TimeSearch : BETWEEN '(' Expression ',' Expression ')' LINK CmpExpression
        {yyrv = new TimeSearch(TimeSearch.BETWEEN, ((Expression)yysv[yysp-6]), ((Expression)yysv[yysp-4]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=8] = yyrv;
        return 93;
    }

    private int yyr9() { // TimeSearch : BEFORE Expression
        {yyrv = new TimeSearch(TimeSearch.BEFORE, ((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return 93;
    }

    private int yyr10() { // TimeSearch : AFTER Expression
        {yyrv = new TimeSearch(TimeSearch.AFTER, ((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return 93;
    }

    private int yyr11() { // TimeSearch : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 93;
    }

    private int yyr12() { // When : WHEN Expression
        {yyrv = new When(((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return 82;
    }

    private int yyr13() { // When : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 82;
    }

    protected String[] yyerrmsgs = {
    };

    /* code in the parser class*/

    private Scanner lexer;
    private RQLEnvironment currentEnv;

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


    public RQLEnvironment getEnvironment() {
        return currentEnv;
    }

    public rqlParser setEnvironment(RQLEnvironment currentEnv) {
        this.currentEnv = currentEnv;
        return this;
    }

}
