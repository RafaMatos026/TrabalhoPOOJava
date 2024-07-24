public class Main {
    public static void main(String[] args){
        App app = new App();
        app.setUtilizadorAtual(null);
        app.InicializarTiposUtilizadores();
        app.MenuInicial();

        app = null;
    }
}
