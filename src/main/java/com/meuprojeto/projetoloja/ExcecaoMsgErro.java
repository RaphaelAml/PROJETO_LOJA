package com.meuprojeto.projetoloja;

public class ExcecaoMsgErro extends Exception{

    private static final long serialVersionUID = 1l;

    public ExcecaoMsgErro(String msgErro){
        super(msgErro);
    }
}
