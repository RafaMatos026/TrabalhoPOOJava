import java.io.Serializable;
import java.util.ArrayList;

public class Revisao implements Serializable, Comparable <Revisao>{
    private ArrayList<Anotacao> listaAnotacoes = new ArrayList<Anotacao>();
    private ArrayList<String> observacoesGenericas = new ArrayList<String>();
    private ArrayList<Revisor> listaRevisoresIndisponiveis = new ArrayList<Revisor>();
    private ArrayList<Revisor> listaRevisores = new ArrayList<Revisor>();
    private String DataRealizacao;
    private Gestor gestorResponsavel;
    private Revisor revisorResponsavel;
    private Obra obra;
    private int numeroSerie = -1;
    private int duracao = -1;
    private int estado = -1; // -1 -> revisao recusada pelo gestor | 0 -> à espera que o gestor aceite | 1 -> aceite pelo gestor e a espera que o revisor aceite | 2 -> aceite pelo revisor | 3 -> revisao finalizada | 4 -> revisao arquivada

    private static boolean ordenarPor = false; // true - duracao | false - data

    public Revisao(Obra aObra, int aEstado, int aNumeroSerie) {
        this.obra = aObra;
        this.estado = aEstado;
        this.numeroSerie = aNumeroSerie;
    }

    public static boolean getOrdenarPor() {
        return ordenarPor;
    }
    
    public static void setOrdenarPor(boolean aOrdenarPor) {
        ordenarPor = aOrdenarPor;
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

    public void setRevisorResponsavel(Revisor aRevisor) {
        this.revisorResponsavel = aRevisor;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public void setDuracao(int aDuracao) {
        this.duracao = aDuracao;
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

    public boolean AdicionarObservacaoGenerica(String aObservacaoGenerica) {
        return this.observacoesGenericas.add(aObservacaoGenerica);
    }

    public void RemoverAnotacao(Anotacao aAnotacao){
        listaAnotacoes.remove(aAnotacao);
    }

    public boolean adicionarRevisorIndisponivel(Revisor aRevisor) {
        return listaRevisoresIndisponiveis.add(aRevisor);
    } 

    public int compareTo(Revisao aRevisao) {
        if(ordenarPor) {
            if(this.duracao > aRevisao.getDuracao()){
                return 1;
            }
            if(this.duracao < aRevisao.getDuracao()){
                return -1;
            }
            return 0;
        } 
        else {
           return this.DataRealizacao.compareTo(aRevisao.getDataRealizacao());
        }
    }

    public String toString() {
        return "Titulo da obra em revisao: " + this.obra.getTitulo() + ", ISBN da obra em revisao: " + this.obra.getISBN() + ", Numero de serie: " + this.numeroSerie;
    }
}
