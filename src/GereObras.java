import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class GereObras implements Serializable{
    private ArrayList<Obra> listaTodasObras;

    public GereObras() {
        listaTodasObras = new ArrayList<Obra>();
    }

    public boolean adicionarObra(Obra aObra) {
        return this.listaTodasObras.add(aObra);
    }

    public String listarObrasConsoanteAutor(Autor aAutor) {
        if(this.listaTodasObras != null && this.listaTodasObras.size() > 0) {
            Iterator<Obra> listaObras = listaTodasObras.iterator();
            String obrasDesteAutor = "";
                
            while(listaObras.hasNext()) {
                Obra obra = (Obra) listaObras.next();
                
                if(obra.getAutor().getLogin().equals(aAutor.getLogin())) {
                    obrasDesteAutor += obra + "\n";
                }
            }

            if(!obrasDesteAutor.isEmpty()) {
                return obrasDesteAutor;
            }
        }
        return "Este autor nao tem obras criadas!";
    }

    public Obra pesquiarObraPorISBN(int aISBN) {
        if(this.listaTodasObras != null && this.listaTodasObras.size() > 0) {
            Iterator<Obra> listaObras = listaTodasObras.iterator();

            while(listaObras.hasNext()) {
                Obra obra = (Obra) listaObras.next();

                if(obra.getISBN() == aISBN) {
                    return obra;
                }
            }
            return null;
        }
        return null;
    }

    public Obra pesquiarObraPorTitulo(String aTitulo) {
        if(this.listaTodasObras != null && this.listaTodasObras.size() > 0) {
            Iterator<Obra> listaObras = listaTodasObras.iterator();

            while(listaObras.hasNext()) {
                Obra obra = (Obra) listaObras.next();

                if(obra.getTitulo().contains(aTitulo)) {
                    return obra;
                }
            }
            return null;
        }
        return null;
    }

    public String listarTodasObras() {
        if(this.listaTodasObras != null && this.listaTodasObras.size() > 0) {
            Iterator<Obra> listaObras = listaTodasObras.iterator();
            String listaTodasObras = "";

            while(listaObras.hasNext()) {
                Obra obra = (Obra) listaObras.next();

                listaTodasObras += obra + "\n";                
            }
            return listaTodasObras;
        }
        return null;
    }
}
