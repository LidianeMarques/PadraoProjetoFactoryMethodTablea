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

import java.util.function.Function;

/**
 *
 * @author Lidiane
 */
/**
 * Gera dados de um atributo de um objeto como uma coluna em uma tabela
 * Markdown.
 *
 * @author Manoel Campos da Silva Filho
 */
class ColunaMarkdown extends AbstractColuna {

    // public static final String SEPARADOR = "|";
    /**
     * Instancia uma coluna para uma tabela Markdown, cujo valor a ser exibido
     * será obtido a partir de uma função que recebe um objeto da lista a ser
     * exportada e retorna uma String com dados obtidos de qualquer atributo
     * deste objeto.
     *
     * @param obtemValorColuna uma função ({@link Function}) que recebe um
     * objeto da lista a ser exportada e retorna uma String que representa o
     * conteúdo a ser exibido para a coluna
     * @param titulo título a ser exibido na coluna
     */
    public ColunaMarkdown(Function<Produto, Object> obtemValorColuna, String titulo) {
        super(obtemValorColuna, titulo);
    }

    @Override
    public String abrir() {
        return " | ";
    }

    @Override
    public String fechar() {
        return "";
    }

}
