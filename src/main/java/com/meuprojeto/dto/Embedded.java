package com.meuprojeto.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Embedded implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ConteudoBoletoJuno> charges = new ArrayList<ConteudoBoletoJuno>();

    public List<ConteudoBoletoJuno> getCharges() {
        return charges;
    }

    public void setCharges(List<ConteudoBoletoJuno> charges) {
        this.charges = charges;
    }
}
