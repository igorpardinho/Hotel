package org.example.hotel.service;

import org.example.hotel.orm.Funcionario;
import org.example.hotel.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void menu() {
        boolean isTrue = true;
        while (isTrue) {
            Scanner sc = new Scanner(System.in);
            System.out.println("--- ENTIDADE FUNCIONARIO ---");
            System.out.println("1 - Cadastrar funcionario");
            System.out.println("2 - Listar funcionarios");
            System.out.println("3 - Deletar um funcionario");
            System.out.println("4 - Atualizar um funcionario");
            System.out.println("0 - Para voltar ao menu");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    listarFuncionarios();
                    break;
                case 3:
                    deletarFuncionario();
                    break;
                case 4:
                    atualizarFuncionario();
                    break;
                default:
                    isTrue = false;
                    break;

            }
        }

    }


    public void cadastrarFuncionario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do funcionario: ");
        String nome = sc.nextLine();
        System.out.println("Digite o cpf do funcionario: ");
        String cpf = sc.nextLine();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionarioRepository.save(funcionario);
    }


    public void listarFuncionarios() {
        Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
        if (funcionarios.iterator().hasNext()) {
            funcionarios.forEach(System.out::println);
        } else {
            System.out.println("Nenhum funcionario encontrado");
        }
    }

    public void deletarFuncionario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do funcionario que deseja excluir:");
        Long id = sc.nextLong();
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);

        if (optionalFuncionario.isPresent()) {
            Funcionario funcionario = optionalFuncionario.get();
            funcionarioRepository.delete(funcionario);
            System.out.println("Funcionario deletado com sucesso");
        } else {
            System.out.println("Nenhum funcionario encontrado");
        }


    }

    public void atualizarFuncionario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do funcionario que deseja atualizar: ");
        Long id = sc.nextLong();
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {
            sc = new Scanner(System.in);
            Funcionario funcionario = optionalFuncionario.get();
            System.out.println("Digite o novo nome do funcionario");
            String nome = sc.nextLine();
            funcionario.setNome(nome);
            System.out.println("Digite o novo cpf do funcionario");
            String cpf = sc.nextLine();
            funcionario.setCpf(cpf);
            funcionarioRepository.save(funcionario);

        }else {
            System.out.println("Nenhum funcionario encontrado");
        }
    }
}
