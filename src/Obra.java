import java.io.Serializable;

public class Obra implements Serializable{
    private Autor Autor;
    private String Titulo;
    private String EstiloLiterario;
    private String TipoPublicacao;
    private int NumeroPaginas;
    private int ISBN; // único
    private int NumeroEdicao;
    private Anotacao anotacao;
    private String DataSubmissao;
    private String DataAprovacao;

    public Obra(Autor aAutor, String aTitulo, String aEstiloLiterario, String aTipoPublicacao, int aNumeroPaginas, int aISBN, int aNumeroEdicao, String aDataSubmissao, String aDataAprovacao) {
        this.Autor = aAutor;
        this.Titulo = aTitulo;
        this.EstiloLiterario = aEstiloLiterario;
        this.TipoPublicacao = aTipoPublicacao;
        this.NumeroPaginas = aNumeroPaginas;
        this.ISBN = aISBN;
        this.NumeroEdicao = aNumeroEdicao;
        this.DataSubmissao = aDataSubmissao;
        this.DataAprovacao = aDataAprovacao;
    }

    public String getTitulo() {
        return this.Titulo;
    }

    public String getEstiloLiterario() {
        return this.EstiloLiterario;
    }

    public String getTipoPublicacao() {
        return this.TipoPublicacao;
    }

    public int getNumEdicao() {
        return this.NumeroEdicao;
    }

    public Autor getAutor() {
        return this.Autor;
    }

    public int getNumeroPaginas() {
        return this.NumeroPaginas;
    }

    public int getNumeroEdicao() {
        return this.NumeroEdicao;
    }

    public Anotacao getAnotacao() {
        return this.anotacao;
    }

    public int getISBN() {
        return this.ISBN;
    }

    public String getDataSubmissao() {
        return this.DataSubmissao;
    }

    public String getDataAprovacao() {
        return this.DataAprovacao;
    }

    public void setTitulo(String aTitulo) {
        this.Titulo = aTitulo;
    }

    public void setEstiloLiterario(String aEstiloLiterario) {
        this.EstiloLiterario = aEstiloLiterario;
    }

    public void setTipoPublicacao(String aTipoPublicacao) {
        this.TipoPublicacao = aTipoPublicacao;
    }

    public void setNumeroEdicao(int aNumeroEdicao) {
        this.NumeroEdicao = aNumeroEdicao;
    }

    public void setAutor(Autor aAutor) {
        this.Autor = aAutor;
    }

    public void setNumeroPaginas(int aNumeroPaginas) {
        this.NumeroPaginas = aNumeroPaginas;
    }

    public void setAnotacao(Anotacao aAnotacao) {
        this.anotacao = aAnotacao;
    }

    public void setISBN(int aISBN) {
        this.ISBN = aISBN;
    }

    public void setDataSubmissao(String aDataSubmissao) {
        this.DataSubmissao = aDataSubmissao;
    }

    public void setDataAceitacao(String aDataAprovacao) {
        this.DataAprovacao = aDataAprovacao;
    }

    public String toString() {
        return "Titulo: " + this.Titulo + ", ISBN: " + this.ISBN;
    }
}
