/*Implementar o Exportador de Produtos com Factory Method
 * 
 * Na atividade anterior, implementamos um conjunto de classes utilizando o padrão Simple Factory 
 * para exportar uma lista de objetos para formatos como HTML e Markdown.
 * Uma nova implementação que inclui algumas melhorias foi feita no vídeo: 
 * https://www.youtube.com/watch?v=_fXOANKlNT4&list=PLyo0RUAM69UtO8Jjq71qgvRxcI2pTrB2m&index=8
 * , incluindo:
 * Separa o código em mais classes, dando maior flexibilidade (como a possibilidade de adicionar 
 * novas colunas na tabela gerada em HTML e outros formatos).
 * Atende ao Interface Segragation Principle - ISP (Princípio da Segregação de Interfaces) 
 * para termos muitas classes pequenos no lugar de poucas classes grandes. Isto torna as classes 
 * mais simples de serem entendidas individualmente.
 * Aplica o padrão Factory Method para permitir que as subclasses de AbstractExportadorListaProdutos 
 * possam decidir que tipo de coluna será instanciada.
 * Assista ao vídeo do link acima, baixe o projeto disponível na descrição do vídeo, 
 * a partir do qual deve realizar as alterações mostradas no vídeo. 

 * O projeto deve ser disponibilizado no GitHub e o link fornecido no moodle.
 *
 * Todo o código abaixo foi pego do GitHub do professor Manoel Campos, link abaixo:
 * https://github.com/manoelcampos/padroes-projetos/tree/master/criacionais/factory-method/exportador-problematico/src/main/java/com/manoelcampos/exportador
 *
 */
package com.mycompany.padraoprojetofactorymethodtablea;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 *
 * @author Lidiane
 */
/**
 *
 * @author Manoel Campos da Silva Filho
 */
public abstract class AbstractColuna implements Coluna {

    /**
     * Representa um determinado campo (atributo) de um objeto. A classe
     * {@link Field} faz parte da API de Reflection fornecida pela linguagem
     * Java. Reflection é um recurso para permitir acesso dinâmico a informações
     * de classes e objetos. Por meio da classe Field, podemos obter o nome de
     * um campo, o valor que um determinado objeto armazena para tal campo, o
     * tipo do campo, etc.
     *
     * <p>
     * Tal atributo só é usado se o construtor {@link #AbstractColuna(Field)}
     * for chamado. Caso contrário, o valor a ser exibido para a coluna é obtido
     * de {@link #funcaoValorColuna}.</p>
     */
    private Field campo;

    /**
     * Título a ser exibido na coluna. Por padrão, este é o nome do campo.
     */
    private String titulo;

    /**
     * Função ({@link Function}) que recebe um objeto da lista a ser exportada e
     * retorna uma String que representa o conteúdo a ser exibido para a coluna.
     * Tal atributo só é usado se o construtor
     * {@link #AbstractColuna(Function, String)} for chamado. Caso contrário, o
     * valor a ser exibido para a coluna é obtido de {@link #campo}.
     */
    private Function<Produto, Object> obtemValorColuna;

    /**
     * Instancia uma coluna para uma tabela, cujo valor a ser exibido será
     * obtido a partir de um campo (atributo) específico de um objeto.
     *
     * @param campo campo (atributo) do objeto a ser obtido o valor
     *
     * @see #AbstractColuna(Function, String)
     */
    protected AbstractColuna(Field campo) {
        this(campo.getName());
        this.campo = campo;

        /*Como estamos acessando um campo (que possivelmente é privado) via Reflection e não
         * por meio de um getter, precisamos chamar o método setAcessible para quebrar o bloqueio
         * ao campo privado e permitir o acesso.*/
        this.campo.setAccessible(true);
    }

    /**
     * Instancia uma coluna para uma tabela, cujo valor a ser exibido será
     * obtido a partir de uma função que recebe um objeto da lista a ser
     * exportada e retorna uma String com dados obtidos de qualquer atributo
     * deste objeto.
     *
     *
     * @param obtemValorColuna uma função ({@link Function}) que recebe um
     * objeto da lista a ser exportada e retorna uma String que representa o
     * conteúdo a ser exibido para a coluna
     * @param titulo título a ser exibido na coluna
     *
     * @see #AbstractColuna(Field)
     */
    public AbstractColuna(Function<Produto, Object> obtemValorColuna, String titulo) {
        this(titulo);
        this.obtemValorColuna = obtemValorColuna;
    }

    /**
     * Construtor usado internamente para inicializar alguns atributos e evitar
     * duplicação de código entre os outros construtores
     *
     * @param titulo título a ser exibido na coluna
     *
     * @see #AbstractColuna(Field)
     * @see #AbstractColuna(Function, String)
     */
    private AbstractColuna(String titulo) {
        setTitulo(titulo);
    }

    /**
     * Obtém o valor de um determinado campo (atributo) de um objeto da lista de
     * objetos a serem exportados na tabela.
     *
     * @param objeto objeto de onde o valor de um determinado campo será obtido
     * para exibição na coluna
     * @return o valor do campo como String ou vazio se o campo for null
     */
    private String getValor(Produto objeto) {
        /* Se o valor da coluna foi definido para ser obtido a partir de uma
         * função passada em um dos construtores, chama tal função. */
        if (obtemValorColuna != null) {
            Object retorno = obtemValorColuna.apply(objeto);
            return retorno.toString();
        }

        /* Caso o valor foi definido para ser obtido a partir de um
         * atributo de um objeto da lista a ser exportada,
         * obtém o valor de tal atributo representado pela variável "campo".
         */
        try {
            final Object valorCampo = campo.get(objeto);
            return valorCampo == null ? "" : String.valueOf(valorCampo);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public final void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public final String exportarCabecalho() {
        return abrir() + titulo + fechar();
    }

    @Override
    public final String exportarDado(Produto produto) {
        Object retorno = obtemValorColuna.apply(produto);
        return abrir() + retorno.toString() + fechar();
    }

}
