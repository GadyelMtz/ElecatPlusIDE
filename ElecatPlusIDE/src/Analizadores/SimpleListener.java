package Analizadores;
// Generated from Simple.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleParser}.
 */
public interface SimpleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(SimpleParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(SimpleParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentencia(SimpleParser.SentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentencia(SimpleParser.SentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(SimpleParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(SimpleParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(SimpleParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(SimpleParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#condicionalif}.
	 * @param ctx the parse tree
	 */
	void enterCondicionalif(SimpleParser.CondicionalifContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#condicionalif}.
	 * @param ctx the parse tree
	 */
	void exitCondicionalif(SimpleParser.CondicionalifContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#cicloWhile}.
	 * @param ctx the parse tree
	 */
	void enterCicloWhile(SimpleParser.CicloWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#cicloWhile}.
	 * @param ctx the parse tree
	 */
	void exitCicloWhile(SimpleParser.CicloWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#accion}.
	 * @param ctx the parse tree
	 */
	void enterAccion(SimpleParser.AccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#accion}.
	 * @param ctx the parse tree
	 */
	void exitAccion(SimpleParser.AccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#esperar}.
	 * @param ctx the parse tree
	 */
	void enterEsperar(SimpleParser.EsperarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#esperar}.
	 * @param ctx the parse tree
	 */
	void exitEsperar(SimpleParser.EsperarContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#girar}.
	 * @param ctx the parse tree
	 */
	void enterGirar(SimpleParser.GirarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#girar}.
	 * @param ctx the parse tree
	 */
	void exitGirar(SimpleParser.GirarContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#escribir}.
	 * @param ctx the parse tree
	 */
	void enterEscribir(SimpleParser.EscribirContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#escribir}.
	 * @param ctx the parse tree
	 */
	void exitEscribir(SimpleParser.EscribirContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#termino}.
	 * @param ctx the parse tree
	 */
	void enterTermino(SimpleParser.TerminoContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#termino}.
	 * @param ctx the parse tree
	 */
	void exitTermino(SimpleParser.TerminoContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresion(SimpleParser.ExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(SimpleParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#operacionBinaria}.
	 * @param ctx the parse tree
	 */
	void enterOperacionBinaria(SimpleParser.OperacionBinariaContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#operacionBinaria}.
	 * @param ctx the parse tree
	 */
	void exitOperacionBinaria(SimpleParser.OperacionBinariaContext ctx);
}