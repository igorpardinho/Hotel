package org.example.hotel.service;

import org.example.hotel.orm.Hospede;
import org.example.hotel.repository.HospedeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudHospedeService {


    private HospedeRepository hospedeRepository;

    public CrudHospedeService(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public void menu() {
        boolean isTrue = true;
        while (isTrue) {
            Scanner sc = new Scanner(System.in);
            System.out.println("--- ENTIDADE HOSPEDE ---");
            System.out.println("1 - Cadastrar hospede");
            System.out.println("2 - Listar hospede");
            System.out.println("3 - Deletar um hospede");
            System.out.println("4 - Atualizar um hospede");
            System.out.println("0 - Para voltar ao menu");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarHospede();
                    break;
                case 2:
                    System.out.println("Digite a senha do gerente: ");
                    Scanner scanner = new Scanner(System.in);
                    String password = scanner.next();

                    if (password.equals("admin")) {
                        listarHospede();
                    } else {
                        System.out.println("Senha incorreta!");
                    }

                    break;
                case 3:
                    System.out.println("Digite a senha do gerente: ");
                    scanner = new Scanner(System.in);
                    password = scanner.next();
                    if (password.equals("admin")) {
                        deletarHospede();
                    } else {
                        System.out.println("Senha incorreta!");
                    }

                    break;
                case 4:
                    System.out.println("Digite a senha do gerente: ");
                    scanner = new Scanner(System.in);
                    password = scanner.next();
                    if (password.equals("admin")) {
                        atualizarHospede();
                    } else {
                        System.out.println("Senha incorreta!");
                    }

                    break;
                default:
                    isTrue = false;
                    break;

            }
        }

    }


    public void cadastrarHospede() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do hospede: ");
        String nome = sc.nextLine();
        System.out.println("Digite o cpf do hospede: ");
        String cpf = sc.nextLine();
        System.out.println("Digite o telefone do hospede: ");
        String telefone = sc.nextLine();


        Hospede hospede = new Hospede();
        hospede.setNome(nome);
        hospede.setCpf(cpf);
        hospede.setTelefone(telefone);

        hospedeRepository.save(hospede);
        System.out.println("Hospede cadastrado com sucesso!");
    }


    public void listarHospede() {
        Iterable<Hospede> hospedes = hospedeRepository.findAll();
        if (hospedes.iterator().hasNext()) {
            hospedes.forEach(System.out::println);
        } else {
            System.out.println("Nenhum hospede encontrado");
        }
    }

    public void deletarHospede() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do hospede que deseja excluir:");
        Long id = sc.nextLong();
        Optional<Hospede> optionalHospede = hospedeRepository.findById(id);

        if (optionalHospede.isPresent()) {
            Hospede hospede = optionalHospede.get();
            hospedeRepository.delete(hospede);
            System.out.println("Hospede deletado com sucesso");
        } else {
            System.out.println("Nenhum hospede encontrado");
        }


    }

    public void atualizarHospede() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do hospede que deseja atualizar: ");
        Long id = sc.nextLong();
        Optional<Hospede> optionalHospede = hospedeRepository.findById(id);
        if (optionalHospede.isPresent()) {
            sc = new Scanner(System.in);
            Hospede hospede = optionalHospede.get();
            System.out.println("Digite o novo nome do hospede:");
            String nome = sc.nextLine();
            hospede.setNome(nome);
            System.out.println("Digite o novo cpf do hospede:");
            String cpf = sc.nextLine();
            hospede.setCpf(cpf);
            System.out.println("Digite o novo telefone do hospede:");
            String telefone = sc.nextLine();
            hospede.setTelefone(telefone);

            hospedeRepository.save(hospede);
            System.out.println("Hospede atualizado com sucesso");

        } else {
            System.out.println("Nenhum hospede encontrado");
        }
    }
}
