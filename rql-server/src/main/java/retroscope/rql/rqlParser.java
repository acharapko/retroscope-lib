// Output created by jacc on Sat Jan 21 22:14:05 EST 2017


package retroscope.rql;
import retroscope.rql.syntaxtree.*;
import java.util.HashMap;


class rqlParser implements mTokens {
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
                case 117:
                    yyn = yys0();
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 118:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = 234;
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 2:
                    yyst[yysp] = 2;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 119:
                    yyn = yys2();
                    continue;

                case 3:
                    yyst[yysp] = 3;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 120:
                    switch (yytok) {
                        case ';':
                            yyn = 32;
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 4:
                    yyst[yysp] = 4;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 121:
                    yyn = yys4();
                    continue;

                case 5:
                    yyst[yysp] = 5;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 122:
                    switch (yytok) {
                        case SELECT:
                            yyn = 10;
                            continue;
                    }
                    yyn = 237;
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
                case 123:
                    yyn = yys6();
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
                case 124:
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
                case 125:
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
                case 126:
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
                case 127:
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
                    yyn = 237;
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
                case 128:
                    yyn = yys11();
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
                case 129:
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
                case 130:
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
                case 131:
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
                case 132:
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
                case 133:
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
                case 134:
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
                case 135:
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
                case 136:
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
                case 137:
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
                case 138:
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
                case 139:
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
                case 140:
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
                case 141:
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
                case 142:
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
                case 143:
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
                case 144:
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
                case 145:
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
                case 146:
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
                case 147:
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
                case 148:
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
                case 149:
                    yyn = yys32();
                    continue;

                case 33:
                    yyst[yysp] = 33;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 150:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr1();
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 34:
                    yyst[yysp] = 34;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 151:
                    switch (yytok) {
                        case ';':
                            yyn = 62;
                            continue;
                    }
                    yyn = 237;
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
                case 152:
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
                case 153:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 65;
                            continue;
                    }
                    yyn = 237;
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
                case 154:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 66;
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 38:
                    yyst[yysp] = 38;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 155:
                    switch (yytok) {
                        case ',':
                            yyn = 67;
                            continue;
                        case FROM:
                            yyn = yyr22();
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 39:
                    yyst[yysp] = 39;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 156:
                    switch (yytok) {
                        case FROM:
                            yyn = 68;
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 40:
                    yyst[yysp] = 40;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 157:
                    switch (yytok) {
                        case '(':
                            yyn = 69;
                            continue;
                    }
                    yyn = 237;
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
                case 158:
                    switch (yytok) {
                        case '(':
                            yyn = yyr27();
                            continue;
                    }
                    yyn = 237;
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
                case 159:
                    switch (yytok) {
                        case '.':
                            yyn = 70;
                            continue;
                        case FROM:
                        case ',':
                            yyn = yyr23();
                            continue;
                    }
                    yyn = 237;
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
                case 160:
                    switch (yytok) {
                        case '(':
                            yyn = yyr28();
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 44:
                    yyst[yysp] = 44;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 161:
                    yyn = yys44();
                    continue;

                case 45:
                    yyst[yysp] = 45;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 162:
                    yyn = yys45();
                    continue;

                case 46:
                    yyst[yysp] = 46;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 163:
                    yyn = yys46();
                    continue;

                case 47:
                    yyst[yysp] = 47;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 164:
                    yyn = yys47();
                    continue;

                case 48:
                    yyst[yysp] = 48;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 165:
                    yyn = yys48();
                    continue;

                case 49:
                    yyst[yysp] = 49;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 166:
                    yyn = yys49();
                    continue;

                case 50:
                    yyst[yysp] = 50;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 167:
                    yyn = yys50();
                    continue;

                case 51:
                    yyst[yysp] = 51;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 168:
                    yyn = yys51();
                    continue;

                case 52:
                    yyst[yysp] = 52;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 169:
                    yyn = yys52();
                    continue;

                case 53:
                    yyst[yysp] = 53;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 170:
                    yyn = yys53();
                    continue;

                case 54:
                    yyst[yysp] = 54;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 171:
                    yyn = yys54();
                    continue;

                case 55:
                    yyst[yysp] = 55;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 172:
                    yyn = yys55();
                    continue;

                case 56:
                    yyst[yysp] = 56;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 173:
                    yyn = yys56();
                    continue;

                case 57:
                    yyst[yysp] = 57;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 174:
                    yyn = yys57();
                    continue;

                case 58:
                    yyst[yysp] = 58;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 175:
                    yyn = yys58();
                    continue;

                case 59:
                    yyst[yysp] = 59;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 176:
                    yyn = yys59();
                    continue;

                case 60:
                    yyst[yysp] = 60;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 177:
                    yyn = yys60();
                    continue;

                case 61:
                    yyst[yysp] = 61;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 178:
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
                case 179:
                    yyn = yys62();
                    continue;

                case 63:
                    yyst[yysp] = 63;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 180:
                    yyn = yys63();
                    continue;

                case 64:
                    yyst[yysp] = 64;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 181:
                    switch (yytok) {
                        case ')':
                            yyn = 73;
                            continue;
                    }
                    yyn = 237;
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
                case 182:
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
                case 183:
                    yyn = yys66();
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
                case 184:
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
                    yyn = 237;
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
                case 185:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 77;
                            continue;
                    }
                    yyn = 237;
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
                case 186:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 78;
                            continue;
                    }
                    yyn = 237;
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
                case 187:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 79;
                            continue;
                    }
                    yyn = 237;
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
                case 188:
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
                case 189:
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
                case 190:
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
                case 191:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 81;
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 75:
                    yyst[yysp] = 75;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 192:
                    switch (yytok) {
                        case FROM:
                            yyn = yyr21();
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 76:
                    yyst[yysp] = 76;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 193:
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
                case 194:
                    yyn = yys77();
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
                case 195:
                    switch (yytok) {
                        case ')':
                            yyn = 86;
                            continue;
                        case '.':
                            yyn = 87;
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 79:
                    yyst[yysp] = 79;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 196:
                    switch (yytok) {
                        case FROM:
                        case ',':
                            yyn = yyr24();
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 80:
                    yyst[yysp] = 80;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 197:
                    switch (yytok) {
                        case ')':
                            yyn = yyr59();
                            continue;
                    }
                    yyn = 237;
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
                case 198:
                    yyn = yys81();
                    continue;

                case 82:
                    yyst[yysp] = 82;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 199:
                    switch (yytok) {
                        case ';':
                            yyn = yyr6();
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 83:
                    yyst[yysp] = 83;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 200:
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
                case 201:
                    yyn = yys84();
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
                case 202:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 77;
                            continue;
                    }
                    yyn = 237;
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
                case 203:
                    switch (yytok) {
                        case FROM:
                        case ',':
                            yyn = yyr25();
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 87:
                    yyst[yysp] = 87;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 204:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 94;
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 88:
                    yyst[yysp] = 88;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 205:
                    switch (yytok) {
                        case AT:
                            yyn = 96;
                            continue;
                        case ';':
                        case ON:
                            yyn = yyr16();
                            continue;
                    }
                    yyn = 237;
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
                case 206:
                    yyn = yys89();
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
                case 207:
                    yyn = yys90();
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
                case 208:
                    switch (yytok) {
                        case '(':
                            yyn = 99;
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 92:
                    yyst[yysp] = 92;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 209:
                    yyn = yys92();
                    continue;

                case 93:
                    yyst[yysp] = 93;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 210:
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
                case 211:
                    switch (yytok) {
                        case ')':
                            yyn = 100;
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 95:
                    yyst[yysp] = 95;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 212:
                    switch (yytok) {
                        case ON:
                            yyn = 102;
                            continue;
                        case ';':
                            yyn = yyr18();
                            continue;
                    }
                    yyn = 237;
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
                case 213:
                    switch (yytok) {
                        case ALL_NODES:
                            yyn = 103;
                            continue;
                        case NODES:
                            yyn = 104;
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 97:
                    yyst[yysp] = 97;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 214:
                    yyn = yys97();
                    continue;

                case 98:
                    yyst[yysp] = 98;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 215:
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
                case 216:
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
                case 217:
                    switch (yytok) {
                        case FROM:
                        case ',':
                            yyn = yyr26();
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 101:
                    yyst[yysp] = 101;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 218:
                    switch (yytok) {
                        case ';':
                            yyn = yyr7();
                            continue;
                    }
                    yyn = 237;
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
                case 219:
                    switch (yytok) {
                        case TIME:
                            yyn = 106;
                            continue;
                    }
                    yyn = 237;
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
                case 220:
                    switch (yytok) {
                        case ';':
                        case ON:
                            yyn = yyr15();
                            continue;
                    }
                    yyn = 237;
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
                case 221:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 77;
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 105:
                    yyst[yysp] = 105;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 222:
                    yyn = yys105();
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
                case 223:
                    yyn = yys106();
                    continue;

                case 107:
                    yyst[yysp] = 107;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 224:
                    switch (yytok) {
                        case ';':
                        case ON:
                            yyn = yyr14();
                            continue;
                    }
                    yyn = 237;
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
                case 225:
                    yyn = yys108();
                    continue;

                case 109:
                    yyst[yysp] = 109;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 226:
                    yyn = yys109();
                    continue;

                case 110:
                    yyst[yysp] = 110;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 227:
                    yyn = yys110();
                    continue;

                case 111:
                    yyst[yysp] = 111;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 228:
                    switch (yytok) {
                        case ';':
                            yyn = yyr17();
                            continue;
                    }
                    yyn = 237;
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
                case 229:
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
                case 230:
                    switch (yytok) {
                        case LINK:
                            yyn = 115;
                            continue;
                    }
                    yyn = 237;
                    continue;

                case 114:
                    yyst[yysp] = 114;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 231:
                    yyn = yys114();
                    continue;

                case 115:
                    yyst[yysp] = 115;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 232:
                    yyn = yys115();
                    continue;

                case 116:
                    yyst[yysp] = 116;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 233:
                    yyn = yys116();
                    continue;

                case 234:
                    return true;
                case 235:
                    yyerror("stack overflow");
                case 236:
                    return false;
                case 237:
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
                return 5;
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case SELECT:
                return 10;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
            case ENDINPUT:
                return yyr2();
        }
        return 237;
    }

    private int yys2() {
        switch (yytok) {
            case AND:
                return 16;
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
        return 237;
    }

    private int yys4() {
        switch (yytok) {
            case EXPLAIN:
                return 5;
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case SELECT:
                return 10;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
            case ENDINPUT:
                return yyr2();
        }
        return 237;
    }

    private int yys6() {
        switch (yytok) {
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr58();
        }
        return 237;
    }

    private int yys7() {
        switch (yytok) {
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr55();
        }
        return 237;
    }

    private int yys8() {
        switch (yytok) {
            case SUM:
            case TRUE:
            case TIME:
            case ALL_NODES:
            case error:
            case WHEN:
            case INTEGER_LITERAL:
            case ENDINPUT:
            case '{':
            case '}':
            case FROM:
            case '!':
            case IDENTIFIER:
            case COUNT:
            case '[':
            case ']':
            case FALSE:
            case NODES:
            case SELECT:
            case EXPLAIN:
            case FLOAT_LITERAL:
            case STRING_LITERAL:
                return 237;
            case '(':
                return 35;
            case '.':
                return 36;
            case ':':
                return 37;
        }
        return yyr50();
    }

    private int yys9() {
        switch (yytok) {
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr54();
        }
        return 237;
    }

    private int yys11() {
        switch (yytok) {
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr56();
        }
        return 237;
    }

    private int yys12() {
        switch (yytok) {
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr57();
        }
        return 237;
    }

    private int yys13() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys14() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys15() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys16() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys17() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys18() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys19() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys20() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys21() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys22() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys23() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys24() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys25() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys26() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys27() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys28() {
        switch (yytok) {
            case '!':
            case INTEGER_LITERAL:
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
                return yyr5();
        }
        return 237;
    }

    private int yys29() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys30() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys31() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys32() {
        switch (yytok) {
            case '!':
            case INTEGER_LITERAL:
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
                return yyr3();
        }
        return 237;
    }

    private int yys35() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
            case ')':
                return yyr61();
        }
        return 237;
    }

    private int yys44() {
        switch (yytok) {
            case AND:
                return 16;
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
            case ';':
            case ON:
            case BEFORE:
            case ',':
            case ')':
            case AT:
            case AFTER:
                return yyr45();
        }
        return 237;
    }

    private int yys45() {
        switch (yytok) {
            case AND:
                return 16;
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
                return 71;
        }
        return 237;
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
                return yyr31();
        }
        return 237;
    }

    private int yys47() {
        switch (yytok) {
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
            case ';':
            case ON:
            case BEFORE:
            case ',':
            case ')':
            case AT:
            case AFTER:
                return yyr46();
        }
        return 237;
    }

    private int yys48() {
        switch (yytok) {
            case GEQ:
                return 18;
            case LEQ:
                return 19;
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
                return yyr39();
        }
        return 237;
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
                return yyr42();
        }
        return 237;
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
                return yyr41();
        }
        return 237;
    }

    private int yys51() {
        switch (yytok) {
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
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
            case ';':
            case ON:
            case BEFORE:
            case ',':
            case LINK:
            case ')':
            case AT:
            case AFTER:
                return yyr48();
        }
        return 237;
    }

    private int yys52() {
        switch (yytok) {
            case GEQ:
                return 18;
            case LEQ:
                return 19;
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
                return yyr40();
        }
        return 237;
    }

    private int yys53() {
        switch (yytok) {
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
            case ';':
            case ON:
            case BEFORE:
            case ',':
            case ')':
            case AT:
            case AFTER:
                return yyr47();
        }
        return 237;
    }

    private int yys54() {
        switch (yytok) {
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr36();
        }
        return 237;
    }

    private int yys55() {
        switch (yytok) {
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr35();
        }
        return 237;
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
                return yyr32();
        }
        return 237;
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
            case BETWEEN:
            case AND:
            case '>':
            case OR:
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
                return yyr33();
        }
        return 237;
    }

    private int yys58() {
        switch (yytok) {
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr34();
        }
        return 237;
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
            case BETWEEN:
            case AND:
            case '>':
            case OR:
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
                return yyr43();
        }
        return 237;
    }

    private int yys60() {
        switch (yytok) {
            case GEQ:
                return 18;
            case LEQ:
                return 19;
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
                return yyr38();
        }
        return 237;
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
            case BETWEEN:
            case AND:
            case '>':
            case OR:
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
                return yyr44();
        }
        return 237;
    }

    private int yys62() {
        switch (yytok) {
            case '!':
            case INTEGER_LITERAL:
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
                return yyr4();
        }
        return 237;
    }

    private int yys63() {
        switch (yytok) {
            case AND:
                return 16;
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
                return 72;
            case ')':
                return yyr60();
        }
        return 237;
    }

    private int yys65() {
        switch (yytok) {
            case ':':
                return 74;
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr51();
        }
        return 237;
    }

    private int yys66() {
        switch (yytok) {
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr53();
        }
        return 237;
    }

    private int yys71() {
        switch (yytok) {
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr37();
        }
        return 237;
    }

    private int yys72() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
            case ')':
                return yyr61();
        }
        return 237;
    }

    private int yys73() {
        switch (yytok) {
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr49();
        }
        return 237;
    }

    private int yys76() {
        switch (yytok) {
            case WHEN:
                return 84;
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr13();
        }
        return 237;
    }

    private int yys77() {
        switch (yytok) {
            case ',':
                return 85;
            case WHEN:
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr30();
        }
        return 237;
    }

    private int yys81() {
        switch (yytok) {
            case '%':
            case TO:
            case GEQ:
            case BETWEEN:
            case AND:
            case '>':
            case OR:
            case ';':
            case ON:
            case BEFORE:
            case '/':
            case '-':
            case ',':
            case NEQ:
            case '+':
            case '<':
            case '=':
            case '*':
            case LINK:
            case ')':
            case LEQ:
            case EQ:
            case AT:
            case AFTER:
                return yyr52();
        }
        return 237;
    }

    private int yys83() {
        switch (yytok) {
            case AFTER:
                return 89;
            case BEFORE:
                return 90;
            case BETWEEN:
                return 91;
            case ';':
            case ON:
            case AT:
                return yyr11();
        }
        return 237;
    }

    private int yys84() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys89() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys90() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys92() {
        switch (yytok) {
            case AND:
                return 16;
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr12();
        }
        return 237;
    }

    private int yys93() {
        switch (yytok) {
            case WHEN:
            case BETWEEN:
            case ';':
            case ON:
            case BEFORE:
            case AT:
            case AFTER:
                return yyr29();
        }
        return 237;
    }

    private int yys97() {
        switch (yytok) {
            case AND:
                return 16;
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
        return 237;
    }

    private int yys98() {
        switch (yytok) {
            case AND:
                return 16;
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
        return 237;
    }

    private int yys99() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys105() {
        switch (yytok) {
            case AND:
                return 16;
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
                return 108;
        }
        return 237;
    }

    private int yys106() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys108() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys109() {
        switch (yytok) {
            case AND:
                return 16;
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
                return 112;
            case ';':
                return yyr20();
        }
        return 237;
    }

    private int yys110() {
        switch (yytok) {
            case AND:
                return 16;
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
                return 113;
        }
        return 237;
    }

    private int yys112() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys114() {
        switch (yytok) {
            case AND:
                return 16;
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
                return yyr19();
        }
        return 237;
    }

    private int yys115() {
        switch (yytok) {
            case FALSE:
                return 6;
            case FLOAT_LITERAL:
                return 7;
            case IDENTIFIER:
                return 8;
            case INTEGER_LITERAL:
                return 9;
            case STRING_LITERAL:
                return 11;
            case TRUE:
                return 12;
            case '!':
                return 13;
            case '(':
                return 14;
            case '-':
                return 15;
        }
        return 237;
    }

    private int yys116() {
        switch (yytok) {
            case AND:
                return 16;
            case EQ:
                return 17;
            case GEQ:
                return 18;
            case LEQ:
                return 19;
            case LINK:
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
                return yyr8();
        }
        return 237;
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

    private int yyr14() { // AtNodes : AT NODES IdentifierList
        {yyrv = new AtNodes(((IdentifierList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 95;
    }

    private int yyr15() { // AtNodes : AT ALL_NODES
        {yyrv = new AtNodes();}
        yysv[yysp-=2] = yyrv;
        return 95;
    }

    private int yyr16() { // AtNodes : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 95;
    }

    private int yyr19() { // AtTimeExtended : TO Expression
        {yyrv = ((Expression)yysv[yysp-1]);}
        yysv[yysp-=2] = yyrv;
        return 111;
    }

    private int yyr20() { // AtTimeExtended : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 111;
    }

    private int yyr31() { // Expression : '-' Expression
        {yyrv = new Minus(currentEnv, ((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return yypExpression();
    }

    private int yyr32() { // Expression : Expression '+' Expression
        {yyrv = new Plus(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr33() { // Expression : Expression '-' Expression
        {yyrv = new Minus(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr34() { // Expression : Expression '/' Expression
        {yyrv = new Divide(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr35() { // Expression : Expression '*' Expression
        {yyrv = new Multiply(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr36() { // Expression : Expression '%' Expression
        {yyrv = new Mod(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr37() { // Expression : '(' Expression ')'
        {yyrv = ((Expression)yysv[yysp-2]);}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr38() { // Expression : Expression '=' Expression
        {yyrv = new EQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr39() { // Expression : Expression EQ Expression
        {yyrv = new EQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr40() { // Expression : Expression NEQ Expression
        {yyrv = new NEQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr41() { // Expression : Expression LEQ Expression
        {yyrv = new LEQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr42() { // Expression : Expression GEQ Expression
        {yyrv = new GEQExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr43() { // Expression : Expression '<' Expression
        {yyrv = new LessThanExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr44() { // Expression : Expression '>' Expression
        {yyrv = new GreaterThanExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr45() { // Expression : '!' Expression
        {yyrv = new NotExpression(currentEnv, ((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return yypExpression();
    }

    private int yyr46() { // Expression : Expression AND Expression
        {yyrv = new And(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr47() { // Expression : Expression OR Expression
        {yyrv = new Or(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr48() { // Expression : Expression LINK Expression
        {yyrv = new LinkConditionalExpression(currentEnv, ((Expression)yysv[yysp-3]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr49() { // Expression : IDENTIFIER '(' ExpressionList ')'
        {yyrv = new FuncCall(currentEnv, ((String)yysv[yysp-4]), ((ExpressionList)yysv[yysp-2]));}
        yysv[yysp-=4] = yyrv;
        return yypExpression();
    }

    private int yyr50() { // Expression : IDENTIFIER
        {yyrv = new Variable(((String)yysv[yysp-1]), currentEnv, true);}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr51() { // Expression : IDENTIFIER '.' IDENTIFIER
        {yyrv = new Variable(((String)yysv[yysp-3]), ((String)yysv[yysp-1]), currentEnv, true);}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr52() { // Expression : IDENTIFIER '.' IDENTIFIER ':' IDENTIFIER
        {yyrv = new Variable(((String)yysv[yysp-5]), ((String)yysv[yysp-3]), ((String)yysv[yysp-1]), currentEnv, true);}
        yysv[yysp-=5] = yyrv;
        return yypExpression();
    }

    private int yyr53() { // Expression : IDENTIFIER ':' IDENTIFIER
        {yyrv = new Variable("", ((String)yysv[yysp-3]), ((String)yysv[yysp-1]), currentEnv, true);}
        yysv[yysp-=3] = yyrv;
        return yypExpression();
    }

    private int yyr54() { // Expression : INTEGER_LITERAL
        {yyrv = new IntegerLiteral(currentEnv, ((Integer)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr55() { // Expression : FLOAT_LITERAL
        {yyrv = new FloatLiteral(currentEnv, ((Double)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr56() { // Expression : STRING_LITERAL
        {yyrv = new StringLiteral(currentEnv, ((String)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr57() { // Expression : TRUE
        {yyrv = new IntegerLiteral(currentEnv, 1);}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yyr58() { // Expression : FALSE
        {yyrv = new IntegerLiteral(currentEnv, 0);}
        yysv[yysp-=1] = yyrv;
        return yypExpression();
    }

    private int yypExpression() {
        switch (yyst[yysp-1]) {
            case 115: return 116;
            case 112: return 114;
            case 108: return 110;
            case 106: return 109;
            case 99: return 105;
            case 90: return 98;
            case 89: return 97;
            case 84: return 92;
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
            case 13: return 44;
            case 4: return 2;
            case 0: return 2;
            default: return 63;
        }
    }

    private int yyr59() { // ExpressionList : Expression ',' ExpressionList
        {yyrv = new ExpressionList(((Expression)yysv[yysp-3]), ((ExpressionList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypExpressionList();
    }

    private int yyr60() { // ExpressionList : Expression
        {yyrv = new ExpressionList(((Expression)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypExpressionList();
    }

    private int yyr61() { // ExpressionList : /* empty */
        {yyrv = new ExpressionList();}
        yysv[yysp-=0] = yyrv;
        return yypExpressionList();
    }

    private int yypExpressionList() {
        switch (yyst[yysp-1]) {
            case 35: return 64;
            default: return 80;
        }
    }

    private int yyr29() { // IdentifierList : IDENTIFIER ',' IdentifierList
        {yyrv = new IdentifierList(((String)yysv[yysp-3]), ((IdentifierList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypIdentifierList();
    }

    private int yyr30() { // IdentifierList : IDENTIFIER
        {yyrv = new IdentifierList(((String)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypIdentifierList();
    }

    private int yypIdentifierList() {
        switch (yyst[yysp-1]) {
            case 85: return 93;
            case 68: return 76;
            default: return 107;
        }
    }

    private int yyr17() { // OnTime : ON TIME Expression AtTimeExtended
        {yyrv = new OnTime(((Expression)yysv[yysp-2]), ((Expression)yysv[yysp-1]));}
        yysv[yysp-=4] = yyrv;
        return 101;
    }

    private int yyr18() { // OnTime : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 101;
    }

    private int yyr23() { // Param : IDENTIFIER
        {yyrv = new Param(((String)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return 38;
    }

    private int yyr24() { // Param : IDENTIFIER '.' IDENTIFIER
        {yyrv = new Param(((String)yysv[yysp-3]), ((String)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return 38;
    }

    private int yyr25() { // Param : Aggregator '(' IDENTIFIER ')'
        {yyrv = new Param(((String)yysv[yysp-2]));}
        yysv[yysp-=4] = yyrv;
        return 38;
    }

    private int yyr26() { // Param : Aggregator '(' IDENTIFIER '.' IDENTIFIER ')'
        {yyrv = new Param(((String)yysv[yysp-4]), ((String)yysv[yysp-2]));}
        yysv[yysp-=6] = yyrv;
        return 38;
    }

    private int yyr21() { // ParamList : Param ',' ParamList
        {yyrv = new ParamList(((Param)yysv[yysp-3]), ((ParamList)yysv[yysp-1]));}
        yysv[yysp-=3] = yyrv;
        return yypParamList();
    }

    private int yyr22() { // ParamList : Param
        {yyrv = new ParamList(((Param)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypParamList();
    }

    private int yypParamList() {
        switch (yyst[yysp-1]) {
            case 10: return 39;
            default: return 75;
        }
    }

    private int yyr6() { // Query : SELECT ParamList FROM IdentifierList QueryEnd
        {yyrv = new Query(((ParamList)yysv[yysp-4]), ((IdentifierList)yysv[yysp-2]), ((QueryEnd)yysv[yysp-1]), currentEnv);}
        yysv[yysp-=5] = yyrv;
        switch (yyst[yysp-1]) {
            case 5: return 34;
            default: return 3;
        }
    }

    private int yyr7() { // QueryEnd : When TimeSearch AtNodes OnTime
        {yyrv = new QueryEnd(((When)yysv[yysp-4]), ((AtNodes)yysv[yysp-2]), ((OnTime)yysv[yysp-1]));}
        yysv[yysp-=4] = yyrv;
        return 82;
    }

    private int yyr3() { // Statement : Query ';'
        {((Query)yysv[yysp-2]).execute();}
        yysv[yysp-=2] = yyrv;
        return 4;
    }

    private int yyr4() { // Statement : EXPLAIN Query ';'
        {((Query)yysv[yysp-2]).explain();}
        yysv[yysp-=3] = yyrv;
        return 4;
    }

    private int yyr5() { // Statement : Expression ';'
        {yyrv = new EvalExpression(((Expression)yysv[yysp-2]));}
        yysv[yysp-=2] = yyrv;
        return 4;
    }

    private int yyr27() { // Aggregator : COUNT
        {yyrv = new Aggregator(AggregatorType.COUNT);}
        yysv[yysp-=1] = yyrv;
        return 40;
    }

    private int yyr28() { // Aggregator : SUM
        {yyrv = new Aggregator(AggregatorType.SUM);}
        yysv[yysp-=1] = yyrv;
        return 40;
    }

    private int yyr8() { // TimeSearch : BETWEEN '(' Expression ',' Expression ')' LINK Expression
        {}
        yysv[yysp-=8] = yyrv;
        return 88;
    }

    private int yyr9() { // TimeSearch : BEFORE Expression
        {}
        yysv[yysp-=2] = yyrv;
        return 88;
    }

    private int yyr10() { // TimeSearch : AFTER Expression
        {}
        yysv[yysp-=2] = yyrv;
        return 88;
    }

    private int yyr11() { // TimeSearch : /* empty */
        return 88;
    }

    private int yyr12() { // When : WHEN Expression
        {yyrv = new When(((Expression)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return 83;
    }

    private int yyr13() { // When : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 83;
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

/* implementation of the nextToken() using lexer.yylex() that throws an
exception 
*/

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
