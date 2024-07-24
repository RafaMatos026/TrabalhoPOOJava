import java.util.ArrayList;

public class Revisão {
    private ArrayList<Utilizador> Revisores = new ArrayList<Utilizador>();
    private ArrayList<Anotacao> anotacoes = new ArrayList<Anotacao>();
    private String DataRevisão;

    public Revisão(ArrayList<Utilizador> Revisores, ArrayList<Anotacao> anotacoes, String DataRevisão) {
        this.Revisores = Revisores;
        this.anotacoes = anotacoes;
        this.DataRevisão = DataRevisão;
    }

    public ArrayList<Utilizador> getRevisores() {
        return Revisores;
    }

    public ArrayList<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public String getDataRevisão() {
        return DataRevisão;
    }

    public void setRevisores(ArrayList<Utilizador> revisores) {
        Revisores = revisores;
    }

    public void setAnotacoes(ArrayList<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }

    public void setDataRevisão(String dataRevisão) {
        DataRevisão = dataRevisão;
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
        for (Utilizador revisor : Revisores) {
            System.out.println(revisor.getNome());
        }
    }

    public void ListarAnotacoes(){
        for (Anotacao anotacao : anotacoes) {
            System.out.println(anotacao.getDescricao());
        }
    }
}
