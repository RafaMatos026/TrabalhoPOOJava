public class Utilizador {
    protected String Login; // único
    protected String Email; // único
    protected String Nome;
    protected String Password;
    protected Boolean Estado;
    protected TipoUtilizador TipoUtilizador;

    public Utilizador(String aLogin, String aEmail, String aNome,
                      String aPassword, Boolean aEstado, TipoUtilizador aTipoUtilizador){
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

    public Boolean getEstado() {
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

    public void setEstado(Boolean estado) {
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
}
