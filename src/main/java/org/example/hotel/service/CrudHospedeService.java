package org.example.hotel.service;

import jakarta.transaction.Transactional;
import org.example.hotel.orm.Hospede;
import org.example.hotel.orm.Quarto;
import org.example.hotel.orm.Reserva;
import org.example.hotel.repository.HospedeRepository;
import org.example.hotel.repository.QuartoRepository;
import org.example.hotel.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

@Service
@Transactional
public class CrudHospedeService {


    private final QuartoRepository quartoRepository;
    private HospedeRepository hospedeRepository;
    private ReservaRepository reservaRepository;

    public CrudHospedeService(HospedeRepository hospedeRepository, ReservaRepository reservaRepository, QuartoRepository quartoRepository) {
        this.hospedeRepository = hospedeRepository;
        this.reservaRepository = reservaRepository;
        this.quartoRepository = quartoRepository;
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

        fazerReserva();
        System.out.println("Digite o id da reserva do hospede:");
        Long idReserva = sc.nextLong();

        Optional<Reserva> reserva = reservaRepository.findById(idReserva);
        if (reserva.isEmpty()) {
            System.out.println("verifique o id da reserva");
        }
        Set<Quarto> quartos = new HashSet<>();
        Hospede hospede = new Hospede(nome, cpf, telefone,quartos, reserva.get());
        hospedeRepository.save(hospede);
        System.out.println("Hospede cadastrado com sucesso!");

        System.out.println("Digite o id do quarto que deseja reservar");
        Long id = sc.nextLong();
        Optional<Quarto> quarto = quartoRepository.findById(id);

        if (quarto.isPresent()) {
            quartos.add(quarto.get());
            hospede.setQuartos(quartos);
            System.out.println("Quarto reservado com sucesso");
        }else {
            System.out.println("Id do quarto não encontrado");
        }

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

    public void fazerReserva() {
        Reserva reserva = new Reserva();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número da reserva: ");
        int numero = sc.nextInt();
        reserva.setNumero(numero);
        reservaRepository.save(reserva);
        System.out.println("Reserva criada com sucesso - ID = "+ reserva.getId());
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
