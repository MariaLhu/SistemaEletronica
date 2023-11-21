import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Garantia {
    //Atributos da Classe Garantia.
    private static int idAutomatico = 1;
    private int idGarantia;
    private Date dataInicial;
    private Date dataFinal;
    private Servico servico;

//O contrutor da Classe, que inicializa uma garantia com os parâmetros fornecidos. 

    public Garantia(Date dataInicial, Date dataFinal, Servico servico) {
        this.idGarantia = idAutomatico++;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.servico = servico;
    }
//Encapsulamento, utilizados para acessar e modificar os atributos que estão privados.    

    public int getIdGarantia() {
        return idGarantia;
    }

    public void setIdGarantia(int id) {
        this.idGarantia = id;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getServico() {
        return servico.getIdServico();
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
//Método para obter uma data do usuário usando um prompt(Indicador que está pronto para aceitar a entrada do usuário) e um formato de data específico. Ele usa um loop para garantir que a entrada seja válida.
    public static Date getDateInput(String prompt, SimpleDateFormat sdf) {
        Date value = null;
        boolean validInput = false;
        while (!validInput) {
            String input = JOptionPane.showInputDialog(prompt);
            if (input == null) {
                return null;
            }
            try {
                value = sdf.parse(input);
                validInput = true;
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null,
                        "Formato de data inválido. Certifique-se de usar o padrão (dd/MM/yyyy)");
            }
        }
        return value;
    }

    //Método para obter um número inteiro do usuário usando um prompt(Indicador que está pronto para aceitar a entrada do usuário). Ele usa um loop para garantir que a entrada seja válida.
    public static int getIntegerInput(String prompt) {
        int value = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog(prompt);
                if (input == null) {
                    return value;
                }
                value = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um número inteiro válido.");
            }
        }
        return value;
    }
//Permite cadastrar uma nova garantia. Solicita informações com ID da garantia, datas iniciais e finais, e associa a um serviço existente a essa garantia.
    public static void cadastrarGarantia(ArrayList<Garantia> listaGarantias, ArrayList<Servico> listaServicos) {

        //ID da garantia:
        int idGarantia = getIntegerInput("ID da Garantia:");
        if (idGarantia == -1) {
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Cadastro da data inicial
        Date dataInicial = getDateInput("Data Inicial (dd/MM/yyyy):", sdf);
        if (dataInicial == null) {
            return;
        }
        // Cadastro da data final
        Date dataFinal = getDateInput("Data Final (dd/MM/yyyy):", sdf);
        if (dataFinal == null) {
            return;
        }

      /*  //Escolha de Serviço:
        ArrayList<Servico> optionsServicos =  new ArrayList<Servico>();
        for (Servico servicoItem : listaServicos) {
            optionsServicos.add(new Servico(servicoItem.getIdServico(), servicoItem.getIdEquipamento(), servicoItem.getCliente()));
        }
        Object[] options = optionsServicos.toArray();
         */

         Servico servicoEncontrado = null;
         int idServico = getIntegerInput("ID do Servico:");
         if (idServico == -1) {
            return;
         }

         for (Servico servico : listaServicos) {
             if (servico.getIdServico() == idServico) {
                 servicoEncontrado = servico;
                 break;
             }
         }

         
        
        Garantia garantiaCadastrar = new Garantia(dataInicial, dataFinal, servicoEncontrado);

        // Adicione a nova garantia a lista.
        listaGarantias.add(garantiaCadastrar);
        JOptionPane.showMessageDialog(null, "Garantia cadastrada com sucesso!");

    }
//Permite editar uma garantia existente. Oferece opções para editar o ID da garantia, trocar o serviço associado, e editar as datas inicial e final.
    public static void editarGarantia(ArrayList<Garantia> listaGarantias, ArrayList<Servico> listaServicos){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean encontrado = false;
        Garantia garantiaEditar = null;


        if (listaGarantias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma garantia cadastrada.");
        } else {
            listarGarantia(listaGarantias);
            int idEditar = getIntegerInput("Qual o ID da garantia que deseja editar?");
            if (idEditar == -1) {
                return;
            }
            for (Garantia garantia : listaGarantias) {
                if (garantia.getIdGarantia() == idEditar) {
                    garantiaEditar = garantia;
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                boolean edicaoConcluida = false;

                while (!edicaoConcluida) {
                    int opcaoEditar;

                    String[] opcoesEditar = {"Editar ID da Garantia", "Trocar Serviço", "Editar a data inicial",
                            "Editar a data final", "Voltar"};
                    opcaoEditar = JOptionPane.showOptionDialog(
                            null,
                            "Escolha uma opção para edição:",
                            "Editar serviço",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            opcoesEditar,
                            opcoesEditar[0]
                    );

                    switch (opcaoEditar) {

                        case 0:// Edição do ID da garantia
                            int novoIdGarantia = getIntegerInput("Digite o novo ID da Garantia:");
                            if (novoIdGarantia != -1) {
                                garantiaEditar.setIdGarantia(novoIdGarantia);
                            }
                            break;

                        case 1:    //Trocar Serviço
                            Object[] options = listaServicos.toArray();
                            Servico novoServico = (Servico) JOptionPane.showInputDialog(null, "Escolha um serviço", "Escolha um serviço", JOptionPane.PLAIN_MESSAGE, null, options, garantiaEditar.getServico());
                            garantiaEditar.setServico(novoServico);
                            break;

                        case 2: //Edição da data inicial
                            Date novaDataInicial = getDateInput(
                            "nova data inicial (dd/MM/yyyy): ", sdf);
                            if (novaDataInicial != null) {
                                garantiaEditar.setDataInicial(novaDataInicial);
                            }
                            break;          
                            
                        case 3: //Edição da data final
                            Date novaDataFinal = getDateInput(
                            "Nova data final (dd/MM/yyyy):", sdf);
                            if (novaDataFinal != null) {
                                garantiaEditar.setDataFinal(novaDataFinal);
                            }
                            break;                            

                        case 4:
                            JOptionPane.showMessageDialog(null, "Edição concluída.");
                            edicaoConcluida = true;
                            break;
                    }

                    JOptionPane.showMessageDialog(null, "Garantia editada!");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Garantia não existe.");
            }
        }
    }
//Exibe as informações de todas as garantias presentes na lista.
    public static void listarGarantia(ArrayList<Garantia> listaGarantias) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (listaGarantias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma garantia cadastrada.");
        } else {
            for (Garantia garantia : listaGarantias) {
                JOptionPane.showMessageDialog(null, "ID: " + garantia.getIdGarantia() +
                        "\nServiço: " + garantia.getServico() +
                        "\nData Inicial: " + sdf.format(garantia.getDataInicial()) +
                        "\nData Final: " + sdf.format(garantia.getDataFinal()));
            }
        }
    }
//Remove uma garantia da lista com base no seu ID.
    public static boolean excluirGarantia(int idGarantia, ArrayList<Garantia> listaGarantias) {
        for (Garantia garantia : listaGarantias) {
            if (garantia.getIdGarantia() == idGarantia) {
                listaGarantias.remove(garantia);
                JOptionPane.showMessageDialog(null, "Garantia removida.");

                return true; // Retorna true se a garantia foi excluída
            }
        }
        JOptionPane.showMessageDialog(null, "Garantia não encontrada.");
        return false; // Retorna false se a garantia não foi encontrada
    }
}