import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ManipulaFicheirosTexto {

    File fileWrite;
    File fileRead;
    FileReader fr;
    BufferedReader br;
    FileWriter fw;
    BufferedWriter bw;

    public boolean abrirFicheiroLeitura(String aPath) {
      if (aPath != null && aPath.length() > 0) {
        fileWrite = new File(aPath);

        if (fileWrite.exists()) {
          try {
            fr = new FileReader(fileWrite);
            br = new BufferedReader(fr);
            return true;
          } catch (IOException ioe) {
            ioe.printStackTrace();
          }
        }
      }
      return false;
    }

    public boolean abrirFicheiroEscrita(String aPath, boolean aAppend) {
      if (aPath != null && aPath.length() > 0) {
        fileRead = new File(aPath);
        try {
          fw = new FileWriter(fileRead, aAppend);
          bw = new BufferedWriter(fw);
          return true;
        } catch (IOException ioe) {
          ioe.printStackTrace();
        }
      }
      return false;
    }

    public boolean fecharFicheiroLeitura() {
      try {
        if (br != null)
          br.close();

        if (fr != null)
          fr.close();

        return true;

      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
      return false;
    }

    public boolean fecharFicheiroEscrita() {
      try {
        if (bw != null)
          bw.close();

        if (fw != null)
          fw.close();

        return true;

      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
      return false;
    }

    public ArrayList<String> lerFicheiro() {
        ArrayList<String> conteudoFicheiro = new ArrayList<>();
        if (br != null) {
            String linha;
            try {
                while ((linha = br.readLine()) != null) {
                    conteudoFicheiro.add(linha);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return conteudoFicheiro;
    }

    public void escreverFicheiro(String aNovoLog, ArrayList<String> aDados) {
        ArrayList<String> Dados = aDados;
        try{
            bw.write(aNovoLog);
            bw.newLine();
            for (String line : Dados) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
}


