public class Autor extends Utilizador{
    private String Telefone;
    private String NIF;
    private String Morada;

    public Autor(String _Login, String _Email, String _Nome, String _Password, Boolean _Estado, TipoUtilizador _TipoUtilizador, String _Telefone, String _NIF, String _Morada) {
        super(_Login, _Email, _Nome, _Password, _Estado, _TipoUtilizador);
        this.Telefone = _Telefone;
        this.NIF = _NIF;
        this.Morada = _Morada;
    }

    public String getTelefone() {
        return Telefone;
    }

    public String getNIF() {
        return NIF;
    }

    public String getMorada() {
        return Morada;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public void setMorada(String morada) {
        Morada = morada;
    }
}