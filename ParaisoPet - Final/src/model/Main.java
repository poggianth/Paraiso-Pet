package model;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArvoreAnimal arvAnimal = new ArvoreAnimal();
		ArvoreServico arvServicos = new ArvoreServico();
		int opcao;

		System.out.println("Alunos: Thiago Melo, Fábio Gonçalves e Marcelo Almeida.\n");

		do {
			System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-= PARAÍSO PET =-=-=-=-=-=-=-=-=-=-=-=\n");
			System.out.println("[1] - Cadastrar animal (check-in)");
			System.out.println("[2] - Alterar dados de um animal");
			System.out.println("[3] - Excluir dados");
			System.out.println("[4] - Relatório de todos animais cadastrados");
			System.out.println("[5] - Relatório de todos serviços prestado por um determinado animal");
			System.out.println("[6] - Inserir serviço realizado em um animal");
			System.out.println("[7] - Check-out de animal");
			System.out.println("[0] - SAIR");

			System.out.print("\nInforme qual opção deseja: ");
			opcao = input.nextInt();

			switch (opcao) {
			case 1: // Check-in
				System.out.println("\n---------------- CHECK-IN ----------------");
				ItemAnimal animal = arvAnimal.cadastrarDados();

				if (animal != null) {
					try {
						arvAnimal.inserir(animal);
						System.out.println("\nCheck-in realizado com sucesso!");

						System.out.println("O código do animal é: " + animal.getCodigo());
					} catch (Exception e) {
						System.err.println("[OPS] - Erro ao realizar check-in: " + e);
					}
				}

//				Fim da instrução fazer check-in
				break;

			case 2: // Alterar dados
				System.out.println("\n---------------- ALTERAR ----------------");
				System.out.print("Informe o código do animal que deseja alterar: ");
				int codAltAnimal = input.nextInt();

				if (arvAnimal.pesquisar(codAltAnimal)) {
					System.out.println("\nAnimal encontrado!");
					// Alterar dados
					ItemAnimal animalOld = arvAnimal.retornaAnimal(codAltAnimal);

					if (animalOld != null) {
						ItemAnimal animalNovo = arvAnimal.cadastrarDados();
						if (arvAnimal.alterar(codAltAnimal, animalNovo)) {
							System.out.println("Animal alterado com SUCESSO!");
						} else {
							System.err.println("[OPS] - Erro ao alterar animal!");
						}
					}
				} else {
					System.err.println("\n[OPS] - Nenhum animal encontrado com o Código fornecido, tente novamente!\n");
				}

				break;

			case 3: // Excluir
				System.out.println("\n---------------- EXCLUIR ----------------");
				System.out.print("Informe o código do animal que deseja excluir: ");
				int opcaoExcluir = input.nextInt();

				if (arvAnimal.pesquisar(opcaoExcluir)) {

					try {
						arvAnimal.remover(opcaoExcluir);
						System.out.println("Animal excluído com sucesso!");
					} catch (Exception e) {
						System.err.println("[OPS] - Erro ao excluir animal: " + e);
					}
				} else {
					System.err.println("\n[OPS] - Nenhum animal encontrado com o código infomado!\n");
				}
				break;

			case 4: // Todos animais
				System.out.println("\n---------------- ANIMAIS CADASTRADOS ----------------");
				System.out.println("Esses são os animais cadastrados: ");
				System.out.println("\n" + arvAnimal.toString());

				break;
			case 5:
				System.out.println("\n---------------- SERVIÇOS POR ANIMAL ----------------");
				System.out.print("Informe o código do ANIMAL: ");
				int idAnimalTodoServicos = input.nextInt();

				if (arvAnimal.pesquisar(idAnimalTodoServicos)) {
					ItemServico[] servicosPorAnimal = arvServicos.servicoPorAnimal(idAnimalTodoServicos);

					for (ItemServico servico : servicosPorAnimal) {
						System.out.println(servico.toString());
					}
				} else {
					System.err.println("\n[OPS] - Código de animal NÃO ENCONTRADO, tente novamente!\n");
				}

				break;
			case 6:
				System.out.println("\n---------------- INSRIR SERVIÇO POR ANIMAL ----------------");
				System.out.print("Informe o código do animal para inserir um serviço: ");
				int idAnmAddServ = input.nextInt();

				if (arvAnimal.pesquisar(idAnmAddServ)) {
					System.out.println("\nAnimal encontrado!");

					try {
						ItemServico servico = new ItemServico();
						// Relaciona com o Animal:
						servico.setIdAnimal(idAnmAddServ);

						System.out.print("Informe a DESCRIÇÃO do serviço realizado: ");
						servico.setDescricao(input.next());

						System.out.print("Informe o PREÇO TOTAL do serviço: ");
						servico.setPreco(input.nextDouble());

						arvServicos.inserir(servico);
						System.out.println("Serviço criado com sucesso!");
					} catch (Exception e) {
						System.err.println("[OPS] - Erro ao criar serviço: " + e);
					}
				} else {
					System.err.println("\n[OPS] - Código de animal não encontrado, tente novamente!\n");
				}
				break;

			case 7: // Check-out
				System.out.println("\n---------------- CHECK-OUT ----------------");
				System.out.print("Informe o código do animal que deseja realizar CHECK-OUT: ");
				int codCheckout = input.nextInt();

				if (arvAnimal.pesquisar(codCheckout)) {
					// Listar os serviços realizados pelo codAnimal -> alterar a dtSaida -> mostrar
					// total
					ItemAnimal anmOut = arvAnimal.retornaAnimal(codCheckout);
					if (anmOut != null && (anmOut.getDtSaida() == null || anmOut.getDtSaida().equals(""))) {
						System.out.print("Informe a DATA do CHECK-OUT: ");
						anmOut.setDtSaida(input.next());

						ItemServico[] servPorAnm = arvServicos.servicoPorAnimal(codCheckout);
						if (servPorAnm[0] != null) {
							double total = 0;
							for (int c = 0; c < servPorAnm.length; c++) {
								total += servPorAnm[c].getPreco();
							}

							System.out.println("\nO valor total durante a estadia do animal de código " + codCheckout
									+ " foi de R$" + total);

							System.out.println("O responsável " + anmOut.getResponsavel().getNome()
									+ " será informado(a) através do e-mail: " + anmOut.getResponsavel().getEmail());
						} else {
							System.err.println(
									"\n[OPS] - Nenhum serviço foi cadastrado para o animal de código " + codCheckout);
						}
					} else {
						System.err.println("\n[OPS] - O animal informado já realizou CHECK-OUT!\n");
					}

				} else {
					System.err.println("\n[OPS] - Código de animal não encontrado, tente novamente!\n");
				}
				break;
			case 0: // Sair
				System.out.println("\nSaindo da aplicação. Obrigado por utilizar!");
				break;

			default:
				System.err.println("\n[ERRO] Opção INVÁLIDA! Tente novamente.");
				break;
			}

		} while (opcao != 0);

		input.close();
	}

}
