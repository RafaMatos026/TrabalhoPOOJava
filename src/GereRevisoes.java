import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GereRevisoes implements Serializable{
    ArrayList<Revisao> listaRevisoes;

    GereRevisoes() {
        listaRevisoes = new ArrayList<Revisao>();
    }

    public ArrayList<Revisao> getListaRevisoes() {
        return this.listaRevisoes;
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

    public String listarPedidosRevisaoMenuRevisor(Revisor aRevisor) {
        if(this.listaRevisoes != null && this.listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            String listaPedidos = "";
                
            while(lista_revisoes.hasNext()) {
                Revisao revisao = (Revisao) lista_revisoes.next();
                
                if(revisao.getEstado() == 1 && revisao.getListaRevisores().contains(aRevisor)) {
                    listaPedidos += revisao + "\n";
                }
            }

            if(!listaPedidos.isEmpty()) {
                return listaPedidos;
            }
        }
        return null;
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
                
                if(revisao.getListaRevisores().contains(aRevisor) && revisao.getEstado() == 2) {
                    listaPedidos += revisao + "\n";
                }
            }

            if(!listaPedidos.isEmpty()) {
                return listaPedidos;
            }
        }
        return "De momento nao tem revisoes ativas!";
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

    public String listarTodasRevisoes() {
        if(this.listaRevisoes != null && this.listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            String resultado = "";

            while(lista_revisoes.hasNext()) {
                Revisao revisao = (Revisao) lista_revisoes.next();

                if(revisao.getEstado() == 0 || revisao.getEstado() == 1 || revisao.getEstado() == 2) {
                    resultado += revisao + "\n";
                }
            }
            return resultado;
        }
        return "De momento, nao existem revisoes ativas";
    }

    public Revisao pesquisarPorNumeroSerie(int aNumeroSerie) {
        if (listaRevisoes != null && listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            while (lista_revisoes.hasNext()) {
                Revisao revisao = (Revisao) lista_revisoes.next();
                
                if(revisao.getNumeroSerie() == revisao.getNumeroSerie()) {
                    return revisao;
                }
            }
            return null;
        }
        return null;
    }

    public String listarRevisoesDesteAutor(Autor aAutor) {
        if (listaRevisoes != null && listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            String listaRevisoesDesteAutor = "";
            while (lista_revisoes.hasNext()) {
                Revisao revisao = (Revisao) lista_revisoes.next();
                
                if(revisao.getObra().getAutor().equals(aAutor)) {
                    listaRevisoesDesteAutor += revisao + "\n";
                }
            }
            return listaRevisoesDesteAutor;
        }
        return "De momento nao tem nenhuma revisao ativa!";
    }

    public String pesquisarPorEstado(int aEstado) {
        if (listaRevisoes != null && listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            String listaRevisoesDesteAutor = "";
            while (lista_revisoes.hasNext()) {
                Revisao revisao = (Revisao) lista_revisoes.next();
                
                if(revisao.getEstado() == aEstado) {
                    listaRevisoesDesteAutor += revisao + "\n";
                }
            }
            return listaRevisoesDesteAutor;
        }
        return null;
    }

    public String pesquiarObraDesteAutorPorTitulo(String aTitulo, Autor aAutor) {
        if (listaRevisoes != null && listaRevisoes.size() > 0) {
            Iterator<Revisao> lista_revisoes = listaRevisoes.iterator();
            String listaRevisoesDesteAutor = "";
            while (lista_revisoes.hasNext()) {
                Revisao revisao = (Revisao) lista_revisoes.next();
                
                if(revisao.getObra().getTitulo().equals(aTitulo) && revisao.getObra().getAutor().equals(aAutor)) {
                    listaRevisoesDesteAutor += revisao + "\n";
                }
            }
            return listaRevisoesDesteAutor;
        }
        return null;
    }

    public boolean ordenarRevisoes(boolean aOrdenarPor) {
        Revisao.setOrdenarPor(aOrdenarPor);
        if(listaRevisoes != null && listaRevisoes.size() > 0) {
            Collections.sort(listaRevisoes);
            return true;
        }
        return false;
    }
}
