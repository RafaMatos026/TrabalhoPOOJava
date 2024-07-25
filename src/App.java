import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class App {
    public Utilizador UtilizadorAtual = null;
    public TipoUtilizador tipoUtilizadorAtual = null;
    public int Opcao;
    public Boolean Running = true;
    public Scanner sc = new Scanner(System.in);
    public GereUtilizador Utilizadores = new GereUtilizador();
    public ArrayList<TipoUtilizador> TiposUtilizadores = new ArrayList<TipoUtilizador>();

    public App(){

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
                System.out.println("A sua conta encontra-se inactiva!");
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

    public void AceitarRecusarUtilizar() {
        Utilizadores.listaUtilizadoresPendentes();
        Utilizador utilizador = null;

        do{
            System.out.println("Introduza o login do utilizador que pretende mudar: ");
            String userLogin = sc.nextLine();
            
            utilizador = Utilizadores.PesquisarUtilizadorPorLogin(userLogin);

            if(utilizador == null) {
                System.out.println("O login que introduziu nao corresponde a nenhum utilizador!");
            }

        }while(utilizador == null);

        int opcao = -1 ;
        do {
            System.out.println("1 - Aceitar pedido");
            System.out.println("2 - Recusar pedido");
            opcao = sc.nextInt();

            if(opcao < 1 && opcao > 2) {
                System.out.println("Por favor introduza uma opcao valida!");
            }

        } while (opcao < 1 && opcao > 2);
    
        if(opcao == 2) {
            utilizador.setEstado(-2);
        }else{
            utilizador.setEstado(1);
        }
        
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
                    _TipoUtilizador, 0,  _NIF, _Morada, _Telefone, _estiloLiterario);

            this.Utilizadores.adicionar(NovoAutor);
        }

        if (_TipoUtilizador.getID() == 2){
            Revisor NovoRevisor = new Revisor(_Login, _Email, _Nome, _Password, _TipoUtilizador,0, _NIF, _Morada, _Telefone, _formacaoAcademica);

            this.Utilizadores.adicionar(NovoRevisor);
        }

        if (_TipoUtilizador.getID() == 3){
            Gestor NovoGestor = new Gestor(_Login, _Email, _Nome, _Password, 0 , _TipoUtilizador);

            this.Utilizadores.adicionar(NovoGestor);
        }
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
            
            this.sc.nextLine();
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
                System.out.println("8 - Cancelar");

                this.Opcao = sc.nextInt();
            }while (this.Opcao < 1 || this.Opcao > 8);

            switch (this.Opcao){
                case 1 ->{
                    String _Nome = "";
                    System.out.println("Novo nome: ");
                    _Nome = this.sc.nextLine();

                    this.UtilizadorAtual.setNome(_Nome);
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 2 -> {
                    String _Login = "";
                    System.out.println("Novo login: ");
                    _Login = sc.nextLine();

                    this.UtilizadorAtual.setLogin(_Login);
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 3 -> {
                    String _Email = "";
                    System.out.println("Novo email: ");
                    _Email = sc.nextLine();

                    this.UtilizadorAtual.setEmail(_Email);
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 4 ->{
                    String _Password = "";
                    System.out.println("Nova password: ");
                    _Password = sc.nextLine();

                    this.UtilizadorAtual.setPassword(_Password);
                    System.out.println("Operacao realizada com sucesso!");
                    this.EditarDados();
                }

                case 5 -> {
                    String _NIF = "";
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
                        break;
                }

                default -> {
                    System.out.println("Opcao Invalida!");
                }
            }
        }
    }

    public void MenuInicialGestor(){
        System.out.println("Bem-Vindo Gestor " + this.UtilizadorAtual.getNome());
        do {
            System.out.println("1  - Notificacoes");
            System.out.println("2  - Criar utilizador");
            System.out.println("3  - Ver pedidos de registo");
            System.out.println("4  - Ver pedidos de remocao de conta");
            System.out.println("5  - Ver pedidos de revisao");
            System.out.println("6  - Atribui processo de revisao");
            System.out.println("7  - Arquivar um processo");
            System.out.println("8  - Consultar estado de uma revisao");
            System.out.println("9  - Listar todas as revisoes");
            System.out.println("10 - Pesquisar todas as revisoes");
            System.out.println("11 - Listar todas as obras");
            System.out.println("12 - Pesquisar todas as obras");
            System.out.println("13 - Ler dados de um ficheiro");
            System.out.println("14 - Gravar dados num ficheiro");
            System.out.println("15 - Consultar o log de acoes");
            System.out.println("16 - Editar dados pessoais");
            System.out.println("17 - Terminar Sessao");
            this.Opcao = sc.nextInt();

            switch (Opcao) {
                case 1:
                    System.out.println("Notificacoes");
                    // Notificações
                    break;

                case 2:
                    System.out.println(">> Criar Utilizador <<");
                    // criar utilizador
                    break;
                case 3:
                    System.out.println(">> Ver pedidos de registo <<");
                    // Ver pedidos de registo
                    break;

                case 4:
                    System.out.println(">> Ver pedidos de remocao de conta <<");
                    // Ver pedidos de remoção de conta
                    break;

                case 5:
                    System.out.println(">> Ver pedidos de revisao <<");
                    // Ver pedidos de revisão
                    break;

                case 6:
                    System.out.println(">> Atribui processo de revisao <<");
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
                    System.out.println("Listar todas as revisoes");
                    // Listar todas as revisões
                    break ;
                    
                case 10:
                    System.out.println("Pesquisar todas as revisoes");
                    // Pesquisar todas as revisões
                    break;

                case 11:
                    System.out.println("Listar todas as obras");
                    // Listar todas as obras
                    break;

                case 12: 
                    System.out.println("Pesquisar todas as obras");
                    // Pesquisar todas as obras
                    break;

                case 13:
                    System.out.println("Ler dados de um ficheiro");
                    // Ler dados de um ficheiro
                    break;

                case 14:
                    System.out.println("Gravar dados num ficheiro");
                    // Gravar dados num ficheiro
                    break;

                case 15:
                    System.out.println("Consultar o log de acoes");
                    // Consultar o log de ações
                    break; 
                    
                case 16:
                    System.out.println("Editar dados pessoais");
                    EditarDados();
                    // Editar dados pessoais
                    break;

                case 17:
                    System.out.println("Adeus " + this.UtilizadorAtual.getNome());
                    this.MenuInicial();
                    break;

                default:
                    System.out.println("Opcao invalida");
            }
        } while (Opcao < 1 || Opcao > 5);
    }

    public void MenuInicialAutor(){
        System.out.println("Bem-Vindo Autor " + this.UtilizadorAtual.getNome());
        do{
            System.out.println("1  - Editar dados pessoais");
            System.out.println("2  - Solicitar remocao de conta");
            System.out.println("3  - Submeter obra para revisao");
            System.out.println("4  - Consultar estado de uma revisao");
            System.out.println("5  - Listar as minhas revisoes");
            System.out.println("6  - Pesquisar as minhas revisoes");
            System.out.println("7  - Listar as minhas obras");
            System.out.println("8  - Pesquisar as minhas obras");
            System.out.println("9  - Terminar Sessao");

            this.Opcao = sc.nextInt();

            switch(this.Opcao)
            {
                case 1:
                    System.out.println("Editar dados pessoais");
                    // Editar dados pessoais
                    break;

                case 2:
                    System.out.println("Solicitar remocao de conta");
                    // Solicitar remoção de conta
                    break;

                case 3:
                    System.out.println("Submeter obra para revisao");
                    // Submeter obra para revisão
                    break;

                case 4:
                    System.out.println("Consultar estado de uma revisao");
                    // Consultar estado de uma revisão
                    break;

                case 5:
                    System.out.println("Listar as minhas revisoes");
                    // Listar as minhas revisões
                    break;

                case 6:
                    System.out.println("Pesquisar as minhas revisoes");
                    // Pesquisar as minhas revisões
                    break;

                case 7:
                    System.out.println("Listar as minhas obras");
                    // Listar as minhas obras
                    break;

                case 8:
                    System.out.println("Pesquisar as minhas obras");
                    // Pesquisar as minhas obras
                    break;

                case 9:
                    System.out.println("Adeus " + this.UtilizadorAtual.getNome());
                    MenuInicial();
                    break;

                default:
                    System.out.println("Algo correu mal!");
                    break;
            }

            if(this.Opcao < 1 || this.Opcao > 6)
            {
                System.out.println("Opcao invalida!");
                System.out.println("Por favor introduza uma opcao valida!");
            }

            if(this.Opcao >= 1 && this.Opcao <= 6)
            {
                break;
            }

        } while(this.Opcao != 0);
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
                    System.out.println(">> Editar dados pessoais <<");
                    // Editar dados pessoais
                    break;

                case 3:
                    System.out.println("Solicitar remocao de conta");
                    // Solicitar remoção de conta
                    break;

                case 4: 
                    System.out.println("Gerir processos de revisao");
                    // Gerir processos de revisão
                    break;

                case 5:
                    System.out.println("Listar as minhas revisoes");
                    // Listar as minhas revisões
                    break;

                case 6:
                    System.out.println("Pesquisar as minhas revisoes");
                    // Pesquisar as minhas revisões

                case 7:
                    System.out.println("Adeus " + this.UtilizadorAtual.getNome());
                    this.MenuInicial();
            }
        } while (Opcao < 1 || Opcao > 3);
    }

    public Utilizador getUtilizadorAtual(){
        return this.UtilizadorAtual;
    }

    public void setRunning(Boolean _Running){
        this.Running = _Running;
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
                        Thread.sleep(2000);
                        sc.close();
                        System.exit(0);
                    } catch (InterruptedException e){
                        System.err.println("Nao foi possivel pausar o programa...");
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
