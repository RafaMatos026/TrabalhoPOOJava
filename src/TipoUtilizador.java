public class TipoUtilizador {
    private final Integer ID;
    private final String Tipo;
    private final String Desc;

    public TipoUtilizador(Integer _ID, String _Tipo, String _Desc){
        this.ID = _ID;
        this.Tipo = _Tipo;
        this.Desc = _Desc;
    }

    public Integer getID() {
        return ID;
    }

    public String getTipo(){
        return this.Tipo;
    }

    public String getDesc() {
        return Desc;
    }
}
