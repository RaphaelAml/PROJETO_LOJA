package com.meuprojeto.controller;

import com.meuprojeto.model.CupDesc;
import com.meuprojeto.projetoloja.ExcecaoMsgErro;
import com.meuprojeto.repository.CupDescontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class CupDescontoController {

    @Autowired
    private CupDescontoRepository cupDescontoRepository;

    @ResponseBody
    @PostMapping(value = "**/salvarCupDesc")
    public ResponseEntity<CupDesc> salvarCupDesc(@RequestBody @Valid CupDesc cupDesc) throws ExcecaoMsgErro {

        CupDesc cupDesc2 = cupDescontoRepository.save(cupDesc);

        return new ResponseEntity<>(cupDesc, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("**/deleteCupomPorId/{id}")
    public ResponseEntity<?> deleteCupomPorId(@PathVariable("id") Long id)  {

        cupDescontoRepository.deleteById(id);
        return new ResponseEntity("Cupom Produto Removido", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/obterCupom/{id}")
    public ResponseEntity<CupDesc> obterCupom(@PathVariable("id") Long id) throws ExcecaoMsgErro {

        CupDesc cupDesc = cupDescontoRepository.findById(id).orElse(null);

        if(cupDesc == null){
            throw new ExcecaoMsgErro("Não encontrou Cupom do produto com codigo: " + id);
        }

        return new ResponseEntity<CupDesc>(cupDesc, HttpStatus.OK);
    }



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
