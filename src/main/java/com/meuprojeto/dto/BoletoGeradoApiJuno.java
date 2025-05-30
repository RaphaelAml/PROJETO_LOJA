package com.meuprojeto.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BoletoGeradoApiJuno implements Serializable {

    private static final long serialVersionUID = 1L;

    private Embedded _embedded = new Embedded();

    private List<Links> _links = new ArrayList<Links>();

    public Embedded get_embedded() {
        return _embedded;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }

    public void set_links(List<Links> links) {
        this._links = links;
    }

    public List<Links> get_links() {
        return _links;
    }

}
