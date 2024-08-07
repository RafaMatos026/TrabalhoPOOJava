public class Autor extends Utilizador{
    private String Telefone;
    private String NIF;
    private String Morada;
    private String estiloLiterario;
    private int numeroTotalObras;

    public Autor(String _Login, String _Email, String _Nome, String _Password, TipoUtilizador _TipoUtilizador, int _Estado, String _Telefone, String _NIF, String _Morada, String _estiloLiterario) {
        super(_Login, _Email, _Nome, _Password, _TipoUtilizador, _Estado);
        this.Telefone = _Telefone;
        this.NIF = _NIF;
        this.Morada = _Morada;
        this.estiloLiterario = _estiloLiterario;
        this.numeroTotalObras = 0;
    }

    public String getEstiloLiterario() {
        return estiloLiterario;
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

    public int getNumeroTotalObras() {
        return this.numeroTotalObras;
    }

    public void setEstiloLiterario(String estiloLiterario) {
        this.estiloLiterario = estiloLiterario;
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

    public void setNumeroTotalObras() {
        this.numeroTotalObras++;
    }

    public int compareTo(Autor aAutor) {
        if(this.numeroTotalObras > aAutor.getNumeroTotalObras()){
            return 1;
        }

        if(this.numeroTotalObras < aAutor.getNumeroTotalObras()){
            return -1;
        }
        return 0;
    }
}