public class Anotacao{
    private String descricao;
    private int pagina;
    private int paragrafo;
    private String data;

    public Anotacao(String aDescricao, int aPagina, int aParagrafo, String aData) {
        this.descricao = aDescricao;
        this.pagina = aPagina;
        this.paragrafo = aParagrafo;
        this.data = aData;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public int getPagina() {
        return this.pagina;
    }

    public int getParagrafo() {
        return this.paragrafo;
    }

    public String data() {
        return this.data;
    }

    public void setDescricao(String aDescricao) {
        this.descricao = aDescricao;
    }

    public void setPagina(int aPagina) {
        this.pagina = aPagina;
    }

    public void setParagrafo(int aParagrafo) {
        this.paragrafo = aParagrafo;
    }

    public void setData(String aData) {
        this.data = aData;
    }
}