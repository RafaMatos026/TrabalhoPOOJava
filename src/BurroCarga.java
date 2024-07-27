import java.io.Serializable;

public class BurroCarga implements Serializable{
    private GereUtilizador gereUtilizador = new GereUtilizador();
    private GereObras gereObras = new GereObras();
    private GereRevisoes gereRevisoes = new GereRevisoes();

    public BurroCarga() {
       
    }

    public GereUtilizador getGereUtilizador() {
        return this.gereUtilizador;
    }

    public GereObras getGereObras() {
        return this.gereObras;
    }

    public GereRevisoes getGereRevisoes() {
        return this.gereRevisoes;
    }

    public void setGereUtilizador(GereUtilizador aGereUtilizador) {
        this.gereUtilizador = aGereUtilizador;
    }

    public void setGereObras(GereObras aGereObras) {
        this.gereObras = aGereObras;
    }

    public void setGereRevisoes(GereRevisoes aGereRevisoes) {
        this.gereRevisoes = aGereRevisoes;
    }
}
