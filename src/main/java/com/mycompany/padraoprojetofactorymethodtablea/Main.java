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

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Lidiane
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Produto> produtos = Arrays.asList(
                new Produto("TV", "Semp Toshiba", "LED", 100),
                new Produto("Celular", "Sansung", "Note 10", 200));

        ExportadorListaProduto exportador = ExportadorListaProduto.newInstance("html");

        System.out.println(exportador.exportar(produtos));
    }

}
