import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class App {
    private BurroCarga burroCarga = new BurroCarga();
    private Utilizador UtilizadorAtual = null;
    private TipoUtilizador tipoUtilizadorAtual = null;
    private GereUtilizador Utilizadores = new GereUtilizador();
    private GereObras gereObras = new GereObras();
    private GereRevisoes gereRevisoes = new GereRevisoes();
    private Scanner sc = new Scanner(System.in);
    private ArrayList<TipoUtilizador> TiposUtilizadores = new ArrayList<TipoUtilizador>();
    private ArrayList<String> listaNotificacoesGestor = new ArrayList<String>();
    private int Opcao;
    private int auxNotificacoesGestor = 0;

  private static BurroCarga lerBurroCarga() {
    ManipulaFicheiros fileRead = new ManipulaFicheiros();
    fileRead.abrirFicheiroLeitura("./dados_apl.dat");
    BurroCarga auxBurroCarga = fileRead.leituraFicheiro();
    fileRead.fecharFicheiroLeitura();
    if(auxBurroCarga == null){
        auxBurroCarga = new BurroCarga();
    }
    return auxBurroCarga;
  }

  private static void escreverBurroCarga(BurroCarga aBurroCarga) {
    try {
      ManipulaFicheiros fileWrite = new ManipulaFicheiros();
      fileWrite.abrirFicheiroEscrita("./dados_apl.dat");
      fileWrite.escreveFicheiro(aBurroCarga);
      fileWrite.fecharFicheiroEscrita();
    } catch (Exception ioe) {
      ioe.printStackTrace();
    }
}

    public void Login(){
        String _Email;
        String _Password;

        this.sc.nextLine();
        System.out.println("Email:");
        _Email = sc.nextLine();
        System.out.println("Password:");
        _Password = sc.nextLine();
        
        if (AtualizarUtilizadorAtual(_Email, _Password)){
            if(UtilizadorAtual.getEstado() == -2) {
                System.out.println("A sua conta foi recusada!");
                this.MenuInicial();
            }
            if(UtilizadorAtual.getEstado() == -1) {
                System.out.println("A sua conta encontra-se inativa!");
                this.MenuInicial();
            }
            if(UtilizadorAtual.getEstado() == 0) {
                System.out.println("A sua conta ainda nao foi aceite!");
                this.MenuInicial();
            }
            
            System.out.println("Login efetuado com sucesso!");
            switch(this.tipoUtilizadorAtual.getID()) {
                case 1: 
                    this.MenuInicialAutor();
                    break;
                
                case 2:
                    this.MenuInicialRevisor();
                    break;

                case 3: 
                    this.MenuInicialGestor();
                    break;
            }
        } else {
            System.out.println("Credenciais invalidas!");
            do {
                System.out.println("Deseja tentar novamente?");
                System.out.println("1 - Sim");
                System.out.println("2 - Nao");
                this.Opcao = sc.nextInt();
            }while (this.Opcao < 1 || this.Opcao > 2);

            switch (this.Opcao){
                case 1 -> {
                    this.Login();
                    break;
                }

                case 2 -> {
                    this.MenuInicial();
                    break;
                }

                default -> {
                    System.out.println("Erro!");
                    System.exit(1);
                }
            }

        }
    }

    public boolean AtualizarUtilizadorAtual(String _Email, String _Password){
        this.UtilizadorAtual = Utilizadores.AtualizarUtilizadorAtual(_Email, _Password);
        if (this.UtilizadorAtual != null) {
            AtualizarTipoUtilizadorAtual(this.UtilizadorAtual);
            return true;
        }
        return false;
    }

    public void AtualizarTipoUtilizadorAtual(Utilizador utilizador) {
        this.tipoUtilizadorAtual = utilizador.getTipoUtilizador();
    }

    public void AceitarRecusarPedidoRegisto() {
        Utilizadores.listarPedidosRegistoPendentes();
        Utilizador utilizador = null;

        do{
            sc.nextLine();
            System.out.println("Introduza o login do utilizador que pretende aceitar/recusar: ");
            String userLogin = sc.nextLine();
            
            utilizador = Utilizadores.PesquisarUtilizadorPorLogin(userLogin);

            if(utilizador == null) {
                System.out.println("O login que introduziu nao corresponde a nenhum utilizador!");
            }

        }while(utilizador == null);

        
        do {
            System.out.println("1 - Aceitar pedido");
            System.out.println("2 - Recusar pedido");
            this.Opcao = sc.nextInt();

            if(this.Opcao < 1 && this.Opcao > 2) {
                System.out.println("Por favor introduza uma opcao valida!");
            }

        } while (this.Opcao < 1 && this.Opcao > 2);
    
        if(this.Opcao == 2) {
            utilizador.setEstado(-2);
        }else{
            utilizador.setEstado(1);
            listaNotificacoesGestor.add(0, "O utilizador com o login " + utilizador.getLogin() + " foi registado no sistema!");
        }

        MenuInicialGestor();
    }

    public void AceitarRecusarPedidoRemocao() {
        Utilizadores.listarPedidosRemocaoPendentes();
        Utilizador utilizador = null;
        int contador = 0;

        do{
            if(!(contador > 0)) {
                sc.nextLine();
            }
            System.out.println("Introduza o login do utilizador a que pretende aceitar/recusar: ");
            String userLogin = sc.nextLine();
            
            utilizador = Utilizadores.PesquisarUtilizadorPorLogin(userLogin);

            if(utilizador == null) {
                System.out.println("O login que introduziu nao corresponde a nenhum utilizador!");
                contador++;
            }

        }while(utilizador == null);

        
        do {
            System.out.println("1 - Aceitar pedido");
            System.out.println("2 - Recusar pedido");
            this.Opcao = sc.nextInt();

            if(this.Opcao < 1 && this.Opcao > 2) {
                System.out.println("Por favor introduza uma opcao valida!");
            }

        } while (this.Opcao < 1 && this.Opcao > 2);
    
        if(this.Opcao == 1) {
            utilizador.setEstado(-4);
            this.Utilizadores.listaUtilizadores.remove(utilizador);
            burroCarga.setGereUtilizador(this.Utilizadores);
            escreverBurroCarga(burroCarga);
            listaNotificacoesGestor.add("O utilizador com o login " + utilizador.getLogin() + " foi removido do sistema!");
        }
        else {
            utilizador.setEstado(1);
        }

        MenuInicialGestor();
    }



    public void RegistoOutroUtilizador(int TipoUtilizador){
        String _Login;
        String _Email;
        String _Nome;
        String _Password;
        String ConfPassword;
        int _Estado = 0;
        String _NIF = "";
        String _Telefone = "";
        String _Morada = "";
        String _estiloLiterario = "";
        String _formacaoAcademica = "";
        TipoUtilizador _TipoUtilizador = null;

        Iterator<TipoUtilizador> tiposUtilizadores = this.TiposUtilizadores.iterator();
        while (tiposUtilizadores.hasNext()) {
            TipoUtilizador tipoUtilizador = (TipoUtilizador) tiposUtilizadores.next();
            if (tipoUtilizador.getID() == TipoUtilizador) {
                _TipoUtilizador = tipoUtilizador;
                break;
            }
        }

        this.sc.nextLine();
        System.out.println("Nome: ");
        _Nome = this.sc.nextLine();

        System.out.println("Login: ");
        _Login = this.sc.nextLine();


        if (_TipoUtilizador.getID() == 1 || _TipoUtilizador.getID() == 2){
            if(_TipoUtilizador.getID() == 1) {
                System.out.println("Estilo literario: ");
                _estiloLiterario = this.sc.nextLine();
            } else {
                System.out.println("Formacao academica: ");
                _formacaoAcademica = this.sc.nextLine();
            }
            System.out.println("NIF: ");
            _NIF = this.sc.nextLine();

            System.out.println("Telemovel: ");
            _Telefone = this.sc.nextLine();

            System.out.println("Morada: ");
            _Morada = this.sc.nextLine();
        }

        System.out.println("Email: ");
        _Email = this.sc.nextLine();

        do {
            System.out.println("Password: ");
            _Password = this.sc.nextLine();

            System.out.println("Confirmar Password: ");
            ConfPassword = this.sc.nextLine();

            if (!_Password.equals(ConfPassword)){
                System.out.println("Password nao sao iguais!");
            }

        } while(!_Password.equals(ConfPassword));

        if (_TipoUtilizador.getID() == 1){
            Autor NovoAutor = new Autor(_Login, _Email, _Nome, _Password,
                    _TipoUtilizador, _Estado,  _NIF, _Morada, _Telefone, _estiloLiterario);

            this.Utilizadores.adicionar(NovoAutor);
            listaNotificacoesGestor.add(0, "O utilizador com o login " + _Login +" realizou um pedido de registo de conta!");
        }

        if (_TipoUtilizador.getID() == 2){
            Revisor NovoRevisor = new Revisor(_Login, _Email, _Nome, _Password, _TipoUtilizador, _Estado, _NIF, _Morada, _Telefone, _formacaoAcademica);

            this.Utilizadores.adicionar(NovoRevisor);
            listaNotificacoesGestor.add(0, "O utilizador com o login " + _Login +" realizou um pedido de registo de conta!");
        }

        if (_TipoUtilizador.getID() == 3){
            Gestor NovoGestor = new Gestor(_Login, _Email, _Nome, _Password, _Estado, _TipoUtilizador);

            this.Utilizadores.adicionar(NovoGestor);
            listaNotificacoesGestor.add(0, "O utilizador com o login " + _Login +" realizou um pedido de registo de conta!");
        }

        burroCarga.setGereUtilizador(this.Utilizadores);
        escreverBurroCarga(burroCarga);
    }

    public String notificacoesGestor() {
        Iterator<String> listaNotificacoes = listaNotificacoesGestor.iterator();
        String resultadoFinal = "";

        while(listaNotificacoes.hasNext()) {
            String notificacao = (String) listaNotificacoes.next();

            resultadoFinal += notificacao + "\n";
        }

        if(!resultadoFinal.isEmpty()) {
            auxNotificacoesGestor = listaNotificacoesGestor.size();
            return resultadoFinal;
        }
        return "De momento nao ha notificacoes!";
    }

    public void Registo(){
        if (this.Utilizadores.isListaEmpty()){
            String _Login;
            String _Email;
            String _Nome;
            String _Password;
            String ConfPassword;
            int _Estado = 1;
            TipoUtilizador _TipoUtilizador = this.TiposUtilizadores.get(2);

            System.out.println("Nao existe nenhum utilizador criado... Sera necessario criar um gestor agora.");
            
            System.out.println("Nome: ");
            _Nome = this.sc.nextLine();

            System.out.println("Login: ");
            _Login = this.sc.nextLine();

            System.out.println("Email: ");
            _Email = this.sc.nextLine();

            do {
                System.out.println("Password: ");
                _Password = this.sc.nextLine();

                System.out.println("Confirmar Password: ");
                ConfPassword = this.sc.nextLine();

                if (!_Password.equals(ConfPassword)){
                    System.out.println("Password nao sao iguais!");
                }
            } while(!_Password.equals(ConfPassword));

            Gestor NovoGestor = new Gestor(_Login, _Email, _Nome, _Password, _Estado , _TipoUtilizador);

            this.Utilizadores.adicionar(NovoGestor);
            burroCarga.setGereUtilizador(this.Utilizadores);
            escreverBurroCarga(burroCarga);

            this.MenuInicial();

        } else {
            do{
                System.out.println("Introduza o seu tipo de conta:");
                System.out.println("1 - Autor");
                System.out.println("2 - Revisor");
                System.out.println("3 - Gestor");
                this.Opcao = sc.nextInt();

                if (this.Opcao < 1 | this.Opcao > 3){
                    System.out.println("Opcao Invalida!");
                }
            }while(this.Opcao < 1 || this.Opcao > 3);

            switch (this.Opcao){
                case 1:
                    this.RegistoOutroUtilizador(1);
                    break;

                case 2:
                    this.RegistoOutroUtilizador(2);
                    break;


                case 3:
                    this.RegistoOutroUtilizador(3);
                    break;

                default:
                    System.out.println("Opcao Invalida!");
            }
            if(this.UtilizadorAtual instanceof Gestor) {
                this.MenuInicialGestor();
            }
            this.MenuInicial();
        }
    }

    public void setUtilizadorAtual(Utilizador _UtilizadorAtual){
        this.UtilizadorAtual = _UtilizadorAtual;
    }

    public void EditarDados(){

        if (this.UtilizadorAtual.getTipoUtilizador().getID() == 1 || this.UtilizadorAtual.getTipoUtilizador().getID() == 2){
            System.out.println("Nome: " + this.UtilizadorAtual.getNome());
            System.out.println("_Login: " + this.UtilizadorAtual.getLogin());
            System.out.println("Email: " + this.UtilizadorAtual.getEmail());
            System.out.println("Estado da Conta: Ativo");
            if (this.UtilizadorAtual instanceof Revisor) {
                Revisor revisor = (Revisor) this.UtilizadorAtual;
                System.out.println("Formacao academica: " + revisor.getFormacaoAcademica());
                System.out.println("NIF: " + revisor.getNIF());
                System.out.println("Morada: " + revisor.getMorada());
                System.out.println("Telefone: " + revisor.getTelefone());
            } else if (this.UtilizadorAtual instanceof Autor) {
                Autor autor = (Autor) this.UtilizadorAtual;
                System.out.println("Estilo literario: " + autor.getEstiloLiterario());
                System.out.println("NIF: " + autor.getNIF());
                System.out.println("Morada: " + autor.getMorada());
                System.out.println("Telefone: " + autor.getTelefone());
            }
            System.out.println("Tipo de Utilizador: " + this.UtilizadorAtual.getTipoUtilizador().getTipo());

            do{
                System.out.println("Indique qual dos dados deseja alterar: ");
                System.out.println("1 - Nome");
                System.out.println("2 - Login");
                System.out.println("3 - Email");
                System.out.println("4 - Password");
                System.out.println("5 - NIF");
                System.out.println("6 - Morada");
                System.out.println("7 - Telefone");
                if(this.UtilizadorAtual instanceof Autor) {
                    System.out.println("8 - Estilo literario");
                }
                else if(this.UtilizadorAtual instanceof Revisor) {
                    System.out.println("8 - Formacao academica");
                }
                System.out.println("9 - Cancelar");
            
                this.Opcao = sc.nextInt();
            }while (this.Opcao < 1 || this.Opcao > 9);

            switch (this.Opcao){
                case 1 ->{
                    String _Nome = "";
                    this.sc.nextLine();
                    System.out.println("Novo nome: ");
                    _Nome = this.sc.nextLine();

                    this.UtilizadorAtual.setNome(_Nome);
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 2 -> {
                    String _Login = "";
                    this.sc.nextLine();
                    System.out.println("Novo login: ");
                    _Login = sc.nextLine();

                    this.UtilizadorAtual.setLogin(_Login);
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 3 -> {
                    String _Email = "";
                    this.sc.nextLine();
                    System.out.println("Novo email: ");
                    _Email = sc.nextLine();

                    this.UtilizadorAtual.setEmail(_Email);
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 4 ->{
                    String _Password = "";
                    this.sc.nextLine();
                    System.out.println("Nova password: ");
                    _Password = sc.nextLine();

                    this.UtilizadorAtual.setPassword(_Password);
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 5 -> {
                    String _NIF = "";
                    this.sc.nextLine();
                    System.out.println("Novo NIF: ");
                    _NIF = sc.nextLine();

                    if(this.UtilizadorAtual instanceof Revisor){
                        Revisor revisor = (Revisor) this.UtilizadorAtual;
                        revisor.setNIF(_NIF);
                    }
                    else {
                        Autor autor = (Autor) this.UtilizadorAtual;
                        autor.setNIF(_NIF);
                    }
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 6 ->{
                    String _Morada = "";
                    this.sc.nextLine();
                    System.out.println("Nova morada: ");
                    _Morada = sc.nextLine();

                    if(this.UtilizadorAtual instanceof Revisor){
                        Revisor revisor = (Revisor) this.UtilizadorAtual;
                        revisor.setMorada(_Morada);
                    }
                    else {
                        Autor autor = (Autor) this.UtilizadorAtual;
                        autor.setMorada(_Morada);
                    }
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 7 ->{
                    String _Telefone = "";
                    this.sc.nextLine();
                    System.out.println("Novo contacto: ");
                    _Telefone = sc.nextLine();

                    if(this.UtilizadorAtual instanceof Revisor){
                        Revisor revisor = (Revisor) this.UtilizadorAtual;
                        revisor.setTelefone(_Telefone);
                    }
                    else {
                        Autor autor = (Autor) this.UtilizadorAtual;
                        autor.setTelefone(_Telefone);
                    }
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 8 ->{
                    if(this.UtilizadorAtual instanceof Autor) {
                        String _EstiloLiterario = "";
                        this.sc.nextLine();
                        System.out.println("Novo estilo literario: ");
                        _EstiloLiterario = this.sc.nextLine();

                        Autor autor = (Autor) this.UtilizadorAtual;
                        autor.setEstiloLiterario(_EstiloLiterario);
                    }
                    else if(this.UtilizadorAtual instanceof Revisor) {
                        String _FormacaoAcademica = "";
                        this.sc.nextLine();
                        System.out.println("Nova formacao academica: ");
                        _FormacaoAcademica = this.sc.nextLine();

                        Revisor revisor = (Revisor) this.UtilizadorAtual;
                        revisor.setFormacaoAcademica(_FormacaoAcademica);
                    }
                    this.EditarDados();
                }

                case 9 ->{
                    switch(this.UtilizadorAtual.getTipoUtilizador().getID()) {
                        case 1: 
                            this.MenuInicialAutor();
                            break;

                        case 2: 
                            this.MenuInicialRevisor();
                            break;

                        case 3:
                            this.MenuInicialGestor();
                            break;

                        default:
                            System.out.println("Algo correu mal!");
                            this.MenuInicial();
                    }
                }

                default -> {
                    System.out.println("Opcao Invalida!");
                }
            }
        } else {
            System.out.println("Nome: " + this.UtilizadorAtual.getNome());
            System.out.println("Login: " + this.UtilizadorAtual.getLogin());
            System.out.println("Email: " + this.UtilizadorAtual.getEmail());
            System.out.println("Estado da Conta: Ativo");
            System.out.println("Tipo de Utilizador: " + this.UtilizadorAtual.getTipoUtilizador().getTipo());

            do{
                System.out.println("Indique qual dos dados deseja alterar: ");
                System.out.println("1 - Nome");
                System.out.println("2 - Login");
                System.out.println("3 - Email");
                System.out.println("4 - Password");
                System.out.println("5 - Cancelar");

                this.Opcao = sc.nextInt();
            }while (this.Opcao < 1 || this.Opcao > 5);

            switch (this.Opcao){
                case 1 ->{
                    String _Nome = "";
                    sc.nextLine();
                    System.out.println("Novo Nome: ");
                    _Nome = sc.nextLine();

                    this.UtilizadorAtual.setNome(_Nome);
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 2 -> {
                    String _Login = "";
                    sc.nextLine();
                    System.out.println("Novo login: ");
                    _Login = sc.nextLine();

                    this.UtilizadorAtual.setLogin(_Login);
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 3 -> {
                    String _Email = "";
                    sc.nextLine();
                    System.out.println("Novo email: ");
                    _Email = sc.nextLine();
                    
                    this.UtilizadorAtual.setEmail(_Email);
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 4 ->{
                    String _Password = "";
                    sc.nextLine();
                    System.out.println("Nova password: ");
                    _Password = sc.nextLine();

                    this.UtilizadorAtual.setPassword(_Password);
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 5 -> {
                        this.MenuInicialGestor();
                }

                default -> {
                    System.out.println("Opcao Invalida!");
                }
            }
        }
    }

    public void ativarInativar(Utilizador aUtilizador) {
        do {
            System.out.println("1 - Ativar conta");
            System.out.println("2 - Inativar conta");
            this.Opcao = sc.nextInt();

            if(this.Opcao < 1 && this.Opcao > 2) {
                System.out.println("Por favor introduza uma opcao valida!");
            }

        } while (this.Opcao < 1 && this.Opcao > 2);

        switch(this.Opcao) {
            case 1:
                if(aUtilizador.getEstado() == 1) {
                    System.out.println("Esta conta já se encontra ativa!");
                }
                else if(aUtilizador.getEstado() == -1) {
                    aUtilizador.setEstado(1);
                }
                System.out.println("Operacao realizada com sucesso!");
                break;

            case 2:
                if(aUtilizador.getEstado() == -1) {
                    System.out.println("Esta conta já se encontra inativa!");
                }
                else if(aUtilizador.getEstado() == 1) {
                    aUtilizador.setEstado(-1);
                }
                System.out.println("Operacao realizada com sucesso!");
                break;
        }
    }

    public void gestorCriaUtilizador() {
        do{
            System.out.println("Introduza o tipo de conta que pretende criar:");
            System.out.println("1 - Autor");
            System.out.println("2 - Revisor");
            System.out.println("3 - Gestor");
            this.Opcao = sc.nextInt();

            if (this.Opcao < 1 | this.Opcao > 3){
                System.out.println("Opcao Invalida!");
            }
        }while(this.Opcao < 1 || this.Opcao > 3);

        switch (this.Opcao){
            case 1:
                this.RegistoOutroUtilizador(1);
                break;

            case 2:
                this.RegistoOutroUtilizador(2);
                break;


            case 3:
                this.RegistoOutroUtilizador(3);
                break;

            default:
                System.out.println("Opcao Invalida!");
        }
    }

    public void gestorAceitarRecusarPedidoRevisao() {
        Revisao revisao = null;
        int ISBN;
        System.out.println(gereRevisoes.listarPedidosRevisaoMenuGestor());

        if(gereRevisoes.listarPedidosRevisaoMenuGestor() == "De momento nao existem pedidos de revisao!") {
            return;
        }
        
        System.out.println("Introduza o codigo ISBN da obra que deseja aceitar/recusar: ");
        ISBN = sc.nextInt();

        revisao = gereRevisoes.pesquisarPorISBN(ISBN);


        do {
            System.out.println("1 - Aceitar");
            System.out.println("2 - Recusar");
            this.Opcao = sc.nextInt();

            if(this.Opcao < 1 || this.Opcao > 2) {
                System.out.println("Por favor introduza uma opcao valida!");
            }
        } while (this.Opcao < 1 || this.Opcao > 2);  
        
        switch(this.Opcao) {
            case 1:
                revisao.setGestorResponsavel((Gestor)this.UtilizadorAtual);
                revisao.setEstado(1);
                System.out.println("Operacao realizada com sucesso!");
                System.out.println("Foi enviado o pedido de revisao desta obra para o revisor!");
                break;
                
            case 2: 
                revisao.setEstado(-1);
                System.out.println("Operacao realizada com sucesso!");
                System.out.println("Revisao recusada!");
                break;
        }                         
    }

    public void pesquisarRevisoesPorEstado() {
        do {
            System.out.println("Em que estado se encontra a revisao que procura?");
            System.out.println("1 - Iniciada, a espera da autorizacao do gestor");
            System.out.println("2 - Aceite pelo gestor, a aguardar pela autorizacao do revisor");
            System.out.println("3 - Em execucao");
            System.out.println("4 - Finalizada");
            System.out.println("5 - Arquivada");
            this.Opcao = sc.nextInt();

            if(this.Opcao < 1 || this.Opcao > 5) {
                System.out.println("Opcao invalida, por favor introduza uma opcao valida");
            }
        } while (this.Opcao < 1 || this.Opcao > 5);

        if(gereRevisoes.pesquisarPorEstado(this.Opcao)==null)
        {
            System.out.println("De momento nao tem nenhuma revisao com esse estado ativa!");
            return;
        }

        System.out.println(gereRevisoes.pesquisarPorEstado(this.Opcao));
    }

    public void listarTodasObras() {
        if(gereObras.listarTodasObras() == null) {
            System.out.println("De momento nao ha nenhuma obra!");
            return;
        }
        System.out.println(gereObras.listarTodasObras());
    }

    public void pesquisarObrasPorTitulo() {
        String Titulo;
        System.out.println("Introduza o titulo da obra que pretende encontrar: ");
        Titulo = sc.nextLine();

        if(gereObras.pesquiarObraPorTitulo(Titulo) == null) {
            System.out.println("Nao ha nenhuma obra com o titulo " + Titulo);
            return;
        }

        System.out.println(gereObras.pesquiarObraPorTitulo(Titulo));
    }

    public void pesquisarUtilizadorPorLogin() {
        String login;
        System.out.println("Introduza o login do utilizador que pretende encontrar: ");
        login = sc.nextLine();
        if(Utilizadores.PesquisarUtilizadorPorLogin(login) == null) {
            System.out.println("Nao existe nenhum utilizador com o login " + login);
            return;
        }
        System.out.println(Utilizadores.PesquisarUtilizadorPorLogin(login));
    }

    public void pesquisarUtilizadorPorTipo() {
        System.out.println("Selecione o tipo de utilizador que pretende encontrar");
        System.out.println("1 - Autor");
        System.out.println("3 - Gestor");
        System.out.println("2 - Revisor");
        this.Opcao = sc.nextInt();
        
        
        if(Utilizadores.pesquisarUtilizadorPorTipo(this.Opcao) == null) {
            System.out.println("De momento nao existem utilizadores deste tipo!");
            return;
        }

        System.out.println(Utilizadores.pesquisarUtilizadorPorTipo(this.Opcao));
    }

    public void gestorAtivarInativarConta() {
        Utilizador utilizador = null;

        do{
            System.out.println(Utilizadores.listarUtilizadoresAtivosEInativos()); 
            sc.nextLine();
            System.out.println("Introduza o login do utilizador que pretende ativar/inativar: ");
            String userLogin = sc.nextLine();
            
            utilizador = Utilizadores.PesquisarUtilizadorPorLogin(userLogin);

            if(utilizador == null) {
                System.out.println("O login que introduziu nao corresponde a nenhum utilizador!");
            }

        }while(utilizador == null);

        ativarInativar(utilizador);
    }

    public void MenuInicialGestor(){
        System.out.println("Bem-Vindo Gestor " + this.UtilizadorAtual.getNome());
        do {
            if(listaNotificacoesGestor.size() - auxNotificacoesGestor == 0) {
                System.out.println("1  - Notificacoes");
            }
            else {
                System.out.println("1  - Notificacoes (" + (listaNotificacoesGestor.size() - auxNotificacoesGestor) + ")");
            }
            System.out.println("2  - Criar utilizador");
            System.out.println("3  - Ver pedidos de registo");
            System.out.println("4  - Ver pedidos de remocao de conta");
            System.out.println("5  - Ver pedidos de revisao");
            System.out.println("6  - Atribuir processo de revisao");
            System.out.println("7  - Arquivar um processo");
            System.out.println("8  - Consultar estado de uma revisao");
            System.out.println("9  - Listar todas as revisoes");
            System.out.println("10 - Pesquisar revisoes por estado");
            System.out.println("11 - Listar todas as obras");
            System.out.println("12 - Pesquisar obras por titulo");
            System.out.println("13 - Listar todos os utilizadores");
            System.out.println("14 - Pesquisar utilizadores");
            System.out.println("15 - Ordenar utilizadores por ordem alfabetica");
            System.out.println("16 - Ordenar autores pelo numero total de obras");
            System.out.println("17 - Ordenar revisoes");
            System.out.println("18 - Ler dados de um ficheiro");
            System.out.println("19 - Gravar dados num ficheiro");
            System.out.println("20 - Consultar o log de acoes");
            System.out.println("21 - Editar dados pessoais");
            System.out.println("22 - Ativar/Inativar uma conta");
            System.out.println("23 - Solicitar remocao de conta");
            System.out.println("24 - Terminar Sessao");
            this.Opcao = sc.nextInt();

            switch (Opcao) {
                case 1:
                    System.out.println(this.notificacoesGestor());
                    this.MenuInicialGestor();
                    // Notificações
                    break;

                case 2:
                    gestorCriaUtilizador();
                    // criar utilizador
                    break;
                case 3:
                    System.out.println(Utilizadores.listarPedidosRegistoPendentes());

                    if(Utilizadores.listarPedidosRegistoPendentes() != "De momento nao ha pedidos de registo!") {
                        AceitarRecusarPedidoRegisto();
                    }

                    this.MenuInicialGestor();
                    // Pedidos de registo
                    break;

                case 4:
                    System.out.println(Utilizadores.listarPedidosRemocaoPendentes());

                    if(Utilizadores.listarPedidosRemocaoPendentes() != "De momento nao ha pedidos de remocao!") {
                        AceitarRecusarPedidoRemocao();
                    }
                    // Pedidos de remoção
                    break;

                case 5:
                    gestorAceitarRecusarPedidoRevisao();
                    this.MenuInicialGestor();
                    // Ver pedidos de revisão
                    break;

                case 6:
                   /*  Revisao revisaoAux;
                    int numeroSerie;
                    System.out.println(gereRevisoes.listarRevisoes());
                    System.out.println("Introduza o numero de serie da revisao a que pretende atribuir o revisor: ");
                    numeroSerie = sc.nextInt();

                    revisaoAux = gereRevisoes.pesquisarPorNumeroSerie(numeroSerie);

                    revisaoAux.getListaRevisoresIndisponiveis();

                    gereRevisoes.getListaRevisoes();

                    revisaoAux.listarRevisoresDisponiveis((Revisao)revisaoAux);
                    


                    System.out.println();

                    System.out.println("Introduza o login do revisor que pretende atribuir: ");
                     */

                    
                    // Atribui processo de revisão
                    break;

                case 7:
                    System.out.println("Arquivar um processo");
                    // Arquivar um processo
                    break ;
                
                case 8:
                    System.out.println("Consultar estado de uma revisao");
                    // Consultar estado de uma revisão
                    break ;

                case 9:
                    System.out.println(gereRevisoes.listarTodasRevisoes());   
                    // Listar todas as revisões
                    break ;
                    
                case 10:
                    pesquisarRevisoesPorEstado();              
                    this.MenuInicialGestor();
                    // Pesquisar revisões por estado
                    break;

                case 11:
                    listarTodasObras();
                    this.MenuInicialGestor();
                    // Listar todas as obras
                    break;

                case 12: 
                    pesquisarObrasPorTitulo();
                    this.MenuInicialGestor();
                    // Pesquisar obras por titulo
                    break;

                case 13:
                    System.out.println(Utilizadores.listarTodosUtilizadores());
                    this.MenuInicialGestor();
                    // Listar todos os utilizadores
                    break;
                
                case 14: 
                    System.out.println("Selecione uma opcao: ");
                    System.out.println("1 - Pesquisar utilizador por login");
                    System.out.println("2 - Pesquisar utilizador por tipo");
                    this.Opcao = sc.nextInt();

                    switch(this.Opcao) {
                        case 1:
                            pesquisarUtilizadorPorLogin();
                            break;

                        case 2:
                            pesquisarUtilizadorPorTipo();
                            break;
                    }

                    this.MenuInicialGestor();
                    // Pesquisar utilizadores (login / tipo)
                    break;

                case 15:
                    if(Utilizadores.ordenarUtilizadoresPorOrdemAlfabetica()) {
                        System.out.println(Utilizadores.listarTodosUtilizadores());
                    }
                    else {
                        System.out.println("Nao foi possivel ordenar os utilizadores!");
                    }
                    // Ordenar utilizadores por ordem alfabetica
                    this.MenuInicialGestor();
                    break;

                case 16:
                    // Ordenar autores pelo numero total de obras
                    break;

                case 17:
                    // Ordenar revisoes (duracao ou data)
                    break;

                case 18:
                    System.out.println("Ler dados de um ficheiro");
                    // Ler dados de um ficheiro
                    break;

                case 19:
                    System.out.println("Gravar dados num ficheiro");
                    // Gravar dados num ficheiro
                    break;

                case 20:
                    System.out.println("Consultar o log de acoes");
                    // Consultar o log de ações
                    break; 
                    
                case 21:
                    EditarDados();
                    // Editar dados pessoais
                    break;

                case 22:
                    gestorAtivarInativarConta();
                    this.MenuInicialGestor();
                    // Ativar/Inativar uma conta
                    break;

                case 23:
                    solicitarRemocaoConta();
                    this.MenuInicialGestor();
                    // Solicitar remoção de conta
                    break;

                case 24:
                    System.out.println("Adeus " + this.UtilizadorAtual.getNome());
                    this.MenuInicial();
                    break;

                default:
                    System.out.println("Opcao invalida");
            }
        } while (Opcao < 1 || Opcao > 24);
    }

    public void criarObra() {
        String _Titulo;
        String _EstiloLiterario;
        String _TipoPublicacao;
        int _NumeroPaginas;
        int _ISBN;
        int _NumeroEdicao;
        String _DataSubmissao;

        sc.nextLine();
        System.out.println("Introduza o titulo da sua obra: ");
        _Titulo = sc.nextLine();
        
        System.out.println("Introduza o estilo literario da sua obra: ");
        _EstiloLiterario = sc.nextLine();

        System.out.println("Introduza o tipo de publicacao da sua obra: ");
        _TipoPublicacao = sc.nextLine();

        System.out.println("Introduza o numero de paginas da sua obra: ");
        _NumeroPaginas = sc.nextInt();

        System.out.println("Introduza o codigo ISBN da sua obra: ");
        _ISBN = sc.nextInt();

        System.out.println("Introduza o numero de edicao da sua obra: ");
        _NumeroEdicao = sc.nextInt();

        sc.nextLine();
        System.out.println("Introduza a data de submissao da sua obra: ");
        _DataSubmissao = sc.nextLine();

        Obra NovaObra = new Obra((Autor) this.UtilizadorAtual, _Titulo, _EstiloLiterario, _TipoPublicacao, _NumeroPaginas, _ISBN, _NumeroEdicao, _DataSubmissao, _DataSubmissao);
        this.gereObras.adicionarObra(NovaObra);

        burroCarga.setGereObras(this.gereObras);
        escreverBurroCarga(burroCarga);
    }

    public void solicitarRemocaoConta() {
        System.out.println("Solicitar remocao de conta");
        this.UtilizadorAtual.setEstado(-3);
        listaNotificacoesGestor.add(0, "O utilizador com o login " + this.UtilizadorAtual.getLogin() + " realizou um pedido de remocao de conta");
        if(this.UtilizadorAtual.getEstado() == -3) {
            System.out.println("O seu pedido foi realizado com sucesso!");
            return;
        }
        System.out.println("Nao foi possivel realizar o seu pedido!");                    
        return;
    }

    public void submeterObraParaRevisao() {
        Obra obra;
        int ISBN;
        int novoNumeroSerie = gereRevisoes.ultimoNumeroSerie();
        System.out.println("As suas obras");
        System.out.println(gereObras.listarObrasConsoanteAutor((Autor)this.UtilizadorAtual));

        do {
            System.out.println("Introduza o codigo ISBN da obra que pretende submeter para revisao: ");
            ISBN = sc.nextInt();

            obra = gereObras.pesquiarObraPorISBN(ISBN);

            if(obra == null) {
                System.out.println("Nenhuma obra encontrada com o codigo " + ISBN);
            }
        } while (obra == null);

        Revisao novaRevisao = new Revisao(obra, 0, ++novoNumeroSerie);
        gereRevisoes.adicionarRevisao(novaRevisao);
        burroCarga.setGereRevisoes(this.gereRevisoes);
        escreverBurroCarga(burroCarga);
        System.out.println("Operacao realizada com sucesso");
        System.out.println("A sua obra foi submetida para revisao!");
    }

    public void consultarEstadodaRevisaoAutor(Autor aAutor) {
        int numeroSerie;
        System.out.println(gereRevisoes.listarRevisoesDesteAutor((Autor)this.UtilizadorAtual));

        if(gereRevisoes.listarRevisoesDesteAutor((Autor)this.UtilizadorAtual).equals("De momento nao tem nenhuma revisao ativa!")) {
            return;
        }

        System.out.println("Introduza o numero de serie da revisao que deseja consultar: ");
        numeroSerie = sc.nextInt();

        switch(gereRevisoes.pesquisarPorNumeroSerie(numeroSerie).getEstado()) {            
            case -1:
                System.out.println("Esta revisao foi recusada pelo gestor");
                break;

            case 0:
                System.out.println("Esta revisao encontra-se iniciada (ja submetida pelo autor, a aguardar autorizacao do gestor)");
                break;

            case 1:
                System.out.println("Esta revisao encontra-se aceite pelo gestor, esta a aguardar autorizacao do revisor");
                break;

            case 2:
                System.out.println("Esta revisao ja foi aceite pelo revisor e encontra-se em execucao");
                break;

            case 3:
                System.out.println("Esta revisao encontra-se finalizada");
                break;

            case 4:
                System.out.println("Esta revisao encontra-se arquivada");
                break;

            default:
                System.out.println("Algo correu mal!");
                break;
        }
    }

    public void MenuInicialAutor(){
        System.out.println("Bem-Vindo Autor " + this.UtilizadorAtual.getNome());
        do{
            System.out.println("1  - Notificacoes");
            System.out.println("2  - Editar dados pessoais");
            System.out.println("3  - Solicitar remocao de conta");
            System.out.println("4  - Criar obra");
            System.out.println("5  - Submeter obra para revisao");
            System.out.println("6  - Consultar estado de uma revisao");
            System.out.println("7  - Listar as minhas revisoes");
            System.out.println("8  - Pesquisar as minhas revisoes");
            System.out.println("9  - Listar as minhas obras");
            System.out.println("10  - Pesquisar as minhas obras");
            System.out.println("11 - Terminar Sessao");

            this.Opcao = sc.nextInt();

            switch(this.Opcao)
            {
                case 1: 
                    System.out.println("Notificacoes Autor");
                    MenuInicialAutor();
                    //Notificações
                    break;

                case 2:
                    this.EditarDados();
                    // Editar dados pessoais
                    break;

                case 3:
                    solicitarRemocaoConta();
                    this.MenuInicialAutor();
                    // Solicitar remoção de conta
                    break;

                case 4:
                    criarObra();
                    this.MenuInicialAutor();
                    // Criar obra
                    break;

                case 5:
                    submeterObraParaRevisao();
                    this.MenuInicialAutor();
                    // Submeter obra para revisão
                    break;

                case 6:
                    consultarEstadodaRevisaoAutor((Autor)this.UtilizadorAtual); 
                    this.MenuInicialAutor();
                    // Consultar estado de uma revisão
                    break;

                case 7:
                    gereRevisoes.listarRevisoesDesteAutor((Autor)this.UtilizadorAtual);
                    this.MenuInicialAutor();
                    // Listar as minhas revisões
                    break;

                case 8:
                    String Titulo;
                    int ISBN;
                    System.out.println("Introduza o titulo da obra da revisao que pretende encontrar: ");
                    Titulo = sc.nextLine();

                    System.out.println(gereObras.pesquiarObraPorTitulo(Titulo));

                    System.out.println("Introduza o codigo ISBN da obra da revisao que pretende encontrar: ");
                    ISBN = sc.nextInt();

                    System.out.println(gereRevisoes.pesquisarPorISBN(ISBN));
                    // Pesquisar as minhas revisões
                    break;

                case 9:
                    System.out.println(gereObras.listarObrasConsoanteAutor((Autor)this.UtilizadorAtual));
                    this.MenuInicialAutor();
                    // Listar as minhas obras
                    break;

                case 10:
                    gereObras.listarObrasConsoanteAutor((Autor)this.UtilizadorAtual);
                    // Pesquisar as minhas obras
                    break;

                case 11:
                    System.out.println("Adeus " + this.UtilizadorAtual.getNome());
                    MenuInicial();
                    break;

                default:
                    System.out.println("Algo correu mal!");
                    break;
            }

            if(this.Opcao < 1 || this.Opcao > 11)
            {
                System.out.println("Opcao invalida!");
                System.out.println("Por favor introduza uma opcao valida!");
            }

            if(this.Opcao >= 1 && this.Opcao <= 11)
            {
                break;
            }

        } while(this.Opcao != 11);
    }

    
    public void revisorAceitarRecusarPedidoRevisao() {
        Revisao revisao = null;
        int ISBN;
        System.out.println(gereRevisoes.listarPedidosRevisaoMenuRevisor());

        do {
            System.out.println("Introduza o codigo ISBN da obra que deseja aceitar/recusar: ");
            ISBN = sc.nextInt();

            revisao = gereRevisoes.pesquisarPorISBN(ISBN);

            if(revisao == null) {
                System.out.println("Nao existe nenhuma obra por aceitar/recusar com o codigo " + ISBN);
            }
        } while (revisao == null);

        do {
            System.out.println("1 - Aceitar");
            System.out.println("2 - Recusar");
            this.Opcao = sc.nextInt();

            if(this.Opcao < 1 || this.Opcao > 2) {
                System.out.println("Por favor introduza uma opcao valida!");
            }
        } while (this.Opcao < 1 || this.Opcao > 2);  
        
        switch(this.Opcao) {
            case 1:
                revisao.setGestorResponsavel((Gestor)this.UtilizadorAtual);
                revisao.setEstado(2);
                System.out.println("Operacao realizada com sucesso!");
                System.out.println("Foi enviado o pedido de revisao desta obra para o revisor!");
                break;
                
            case 2: 
                revisao.setEstado(0);
                revisao.adicionarRevisorIndisponivel((Revisor)this.UtilizadorAtual);
                System.out.println("Operacao realizada com sucesso!");
                System.out.println("Revisao recusada!");
                break;
        }                         
    }

    public void revisorPesquisarRevisoes() {
        String Titulo;
        System.out.println("Introduza o titulo da obra em revisao: ");
        Titulo = sc.nextLine();
        System.out.println(gereRevisoes.pesquisarRevisaoPorTituloObra((Revisor)this.UtilizadorAtual, Titulo));
    }

    public void MenuInicialRevisor(){
        System.out.println("Bem-Vindo Revisor " + this.UtilizadorAtual.getNome());
        do {
            System.out.println("1  - Notificacoes");
            System.out.println("2  - Editar dados pessoais");
            System.out.println("3  - Solicitar remocao de conta");
            System.out.println("4  - Gerir processos de revisao");
            System.out.println("5  - Listar as minhas revisoes");
            System.out.println("6  - Pesquisar as minhas revisoes");
            System.out.println("7  - Terminar sessao");
            
            this.Opcao = sc.nextInt();

            switch (this.Opcao) {
                case 1:
                    System.out.println(">> Notificacoes <<");
                    // Notificações
                    break;

                case 2:
                    this.EditarDados();
                    // Editar dados pessoais
                    break;

                case 3:
                    solicitarRemocaoConta();              
                    this.MenuInicialRevisor();
                    // Solicitar remoção de conta
                    break;

                case 4: 
                    revisorAceitarRecusarPedidoRevisao();
                    this.MenuInicialRevisor();
                    // Gerir processos de revisão
                    break;

                case 5:
                    System.out.println(gereRevisoes.listarRevisoesConsoanteRevisor((Revisor)this.UtilizadorAtual));
                    this.MenuInicialRevisor();
                    // Listar as minhas revisões
                    break;

                case 6:
                    revisorPesquisarRevisoes();
                    this.MenuInicialRevisor();
                    // Pesquisar as minhas revisões

                case 7:
                    System.out.println("Adeus " + this.UtilizadorAtual.getNome());
                    this.MenuInicial();
            }
        } while (Opcao < 1 || Opcao > 7);
    }

    public Utilizador getUtilizadorAtual(){
        return this.UtilizadorAtual;
    }

    public int getOpcao() {
        return this.Opcao;
    }

    public void setOpcao(int _Opcao) {
        this.Opcao = _Opcao;
    }

    public void InicializarTiposUtilizadores(){
        TipoUtilizador Autor = new TipoUtilizador(1, "Autor", "Os autores submetem obras para revisao, que devem ser revistas por um ou mais revisores, e realizam o\r\n" + //
                        "respectivo pagamento.");
        TipoUtilizador Revisor = new TipoUtilizador(2, "Revisor", "Os revisores gerem (aprovam e finalizam) o processo de revisao.");
        TipoUtilizador Gestor = new TipoUtilizador(3, "Gestor", "Faz a gestao de utilizadores da aplicacao");

        TiposUtilizadores.add(Autor);
        TiposUtilizadores.add(Revisor);
        TiposUtilizadores.add(Gestor);
    }

    public void MenuInicial(){
        burroCarga = lerBurroCarga();
        gereObras = burroCarga.getGereObras();
        gereRevisoes = burroCarga.getGereRevisoes();
        Utilizadores = burroCarga.getGereUtilizador();
        
        if(this.Utilizadores.isListaEmpty()) {
            Registo();
        }
        do{
            System.out.println("Bem-Vindo!!!");
            System.out.println("Selecione uma das seguintes opcoes:");
            System.out.println("1 - Login");
            System.out.println("2 - Registar Novo Utilizador");
            System.out.println("3 - Terminar");
            this.Opcao = sc.nextInt();

            switch (Opcao){
                case 1:
                    Login();
                    break;

                case 2:
                    Registo();
                    break;

                case 3:
                    System.out.println("Adeus...");
                    try {
                        burroCarga.setGereObras(this.gereObras);
                        escreverBurroCarga(burroCarga);
                        Thread.sleep(2000);
                        sc.close();
                        System.exit(0);
                    } catch (InterruptedException e){
                        System.err.println("Nao foi possivel encerrar o programa...");
                        sc.close();
                        System.exit(1);
                    }
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
                    break;
            }
        }while(Opcao < 1 || Opcao > 3);
    }
}