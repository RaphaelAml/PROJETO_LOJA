package com.meuprojeto.controller;

import com.meuprojeto.model.CupDesc;
import com.meuprojeto.repository.CupDescontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CupDescontoController {

    @Autowired
    private CupDescontoRepository cupDescontoRepository;

    @ResponseBody
    @GetMapping(value = "**/listaCupomDesc/{idEmpresa}")
    public ResponseEntity<List<CupDesc>> listaCupomDesc(@PathVariable("idEmpresa") Long idEmpresa){

        return new ResponseEntity<List<CupDesc>>(cupDescontoRepository.cupDescontoPorEmpresa(idEmpresa), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/listaCupomDesc")
    public ResponseEntity<List<CupDesc>> listaCupomDesc(){

        return new ResponseEntity<List<CupDesc>>(cupDescontoRepository.findAll() , HttpStatus.OK);
    }


}
