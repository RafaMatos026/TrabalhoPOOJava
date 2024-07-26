import java.util.ArrayList;
import java.util.Iterator;

public class Revisao {
    private ArrayList<Anotacao> listaAnotacoes = new ArrayList<Anotacao>();
    private ArrayList<String> observacoesGenericas = new ArrayList<String>();
    private ArrayList<Revisor> listaRevisoresIndisponiveis = new ArrayList<Revisor>();
    private ArrayList<Revisor> listaRevisores = new ArrayList<Revisor>();
    private String DataRealizacao;
    private Gestor gestorResponsavel;
    private Obra obra;
    private int numeroSerie = -1;
    private int estado = -1;

    public Revisao(ArrayList<Revisor> aListaRevisores, ArrayList<Revisor> aListaRevisoresIndisponiveis, ArrayList<Anotacao> aListaAnotacoes, Obra aObra, String aDataRealizacao, Gestor aGestorResponsavel, int aNumeroSerie, int aEstado) {
        this.listaRevisores = aListaRevisores;
        this.listaRevisoresIndisponiveis = aListaRevisoresIndisponiveis;
        this.listaAnotacoes = aListaAnotacoes;
        this.obra = aObra;
        this.DataRealizacao = aDataRealizacao;
        this.gestorResponsavel = aGestorResponsavel;
        this.numeroSerie = aNumeroSerie;
        this.estado = aEstado;
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

    public void setDataReealizacao(String aDataRealizacao) {
        this.DataRealizacao = aDataRealizacao;
    }

    public void setEstado(int aEstado) {
        this.estado = aEstado;
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

    public void ListarRevisores(){
        Iterator<Revisor> lista_revisores = listaRevisores.iterator();
        while (lista_revisores.hasNext()) {
            Utilizador revisor = lista_revisores.next();
            System.out.println(revisor.getNome());
        }
    }

    public void ListarAnotacoes(){
        Iterator<Anotacao> lista_anotacoes = listaAnotacoes.iterator();
        while (lista_anotacoes.hasNext()) {
            Anotacao anotacao = lista_anotacoes.next();
            System.out.println(anotacao.getDescricao());
        }
    }
}
