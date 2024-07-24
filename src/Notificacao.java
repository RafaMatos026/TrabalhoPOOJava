public class Notificacao {
    private String Mensagem;
    private String Data;
    private Utilizador Remetente;
    private Utilizador Destinatario;

    public Notificacao(String _Mensagem, String _Data, Utilizador _Remetente, Utilizador _Destinatario) {
        this.Mensagem = _Mensagem;
        this.Data = _Data;
        this.Remetente = _Remetente;
        this.Destinatario = _Destinatario;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public String getData() {
        return Data;
    }

    public Utilizador getRemetente() {
        return Remetente;
    }

    public Utilizador getDestinatario() {
        return Destinatario;
    }

    public void setMensagem(String mensagem) {
        Mensagem = mensagem;
    }

    public void setData(String data) {
        Data = data;
    }

    public void setRemetente(Utilizador remetente) {
        Remetente = remetente;
    }

    public void setDestinatario(Utilizador destinatario) {
        Destinatario = destinatario;
    }

    public void EnviarNotificacao(){
        //TODO
    }

    public void ListarNotificacoes(){
        //TODO
    }



}
