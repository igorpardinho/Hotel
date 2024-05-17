package org.example.hotel.service;


import jakarta.transaction.Transactional;
import org.example.hotel.orm.Quarto;
import org.example.hotel.orm.TipoQuarto;
import org.example.hotel.repository.QuartoRepository;
import org.example.hotel.repository.TipoQuartoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
@Transactional
public class CrudQuartoService {

    private QuartoRepository quartoRepository;
    private TipoQuartoRepository tipoQuartoRepository;


    public CrudQuartoService(QuartoRepository quartoRepository, TipoQuartoRepository tipoQuartoRepository) {
        this.quartoRepository = quartoRepository;
        this.tipoQuartoRepository = tipoQuartoRepository;

    }

    public void menu() {
        boolean isTrue = true;
        while (isTrue) {
            Scanner sc = new Scanner(System.in);
            System.out.println("--- ENTIDADE QUARTO ---");
            System.out.println("1 - Cadastrar quarto");
            System.out.println("2 - Listar quarto");
            System.out.println("3 - Deletar um quarto");
            System.out.println("4 - Atualizar um quarto");
            System.out.println("0 - Para voltar ao menu");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarQuarto();
                    break;
                case 2:
                    listarQuarto();
                    break;
                case 3:
                    deletarQuarto();
                    break;
                case 4:
                    atualizarQuarto();
                    break;
                default:
                    isTrue = false;
                    break;

            }
        }

    }


    public void cadastrarQuarto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o numero do quarto: ");
        Integer numero = sc.nextInt();
        System.out.println("Digite o nome do quarto: ");
        String nome = sc.next();

        Quarto quarto =  new Quarto(nome,numero);
        quartoRepository.save(quarto);
        System.out.println("Quarto Cadastrado com sucesso!");

    }


    public void listarQuarto() {
        Iterable<Quarto> quartos = quartoRepository.findAll();
        if (quartos.iterator().hasNext()) {
            quartos.forEach(System.out::println);
        } else {
            System.out.println("Nenhum quarto encontrado");
        }
    }

    public void deletarQuarto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do quarto que deseja excluir:");
        Long id = sc.nextLong();
        Optional<Quarto> optionalQuarto = quartoRepository.findById(id);

        if (optionalQuarto.isPresent()) {

            Quarto quarto = optionalQuarto.get();

            quartoRepository.delete(quarto);
            System.out.println("Quarto deletado com sucesso");
        } else {
            System.out.println("Nenhum quarto encontrado");
        }


    }

    public void atualizarQuarto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do quarto que deseja atualizar: ");
        Long id = sc.nextLong();
        Optional<Quarto> optionalQuarto = quartoRepository.findById(id);
        if (optionalQuarto.isPresent()) {
            sc = new Scanner(System.in);
            Quarto quarto = optionalQuarto.get();
            System.out.println("Digite o novo nome do Quarto:");
            String nome = sc.nextLine();
            quarto.setNome(nome);
            System.out.println("Digite o novo numero do Quarto:");
            Integer numero = sc.nextInt();
            quarto.setNumero(numero);
            quartoRepository.save(quarto);
            System.out.println("Quarto atualizado com sucesso");

        } else {
            System.out.println("Nenhum funcionario encontrado");
        }
    }
}
