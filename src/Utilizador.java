public class Utilizador {
    protected String Login; // único
    protected String Email; // único
    protected String Nome;
    protected String Password;
    protected int Estado; // -2 -> recusado | -1 -> Inativo | 0 -> Pendente | 1 -> Aceite
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

    public String toString() {
        return this.Nome  ;
    }
}
