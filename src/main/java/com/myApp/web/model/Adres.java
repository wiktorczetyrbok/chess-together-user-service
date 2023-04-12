package com.myApp.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adres {
    private int AdresID;
    private String Miasto;
    private String KodPocztowy;
    private String Ulica;
    private String NumberBudynku;
    private String DodatkoweInformacje;
}
