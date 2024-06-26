package org.example.hotel;

import org.example.hotel.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

//A aplicação sempre vai rodar na classe com Application com o @SpringBootApplication
@SpringBootApplication
public class HotelApplication implements CommandLineRunner {
// instanciando as classes de serviço que tem as nossas logicas de negocio
    private final CrudFuncionarioService FUNCIONARIO_SERVICE;
    private final CrudHospedeService HOSPEDE_SERVICE;
    private final CrudQuartoService QUARTO_SERVICE;
    private final CrudReservaService RESERVA_SERVICE;
    private final CrudTipoQuartoService TIPO_QUARTO_SERVICE;

    // aplicando injeção de dependencia pra não precisarmos dar um 'new' nas nossas classes de serviço
    public HotelApplication(CrudFuncionarioService FUNCIONARIO_SERVICE, CrudHospedeService HOSPEDE_SERVICE,
                            CrudQuartoService QUARTO_SERVICE, CrudReservaService RESERVA_SERVICE, CrudTipoQuartoService TIPO_QUARTO_SERVICE) {
        this.FUNCIONARIO_SERVICE = FUNCIONARIO_SERVICE;
        this.HOSPEDE_SERVICE = HOSPEDE_SERVICE;
        this.QUARTO_SERVICE = QUARTO_SERVICE;
        this.RESERVA_SERVICE = RESERVA_SERVICE;
        this.TIPO_QUARTO_SERVICE = TIPO_QUARTO_SERVICE;
    }

    //metodo main que vai iniciar a aplicação
    public static void main(String[] args) {

        SpringApplication.run(HotelApplication.class, args);
    }

    //metodo para rodar nossa aplicação no terminal
    @Override
    public void run(String... args) {
        boolean isTrue = true;

        while (isTrue) {
            Scanner sc = new Scanner(System.in);
            System.out.println("O que deseja fazer?");
            System.out.println("1 - para interagir com Funcionario");
            System.out.println("2 - para interagir com Hospede");
            System.out.println("3 - para interagir com Quarto");
            System.out.println("4 - para interagir com a Reserva");
            System.out.println("5 - para interagir com o Tipo de Quarto");
            System.out.println("0 - para Sair");
            int opcao = sc.nextInt();
            //Chamando os metodos das classes services
            switch (opcao) {
                case 1:
                    FUNCIONARIO_SERVICE.menu();
                    break;
                case 2:
                    HOSPEDE_SERVICE.menu();
                    break;
                case 3:
                    QUARTO_SERVICE.menu();
                    break;
                case 4:
                    RESERVA_SERVICE.menu();
                    break;
                case 5:
                    TIPO_QUARTO_SERVICE.menu();
                    break;

                default:
                    isTrue = false;
            }
        }

    }
}
