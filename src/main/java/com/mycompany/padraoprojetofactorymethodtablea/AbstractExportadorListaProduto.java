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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lidiane
 */
/**
 * Classe abstrata que fornece uma implementação base para as subclasses que
 * definem formatos específicos de exportação de dados.
 *
 * @author Manoel Campos da Silva Filho
 */
public abstract class AbstractExportadorListaProduto implements ExportadorListaProduto {

    /**
     * Lista de colunas que foramarão a tabela a ser exportada.
     */
    private List<Coluna> colunas;

    /**
     * @return the colunas
     */
    protected List<Coluna> getColunas() {
        return colunas;
    }

    /**
     * Instancia um exportador de uma determinada lista de objetos.
     *
     */
    public AbstractExportadorListaProduto() {
        colunas = new ArrayList<>();
        colunas.add(newColuna(Produto::getId, "Código"));
        colunas.add(newColuna(Produto::getDescricao, "Descrição"));
        colunas.add(newColuna(Produto::getMarca, "Marca"));
        colunas.add(newColuna(Produto::getModelo, "Modelo"));
        colunas.add(newColuna(Produto::getEstoque, "Estoque"));

    }

    @Override
    public void addColuna(Coluna coluna) {
        getColunas().add(coluna);
    }

    @Override
    public final String exportar(List<Produto> listaProdutos) {
        final StringBuilder sb = new StringBuilder();
        sb.append(abrirTabela());

        sb.append(abrirLinha());
        for (Coluna coluna : colunas) {
            sb.append(coluna.exportarCabecalho());
        }
        sb.append(fecharLinha());

        sb.append("\n");
        sb.append(fecharLinhaTitulos());
        gerarLinhasProdutos(sb, listaProdutos);

        sb.append(fecharTabela());
        return sb.toString();
    }

    private void gerarLinhasProdutos(StringBuilder builder, List<Produto> listaProdutos) {
        for (Produto produto : listaProdutos) {

            builder.append(gerarColunasLinha(produto));
        }
    }

    private String gerarColunasLinha(Produto produto) {
        final StringBuilder builder = new StringBuilder();
        builder.append(abrirLinha());
        for (Coluna coluna : getColunas()) {
            builder.append(coluna.exportarDado(produto));
        }
        builder.append(fecharLinha());
        builder.append("\n");
        return builder.toString();
    }

}
