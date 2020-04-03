package br.com.eiasiscon.produto.tributacao.pis;

public enum TpCalcPIS {
    
    NA(""),
    ALIQUOTA("Percentual"),
    UNIDADE("Em Valor");
    
    TpCalcPIS(String description){
        this.descricao = description;
    }
    private String descricao;

    @Override
    public String toString() {
        return getDescricao();
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }
    
    
}
