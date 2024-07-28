import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class ManipulaFicheiros {
    File FicheiroLeitura;
    File FicheiroEscrita;
    FileInputStream fr;   
    ObjectInputStream br;  
    FileOutputStream fw; 
    ObjectOutputStream bw; 

    public boolean abrirFicheiroLeitura(String aCaminho) {
    if (aCaminho!=null && aCaminho.length()> 0 ) {
        FicheiroLeitura = new File(aCaminho);

        if(FicheiroLeitura.exists()) {
        try {
            fr = new FileInputStream(FicheiroLeitura);
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
        FicheiroEscrita = new File(aCaminho);

        try {
            fw = new FileOutputStream(FicheiroEscrita);
            bw = new ObjectOutputStream(fw);
            return true;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    } 
    return false;
    }

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

    public GereInfoSistema leituraFicheiroInfoSistema() {

        if(br != null) {
            try {
                return (GereInfoSistema) br.readObject();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }
        }
        return null;
        }

    public boolean escreveFicheiro(BurroCarga aNovosDados) {
        if (bw != null) {
            try {
            bw.writeObject(aNovosDados);
            return true;
            } catch (IOException ioe) {
            ioe.printStackTrace();
            }
        }
        return false;
    }

    public boolean escreveFicheiro(GereInfoSistema aNovosDados) {
        if (bw != null) {
            try {
            bw.writeObject(aNovosDados);
            return true;
            } catch (IOException ioe) {
            ioe.printStackTrace();
            }
        }
        return false;
    }
}

