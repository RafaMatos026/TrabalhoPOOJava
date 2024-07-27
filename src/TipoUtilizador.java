import java.io.Serializable;

public class TipoUtilizador implements Serializable{
    private final int ID;
    private final String Tipo;
    private final String Desc;

    public TipoUtilizador(int _ID, String _Tipo, String _Desc){
        this.ID = _ID;
        this.Tipo = _Tipo;
        this.Desc = _Desc;
    }

    public int getID() {
        return ID;
    }

    public String getTipo(){
        return this.Tipo;
    }

    public String getDesc() {
        return Desc;
    }
}
