package org.example;

import java.sql.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.TransactionSettings;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws Exception {

        SessionFactory sf = new Configuration()
                .addAnnotatedClass(Quarto.class)
                .addAnnotatedClass(Hospede.class)
                .addAnnotatedClass(Reserva.class)
                .configure()
                .buildSessionFactory();

        Session se = sf.openSession();

        List<Quarto> quartoList;
        quartoList=se.createQuery("from Quarto",Quarto.class).list();

        if(quartoList.isEmpty()){
            Quarto q1 = new Quarto();
            q1.setId(1);
            q1.setNumero(125);
            q1.setValor(100);

            Quarto q2 = new Quarto();
            q2.setId(2);
            q2.setNumero(126);
            q2.setValor(100);


            Transaction transaction = se.beginTransaction();
            se.persist(q1);
            se.persist(q2);
            transaction.commit();



        }

        se.close();

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Fazer uma reserva");
            System.out.println("2 - Ver todos os quartos disponíveis");
            System.out.println("3 - Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    Session se1 = sf.openSession();
                    System.out.println("Digite seu cpf");
                    int cpf ;
                    cpf = scanner.nextInt();

                    Hospede h =se1.createQuery("From Hospede where cpf = :cpf", Hospede.class)
                            .setParameter("cpf",cpf)
                            .uniqueResult();

                    if(h ==null) {

                        Hospede hospede = new Hospede();
                        hospede.setCpf(cpf);

                        System.out.println("Digite seu nome");
                        String nome;
                        nome = scanner.next();
                        hospede.setNome(nome);

                        System.out.println("Digite seu telefone");
                        int telefone;
                        telefone = scanner.nextInt();
                        hospede.setTelefone(telefone);

                        Transaction transaction = se1.beginTransaction();
                        se1.persist(hospede);
                        transaction.commit();

                        h = hospede;
                    }

                    System.out.println("Digite o numero do quarto que deseja");
                    int quarto ;
                    quarto = scanner.nextInt();

                    Quarto q=se1.createQuery("From Quarto where numero = :quarto", Quarto.class)
                            .setParameter("quarto",quarto)
                            .uniqueResult();


                    if (q != null) {
                        Session se2 = sf.openSession();
                        System.out.println("Quarto encontrado. Realizando a reserva...");

                        Reserva reserva = new Reserva();
                        int dias;
                        System.out.println("Reservas para quantos dias?");
                        dias = scanner.nextInt();
                        reserva.setDias(dias);
                        int valor_final = dias * q.getValor();
                        reserva.setValor_final(valor_final);
                        q.setReservas(Arrays.asList(reserva));
                        h.setReservas(Arrays.asList(reserva));
                        reserva.setHospede(h);
                        reserva.setQuarto(q);


                        Transaction transaction = se2.beginTransaction();
                        se2.persist(reserva);
                        transaction.commit();

                        System.out.println("reserva realizado com sucesso");
                        se2.close();

                    } else {
                        System.out.println("Quarto não encontrado.");
                    }
                    break;
                case 2:
                    Session se2 = sf.openSession();
                    List<Quarto> quartoLists;
                    quartoLists=se2.createQuery("from Quarto",Quarto.class).list();

                    for(Quarto quartos:quartoLists){
                        System.out.println("Numero: "+quartos.getNumero() +" "+ "Valor diaria: " + quartos.getValor());
                    }
                    se2.close();

                    break;
                case 3:

                    System.out.println("Saindo...");
                    sf.close();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }







    }
}