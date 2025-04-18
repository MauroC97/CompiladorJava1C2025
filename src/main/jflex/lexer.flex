package lyc.compiler;

import java_cup.runtime.Symbol;
import lyc.compiler.ParserSym;
import lyc.compiler.model.*;
import static lyc.compiler.constants.Constants.*;

%%

%public
%class Lexer
%unicode
%cup
%line
%column
%throws CompilerException
%eofval{
  return symbol(ParserSym.EOF);
%eofval}


%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}


LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Identation =  [ \t\f]

Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Assig = ":="
Colon = ":"
Comma = ","
OpenBracket = "("
CloseBracket = ")"
OpenCurlyBracket = "{"
CloseCurlyBracket = "}"
Letter = [a-zA-Z]
Digit = [0-9]

WhiteSpace = {LineTerminator} | {Identation}
Comment = "#+" ({Letter}|{Digit}|{WhiteSpace})* "+#"
Identifier = {Letter} ({Letter}|{Digit})*

IntegerConstant = {Digit}+
FloatConstant =  {Digit}+"."{Digit}+
//acepta cualquier cosa que no sea letra o numero. capaz convenga cambiarlo a carateres especificos mas adelante.
NonAlphanumeric = [^a-zA-Z0-9]
StringConstant = "\"" ({Letter}*|{Digit}*|{NonAlphanumeric}*)+ "\""

/* palabras reservadas */
Int = "Int"
Float = "Float"
String = "String"
Init = "init"
%%


/* keywords */

<YYINITIAL> {
  /* los token se reconocen en el orden en el que se escriben aca, primero en la lista tiene mas prioridad */

  /* palabras reservadas */
  {Init}                                   { return symbol(ParserSym.INIT); }
  /* tipos de dato */
  {Int}                                   { return symbol(ParserSym.INT); }
  {Float}                                 { return symbol(ParserSym.FLOAT); }
  {String}                                { return symbol(ParserSym.STRING); }
  /* identifiers */
  {Identifier}                             { return symbol(ParserSym.IDENTIFIER, yytext()); }
  /* Constants */
  {IntegerConstant}                        { return symbol(ParserSym.INTEGER_CONSTANT, yytext()); }
  {FloatConstant}                          { return symbol(ParserSym.FLOAT_CONSTANT, yytext()); }
  {StringConstant}                         { return symbol(ParserSym.STRING_CONSTANT, yytext()); }

  /* operators */
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }
  {OpenCurlyBracket}                        { return symbol(ParserSym.OPEN_CURLY_BRACKET); }
  {CloseCurlyBracket}                       { return symbol(ParserSym.CLOSE_CURLY_BRACKET); }
  {Colon}                                   { return symbol(ParserSym.COLON); }
  {Comma}                                   { return symbol(ParserSym.COMMA); }
  /* whitespace, comment */
  {WhiteSpace}                   { /* ignore */ }
  {Comment}                      { /* ignore */ }
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
