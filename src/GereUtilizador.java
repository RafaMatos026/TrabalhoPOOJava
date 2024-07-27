import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GereUtilizador implements Serializable{
    private ArrayList<Utilizador> listaUtilizadores;
    private ArrayList<Revisor> listaTodosRevisores;
    
    GereUtilizador() {
        this.listaUtilizadores = new ArrayList<Utilizador>();
        this.listaTodosRevisores = new ArrayList<Revisor>();
    }
    
    public boolean adicionar(Utilizador aUtilizador) {
        if(aUtilizador instanceof Revisor) {
            listaTodosRevisores.add((Revisor)aUtilizador);
        }
        return listaUtilizadores.add(aUtilizador);
    }

    public ArrayList<Utilizador> getListaUtilizadores() {
        return listaUtilizadores;
    }

    public ArrayList<Revisor> getListaTodosRevisores() {
        return listaTodosRevisores;
    }

    public Utilizador AtualizarUtilizadorAtual(String aEmail, String aPassword) {
        if (listaUtilizadores != null && listaUtilizadores.size() > 0) {
            Iterator<Utilizador> lista_utilizadores = listaUtilizadores.iterator();
            while (lista_utilizadores.hasNext()) {
                Utilizador utilizador = (Utilizador) lista_utilizadores.next();
                
                if (utilizador.getEmail().equals(aEmail) && utilizador.getPassword().equals(aPassword)) {
                    return utilizador;
                }
            }
            return null;
        }
        return null;
    }

    public String listarPedidosRegistoPendentes() {
        if (listaUtilizadores != null && listaUtilizadores.size() > 0) {
            Iterator<Utilizador> lista_utilizadores = listaUtilizadores.iterator();
            String listaPedidos = "";

            while (lista_utilizadores.hasNext()) {
                Utilizador utilizador = (Utilizador) lista_utilizadores.next();
                
                if (utilizador.getEstado() == 0) {
                    listaPedidos += utilizador + "\n";
                }
            }
            if (!listaPedidos.isEmpty()) {
                return listaPedidos;
            }
        }
        return "De momento nao ha pedidos de registo!";
    }

    public String listarPedidosRemocaoPendentes() {
        if (listaUtilizadores != null && listaUtilizadores.size() > 0) {
            Iterator<Utilizador> lista_utilizadores = listaUtilizadores.iterator();
            String listaPedidos = "";

            while (lista_utilizadores.hasNext()) {
                Utilizador utilizador = (Utilizador) lista_utilizadores.next();
                
                if (utilizador.getEstado() == -3) {
                    listaPedidos += utilizador + "\n";
                }
            }
            if (!listaPedidos.isEmpty()) {
                return listaPedidos;
            }
        }
        return "De momento nao ha pedidos de remocao!";
    }

    public Utilizador PesquisarUtilizadorPorLogin(String aLogin) {
        if (listaUtilizadores != null && listaUtilizadores.size() > 0) {
            Iterator<Utilizador> lista_utilizadores = listaUtilizadores.iterator();
            while (lista_utilizadores.hasNext()) {
                Utilizador utilizador = (Utilizador) lista_utilizadores.next();
                
                if (utilizador.getLogin().equals(aLogin)) {
                    return utilizador;
                }
            }
            return null;
        }
        return null;
    }

    public boolean isListaEmpty() {
        return this.listaUtilizadores.isEmpty();
    }

    public String listarUtilizadoresAtivosEInativos() {
        if (listaUtilizadores != null && listaUtilizadores.size() > 0) {
            Iterator<Utilizador> lista_utilizadores = listaUtilizadores.iterator();
            String listaUtilizadores = "";

            while (lista_utilizadores.hasNext()) {
                Utilizador utilizador = (Utilizador) lista_utilizadores.next();
                
                if (utilizador.getEstado() == 1 || utilizador.getEstado() == -1) {
                    listaUtilizadores += utilizador + "\n";
                }
            }
            return listaUtilizadores;
        }
        return null;
    }

    public String listarTodosUtilizadores() {
        if (listaUtilizadores != null && listaUtilizadores.size() > 0) {
            Iterator<Utilizador> lista_utilizadores = listaUtilizadores.iterator();
            String listaUtilizadores = "";

            while (lista_utilizadores.hasNext()) {
                Utilizador utilizador = (Utilizador) lista_utilizadores.next();
                
                if (utilizador.getEstado() == 1 || utilizador.getEstado() == -3) {
                    listaUtilizadores += utilizador + "\n";
                }
            }
            return listaUtilizadores;
        }
        return null;
    }

    public String pesquisarUtilizadorPorTipo(int aTipoUtilizador) {
        if (listaUtilizadores != null && listaUtilizadores.size() > 0) {
            Iterator<Utilizador> lista_utilizadores = listaUtilizadores.iterator();
            String utilizadoresDesteTipo = "";

            while (lista_utilizadores.hasNext()) {
                Utilizador utilizador = (Utilizador) lista_utilizadores.next();
                
                if(utilizador.getTipoUtilizador().getID() == aTipoUtilizador) {
                    utilizadoresDesteTipo += utilizador + "\n";
                }
            }
            return utilizadoresDesteTipo;
        }
        return null;
    }

    public boolean ordenarUtilizadoresPorOrdemAlfabetica() {
        if (listaUtilizadores != null && listaUtilizadores.size() > 0) {
            Collections.sort(listaUtilizadores);
            return true;
        }
        return false;
    }

    public boolean verificaLogin(String aLogin) {
        if (listaUtilizadores != null && listaUtilizadores.size() > 0) {
            Iterator<Utilizador> lista_utilizadores = listaUtilizadores.iterator();

            while (lista_utilizadores.hasNext()) {
                Utilizador utilizador = (Utilizador) lista_utilizadores.next();
                
                if(utilizador.getLogin().equals(aLogin)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean verificaEmail(String aEmail) {
        if (listaUtilizadores != null && listaUtilizadores.size() > 0) {
            Iterator<Utilizador> lista_utilizadores = listaUtilizadores.iterator();

            while (lista_utilizadores.hasNext()) {
                Utilizador utilizador = (Utilizador) lista_utilizadores.next();
                
                if(utilizador.getEmail().equals(aEmail)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
