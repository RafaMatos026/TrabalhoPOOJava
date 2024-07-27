import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.Enumeration;

public class ManipulaFicheiros {
    // acesso ficheiro
    File fileFicheiroLeitura;
    File fileFicheiroEscrita;
    // stream de leitura
    FileInputStream fr;    // substitui o FileReader fr;
    ObjectInputStream br;  // substitui o BufferedReader br;
    // stream de escrita
    FileOutputStream fw;   // substitui o FileWriter fw;
    ObjectOutputStream bw; // substitui o BufferedWriter bw;


    // abertura ficheiro - leitura + escrita
    public boolean abrirFicheiroLeitura(String aCaminho) {
    if (aCaminho!=null && aCaminho.length()> 0 ) {
        fileFicheiroLeitura = new File(aCaminho);

        if(fileFicheiroLeitura.exists()) {
        try {
            fr = new FileInputStream(fileFicheiroLeitura);
            br = new ObjectInputStream(fr);
            return true;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        }
    } 
    return false;
    }

    public boolean abrirFicheiroEscrita(String aCaminho) {
    if (aCaminho!=null && aCaminho.length()> 0 ) {
        fileFicheiroEscrita = new File(aCaminho);

        //if(fileFicheiroEscrita.exists()) {
        try {
            fw = new FileOutputStream(fileFicheiroEscrita);
            bw = new ObjectOutputStream(fw);
            return true;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        //}
    } 
    return false;
    }


    // fecho ficheiro - leitura + escrita
    public boolean fecharFicheiroLeitura(){

    try {
        if(br != null)
        br.close();

        if (fr != null)
        fr.close();

        return true;

    } catch (IOException ioe) {
        ioe.printStackTrace();
    }
    return false;
    }

    public boolean fecharFicheiroEscrita(){

    try {
        if(bw != null)
        bw.close();

        if (fw != null)
        fw.close();

        return true;

    } catch (IOException ioe) {
        ioe.printStackTrace();
    }
    return false;
    }

    // leitura ficheiro
    public BurroCarga leituraFicheiro() {

    if(br != null) {
        try {
            return (BurroCarga) br.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
    return null;
    }

    // escrita ficheiro 
    public boolean escreveFicheiro(BurroCarga aBilio) {
        if (bw != null) {
            try {
            bw.writeObject(aBilio);
            return true;
            } catch (IOException ioe) {
            ioe.printStackTrace();
            }
        }
        return false;
    }
}

