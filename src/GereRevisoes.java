import java.util.ArrayList;
import java.util.Iterator;

public class GereRevisoes {
    ArrayList<Revisao> listaRevisoes;

    GereRevisoes() {
        listaRevisoes = new ArrayList<Revisao>();
    }

    public int ultimoNumeroSerie() {
       if(this.listaRevisoes != null && this.listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            Revisao revisao = null;
            while(lista_revisoes.hasNext()) {
                revisao = (Revisao) lista_revisoes.next();
            }
            return revisao.getNumeroSerie();
        }
        return 0;
    }

    public boolean adicionarRevisao(Revisao aRevisao) {
        return this.listaRevisoes.add(aRevisao);
    }

    public String listarPedidosRevisaoMenuGestor() {
        if(this.listaRevisoes != null && this.listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            String listaPedidos = "";
                
            while(lista_revisoes.hasNext()) {
                Revisao revisao = (Revisao) lista_revisoes.next();
                
                if(revisao.getEstado() == 0) {
                    listaPedidos += revisao + "\n";
                }
            }

            if(!listaPedidos.isEmpty()) {
                return listaPedidos;
            }
        }
        return "De momento nao existem pedidos de revisao!";
    }

    public String listarPedidosRevisaoMenuRevisor() {
        if(this.listaRevisoes != null && this.listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            String listaPedidos = "";
                
            while(lista_revisoes.hasNext()) {
                Revisao revisao = (Revisao) lista_revisoes.next();
                
                if(revisao.getEstado() == 1) {
                    listaPedidos += revisao + "\n";
                }
            }

            if(!listaPedidos.isEmpty()) {
                return listaPedidos;
            }
        }
        return "De momento nao existem pedidos de revisao!";
    }

    public Revisao pesquisarPorISBN(int aISBN) {
        if (listaRevisoes != null && listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            while (lista_revisoes.hasNext()) {
                Revisao revisao = (Revisao) lista_revisoes.next();
                
                if(revisao.getObra().getISBN() == aISBN) {
                    return revisao;
                }
            }
            return null;
        }
        return null;
    }

    public String listarRevisoesConsoanteRevisor(Revisor aRevisor) {
        if(this.listaRevisoes != null && this.listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            String listaPedidos = "";
                
            while(lista_revisoes.hasNext()) {
                Revisao revisao = (Revisao) lista_revisoes.next();
                
                if(revisao.getListaRevisores().contains(aRevisor)) {
                    listaPedidos += revisao + "\n";
                }
            }

            if(!listaPedidos.isEmpty()) {
                return listaPedidos;
            }
        }
        return "Este autor nao tem obras criadas!";
    }

    public String pesquisarRevisaoPorTituloObra(Revisor aRevisor, String aTitulo) {
        if(this.listaRevisoes != null && this.listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            String resultado = "";

            while(lista_revisoes.hasNext()) {
                Revisao revisao = (Revisao) lista_revisoes.next();

                if(revisao.getObra().getTitulo().contains(aTitulo)) {
                    resultado += revisao + "\n";
                }
            }
            return resultado;
        }
        return "Nao existe nenhuma obra com o titulo " + aTitulo + " em revisao";
    }

    public String listarRevisoes() {
        if(this.listaRevisoes != null && this.listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            String resultado = "";

            while(lista_revisoes.hasNext()) {
                Revisao revisao = (Revisao) lista_revisoes.next();

                if(revisao.getEstado() == 1 || revisao.getEstado() == 2) {
                    resultado += revisao + "\n";
                }
            }
            return resultado;
        }
        return "De momento, nao existem revisoes ativas";
    }

    public void pesquisarPorTitulo(String aTitulo) {
        
    }

}