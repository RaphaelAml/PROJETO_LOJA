package com.meuprojeto.dto;

import java.io.Serializable;

public class AtividadeDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private String text;


    private String code;


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }



}
