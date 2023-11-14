import javax.swing.JOptionPane;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Recibo {
    private static int idAutomatico = 1;
    private int idRecibo;
    private int idServico;
    private float valor;
    private Date dataDePagamento;
    private Date dataDeEmissao;

    // Construtor

    public Recibo(int idServico, float valor, Date dataDePagamento) {
        this.idRecibo = idAutomatico++;
        this.idServico = idServico;
        this.valor = valor;
        this.dataDePagamento = dataDePagamento;
        this.dataDeEmissao = new Date();
    }

    // Getters

    public int getIdRecibo() {
        return idRecibo;
    }

    public int getIdServico() {
        return idServico;
    }

    public float getValor() {
        return valor;
    }

    public Date getDataDePagamento() {
        return dataDePagamento;
    }

    public Date getDataDeEmissao() {
        return dataDeEmissao;
    }

    // Setters

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setDataDePagamento(Date dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
    }

    public void setDataDeEmissao(Date dataDeEmissao) {
        this.dataDeEmissao = dataDeEmissao;
    }

    // Métodos

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

    public static float getFloatInput(String prompt) {
        float value = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog(prompt);
                if (input == null) {
                    return value;
                }
                value = Float.parseFloat(input);
                validInput = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um valor numérico válido.");
            }
        }
        return value;
    }

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
                        "Formato de data inválido. Certifique-se de usar o padrão dd/mm/aaaa.");
            }
        }
        return value;
    }

    public static void criarNovoRecibo(ArrayList<Recibo> listaRecibos, SimpleDateFormat sdf) {
        int idServico = getIntegerInput("Digite o id do Serviço:");
        if (idServico == -1) {
            return;
        }
        float valor = getFloatInput("Digite o valor:");
        if (valor == -1) {
            return;
        }
        Date dataDePagamento = getDateInput("Digite a data de pagamento. Use o formato (dd/mm/aaaa):", sdf);
        if (dataDePagamento == null) {
            return;
        }

        Recibo recibo = new Recibo(idServico, valor, dataDePagamento);
        listaRecibos.add(recibo);
    }

    public static void emitirRecibo(int idDesejado, ArrayList<Recibo> listaRecibos) {
        boolean encontrado = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (listaRecibos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum recibo cadastrado.");

            System.out.println();
        } else {
            for (Recibo recibo : listaRecibos) {
                if (recibo.getIdRecibo() == idDesejado) {
                    JOptionPane.showMessageDialog(null, "ID: " + recibo.getIdRecibo() +
                            "\nID do Servico: " + recibo.getIdServico() +
                            "\nID do Servico: " + recibo.getIdServico() +
                            "\nData de Pagamento: " + sdf.format(recibo.getDataDePagamento()) +
                            "\nData de Emissão: " + sdf.format(recibo.getDataDeEmissao()) +
                            "\nValor: R$" + recibo.getValor());
                    encontrado = true;
                    break;
                }
            }
        }

    }

    public static void editarRecibo(ArrayList<Recibo> listaRecibos) {
        Scanner ler = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean encontrado = false;
        Recibo reciboEditar = null;

        if (listaRecibos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum recibo cadastrado.");
        } else {
            listarRecibo(listaRecibos);
            int idEditar = getIntegerInput("Qual o ID do recibo que deseja editar?");
            if (idEditar == -1) {
                return;
            }
            for (Recibo recibo : listaRecibos) {
                if (recibo.getIdRecibo() == idEditar) {
                    reciboEditar = recibo;
                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {
                boolean edicaoConcluida = false;

                while (!edicaoConcluida) {
                    int opcaoEditar;
                    String[] opcoesEditar = { "Editar ID do Serviço", "Editar valor", "Editar data de pagamento", "Concluir edição" };
                    opcaoEditar = JOptionPane.showOptionDialog(
                            null,
                            "Escolha uma opção de edição",
                            "Editar recibo",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            opcoesEditar,
                            opcoesEditar[0]);

                    switch (opcaoEditar) {
                        case 0:
                            int novoId = getIntegerInput("Novo ID do serviço: ");
                            if (novoId != -1) {
                                reciboEditar.setIdServico(novoId);
                            }
                            break;

                        case 1:
                            float novoValor = getFloatInput("Digite o novo valor: ");
                            if (novoValor != -1) {
                                reciboEditar.setValor(novoValor);
                            }
                            break;

                        case 2:
                            Date novaData = getDateInput(
                                    "Digite a nova data de pagamento. Use o formato (dd/mm/aaaa): ", sdf);
                            if (novaData != null) {
                                reciboEditar.setDataDePagamento(novaData);
                            }
                            break;

                        case 3:
                            JOptionPane.showMessageDialog(null, "Edição concluída.");
                            edicaoConcluida = true;
                            break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Recibo não existe.");
            }
        }
    }

    public static void listarRecibo(ArrayList<Recibo> listaRecibos) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (listaRecibos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum recibo cadastrado.");
        } else {
            for (Recibo recibo : listaRecibos) {

                JOptionPane.showMessageDialog(null, "ID: " + recibo.getIdRecibo() +
                        "\nID do Servico: " + recibo.getIdServico() +
                        "\nData de Pagamento: " + sdf.format(recibo.getDataDePagamento()) +
                        "\nData de Emissão: " + sdf.format(recibo.getDataDeEmissao()) +
                        "\nValor: R$" + recibo.getValor());
            }
        }
    }

}