package org.example.hotel.service;


import jakarta.transaction.Transactional;
import org.example.hotel.orm.Quarto;
import org.example.hotel.orm.TipoQuarto;
import org.example.hotel.repository.QuartoRepository;
import org.example.hotel.repository.TipoQuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Scanner;

//na classe Service podemos fazer a logica de negocio usando o repository que extendemos da interface para aplicar os metodos e aplicar no banco de dados
@Service
@Transactional
public class CrudTipoQuartoService {

    @Autowired
    private TipoQuartoRepository tipoQuartoRepository;



    public void menu() {
        boolean isTrue = true;
        while (isTrue) {
            Scanner sc = new Scanner(System.in);
            System.out.println("--- ENTIDADE TIPO DE QUARTO ---");
            System.out.println("1 - Cadastrar tipo de quarto");
            System.out.println("2 - Listar tipos de quarto");
            System.out.println("3 - Deletar um tipo de quarto");
            System.out.println("4 - Atualizar um tipo de quarto");
            System.out.println("0 - Para voltar ao menu");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarTipoQuarto();
                    break;
                case 2:
                    listarTipoQuarto();
                    break;
                case 3:
                    deletarTipoQuarto();
                    break;
                case 4:
                    atualizarTipoQuarto();
                    break;
                default:
                    isTrue = false;
                    break;

            }
        }

    }


    public void cadastrarTipoQuarto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do tipo de quarto: ");
        String nome = sc.nextLine();
        TipoQuarto tipoQuarto = new TipoQuarto(nome);
        tipoQuartoRepository.save(tipoQuarto);
        System.out.println("Tipo de quarto cadastrado com sucesso!");


    }


    public void listarTipoQuarto() {
        Iterable<TipoQuarto> tipoQuartos = tipoQuartoRepository.findAll();
        if (tipoQuartos.iterator().hasNext()) {
            tipoQuartos.forEach(System.out::println);
        } else {
            System.out.println("Nenhum tipo de quarto encontrado!");
        }
    }

    public void deletarTipoQuarto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do tipo de quarto que deseja excluir:");
        Long id = sc.nextLong();
        Optional<TipoQuarto> optionalTipoQuarto = tipoQuartoRepository.findById(id);

        if (optionalTipoQuarto.isPresent()) {
            TipoQuarto tipoQuarto = optionalTipoQuarto.get();
            tipoQuartoRepository.delete(tipoQuarto);
            System.out.println("Tipo de quarto deletado com sucesso");
        } else {
            System.out.println("Nenhum tipo de quarto encontrado");
        }


    }

    public void atualizarTipoQuarto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do tipo de quarto que deseja atualizar: ");
        Long id = sc.nextLong();
        Optional<TipoQuarto> optionalTipoQuarto = tipoQuartoRepository.findById(id);
        if (optionalTipoQuarto.isPresent()) {
            sc = new Scanner(System.in);
            TipoQuarto tipoQuarto = optionalTipoQuarto.get();
            System.out.println("Digite o novo nome do tipo de quarto");
            String nome = sc.nextLine();
            tipoQuarto.setNome(nome);

            tipoQuartoRepository.save(tipoQuarto);
            System.out.println("Tipo de quarto atualizado com sucesso");

        } else {
            System.out.println("Nenhum tipo de quarto encontrado");
        }
    }
}
