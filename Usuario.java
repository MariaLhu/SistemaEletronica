import javax.swing.*;
import java.util.ArrayList;

public class Usuario {
//Atributos da Classe Usuário.    
    private static int idAutomatico = 1;
    private int idUsuario;
    private String login;
    private String senha;
//O contrutor da Classe, que inicializa uma usuário com os parâmetros fornecidos. Uma nova instância de Usuário.
    public Usuario(String login, String senha) {
        this.idUsuario = idAutomatico++;
        this.login = login;
        this.senha = senha;
    }
//Encapsulamento, utilizando Getters e Setters para acessar e modificar os atributos que estão privados.   
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id) {
        this.idUsuario = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
//Método para realizar o login comparando as credenciais inseridas (login e senha) com os usuários na lista, para verificar se são válidas.
    public static boolean realizarLogin(ArrayList<Usuario> listaUsuarios) {

        String login = JOptionPane.showInputDialog("Login:");
        String senha = JOptionPane.showInputDialog("Senha:");

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return true; // Retorna true se o usuário foi encontrado.
            }
        }
        return false; // Retorna false se o usuário não foi encontrado.
    }
//Método para cadastrar um novo usuário com validação de entrada.
//Solicita login e senha ao usuário
// Tenta converter a string para um número
// Cria um novo objeto Usuario e o adiciona à lista de usuários
    public static void cadastrarUsuario(ArrayList<Usuario> listaUsuarios) {
       

        
        try {

            String login = JOptionPane.showInputDialog("Login:");

            // Verifica se o campo de login está vazio
            if (login == null || login.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo de login não pode estar vazio. Tente novamente.");
                return;
            }

            String senha = JOptionPane.showInputDialog("Senha:");

            // Verifica se o campo de senha está vazio
            if (senha == null || senha.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo de senha não pode estar vazio. Tente novamente.");
                return;
            }

            Usuario usuarioCadastrar = new Usuario(login, senha);

            // Adicione o novo usuário à lista.
            listaUsuarios.add(usuarioCadastrar);
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para o ID do Usuário.");
        }

    }
//Método para editar um usuário existente.
    public static void editarUsuario(String[] args, ArrayList<Usuario> listaUsuarios) {
        boolean encontrado = false;
        Usuario usuarioEditar = null;

        if (listaUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum usuário cadastrado.");
        } else {
            listarUsuarios(listaUsuarios);
            int idEditar = Integer.parseInt(JOptionPane.showInputDialog("Qual o ID do usuário que deseja editar?"));
            if (idEditar == -1) {
                return;
            }
            for (Usuario usuario : listaUsuarios) {
                if (usuario.getIdUsuario() == idEditar) {
                    usuarioEditar = usuario;
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {

                String[] opcoes = {"Editar ID do Usuário", "Editar Login", "Editar Senha", "Voltar"};

                int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

                switch (opcao) {

                    case 0:// Edição do ID do usuário
                        String novoIdUsuarioStr = JOptionPane.showInputDialog("Digite o novo ID do Usuário:");
                        if (novoIdUsuarioStr == null) {
                            break;
                        }
                        try {
                            int novoIdUsuario = Integer.parseInt(novoIdUsuarioStr);
                            usuarioEditar.setIdUsuario(novoIdUsuario);
                            JOptionPane.showMessageDialog(null, "ID de usuário editado!");
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para o ID do Usuário.");
                        }
                        break;

                    case 1:    //Edição do login
                        String novoLogin = JOptionPane.showInputDialog("Edite o Login:");

                        // Verifica se o campo de login está vazio
                        if (novoLogin == null || novoLogin.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "O campo de login não pode estar vazio. Tente novamente.");
                            break;
                        }

                        usuarioEditar.setLogin(novoLogin);
                        JOptionPane.showMessageDialog(null, "Login editado!");
                        break;

                    case 2:    //Edição da senha
                        String novaSenha = JOptionPane.showInputDialog("Edite a Senha:");

                        // Verifica se o campo de senha está vazio
                        if (novaSenha == null || novaSenha.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "O campo de senha não pode estar vazio. Tente novamente.");
                            break;
                        }

                        usuarioEditar.setSenha(novaSenha);
                        JOptionPane.showMessageDialog(null, "Senha editada!");
                        break;

                    case 3:
                        break;

                    default:
                        break;

                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuário não existe.");
            }
        }
    }
//Método para excluir um usuário da lista, por meio o ID do usuário.
    public static boolean excluirUsuario(int idUsuario, ArrayList<Usuario> listaUsuarios) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                listaUsuarios.remove(usuario);
                return true; // Retorna true se o usuário foi excluído.
            }
        }
        return false; // Retorna false se o usuário não foi encontrado.
    }
//Método para exibir informações de todos os usuários na lista.
    public static void listarUsuarios(ArrayList<Usuario> listaUsuarios) {

        if (listaUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum usuário cadastrado.");
        } else {
            for (Usuario usuario : listaUsuarios) {
                JOptionPane.showMessageDialog(null, "ID: " + usuario.getIdUsuario() + "\nLogin: " + usuario.getLogin() + "\nSenha: " + usuario.getSenha());
            }
        }
    }
}