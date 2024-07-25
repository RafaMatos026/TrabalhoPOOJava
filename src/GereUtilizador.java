import java.util.ArrayList;
import java.util.Iterator;

public class GereUtilizador {
    public ArrayList<Utilizador> listaUtilizadores;
    
    GereUtilizador() {
        this.listaUtilizadores = new ArrayList<Utilizador>();
    }
    
    public boolean adicionar(Utilizador aUtilizador) {
        return listaUtilizadores.add(aUtilizador);
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

    public String listaUtilizadoresPendentes() {
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

}
