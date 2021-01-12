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

/**
 *
 * @author Lidiane
 */
public class Produto {

    /**
     * Último ID gerado automaticamente.
     */
    private static int ultimoId = 0;

    private int id;
    private String descricao;
    private String marca;
    private String modelo;
    private int estoque;

    public Produto() {
        this.id = ++ultimoId;
    }

    /**
     * Construtor que inicializa todos os atributos do produto. Ele possui uma
     * grande quantidade de parâmetros, que não é recomendável por tornar a
     * chamada do método confusa. Ele foi usado aqui apenas para tornar o
     * exemplo mais simples. Sempre que possível, defina métodos com no máximo 3
     * parâmetros.
     *
     * @param descricao
     * @param marca
     * @param modelo
     * @param estoque
     */
    public Produto(String descricao, String marca, String modelo, int estoque) {
        this();
        this.descricao = descricao;
        this.marca = marca;
        this.modelo = modelo;
        this.estoque = estoque;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

}
