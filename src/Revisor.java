public class Revisor extends Utilizador{
    private String Telefone;
    private String NIF;
    private String Morada;
    private String formacaoAcademica;

    public Revisor(String _Login, String _Email, String _Nome, String _Password, TipoUtilizador _TipoUtilizador, int _Estado, String _Telefone, String _NIF, String _Morada, String _formacaoAcademica) {
        super(_Login, _Email, _Nome, _Password, _TipoUtilizador, _Estado);
        this.Telefone = _Telefone;
        this.NIF = _NIF;
        this.Morada = _Morada;
        this.formacaoAcademica = _formacaoAcademica;
    }

    public String getFormacaoAcademica() {
        return this.formacaoAcademica;
    }

    public String getTelefone() {
        return this.Telefone;
    }

    public String getNIF() {
        return this.NIF;
    }

    public String getMorada() {
        return this.Morada;
    }

    public void setTelefone(String telefone) {
        this.Telefone = telefone;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public void setMorada(String morada) {
        this.Morada = morada;
    }

    public void setFormacaoAcademica(String aFormacaoAcademica) {
        this.formacaoAcademica = aFormacaoAcademica;
    }
}