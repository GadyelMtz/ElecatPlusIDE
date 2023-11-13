//Basado en: ClassDeclaration,ClassBody

parser grammar SimpleParser;
options {
	tokenVocab = SimpleLexer;
}
programa: PROGRAMA ID  cuerpoPrograma EOF;
cuerpoPrograma: BRACKET_ABRIR sentencia+ BRACKET_CERRAR;
sentencia:
	declaracion
	| asignacion
	| condicionalif
	| cicloWhile
	| accion;
declaracion: (tipo_dato | COMPONENTE) (
		(ID FIN_LINEA)
		| asignacion
	);
asignacion: ID SIGNO_IGUAL expresion FIN_LINEA;
condicionalif:
	SI PAR_ABRIR expresion PAR_CERRAR BRACKET_ABRIR sentencia* BRACKET_CERRAR (
		SI_NO (SI PAR_ABRIR expresion PAR_CERRAR)? BRACKET_ABRIR sentencia* BRACKET_CERRAR
	)?;
cicloWhile:
	REPETIR MIENTRAS PAR_ABRIR expresion PAR_CERRAR BRACKET_ABRIR sentencia* BRACKET_CERRAR;
accion:
	esperar
	| ACCION PAR_ABRIR ID COMA (girar | escribir) PAR_CERRAR FIN_LINEA;
esperar:
	ESPERAR PAR_ABRIR numero (COMA TIEMPO)? PAR_CERRAR FIN_LINEA;
girar: GIRAR PAR_ABRIR numero COMA DIRECCION PAR_CERRAR;
escribir: ESCRIBIR PAR_ABRIR (ID | CADENA | numero) PAR_CERRAR;
termino: (BOOLEANO | ID)
	| numero
	| PAR_ABRIR expresion PAR_CERRAR;
expresion: termino operacionBinaria*;
operacionBinaria: (op_bin termino);
numero: (DECIMAL | ENTERO);
op_bin: (OP_LOGICO | OP_COMPARADOR | OP_ARITMETICO);
tipo_dato: (
		TD_DECIMAL
		| TD_ENTERO
		| TD_CARACTER
		| TD_CADENA
		| TD_BOOLEANO
	);