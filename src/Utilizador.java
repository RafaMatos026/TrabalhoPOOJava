import java.io.Serializable;
import java.lang.Comparable;

public class Utilizador implements Serializable, Comparable<Utilizador>{
    protected String Login; // único
    protected String Email; // único
    protected String Nome;
    protected String Password;
    protected int Estado; // -4 -> conta removida | -3 -> à espera de remocao | -2 -> Registo recusado | -1 -> Inativo | 0 -> Pendente | 1 -> Registado
    protected TipoUtilizador TipoUtilizador;

    public Utilizador(String aLogin, String aEmail, String aNome,
                      String aPassword, TipoUtilizador aTipoUtilizador, int aEstado){
        this.Login = aLogin;
        this.Nome = aNome;
        this.Email = aEmail;
        this.Password = aPassword;
        this.TipoUtilizador = aTipoUtilizador;
        this.Estado = aEstado;
    }

    public String getEmail() {
        return this.Email;
    }

    public int getEstado() {
        return this.Estado;
    }

    public String getNome() {
        return this.Nome;
    }

    public String getPassword() {
        return this.Password;
    }

    public TipoUtilizador getTipoUtilizador() {
        return this.TipoUtilizador;
    }

    public String getLogin() {
        return this.Login;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public void setNome(String nome) {
        Nome = nome;
    }


    public void setTipoUtilizador(TipoUtilizador tipoUtilizador) {
        TipoUtilizador = tipoUtilizador;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int compareTo(Utilizador aUtilizadores) {
        return this.Nome.compareTo(aUtilizadores.getNome());
    }

    public String toString() {
        return "Nome: " + this.Nome + ", Login: " + this.Login;
    }
}