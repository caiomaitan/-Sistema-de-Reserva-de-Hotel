Sistema de Reserva de Hotel

Descrição
Este é um sistema de reserva de hotel desenvolvido utilizando Hibernate para gerenciamento de banco de dados. O sistema permite o cadastro de hóspedes, registro de reservas e o cálculo do valor total com base no número de dias de hospedagem.

Funcionalidades
*Cadastro de Hóspede: Permite o registro de novos hóspedes no sistema.
*Cadastro de Quarto: Possibilita o cadastro de quartos no hotel.
*Registro de Reserva: Realiza a reserva de quartos para um hóspede específico em datas específicas.
*Cálculo de Valor Total: Calcula o valor total de uma reserva com base no número de dias de hospedagem e no tipo de quarto.

Tecnologias Utilizadas
*Java 21
*Hibernate: Framework ORM para interação com o banco de dados.
*Banco de Dados: O banco de dados utilizado pode ser configurado no arquivo hibernate.cfg.xml.
*Estrutura do Projeto

O projeto é composto pelas seguintes entidades:

1. Quarto
Representa os quartos disponíveis no hotel. A classe contém informações sobre o número do quarto, tipo e preço por noite.

2. Hóspede
Representa os hóspedes do hotel. A classe contém informações como nome, endereço e dados de contato.

3. Reserva
Representa uma reserva feita por um hóspede. A classe contém informações sobre o hóspede, o quarto reservado e as datas de check-in e check-out.
