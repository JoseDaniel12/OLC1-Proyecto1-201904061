package analizadores;
import java_cup.runtime.Symbol; 
import compi1_proyecto1.AppState;
import compi1_proyecto1.Errorr;

%% 
%class lexico
%public 
%line 
%char 
%cup 
%unicode
%ignorecase
%init{ 
    yyline = 1; 
    yychar = 1; 
%init} 


comentario = ("//"(.|\'|\")*(\n|\r))
comentarioMultiple = ("<""!"[^.]*"!"">")
simbolo = (("\""[^\"]*"\"")|"\\""n"|"\\""\'"|"\\""\"")
entero = [0-9]
letra = [a-zA-Z]
identificador = ({letra}({letra}|{entero}|"_")*)
BLANCOS = [\r\t nbsp]+


%%
"CONJ" {return new Symbol(sym.conj,yyline,yychar, yytext());}
"!" {return new Symbol(sym.admiracion,yyline,yychar, yytext());}
"#" {return new Symbol(sym.hash,yyline,yychar, yytext());}
"$" {return new Symbol(sym.dollar,yyline,yychar, yytext());}
"%" {return new Symbol(sym.porcentaje,yyline,yychar, yytext());}
"&" {return new Symbol(sym.amberson,yyline,yychar, yytext());}
"(" {return new Symbol(sym.parentesisA,yyline,yychar, yytext());}
")" {return new Symbol(sym.parentesisC,yyline,yychar, yytext());}
"*" {return new Symbol(sym.asterisco,yyline,yychar, yytext());}
"+" {return new Symbol(sym.mas,yyline,yychar, yytext());} 
"," {return new Symbol(sym.coma,yyline,yychar, yytext());}
"-" {return new Symbol(sym.guion,yyline,yychar, yytext());} 
"." {return new Symbol(sym.punto,yyline,yychar, yytext());} 
"/" {return new Symbol(sym.slash,yyline,yychar, yytext());}
":" {return new Symbol(sym.dosPts,yyline,yychar, yytext());} 
";" {return new Symbol(sym.ptyComa,yyline,yychar, yytext());}
"<" {return new Symbol(sym.menor,yyline,yychar, yytext());} 
"=" {return new Symbol(sym.igual,yyline,yychar, yytext());} 
">" {return new Symbol(sym.mayor,yyline,yychar, yytext());} 
"?" {return new Symbol(sym.interrogacion,yyline,yychar, yytext());}
"@" {return new Symbol(sym.arroba,yyline,yychar, yytext());} 
"[" {return new Symbol(sym.corcheteA,yyline,yychar, yytext());} 
"^" {return new Symbol(sym.sombrero,yyline,yychar, yytext());} 
"_" {return new Symbol(sym.underScore,yyline,yychar, yytext());} 
"{" {return new Symbol(sym.llaveA,yyline,yychar, yytext());}
"|" {return new Symbol(sym.or,yyline,yychar, yytext());} 
"}" {return new Symbol(sym.llaveC,yyline,yychar, yytext());}
"~" {return new Symbol(sym.colocho,yyline,yychar, yytext());}
"\\" {return new Symbol(sym.slashInverso,yyline,yychar, yytext());}
{entero} {return new Symbol(sym.entero,yyline,yychar, yytext());}  
{letra} {return new Symbol(sym.letra,yyline,yychar, yytext());}
{simbolo} {return new Symbol(sym.simbolo,yyline,yychar, yytext());}
{identificador} {return new Symbol(sym.identificador,yyline,yychar, yytext());}
{BLANCOS} {} 
{comentario} {}
{comentarioMultiple} {}
\n {yychar=1;}

. {
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "+yyline+", en la columna: "+yychar);
    Errorr errorr = new Errorr();
    errorr.tipo = "Léxico";
    errorr.descripcion = "Este es un error lexico: "+yytext();
    errorr.linea = yyline + "";
    errorr.columna = yychar + "";
    AppState.errores.add(errorr);
}