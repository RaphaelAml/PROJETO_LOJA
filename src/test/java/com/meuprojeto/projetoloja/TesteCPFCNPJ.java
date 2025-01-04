package com.meuprojeto.projetoloja;

import com.meuprojeto.util.ValidaCNPJ;
import com.meuprojeto.util.ValidaCPF;

public class TesteCPFCNPJ {

    public static void main(String[] args) {

        boolean isCnpj = ValidaCNPJ.isCNPJ("78.747.562/0001-99");

        System.out.println("Cnpj válido : " + isCnpj);

        boolean isCpf = ValidaCPF.isCPF("850.701.390-00");

        System.out.println("Cpf válido : " + isCpf);
    }
}
