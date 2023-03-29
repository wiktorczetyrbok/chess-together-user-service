package com.myApp.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adres {
    private int AdresID;
    private String Miasto;

    private String KodPocztowy;

    @Override
    public String toString() {
        return "Adres{" +
                "AdresID=" + AdresID +
                ", Miasto='" + Miasto + '\'' +
                ", KodPocztowy='" + KodPocztowy + '\'' +
                ", Ulica='" + Ulica + '\'' +
                ", NumberBudynku='" + NumberBudynku + '\'' +
                ", DodatkoweInformacje='" + DodatkoweInformacje + '\'' +
                '}';
    }

    private String Ulica;
    private String NumberBudynku;
    private String DodatkoweInformacje;
}
