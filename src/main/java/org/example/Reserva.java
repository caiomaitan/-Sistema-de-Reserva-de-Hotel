package org.example;

import jakarta.persistence.*;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int valor_final;
    private int dias;
    @ManyToOne
    @JoinColumn(name="cpf")
    private Hospede hospede;
    @ManyToOne
    @JoinColumn(name="id_quarto")
    private Quarto quarto;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor_final() {
        return valor_final;
    }

    public void setValor_final(int valor_final) {
        this.valor_final = valor_final;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
}
