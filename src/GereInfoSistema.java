import java.io.Serializable;
public class GereInfoSistema implements Serializable {


    private String ultimoLogin = "";
    private int numeroExecucoes = 0;

    public GereInfoSistema() {
       
    }

    public String getUltimoLogin() {
        return this.ultimoLogin;
    }

    public int getNumeroExecucoes() {
        return this.numeroExecucoes;
    }

    public void setUltimoLogin(String aUltimoLogin) {
        this.ultimoLogin = aUltimoLogin;
    }

    public void setNumeroExecucoes(int aNumeroExecucoes) {
        this.numeroExecucoes = aNumeroExecucoes;
    }

    public String toString() {
        return "O ultimo login: " + this.ultimoLogin + "\nNumero de execucoes: " + this.numeroExecucoes;
    }
}
