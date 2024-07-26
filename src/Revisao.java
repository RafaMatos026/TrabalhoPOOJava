import java.util.ArrayList;
import java.util.Iterator;

public class Revisao {
    private ArrayList<Anotacao> listaAnotacoes = new ArrayList<Anotacao>();
    private ArrayList<String> observacoesGenericas = new ArrayList<String>();
    private ArrayList<Revisor> listaRevisoresIndisponiveis = new ArrayList<Revisor>();
    private ArrayList<Revisor> listaRevisores = new ArrayList<Revisor>();
    private String DataRealizacao;
    private Gestor gestorResponsavel;
    private Revisor revisorResponsavel;
    private Obra obra;
    private int numeroSerie = -1;
    private int estado = -1; // -2 -> revisao recusada pelo revisor | -1 -> revisao recusada pelo gestor | 0 -> à espera que o gestor aceite | 1 -> aceite pelo gestor e a espera que o revisor aceite | 2 -> aceite pelo revisor | 3 -> revisao finalizada | 4 -> revisao arquivada

    public Revisao(Obra aObra, int aEstado, int aNumeroSerie) {
        this.obra = aObra;
        this.estado = aEstado;
        this.numeroSerie = aNumeroSerie;
    }

    public Obra getObra() {
        return this.obra;
    }

    public Gestor getGestorResponsavel() {
        return this.gestorResponsavel;
    }

    public Revisor getRevisorResponsavel() {
        return this.revisorResponsavel;
    }

    public int getNumeroSerie() {
        return this.numeroSerie;
    }

    public int getEstado() {
        return this.estado;
    }

    public ArrayList<Anotacao> getAnotacoes() {
        return this.listaAnotacoes;
    }

    public ArrayList<String> getObservacoesGenericas() {
        return this.observacoesGenericas;
    }

    public ArrayList<Revisor> getListaRevisoresIndisponiveis() {
        return this.listaRevisoresIndisponiveis;
    }

    public ArrayList<Revisor> getListaRevisores() {
        return this.listaRevisores;
    }

    public String getDataRealizacao() {
        return this.DataRealizacao;
    }

    public void setDataRealizacao(String aDataRealizacao) {
        this.DataRealizacao = aDataRealizacao;
    }

    public void setEstado(int aEstado) {
        this.estado = aEstado;
    }

    public void setGestorResponsavel(Gestor aGestorResponsavel) {
        this.gestorResponsavel = aGestorResponsavel;
    }

    public boolean AdicionarRevisor(Revisor aRevisor){
        return listaRevisores.add(aRevisor);
    }

    public boolean RemoverRevisor(Revisor aRevisor){
        return listaRevisores.remove(aRevisor);
    }

    public boolean AdicionarAnotacao(Anotacao aAnotacao){
        return listaAnotacoes.add(aAnotacao);
    }

    public void RemoverAnotacao(Anotacao aAnotacao){
        listaAnotacoes.remove(aAnotacao);
    }

    public boolean adicionarRevisorIndisponivel(Revisor aRevisor) {
        return listaRevisoresIndisponiveis.add(aRevisor);
    } 

    public String toString() {
        return "Titulo: " + this.obra.getTitulo() + ", ISBN: " + this.obra.getISBN() + ", Numero de serie: " + this.numeroSerie;
    }

    /* public String listarRevisoresDisponiveis(Revisao aRevisao, Revisao aListaTodos) {
        Iterator<Revisor> listaRevisores = aListaTodos.iterator();
        String listaRevisoresDisponiveis = "";

        while(listaRevisores.hasNext()) {
            Revisor revisor = (Revisor) listaRevisores.next();
            if(!(listaRevisoresIndisponiveis.contains(revisor)) && !(aRevisao.getListaRevisores().contains(revisor))) {
                listaRevisoresDisponiveis += revisor + "\n";
            }
            return listaRevisoresDisponiveis;
        }
        return "De momento não existem revisores disponiveis!";
    } */
}
