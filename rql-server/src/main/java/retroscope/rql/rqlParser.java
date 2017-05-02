// Output created by jacc on Tue May 02 17:18:08 EDT 2017


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
                case 138:
                    yyn = yys0();
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 139:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = 276;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 2:
                    yyst[yysp] = 2;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 140:
                    yyn = yys2();
                    continue;

                case 3:
                    yyst[yysp] = 3;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 141:
                    yyn = yys3();
                    continue;

                case 4:
                    yyst[yysp] = 4;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 142:
                    switch (yytok) {
                        case ';':
                            yyn = 32;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 5:
                    yyst[yysp] = 5;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 143:
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
                case 144:
                    switch (yytok) {
                        case SELECT:
                            yyn = 11;
                            continue;
                    }
                    yyn = 279;
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
                case 145:
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
                case 146:
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
                case 147:
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
                case 148:
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
                case 149:
                    switch (yytok) {
                        case COUNT:
                            yyn = 42;
                            continue;
                        case IDENTIFIER:
                            yyn = 43;
                            continue;
                        case SUM:
                            yyn = 44;
                            continue;
                    }
                    yyn = 279;
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
                case 150:
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
                case 151:
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
                case 152:
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
                case 153:
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
                case 154:
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
                case 155:
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
                case 156:
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
                case 157:
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
                case 158:
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
                case 159:
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
                case 160:
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
                case 161:
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
                case 162:
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
                case 163:
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
                case 164:
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
                case 165:
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
                case 166:
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
                case 167:
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
                case 168:
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
                case 169:
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
                case 170:
                    yyn = yys32();
                    continue;

                case 33:
                    yyst[yysp] = 33;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 171:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr1();
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 34:
                    yyst[yysp] = 34;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 172:
                    switch (yytok) {
                        case ';':
                            yyn = 62;
                            continue;
                    }
                    yyn = 279;
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
                case 173:
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
                case 174:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 65;
                            continue;
                    }
                    yyn = 279;
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
                case 175:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 66;
                            continue;
                    }
                    yyn = 279;
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
                case 176:
                    yyn = yys38();
                    continue;

                case 39:
                    yyst[yysp] = 39;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 177:
                    switch (yytok) {
                        case ',':
                            yyn = 68;
                            continue;
                        case FROM:
                            yyn = yyr27();
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 40:
                    yyst[yysp] = 40;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 178:
                    switch (yytok) {
                        case FROM:
                            yyn = 69;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 41:
                    yyst[yysp] = 41;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 179:
                    switch (yytok) {
                        case '(':
                            yyn = 70;
                            continue;
                    }
                    yyn = 279;
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
                case 180:
                    switch (yytok) {
                        case '(':
                            yyn = yyr32();
                            continue;
                    }
                    yyn = 279;
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
                case 181:
                    switch (yytok) {
                        case '.':
                            yyn = 71;
                            continue;
                        case FROM:
                        case ',':
                            yyn = yyr28();
                            continue;
                    }
                    yyn = 279;
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
                case 182:
                    switch (yytok) {
                        case '(':
                            yyn = yyr33();
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 45:
                    yyst[yysp] = 45;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 183:
                    yyn = yys45();
                    continue;

                case 46:
                    yyst[yysp] = 46;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 184:
                    yyn = yys46();
                    continue;

                case 47:
                    yyst[yysp] = 47;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 185:
                    yyn = yys47();
                    continue;

                case 48:
                    yyst[yysp] = 48;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 186:
                    yyn = yys48();
                    continue;

                case 49:
                    yyst[yysp] = 49;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 187:
                    yyn = yys49();
                    continue;

                case 50:
                    yyst[yysp] = 50;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 188:
                    yyn = yys50();
                    continue;

                case 51:
                    yyst[yysp] = 51;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 189:
                    yyn = yys51();
                    continue;

                case 52:
                    yyst[yysp] = 52;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 190:
                    yyn = yys52();
                    continue;

                case 53:
                    yyst[yysp] = 53;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 191:
                    yyn = yys53();
                    continue;

                case 54:
                    yyst[yysp] = 54;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 192:
                    yyn = yys54();
                    continue;

                case 55:
                    yyst[yysp] = 55;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 193:
                    yyn = yys55();
                    continue;

                case 56:
                    yyst[yysp] = 56;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 194:
                    yyn = yys56();
                    continue;

                case 57:
                    yyst[yysp] = 57;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 195:
                    yyn = yys57();
                    continue;

                case 58:
                    yyst[yysp] = 58;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 196:
                    yyn = yys58();
                    continue;

                case 59:
                    yyst[yysp] = 59;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 197:
                    yyn = yys59();
                    continue;

                case 60:
                    yyst[yysp] = 60;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 198:
                    yyn = yys60();
                    continue;

                case 61:
                    yyst[yysp] = 61;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 199:
                    yyn = yys61();
                    continue;

                case 62:
                    yyst[yysp] = 62;
                    yysv[yysp] = (lexer.semanticValue
                    );
                    yytok = (nextToken()
                    );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 200:
                    yyn = yys62();
                    continue;

                case 63:
                    yyst[yysp] = 63;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 201:
                    yyn = yys63();
                    continue;

                case 64:
                    yyst[yysp] = 64;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 202:
                    switch (yytok) {
                        case ')':
                            yyn = 74;
                            continue;
                    }
                    yyn = 279;
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
                case 203:
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
                case 204:
                    yyn = yys66();
                    continue;

                case 67:
                    yyst[yysp] = 67;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 205:
                    yyn = yys67();
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
                case 206:
                    switch (yytok) {
                        case COUNT:
                            yyn = 42;
                            continue;
                        case IDENTIFIER:
                            yyn = 43;
                            continue;
                        case SUM:
                            yyn = 44;
                            continue;
                    }
                    yyn = 279;
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
                case 207:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 80;
                            continue;
                    }
                    yyn = 279;
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
                case 208:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 81;
                            continue;
                    }
                    yyn = 279;
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
                case 209:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 82;
                            continue;
                    }
                    yyn = 279;
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
                case 210:
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
                case 211:
                    yyn = yys73();
                    continue;

                case 74:
                    yyst[yysp] = 74;
                    yysv[yysp] = (lexer.semanticValue
                    );
                    yytok = (nextToken()
                    );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 212:
                    yyn = yys74();
                    continue;

                case 75:
                    yyst[yysp] = 75;
                    yysv[yysp] = (lexer.semanticValue
                    );
                    yytok = (nextToken()
                    );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 213:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 84;
                            continue;
                    }
                    yyn = 279;
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
                case 214:
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
                case 215:
                    yyn = yys77();
                    continue;

                case 78:
                    yyst[yysp] = 78;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 216:
                    switch (yytok) {
                        case FROM:
                            yyn = yyr26();
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 79:
                    yyst[yysp] = 79;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 217:
                    yyn = yys79();
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
                case 218:
                    yyn = yys80();
                    continue;

                case 81:
                    yyst[yysp] = 81;
                    yysv[yysp] = (lexer.semanticValue
                    );
                    yytok = (nextToken()
                    );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 219:
                    switch (yytok) {
                        case ')':
                            yyn = 90;
                            continue;
                        case '.':
                            yyn = 91;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 82:
                    yyst[yysp] = 82;
                    yysv[yysp] = (lexer.semanticValue
                    );
                    yytok = (nextToken()
                    );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 220:
                    switch (yytok) {
                        case FROM:
                        case ',':
                            yyn = yyr29();
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 83:
                    yyst[yysp] = 83;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 221:
                    switch (yytok) {
                        case ')':
                            yyn = yyr67();
                            continue;
                    }
                    yyn = 279;
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
                case 222:
                    yyn = yys84();
                    continue;

                case 85:
                    yyst[yysp] = 85;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 223:
                    yyn = yys85();
                    continue;

                case 86:
                    yyst[yysp] = 86;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 224:
                    switch (yytok) {
                        case ';':
                            yyn = yyr6();
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 87:
                    yyst[yysp] = 87;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 225:
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
                case 226:
                    yyn = yys88();
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
                case 227:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 80;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 90:
                    yyst[yysp] = 90;
                    yysv[yysp] = (lexer.semanticValue
                    );
                    yytok = (nextToken()
                    );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 228:
                    switch (yytok) {
                        case FROM:
                        case ',':
                            yyn = yyr30();
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 91:
                    yyst[yysp] = 91;
                    yysv[yysp] = (lexer.semanticValue
                    );
                    yytok = (nextToken()
                    );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 229:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 98;
                            continue;
                    }
                    yyn = 279;
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
                case 230:
                    yyn = yys92();
                    continue;

                case 93:
                    yyst[yysp] = 93;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 231:
                    yyn = yys93();
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
                case 232:
                    switch (yytok) {
                        case '(':
                            yyn = 104;
                            continue;
                    }
                    yyn = 279;
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
                case 233:
                    switch (yytok) {
                        case '(':
                            yyn = 105;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 96:
                    yyst[yysp] = 96;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 234:
                    yyn = yys96();
                    continue;

                case 97:
                    yyst[yysp] = 97;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 235:
                    yyn = yys97();
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
                case 236:
                    switch (yytok) {
                        case ')':
                            yyn = 106;
                            continue;
                    }
                    yyn = 279;
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
                case 237:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 107;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 100:
                    yyst[yysp] = 100;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 238:
                    switch (yytok) {
                        case AT:
                            yyn = 109;
                            continue;
                        case ';':
                        case ON:
                            yyn = yyr21();
                            continue;
                    }
                    yyn = 279;
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
                case 239:
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
                case 240:
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
                case 241:
                    switch (yytok) {
                        case '(':
                            yyn = 112;
                            continue;
                    }
                    yyn = 279;
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
                case 242:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 80;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 105:
                    yyst[yysp] = 105;
                    yysv[yysp] = (lexer.semanticValue
                    );
                    yytok = (nextToken()
                    );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 243:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 80;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 106:
                    yyst[yysp] = 106;
                    yysv[yysp] = (lexer.semanticValue
                    );
                    yytok = (nextToken()
                    );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 244:
                    switch (yytok) {
                        case FROM:
                        case ',':
                            yyn = yyr31();
                            continue;
                    }
                    yyn = 279;
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
                case 245:
                    yyn = yys107();
                    continue;

                case 108:
                    yyst[yysp] = 108;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 246:
                    switch (yytok) {
                        case ON:
                            yyn = 116;
                            continue;
                        case ';':
                            yyn = yyr23();
                            continue;
                    }
                    yyn = 279;
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
                case 247:
                    switch (yytok) {
                        case ALL_NODES:
                            yyn = 117;
                            continue;
                        case NODES:
                            yyn = 118;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 110:
                    yyst[yysp] = 110;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 248:
                    yyn = yys110();
                    continue;

                case 111:
                    yyst[yysp] = 111;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 249:
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
                case 250:
                    yyn = yys112();
                    continue;

                case 113:
                    yyst[yysp] = 113;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 251:
                    switch (yytok) {
                        case ')':
                            yyn = 120;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 114:
                    yyst[yysp] = 114;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 252:
                    switch (yytok) {
                        case ')':
                            yyn = 121;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 115:
                    yyst[yysp] = 115;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 253:
                    switch (yytok) {
                        case ';':
                            yyn = yyr7();
                            continue;
                    }
                    yyn = 279;
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
                case 254:
                    switch (yytok) {
                        case TIME:
                            yyn = 122;
                            continue;
                    }
                    yyn = 279;
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
                case 255:
                    switch (yytok) {
                        case ';':
                        case ON:
                            yyn = yyr20();
                            continue;
                    }
                    yyn = 279;
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
                case 256:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 80;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 119:
                    yyst[yysp] = 119;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 257:
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
                case 258:
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
                case 259:
                    yyn = yys121();
                    continue;

                case 122:
                    yyst[yysp] = 122;
                    yysv[yysp] = (lexer.semanticValue
                    );
                    yytok = (nextToken()
                    );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 260:
                    yyn = yys122();
                    continue;

                case 123:
                    yyst[yysp] = 123;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 261:
                    switch (yytok) {
                        case ';':
                        case ON:
                            yyn = yyr19();
                            continue;
                    }
                    yyn = 279;
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
                case 262:
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
                case 263:
                    yyn = yys125();
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
                case 264:
                    yyn = yys126();
                    continue;

                case 127:
                    yyst[yysp] = 127;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 265:
                    yyn = yys127();
                    continue;

                case 128:
                    yyst[yysp] = 128;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 266:
                    yyn = yys128();
                    continue;

                case 129:
                    yyst[yysp] = 129;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 267:
                    yyn = yys129();
                    continue;

                case 130:
                    yyst[yysp] = 130;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 268:
                    yyn = yys130();
                    continue;

                case 131:
                    yyst[yysp] = 131;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 269:
                    switch (yytok) {
                        case ';':
                            yyn = yyr22();
                            continue;
                    }
                    yyn = 279;
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
                case 270:
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
                case 271:
                    switch (yytok) {
                        case LINK:
                            yyn = 135;
                            continue;
                    }
                    yyn = 279;
                    continue;

                case 134:
                    yyst[yysp] = 134;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 272:
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
                case 273:
                    yyn = yys135();
                    continue;

                case 136:
                    yyst[yysp] = 136;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 274:
                    yyn = yys136();
                    continue;

                case 137:
                    yyst[yysp] = 137;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 275:
                    yyn = yys137();
                    continue;

                case 276:
                    return true;
                case 277:
                    yyerror("stack overflow");
                case 278:
                    return false;
                case 279:
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
        return 279;
    }

    private int yys2() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr43();
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
        return 279;
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
        return 279;
    }

    private int yys7() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr59();
    }

    private int yys8() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr56();
    }

    private int yys9() {
        switch (yytok) {
            case ENDINPUT:
            case TIME:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case WHEN:
            case COUNT:
            case SUM:
            case '!':
            case INTEGER_LITERAL:
            case '}':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '{':
            case FALSE:
            case NODES:
            case SELECT:
            case SAME_NODE:
            case EXPLAIN:
                return 279;
            case '(':
                return 35;
            case '.':
                return 36;
            case ':':
                return 37;
            case '[':
                return 38;
        }
        return yyr49();
    }

    private int yys10() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr55();
    }

    private int yys12() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr57();
    }

    private int yys13() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr58();
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
        return 279;
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
                return yyr69();
        }
        return 279;
    }

    private int yys38() {
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
        return 279;
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
            case TO:
            case ']':
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
        return 279;
    }

    private int yys46() {
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
                return 72;
        }
        return 279;
    }

    private int yys47() {
        switch (yytok) {
            case '%':
                return 23;
            case '*':
                return 24;
            case '/':
                return 27;
            case TO:
            case GEQ:
            case ']':
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
        return 279;
    }

    private int yys48() {
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
            case ']':
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
        return 279;
    }

    private int yys49() {
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
            case ']':
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
                return yyr61();
        }
        return 279;
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
            case ']':
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
                return yyr64();
        }
        return 279;
    }

    private int yys51() {
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
            case ']':
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
        return 279;
    }

    private int yys52() {
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
            case ']':
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
                return yyr62();
        }
        return 279;
    }

    private int yys53() {
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
            case ']':
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
        return 279;
    }

    private int yys54() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr41();
    }

    private int yys55() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr40();
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
            case ']':
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
        return 279;
    }

    private int yys57() {
        switch (yytok) {
            case '%':
                return 23;
            case '*':
                return 24;
            case '/':
                return 27;
            case TO:
            case GEQ:
            case ']':
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
        return 279;
    }

    private int yys58() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr39();
    }

    private int yys59() {
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
            case ']':
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
                return yyr65();
        }
        return 279;
    }

    private int yys60() {
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
            case ']':
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
                return yyr60();
        }
        return 279;
    }

    private int yys61() {
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
            case ']':
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
                return yyr66();
        }
        return 279;
    }

    private int yys62() {
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
        return 279;
    }

    private int yys63() {
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
                return 73;
            case ')':
                return yyr68();
        }
        return 279;
    }

    private int yys65() {
        switch (yytok) {
            case STRING_LITERAL:
            case TIME:
            case SUM:
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '!':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '.':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
                return 279;
            case ':':
                return 75;
            case '[':
                return 76;
        }
        return yyr50();
    }

    private int yys66() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr54();
    }

    private int yys67() {
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
            case ']':
                return 77;
        }
        return 279;
    }

    private int yys72() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr42();
    }

    private int yys73() {
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
                return yyr69();
        }
        return 279;
    }

    private int yys74() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr47();
    }

    private int yys76() {
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
        return 279;
    }

    private int yys77() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr48();
    }

    private int yys79() {
        switch (yytok) {
            case WHEN:
                return 88;
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
        return 279;
    }

    private int yys80() {
        switch (yytok) {
            case ',':
                return 89;
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
        return 279;
    }

    private int yys84() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr52();
    }

    private int yys85() {
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
            case ']':
                return 92;
        }
        return 279;
    }

    private int yys87() {
        switch (yytok) {
            case FA_LINK:
                return 94;
            case LINK:
                return 95;
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr18();
        }
        return 279;
    }

    private int yys88() {
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
        return 279;
    }

    private int yys92() {
        switch (yytok) {
            case SUM:
            case TIME:
            case '!':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case NODES:
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case '.':
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
                return 279;
            case ':':
                return 99;
        }
        return yyr51();
    }

    private int yys93() {
        switch (yytok) {
            case AFTER:
                return 101;
            case BEFORE:
                return 102;
            case BETWEEN:
                return 103;
            case ';':
            case ON:
            case AT:
                return yyr11();
        }
        return 279;
    }

    private int yys96() {
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
        return 279;
    }

    private int yys97() {
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
        return 279;
    }

    private int yys101() {
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
        return 279;
    }

    private int yys102() {
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
        return 279;
    }

    private int yys107() {
        switch (yytok) {
            case '!':
            case TIME:
            case ':':
            case INTEGER_LITERAL:
            case WHEN:
            case COUNT:
            case '{':
            case '.':
            case SELECT:
            case '(':
            case error:
            case FROM:
            case TRUE:
            case IDENTIFIER:
            case ALL_NODES:
            case '}':
            case '[':
            case NODES:
            case FALSE:
            case SAME_NODE:
            case EXPLAIN:
            case ENDINPUT:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
            case SUM:
                return 279;
        }
        return yyr53();
    }

    private int yys110() {
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
        return 279;
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
            case ';':
            case ON:
            case AT:
                return yyr9();
        }
        return 279;
    }

    private int yys112() {
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
        return 279;
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
            case ',':
                return 124;
        }
        return 279;
    }

    private int yys120() {
        switch (yytok) {
            case ',':
                return 125;
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr17();
        }
        return 279;
    }

    private int yys121() {
        switch (yytok) {
            case ',':
                return 126;
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr16();
        }
        return 279;
    }

    private int yys122() {
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
        return 279;
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
        return 279;
    }

    private int yys125() {
        switch (yytok) {
            case FA_LINK:
                return 94;
            case LINK:
                return 95;
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr18();
        }
        return 279;
    }

    private int yys126() {
        switch (yytok) {
            case FA_LINK:
                return 94;
            case LINK:
                return 95;
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr18();
        }
        return 279;
    }

    private int yys127() {
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
                return 132;
            case ';':
                return yyr25();
        }
        return 279;
    }

    private int yys128() {
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
                return 133;
        }
        return 279;
    }

    private int yys129() {
        switch (yytok) {
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr15();
        }
        return 279;
    }

    private int yys130() {
        switch (yytok) {
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr14();
        }
        return 279;
    }

    private int yys132() {
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
        return 279;
    }

    private int yys134() {
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
        return 279;
    }

    private int yys135() {
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
        return 279;
    }

    private int yys136() {
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
        return 279;
    }

    private int yys137() {
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
        return 279;
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
        return 108;
    }

    private int yyr20() { // AtNodes : AT ALL_NODES
        {yyrv = new AtNodes();}
        yysv[yysp-=2] = yyrv;
        return 108;
    }

    private int yyr21() { // AtNodes : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 108;
    }

    private int yyr24() { // AtTimeExtended : TO Expression
        {yyrv = ((Expression)yysv[yysp-1]);}
        yysv[yysp-=2] = yyrv;
        return 131;
    }

    private int yyr25() { // AtTimeExtended : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 131;
    }

    private int yyr60() { // CmpExpression : Expression '=' Expression
        {yyrv = new EQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yyr61() { // CmpExpression : Expression EQ Expression
        {yyrv = new EQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yyr62() { // CmpExpression : Expression NEQ Expression
        {yyrv = new NEQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yyr63() { // CmpExpression : Expression LEQ Expression
        {yyrv = new LEQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yyr64() { // CmpExpression : Expression GEQ Expression
        {yyrv = new GEQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yyr65() { // CmpExpression : Expression '<' Expression
        {yyrv = new LessThanExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yyr66() { // CmpExpression : Expression '>' Expression
        {yyrv = new GreaterThanExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypCmpExpression();
    }

    private int yypCmpExpression() {
        switch (yyst[yysp-1]) {
            case 135: return 136;
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

    private int yyr48() { // Expression : IDENTIFIER '[' Expression ']'
        {yyrv = new Variable(((String)yysv[yysp-4]), currentEnv, ((Expression)yysv[yysp-2]));}
        yysv[yysp-=4] = yyrv;
        return yypExpression();
    }

    private int yyr49() { // Expression : IDENTIFIER
        {yyrv = new Variable(((String)yysv[yysp-1]), currentEnv, null);}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr50() { // Expression : IDENTIFIER '.' IDENTIFIER
        {yyrv = new Variable(((String)yysv[yysp-3]), ((String)yysv[yysp-1]), currentEnv, null);}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr51() { // Expression : IDENTIFIER '.' IDENTIFIER '[' Expression ']'
        {yyrv = new Variable(((String)yysv[yysp-6]), ((String)yysv[yysp-4]), currentEnv, ((Expression)yysv[yysp-2]));}
        yysv[yysp-=6] = yyrv;
        return yypExpression();
    }

    private int yyr52() { // Expression : IDENTIFIER '.' IDENTIFIER ':' IDENTIFIER
        {yyrv = new Variable(((String)yysv[yysp-5]), ((String)yysv[yysp-3]), ((String)yysv[yysp-1]), currentEnv, null);}
        yysv[yysp-=5] = yyrv;
        return yypExpression();
    }

    private int yyr53() { // Expression : IDENTIFIER '.' IDENTIFIER '[' Expression ']' ':' IDENTIFIER
        {yyrv = new Variable(((String)yysv[yysp-8]), ((String)yysv[yysp-6]), ((String)yysv[yysp-1]), currentEnv, ((Expression)yysv[yysp-4]));}
        yysv[yysp-=8] = yyrv;
        return yypExpression();
    }

    private int yyr54() { // Expression : IDENTIFIER ':' IDENTIFIER
        {yyrv = new Variable("", ((String)yysv[yysp-3]), ((String)yysv[yysp-1]), currentEnv, null);}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr55() { // Expression : INTEGER_LITERAL
        {yyrv = new IntegerLiteral(currentEnv, ((Integer)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr56() { // Expression : FLOAT_LITERAL
        {yyrv = new FloatLiteral(currentEnv, ((Double)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr57() { // Expression : STRING_LITERAL
        {yyrv = new StringLiteral(currentEnv, ((String)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr58() { // Expression : TRUE
        {yyrv = new IntegerLiteral(currentEnv, 1);}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr59() { // Expression : FALSE
        {yyrv = new IntegerLiteral(currentEnv, 0);}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yypExpression() {
        switch (yyst[yysp-1]) {
            case 135: return 137;
            case 132: return 134;
            case 124: return 128;
            case 122: return 127;
            case 112: return 119;
            case 102: return 111;
            case 101: return 110;
            case 88: return 96;
            case 76: return 85;
            case 38: return 67;
            case 31: return 61;
            case 30: return 60;
            case 29: return 59;
            case 27: return 58;
            case 26: return 57;
            case 25: return 56;
            case 24: return 55;
            case 23: return 54;
            case 22: return 53;
            case 21: return 52;
            case 20: return 51;
            case 19: return 50;
            case 18: return 49;
            case 17: return 48;
            case 16: return 47;
            case 15: return 46;
            case 14: return 45;
            case 5: return 3;
            case 0: return 3;
            default: return 63;
        }
    }

    private int yyr67() { // ExpressionList : Expression ',' ExpressionList
        {yyrv = new ExpressionList(((Expression)yysv[yysp-3]), ((ExpressionList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpressionList();
    }

    private int yyr68() { // ExpressionList : Expression
        {yyrv = new ExpressionList(((Expression)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpressionList();
    }

    private int yyr69() { // ExpressionList : /* empty */
        {yyrv = new ExpressionList();}
        yysv[yysp-=0] = yyrv;
        return yypExpressionList();
    }

    private int yypExpressionList() {
        switch (yyst[yysp-1]) {
            case 35: return 64;
            default: return 83;
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
            case 105: return 114;
            case 104: return 113;
            case 89: return 97;
            case 69: return 79;
            default: return 123;
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
            case 125: return 129;
            case 87: return 93;
            default: return 130;
        }
    }

    private int yyr22() { // OnTime : ON TIME Expression AtTimeExtended
        {yyrv = new OnTime(((Expression)yysv[yysp-2]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=4] = yyrv;
        return 115;
    }

    private int yyr23() { // OnTime : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 115;
    }

    private int yyr28() { // Param : IDENTIFIER
        {yyrv = new Param(((String)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return 39;
    }

    private int yyr29() { // Param : IDENTIFIER '.' IDENTIFIER
        {yyrv = new Param(((String)yysv[yysp-3]), ((String)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 39;
    }

    private int yyr30() { // Param : Aggregator '(' IDENTIFIER ')'
        {yyrv = new Param(((String)yysv[yysp-2]));}
        yysv[yysp-=4] = yyrv;
        return 39;
    }

    private int yyr31() { // Param : Aggregator '(' IDENTIFIER '.' IDENTIFIER ')'
        {yyrv = new Param(((String)yysv[yysp-4]), ((String)yysv[yysp-2]));}
        yysv[yysp-=6] = yyrv;
        return 39;
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
            case 11: return 40;
            default: return 78;
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
        return 86;
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
        return 41;
    }

    private int yyr33() { // Aggregator : SUM
        {yyrv = new Aggregator(AggregatorType.SUM);}
        yysv[yysp-=1] = yyrv;
        return 41;
    }

    private int yyr8() { // TimeSearch : BETWEEN '(' Expression ',' Expression ')' LINK CmpExpression
        {yyrv = new TimeSearch(TimeSearch.BETWEEN, ((Expression)yysv[yysp-6]), ((Expression)yysv[yysp-4]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=8] = yyrv;
        return 100;
    }

    private int yyr9() { // TimeSearch : BEFORE Expression
        {yyrv = new TimeSearch(TimeSearch.BEFORE, ((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return 100;
    }

    private int yyr10() { // TimeSearch : AFTER Expression
        {yyrv = new TimeSearch(TimeSearch.AFTER, ((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return 100;
    }

    private int yyr11() { // TimeSearch : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 100;
    }

    private int yyr12() { // When : WHEN Expression
        {yyrv = new When(((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return 87;
    }

    private int yyr13() { // When : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 87;
    }

    protected String[] yyerrmsgs = {
    };



/* code in the parser class*/

    private Scanner lexer;
    private QueryEnvironment currentEnv;

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


    public QueryEnvironment getEnvironment() {
        return currentEnv;
    }

    public rqlParser setEnvironment(QueryEnvironment currentEnv) {
        this.currentEnv = currentEnv;
        return this;
    }


}
