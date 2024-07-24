public class Obra {
    private String Titulo;
    private String EstiloLiterario;
    private String TipoPub;
    private int NumEd;
    private Autor Autor;
    private int NumPaginas;
    private int numeroEdicao;
    private Anotacao anotacoes;
    private int ISBN; // único
    private String DataSubmissao;
    private String DataAceitacao;

    public Obra(String _Titulo, String _EstiloLiterario, String _TipoPub, int _NumEd, Autor _Autor, int _NumPaginas, int _numeroEdicao, Anotacao _anotacoes, int _ISBN, String _DataSubmissao, String _DataAceitacao) {
        this.Titulo = _Titulo;
        this.EstiloLiterario = _EstiloLiterario;
        this.TipoPub = _TipoPub;
        this.NumEd = _NumEd;
        this.Autor = _Autor;
        this.NumPaginas = _NumPaginas;
        this.numeroEdicao = _numeroEdicao;
        this.anotacoes = _anotacoes;
        this.ISBN = _ISBN;
        this.DataSubmissao = _DataSubmissao;
        this.DataAceitacao = _DataAceitacao;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getEstiloLiterario() {
        return EstiloLiterario;
    }

    public String getTipoPub() {
        return TipoPub;
    }

    public int getNumEd() {
        return NumEd;
    }

    public Autor getAutor() {
        return Autor;
    }

    public int getNumPaginas() {
        return NumPaginas;
    }

    public int getNumeroEdicao() {
        return numeroEdicao;
    }

    public Anotacao getAnotacoes() {
        return anotacoes;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getDataSubmissao() {
        return DataSubmissao;
    }

    public String getDataAceitacao() {
        return DataAceitacao;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public void setEstiloLiterario(String estiloLiterario) {
        EstiloLiterario = estiloLiterario;
    }

    public void setTipoPub(String tipoPub) {
        TipoPub = tipoPub;
    }

    public void setNumEd(int numEd) {
        NumEd = numEd;
    }

    public void setAutor(Autor autor) {
        Autor = autor;
    }

    public void setNumPaginas(int numPaginas) {
        NumPaginas = numPaginas;
    }

    public void setNumeroEdicao(int numeroEdicao) {
        this.numeroEdicao = numeroEdicao;
    }

    public void setAnotacoes(Anotacao anotacoes) {
        this.anotacoes = anotacoes;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setDataSubmissao(String dataSubmissao) {
        DataSubmissao = dataSubmissao;
    }

    public void setDataAceitacao(String dataAceitacao) {
        DataAceitacao = dataAceitacao;
    }
    
}
