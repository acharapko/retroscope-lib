// Output created by jacc on Sat Dec 09 19:35:56 EST 2017


package retroscope.datamodel.parser;
import retroscope.datamodel.datastruct.*;
import retroscope.datamodel.datastruct.variables.*;
import retroscope.datamodel.datastruct.misc.*;
import retroscope.datamodel.datastruct.sets.*;
import retroscope.datamodel.datastruct.struct.*;
import java.util.ArrayList;



public class DataParser implements dataTokens {
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
                case 73:
                    switch (yytok) {
                        case '<':
                            yyn = 2;
                            continue;
                        case ENDINPUT:
                            yyn = yyr3();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 74:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = 146;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 2:
                    yyst[yysp] = 2;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 75:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 3;
                            continue;
                        case INTEGER_LITERAL:
                            yyn = 4;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 3:
                    yyst[yysp] = 3;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 76:
                    switch (yytok) {
                        case ',':
                            yyn = 5;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 4:
                    yyst[yysp] = 4;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 77:
                    switch (yytok) {
                        case '>':
                            yyn = 6;
                            continue;
                    }
                    yyn = 149;
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
                case 78:
                    switch (yytok) {
                        case INTEGER_LITERAL:
                            yyn = 7;
                            continue;
                    }
                    yyn = 149;
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
                case 79:
                    switch (yytok) {
                        case ':':
                            yyn = 8;
                            continue;
                    }
                    yyn = 149;
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
                case 80:
                    switch (yytok) {
                        case ',':
                            yyn = 9;
                            continue;
                    }
                    yyn = 149;
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
                case 81:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 12;
                            continue;
                    }
                    yyn = 149;
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
                case 82:
                    switch (yytok) {
                        case INTEGER_LITERAL:
                            yyn = 13;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 10:
                    yyst[yysp] = 10;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 83:
                    switch (yytok) {
                        case ',':
                            yyn = 14;
                            continue;
                        case ENDINPUT:
                            yyn = yyr5();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 11:
                    yyst[yysp] = 11;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 84:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr2();
                            continue;
                    }
                    yyn = 149;
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
                case 85:
                    switch (yytok) {
                        case ':':
                            yyn = 15;
                            continue;
                        case '|':
                            yyn = 16;
                            continue;
                    }
                    yyn = 149;
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
                case 86:
                    switch (yytok) {
                        case '>':
                            yyn = 17;
                            continue;
                    }
                    yyn = 149;
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
                case 87:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 12;
                            continue;
                    }
                    yyn = 149;
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
                case 88:
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
                case 89:
                    switch (yytok) {
                        case INTEGER_LITERAL:
                            yyn = 36;
                            continue;
                        case ':':
                            yyn = yyr21();
                            continue;
                    }
                    yyn = 149;
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
                case 90:
                    switch (yytok) {
                        case ':':
                            yyn = 37;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 18:
                    yyst[yysp] = 18;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 91:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr4();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 19:
                    yyst[yysp] = 19;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 92:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr11();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 20:
                    yyst[yysp] = 20;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 93:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr13();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 21:
                    yyst[yysp] = 21;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 94:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr9();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 22:
                    yyst[yysp] = 22;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 95:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr10();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 23:
                    yyst[yysp] = 23;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 96:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr8();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 24:
                    yyst[yysp] = 24;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 97:
                    switch (yytok) {
                        case ENDINPUT:
                        case ',':
                            yyn = yyr6();
                            continue;
                    }
                    yyn = 149;
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
                case 98:
                    switch (yytok) {
                        case '{':
                            yyn = 38;
                            continue;
                    }
                    yyn = 149;
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
                case 99:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr17();
                            continue;
                    }
                    yyn = 149;
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
                case 100:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr16();
                            continue;
                    }
                    yyn = 149;
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
                case 101:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr12();
                            continue;
                    }
                    yyn = 149;
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
                case 102:
                    switch (yytok) {
                        case '{':
                            yyn = 39;
                            continue;
                    }
                    yyn = 149;
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
                case 103:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr15();
                            continue;
                    }
                    yyn = 149;
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
                case 104:
                    switch (yytok) {
                        case FLOAT_LITERAL:
                            yyn = 26;
                            continue;
                        case INTEGER_LITERAL:
                            yyn = 27;
                            continue;
                    }
                    yyn = 149;
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
                case 105:
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
                case 106:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 47;
                            continue;
                        case ']':
                            yyn = yyr34();
                            continue;
                    }
                    yyn = 149;
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
                case 107:
                    yyn = yys34();
                    continue;

                case 35:
                    yyst[yysp] = 35;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 108:
                    switch (yytok) {
                        case ':':
                            yyn = 49;
                            continue;
                    }
                    yyn = 149;
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
                case 109:
                    switch (yytok) {
                        case ',':
                            yyn = 50;
                            continue;
                        case ':':
                            yyn = yyr20();
                            continue;
                    }
                    yyn = 149;
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
                case 110:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 12;
                            continue;
                    }
                    yyn = 149;
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
                case 111:
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
                case 112:
                    yyn = yys39();
                    continue;

                case 40:
                    yyst[yysp] = 40;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 113:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr14();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 41:
                    yyst[yysp] = 41;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 114:
                    switch (yytok) {
                        case ',':
                            yyn = 54;
                            continue;
                        case '}':
                        case '>':
                            yyn = yyr27();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 42:
                    yyst[yysp] = 42;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 115:
                    switch (yytok) {
                        case '>':
                            yyn = 55;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 43:
                    yyst[yysp] = 43;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 116:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case '>':
                            yyn = yyr31();
                            continue;
                    }
                    yyn = 149;
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
                case 117:
                    switch (yytok) {
                        case ':':
                            yyn = 56;
                            continue;
                        case '|':
                            yyn = 57;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 45:
                    yyst[yysp] = 45;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 118:
                    switch (yytok) {
                        case ',':
                            yyn = 58;
                            continue;
                        case ']':
                            yyn = yyr33();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 46:
                    yyst[yysp] = 46;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 119:
                    switch (yytok) {
                        case ']':
                            yyn = 59;
                            continue;
                    }
                    yyn = 149;
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
                case 120:
                    switch (yytok) {
                        case ':':
                            yyn = 60;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 48:
                    yyst[yysp] = 48;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 121:
                    switch (yytok) {
                        case '}':
                            yyn = 61;
                            continue;
                    }
                    yyn = 149;
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
                case 122:
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
                case 123:
                    switch (yytok) {
                        case INTEGER_LITERAL:
                            yyn = 36;
                            continue;
                        case ':':
                            yyn = yyr21();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 51:
                    yyst[yysp] = 51;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 124:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr1();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 52:
                    yyst[yysp] = 52;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 125:
                    switch (yytok) {
                        case '}':
                            yyn = 64;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 53:
                    yyst[yysp] = 53;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 126:
                    switch (yytok) {
                        case '}':
                            yyn = 65;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 54:
                    yyst[yysp] = 54;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 127:
                    yyn = yys54();
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
                case 128:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr25();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 56:
                    yyst[yysp] = 56;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 129:
                    yyn = yys56();
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
                case 130:
                    switch (yytok) {
                        case INTEGER_LITERAL:
                            yyn = 36;
                            continue;
                        case ':':
                            yyn = yyr21();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 58:
                    yyst[yysp] = 58;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 131:
                    switch (yytok) {
                        case IDENTIFIER:
                            yyn = 47;
                            continue;
                        case ']':
                            yyn = yyr34();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 59:
                    yyst[yysp] = 59;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 132:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr18();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 60:
                    yyst[yysp] = 60;
                    yysv[yysp] = (lexer.semanticValue
                                 );
                    yytok = (nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 133:
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
                case 134:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr22();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 62:
                    yyst[yysp] = 62;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 135:
                    switch (yytok) {
                        case ENDINPUT:
                        case ',':
                            yyn = yyr7();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 63:
                    yyst[yysp] = 63;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 136:
                    switch (yytok) {
                        case ':':
                            yyn = yyr19();
                            continue;
                    }
                    yyn = 149;
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
                case 137:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr23();
                            continue;
                    }
                    yyn = 149;
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
                case 138:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case ']':
                        case ENDINPUT:
                        case '>':
                            yyn = yyr24();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 66:
                    yyst[yysp] = 66;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 139:
                    switch (yytok) {
                        case '}':
                        case '>':
                            yyn = yyr26();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 67:
                    yyst[yysp] = 67;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 140:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case '>':
                            yyn = yyr29();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 68:
                    yyst[yysp] = 68;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 141:
                    switch (yytok) {
                        case ':':
                            yyn = 71;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 69:
                    yyst[yysp] = 69;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 142:
                    switch (yytok) {
                        case ']':
                            yyn = yyr32();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 70:
                    yyst[yysp] = 70;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 143:
                    switch (yytok) {
                        case ',':
                        case ']':
                            yyn = yyr35();
                            continue;
                    }
                    yyn = 149;
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
                case 144:
                    yyn = yys71();
                    continue;

                case 72:
                    yyst[yysp] = 72;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 145:
                    switch (yytok) {
                        case '}':
                        case ',':
                        case '>':
                            yyn = yyr30();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 146:
                    return true;
                case 147:
                    yyerror("stack overflow");
                case 148:
                    return false;
                case 149:
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

    private int yys15() {
        switch (yytok) {
            case APPEND:
                return 25;
            case FLOAT_LITERAL:
                return 26;
            case INTEGER_LITERAL:
                return 27;
            case NIL:
                return 28;
            case REMOVE:
                return 29;
            case STRING_LITERAL:
                return 30;
            case '-':
                return 31;
            case '<':
                return 32;
            case '[':
                return 33;
            case '{':
                return 34;
        }
        return 149;
    }

    private int yys32() {
        switch (yytok) {
            case APPEND:
                return 25;
            case FLOAT_LITERAL:
                return 26;
            case INTEGER_LITERAL:
                return 27;
            case NIL:
                return 28;
            case REMOVE:
                return 29;
            case STRING_LITERAL:
                return 30;
            case '-':
                return 31;
            case '<':
                return 32;
            case '[':
                return 33;
            case '{':
                return 34;
            case IDENTIFIER:
                return 44;
            case '>':
                return yyr28();
        }
        return 149;
    }

    private int yys34() {
        switch (yytok) {
            case APPEND:
                return 25;
            case FLOAT_LITERAL:
                return 26;
            case INTEGER_LITERAL:
                return 27;
            case NIL:
                return 28;
            case REMOVE:
                return 29;
            case STRING_LITERAL:
                return 30;
            case '-':
                return 31;
            case '<':
                return 32;
            case '[':
                return 33;
            case '{':
                return 34;
            case IDENTIFIER:
                return 44;
            case '}':
                return yyr28();
        }
        return 149;
    }

    private int yys38() {
        switch (yytok) {
            case APPEND:
                return 25;
            case FLOAT_LITERAL:
                return 26;
            case INTEGER_LITERAL:
                return 27;
            case NIL:
                return 28;
            case REMOVE:
                return 29;
            case STRING_LITERAL:
                return 30;
            case '-':
                return 31;
            case '<':
                return 32;
            case '[':
                return 33;
            case '{':
                return 34;
            case IDENTIFIER:
                return 44;
            case '}':
                return yyr28();
        }
        return 149;
    }

    private int yys39() {
        switch (yytok) {
            case APPEND:
                return 25;
            case FLOAT_LITERAL:
                return 26;
            case INTEGER_LITERAL:
                return 27;
            case NIL:
                return 28;
            case REMOVE:
                return 29;
            case STRING_LITERAL:
                return 30;
            case '-':
                return 31;
            case '<':
                return 32;
            case '[':
                return 33;
            case '{':
                return 34;
            case IDENTIFIER:
                return 44;
            case '}':
                return yyr28();
        }
        return 149;
    }

    private int yys49() {
        switch (yytok) {
            case APPEND:
                return 25;
            case FLOAT_LITERAL:
                return 26;
            case INTEGER_LITERAL:
                return 27;
            case NIL:
                return 28;
            case REMOVE:
                return 29;
            case STRING_LITERAL:
                return 30;
            case '-':
                return 31;
            case '<':
                return 32;
            case '[':
                return 33;
            case '{':
                return 34;
        }
        return 149;
    }

    private int yys54() {
        switch (yytok) {
            case APPEND:
                return 25;
            case FLOAT_LITERAL:
                return 26;
            case INTEGER_LITERAL:
                return 27;
            case NIL:
                return 28;
            case REMOVE:
                return 29;
            case STRING_LITERAL:
                return 30;
            case '-':
                return 31;
            case '<':
                return 32;
            case '[':
                return 33;
            case '{':
                return 34;
            case IDENTIFIER:
                return 44;
            case '}':
            case '>':
                return yyr28();
        }
        return 149;
    }

    private int yys56() {
        switch (yytok) {
            case APPEND:
                return 25;
            case FLOAT_LITERAL:
                return 26;
            case INTEGER_LITERAL:
                return 27;
            case NIL:
                return 28;
            case REMOVE:
                return 29;
            case STRING_LITERAL:
                return 30;
            case '-':
                return 31;
            case '<':
                return 32;
            case '[':
                return 33;
            case '{':
                return 34;
        }
        return 149;
    }

    private int yys60() {
        switch (yytok) {
            case APPEND:
                return 25;
            case FLOAT_LITERAL:
                return 26;
            case INTEGER_LITERAL:
                return 27;
            case NIL:
                return 28;
            case REMOVE:
                return 29;
            case STRING_LITERAL:
                return 30;
            case '-':
                return 31;
            case '<':
                return 32;
            case '[':
                return 33;
            case '{':
                return 34;
        }
        return 149;
    }

    private int yys71() {
        switch (yytok) {
            case APPEND:
                return 25;
            case FLOAT_LITERAL:
                return 26;
            case INTEGER_LITERAL:
                return 27;
            case NIL:
                return 28;
            case REMOVE:
                return 29;
            case STRING_LITERAL:
                return 30;
            case '-':
                return 31;
            case '<':
                return 32;
            case '[':
                return 33;
            case '{':
                return 34;
        }
        return 149;
    }

    private int yyr1() { // Data : '<' IDENTIFIER ',' INTEGER_LITERAL ',' INTEGER_LITERAL '>' ':' DataItems
        {data.setTimestamp(((Long)yysv[yysp-6])); data.setLogName(((String)yysv[yysp-8])); data.setNodeId(((Long)yysv[yysp-4]));}
        yysv[yysp-=9] = yyrv;
        return 1;
    }

    private int yyr2() { // Data : '<' INTEGER_LITERAL '>' ':' DataItems
        {data.setTimestamp(((Long)yysv[yysp-4]));}
        yysv[yysp-=5] = yyrv;
        return 1;
    }

    private int yyr3() { // Data : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return 1;
    }

    private int yyr6() { // DataItem : IDENTIFIER ':' Value
        {((RQLSymbol)yysv[yysp-1]).setName(((String)yysv[yysp-3])); yyrv = ((RQLSymbol)yysv[yysp-1]);}
        yysv[yysp-=3] = yyrv;
        return 10;
    }

    private int yyr7() { // DataItem : IDENTIFIER '|' IdList ':' Value
        {((RQLSymbol)yysv[yysp-1]).setName(((String)yysv[yysp-5])); ((RQLSymbol)yysv[yysp-1]).addNodeIDs(((IdList)yysv[yysp-3])); yyrv = ((RQLSymbol)yysv[yysp-1]);}
        yysv[yysp-=5] = yyrv;
        return 10;
    }

    private int yyr4() { // DataItems : DataItem ',' DataItems
        {data.appendDataItem(((RQLSymbol)yysv[yysp-3]));}
        yysv[yysp-=3] = yyrv;
        return yypDataItems();
    }

    private int yyr5() { // DataItems : DataItem
        {data.appendDataItem(((RQLSymbol)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypDataItems();
    }

    private int yypDataItems() {
        switch (yyst[yysp-1]) {
            case 14: return 18;
            case 8: return 11;
            default: return 51;
        }
    }

    private int yyr19() { // IdList : INTEGER_LITERAL ',' IdList
        {((IdList)yysv[yysp-1]).addId(((Long)yysv[yysp-3])); yyrv = ((IdList)yysv[yysp-1]);}
        yysv[yysp-=3] = yyrv;
        return yypIdList();
    }

    private int yyr20() { // IdList : INTEGER_LITERAL
        {yyrv = new IdList(((Long)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypIdList();
    }

    private int yyr21() { // IdList : /* empty */
        {yyrv = null;}
        yysv[yysp-=0] = yyrv;
        return yypIdList();
    }

    private int yypIdList() {
        switch (yyst[yysp-1]) {
            case 50: return 63;
            case 16: return 35;
            default: return 68;
        }
    }

    private int yyr25() { // List : '<' SetItems '>'
        {}
        yysv[yysp-=3] = yyrv;
        return 19;
    }

    private int yyr16() { // PosNum : INTEGER_LITERAL
        {yyrv = new LongRQLVariable(((Long)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypPosNum();
    }

    private int yyr17() { // PosNum : FLOAT_LITERAL
        {yyrv = new DoubleRQLVariable(((Double)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypPosNum();
    }

    private int yypPosNum() {
        switch (yyst[yysp-1]) {
            case 31: return 40;
            default: return 20;
        }
    }

    private int yyr22() { // Set : '{' SetItems '}'
        {yyrv = new RQLSet(((SetItems)yysv[yysp-2]));}
        yysv[yysp-=3] = yyrv;
        return 21;
    }

    private int yyr23() { // Set : APPEND '{' SetItems '}'
        {yyrv = new AppendRQLSet(((SetItems)yysv[yysp-2]));}
        yysv[yysp-=4] = yyrv;
        return 21;
    }

    private int yyr24() { // Set : REMOVE '{' SetItems '}'
        {yyrv = new RemoveRQLSet(((SetItems)yysv[yysp-2]));}
        yysv[yysp-=4] = yyrv;
        return 21;
    }

    private int yyr29() { // SetItem : IDENTIFIER ':' Value
        {((RQLSymbol)yysv[yysp-1]).setName(((String)yysv[yysp-3])); yyrv = ((RQLSymbol)yysv[yysp-1]);}
        yysv[yysp-=3] = yyrv;
        return 41;
    }

    private int yyr30() { // SetItem : IDENTIFIER '|' IdList ':' Value
        {((RQLSymbol)yysv[yysp-1]).setName(((String)yysv[yysp-5])); ((RQLSymbol)yysv[yysp-1]).addNodeIDs(((IdList)yysv[yysp-3])); yyrv = ((RQLSymbol)yysv[yysp-1]);}
        yysv[yysp-=5] = yyrv;
        return 41;
    }

    private int yyr31() { // SetItem : Value
        {yyrv = ((RQLSymbol)yysv[yysp-1]);}
        yysv[yysp-=1] = yyrv;
        return 41;
    }

    private int yyr26() { // SetItems : SetItem ',' SetItems
        {yyrv = ((SetItems)yysv[yysp-1]).addItem(((RQLSymbol)yysv[yysp-3]));}
        yysv[yysp-=3] = yyrv;
        return yypSetItems();
    }

    private int yyr27() { // SetItems : SetItem
        {yyrv = new SetItems(((RQLSymbol)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypSetItems();
    }

    private int yyr28() { // SetItems : /* empty */
        {yyrv = new SetItems();}
        yysv[yysp-=0] = yyrv;
        return yypSetItems();
    }

    private int yypSetItems() {
        switch (yyst[yysp-1]) {
            case 39: return 53;
            case 38: return 52;
            case 34: return 48;
            case 32: return 42;
            default: return 66;
        }
    }

    private int yyr18() { // Struct : '[' StructItems ']'
        {yyrv = new RQLStruct(((SetItems)yysv[yysp-2]));}
        yysv[yysp-=3] = yyrv;
        return 22;
    }

    private int yyr35() { // StructItem : IDENTIFIER ':' Value
        {((RQLSymbol)yysv[yysp-1]).setName(((String)yysv[yysp-3])); yyrv = ((RQLSymbol)yysv[yysp-1]);}
        yysv[yysp-=3] = yyrv;
        return 45;
    }

    private int yyr32() { // StructItems : StructItem ',' StructItems
        {yyrv = ((SetItems)yysv[yysp-1]).addItem(((RQLSymbol)yysv[yysp-3]));}
        yysv[yysp-=3] = yyrv;
        return yypStructItems();
    }

    private int yyr33() { // StructItems : StructItem
        {yyrv = new SetItems(((RQLSymbol)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypStructItems();
    }

    private int yyr34() { // StructItems : /* empty */
        {yyrv = new SetItems();}
        yysv[yysp-=0] = yyrv;
        return yypStructItems();
    }

    private int yypStructItems() {
        switch (yyst[yysp-1]) {
            case 33: return 46;
            default: return 69;
        }
    }

    private int yyr13() { // Val : PosNum
        {yyrv = ((RQLVariable)yysv[yysp-1]);}
        yysv[yysp-=1] = yyrv;
        return 23;
    }

    private int yyr14() { // Val : '-' PosNum
        {((RQLVariable)yysv[yysp-1]).negate(); yyrv = ((RQLVariable)yysv[yysp-1]);}
        yysv[yysp-=2] = yyrv;
        return 23;
    }

    private int yyr15() { // Val : STRING_LITERAL
        {yyrv = new StringRQLVariable(((String)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return 23;
    }

    private int yyr8() { // Value : Val
        {yyrv = ((RQLVariable)yysv[yysp-1]);}
        yysv[yysp-=1] = yyrv;
        return yypValue();
    }

    private int yyr9() { // Value : Set
        {yyrv = yysv[yysp-1];}
        yysv[yysp-=1] = yyrv;
        return yypValue();
    }

    private int yyr10() { // Value : Struct
        {yyrv = yysv[yysp-1];}
        yysv[yysp-=1] = yyrv;
        return yypValue();
    }

    private int yyr11() { // Value : List
        {}
        yysv[yysp-=1] = yyrv;
        return yypValue();
    }

    private int yyr12() { // Value : NIL
        {yyrv = new Null();}
        yysv[yysp-=1] = yyrv;
        return yypValue();
    }

    private int yypValue() {
        switch (yyst[yysp-1]) {
            case 71: return 72;
            case 60: return 70;
            case 56: return 67;
            case 49: return 62;
            case 15: return 24;
            default: return 43;
        }
    }

    protected String[] yyerrmsgs = {
    };



/* code in the parser class*/

private DataScanner lexer;
private RQLData data;

public RQLData getData() {
    return this.data;
}

/* constructor registering a lexer for lang */
public DataParser(DataScanner lexer){
    setScanner(lexer);
}

public DataParser(){
     data = new RQLData();
}

/* implementation of the nextToken() using lexer.yylex() that throws an
exception
*/

public void setScanner(DataScanner lexer) {
    this.lexer = lexer;
    this.data = new RQLData();
}

private int nextToken(){
      try{
          return lexer.yylex();
       }catch(java.io.IOException e){System.out.println("IO exception from lexer!");e.printStackTrace();}
       return 0;
}

private void yyerror(String msg) {
    System.out.println(
      "DataParser ERROR "+ msg + "\n" +
      " with token <<" + lexer.semanticValue + ">>"); }

}
