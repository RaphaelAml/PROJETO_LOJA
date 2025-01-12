package com.meuprojeto.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
    private Long id;

    @NotNull(message = "O tipo da unidade deve ser informado")
    @Column(nullable = false)
    private String tipoUnidade;

    @Size(min = 10, message = "O nome do produto precisar ter no minimo 10 letras")
    @NotNull(message = "Nome deve ser informado")
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Boolean ativo = Boolean.TRUE;

    @NotNull(message = "Descrição do produto deve ser informado")
    @Column(columnDefinition = "text", length = 2000, nullable = false)
    private String descricao;

    @NotNull(message = "Peso do produto deve ser informado")
    @Column(nullable = false)
    private Double peso;

    @NotNull(message = "Largura do produto deve ser informado")
    @Column(nullable = false)
    private Double largura;

    @NotNull(message = "Altura do produto deve ser informado")
    @Column(nullable = false)
    private Double altura;

    @NotNull(message = "Profundidade do produto deve ser informado")
    @Column(nullable = false)
    private Double profundidade;

    @NotNull(message = "Valor da venda deve ser informado")
    @Column(nullable = false)
    private BigDecimal valorVenda = BigDecimal.ZERO;

    @Column(nullable = false)
    private Integer QtdaEstoque = 0;

    private Integer QtdaAlertaEstoque = 0;

    private String linkYouTube;

    private Boolean alertaQtdaEstoque = Boolean.FALSE;

    private Integer qtdeClique = 0;

    @NotNull(message = "A empresa responsável de ser informada")
    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
    private PessoaJuridica empresa;

    @NotNull(message = "A Categoria do Produto deve ser informada")
    @ManyToOne(targetEntity = CategoriaProduto.class)
    @JoinColumn(name = "categoria_produto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "categoria_produto_id_fk"))
    private CategoriaProduto categoriaProduto ;

    @NotNull(message = "A Marca do Produto deve ser informada")
    @ManyToOne(targetEntity = MarcaProduto.class)
    @JoinColumn(name = "marca_produto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "marca_produto_id_fk"))
    private MarcaProduto marcaProduto = new MarcaProduto();


    public void setMarcaProduto(MarcaProduto marcaProduto) {
        this.marcaProduto = marcaProduto;
    }

    public MarcaProduto getMarcaProduto() {
        return marcaProduto;
    }


    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }


    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }


    public PessoaJuridica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaJuridica empresa) {
        this.empresa = empresa;
    }


    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(String tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Double profundidade) {
        this.profundidade = profundidade;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Integer getQtdaEstoque() {
        return QtdaEstoque;
    }

    public void setQtdaEstoque(Integer qtdaEstoque) {
        QtdaEstoque = qtdaEstoque;
    }

    public Integer getQtdaAlertaEstoque() {
        return QtdaAlertaEstoque;
    }

    public void setQtdaAlertaEstoque(Integer qtdaAlertaEstoque) {
        QtdaAlertaEstoque = qtdaAlertaEstoque;
    }

    public String getLinkYouTube() {
        return linkYouTube;
    }

    public void setLinkYouTube(String linkYouTube) {
        this.linkYouTube = linkYouTube;
    }

    public Boolean getAlertaQtdaEstoque() {
        return alertaQtdaEstoque;
    }

    public void setAlertaQtdaEstoque(Boolean alertaQtdaEstoque) {
        this.alertaQtdaEstoque = alertaQtdaEstoque;
    }

    public Integer getQtdeClique() {
        return qtdeClique;
    }

    public void setQtdeClique(Integer qtdeClique) {
        this.qtdeClique = qtdeClique;
    }
}
