import java.util.ArrayList;
import java.util.Iterator;

public class Revisao {
    private ArrayList<Utilizador> Revisores = new ArrayList<Utilizador>();
    private ArrayList<Anotacao> anotacoes = new ArrayList<Anotacao>();
    private String DataRevisao;

    public Revisao(ArrayList<Utilizador> Revisores, ArrayList<Anotacao> anotacoes, String DataRevisao) {
        this.Revisores = Revisores;
        this.anotacoes = anotacoes;
        this.DataRevisao = DataRevisao;
    }

    public ArrayList<Utilizador> getRevisores() {
        return Revisores;
    }

    public ArrayList<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public String getDataRevisão() {
        return DataRevisao;
    }

    public void setRevisores(ArrayList<Utilizador> revisores) {
        Revisores = revisores;
    }

    public void setAnotacoes(ArrayList<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }

    public void setDataRevisão(String dataRevisao) {
        DataRevisao = dataRevisao;
    }

    public void AdicionarRevisor(Utilizador revisor){
        Revisores.add(revisor);
    }

    public void AdicionarAnotacao(Anotacao anotacao){
        anotacoes.add(anotacao);
    }

    public void RemoverRevisor(Utilizador revisor){
        Revisores.remove(revisor);
    }

    public void RemoverAnotacao(Anotacao anotacao){
        anotacoes.remove(anotacao);
    }

    public void ListarRevisores(){
        Iterator<Utilizador> iterator = Revisores.iterator();
        while (iterator.hasNext()) {
            Utilizador revisor = iterator.next();
            System.out.println(revisor.getNome());
        }
    }

    public void ListarAnotacoes(){
        Iterator<Anotacao> iterator = anotacoes.iterator();
        while (iterator.hasNext()) {
            Anotacao anotacao = iterator.next();
            System.out.println(anotacao.getDescricao());
        }
    }
}
