package org.example.hotel.service;

import org.example.hotel.orm.Funcionario;
import org.example.hotel.orm.Hospede;
import org.example.hotel.orm.Reserva;
import org.example.hotel.repository.HospedeRepository;
import org.example.hotel.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudReservaService {

    private ReservaRepository reservaRepository;
    private HospedeRepository hospedeRepository;

    public CrudReservaService(ReservaRepository reservaRepository, HospedeRepository hospedeRepository) {
        this.reservaRepository = reservaRepository;
        this.hospedeRepository = hospedeRepository;
    }

    public void menu() {
        boolean isTrue = true;
        while (isTrue) {
            Scanner sc = new Scanner(System.in);
            System.out.println("--- ENTIDADE RESERVA ---");
            System.out.println("1 - Cadastrar reserva");
            System.out.println("2 - Listar reservas");
            System.out.println("3 - Deletar uma reserva");
            System.out.println("4 - Atualizar uma reserva");
            System.out.println("0 - Para voltar ao menu");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarReserva();
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    deletarReserva();
                    break;
                case 4:
                    atualizarReserva();
                    break;
                default:
                    isTrue = false;
                    break;

            }
        }

    }


    public void cadastrarReserva() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o numero da reserva: ");
        Integer numero = sc.nextInt();
        System.out.println("Digite o id do hospede que vai fazer a reserva:");
        Long idHospede = sc.nextLong();
        Optional<Hospede> optionalHospede = hospedeRepository.findById(idHospede);
        if (optionalHospede.isPresent()) {
            Reserva reserva = new Reserva();
            reserva.setNumero(numero);
            Hospede hospede = optionalHospede.get();
            reserva.setHospede(hospede);
            reservaRepository.save(reserva);
            hospedeRepository.save(hospede);
            System.out.println("Reserva cadastrada com sucesso!");
        } else {
            System.out.println("Nenhum hospede encontrado");
        }


    }


    public void listarReservas() {
        Iterable<Reserva> reservas = reservaRepository.findAll();
        if (reservas.iterator().hasNext()) {
            reservas.forEach(System.out::println);
        } else {
            System.out.println("Nenhuma reserva encontrada");
        }
    }

    public void deletarReserva() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id da reserva que deseja excluir:");
        Long id = sc.nextLong();
        Optional<Reserva> optionalReserva = reservaRepository.findById(id);

        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            reservaRepository.delete(reserva);
            System.out.println("Reserva deletada com sucesso");
        } else {
            System.out.println("Nenhuma reserva encontrada");
        }


    }

    public void atualizarReserva() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id da reserva que deseja atualizar: ");
        Long id = sc.nextLong();
        Optional<Reserva> optionalReserva = reservaRepository.findById(id);
        if (optionalReserva.isPresent()) {
            sc = new Scanner(System.in);
            Reserva reserva = optionalReserva.get();
            System.out.println("Digite o novo numero da reserva");
            int numero = sc.nextInt();
            reserva.setNumero(numero);

            reservaRepository.save(reserva);
            System.out.println("Reserva atualizada com sucesso");

        } else {
            System.out.println("Nenhuma reserva encontrada");
        }
    }


}
