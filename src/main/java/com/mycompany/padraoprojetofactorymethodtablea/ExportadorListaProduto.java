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

import java.util.List;
import java.util.function.Function;

/**
 *
 * @author Lidiane
 */
/**
 * Define uma interface para criação de classes que implementam a exportação de
 * uma lista de objetos para formatos específicos como HTML, CSV, XML, AsciiDoc,
 * Markdown, etc.
 *
 * <p>
 * Esta interface e suas implementações usam adequadamente o recurso de Generics
 * do Java, permitindo saber exatamente qual o tipo dos objetos da lista, não
 * apenas usando um tipo coringa como ? na declaração da lista.
 * </p>
 *
 * @author Manoel Campos da Silva Filho
 */
public interface ExportadorListaProduto {

    /**
     * Retorna o código HTML para abertura de uma tabela.
     *
     * @return
     */
    String abrirTabela();

    /**
     * Retorna o código para fechamento de uma tabela em um formato de dados
     * específico.
     *
     * @return
     */
    String fecharTabela();

    /**
     * Retorna o código para abertura de uma linha da tabela em um formato de
     * dados específico.
     *
     * @return
     */
    String abrirLinha();

    /**
     * Retorna o código para fechamento de uma linha da tabela em um formato de
     * dados específico.
     *
     * @return
     */
    String fecharLinha();

    /**
     * Retorna o código para fechamento da linha de títulos de uma tabela em um
     * formato de dados específico.
     *
     * @return
     */
    String fecharLinhaTitulos();

    /**
     * Inicia a exportação da lista de objetos para um formato específico.
     *
     * @param listaProdutos
     * @return String contendo o conteúdo da lista de objetos em um formato
     * específico
     */
    String exportar(List<Produto> listaProdutos);

    Coluna newColuna(Function<Produto, Object> obtemValorColuna, String titulo);

    /**
     * Adiciona uma coluna à tabela.
     *
     * @param coluna coluna a ser adicionada
     */
    void addColuna(Coluna coluna);

    /**
     * Cria uma instância de uma classe que realiza a exportação de dados para
     * um formato padrão.Neste caso, tal formato padrão é HTML.
     * @return 
     */
    static ExportadorListaProduto newInstance() {
        return newInstance("html");
    }

    /**
     * Cria uma instância de uma classe que realiza a exportação de dados para
     * um formato definido.
     *
     * 
     * @param extensaoArquivoExportacao extensão de arquivo que indica o formato
     * para converter os dados, como html, csv, md (markdown), etc.
     * @return
     */
    static ExportadorListaProduto newInstance(String extensaoArquivoExportacao) {
        if (extensaoArquivoExportacao.equals("html")) {
            return new ExportadorListaProdutosHtml();

        } else if (extensaoArquivoExportacao.equals("md")) {
            return new ExportadorListaProdutosMarkdown();

        }
        throw new UnsupportedOperationException("Formato de arquivo não suportado: " + extensaoArquivoExportacao);

    }
}
