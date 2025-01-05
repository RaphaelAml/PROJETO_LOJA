package com.meuprojeto.controller;

import com.meuprojeto.dto.CepDTO;
import com.meuprojeto.model.Endereco;
import com.meuprojeto.model.PessoaFisica;
import com.meuprojeto.model.PessoaJuridica;
import com.meuprojeto.projetoloja.ExcecaoMsgErro;
import com.meuprojeto.repository.EnderecoRepository;
import com.meuprojeto.repository.PessoaFisicaRepository;
import com.meuprojeto.repository.PessoaRepository;
import com.meuprojeto.service.PessoaUserService;
import com.meuprojeto.util.ValidaCNPJ;
import com.meuprojeto.util.ValidaCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
public class PessoaController {

    @Autowired
    private PessoaRepository pesssoaRepository;

    @Autowired
    private PessoaUserService pessoaUserService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @ResponseBody
    @GetMapping(value = "**/consultaPfNome/{nome}")
    public ResponseEntity<List<PessoaFisica>> consultaPfNome(@PathVariable("nome") String nome){

        List<PessoaFisica> fisicas = pessoaFisicaRepository.pesquisaPorNomePF(nome.trim().toUpperCase());

        jdbcTemplate.execute("begin; update tabela_acesso_end_potin set qtd_acesso_end_point = qtd_acesso_end_point + 1 where nome_end_point = 'END-POINT-NOME-PESSOA-FISICA'; commit;");

        return new ResponseEntity<List<PessoaFisica>>(fisicas, HttpStatus.OK);
    }



    @ResponseBody
    @GetMapping(value = "**/consultaPfCpf/{cpf}")
    public ResponseEntity<List<PessoaFisica>> consultaPfCpf(@PathVariable("cpf") String cpf) {

        List<PessoaFisica> fisicas = pessoaFisicaRepository.pesquisaPorCpfPF(cpf);

        return new ResponseEntity<List<PessoaFisica>>(fisicas, HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping(value = "**/consultaNomePJ/{nome}")
    public ResponseEntity<List<PessoaJuridica>> consultaNomePJ(@PathVariable("nome") String nome) {

        List<PessoaJuridica> fisicas = pesssoaRepository.pesquisaPorNomePJ(nome.trim().toUpperCase());

        return new ResponseEntity<List<PessoaJuridica>>(fisicas, HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping(value = "**/consultaCnpjPJ/{cnpj}")
    public ResponseEntity<List<PessoaJuridica>> consultaCnpjPJ(@PathVariable("cnpj") String cnpj) {

        List<PessoaJuridica> fisicas = pesssoaRepository.existeCnpjCadastradoList(cnpj.trim().toUpperCase());

        return new ResponseEntity<List<PessoaJuridica>>(fisicas, HttpStatus.OK);
    }



    @ResponseBody
    @GetMapping(value = "**/consultaCep/{cep}")
    public ResponseEntity<CepDTO> consultaCep(@PathVariable("cep") String cep){

        return new ResponseEntity<CepDTO>(pessoaUserService.consultaCep(cep), HttpStatus.OK);

    }





    /*end-point é microsservicos é um API*/
    @ResponseBody
    @PostMapping(value = "**/salvarPj")
    public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody @Valid PessoaJuridica pessoaJuridica) throws ExcecaoMsgErro{

		/*if (pessoaJuridica.getNome() == null || pessoaJuridica.getNome().trim().isEmpty()) {
			throw new ExceptionMentoriaJava("Informe o campo de nome");
		}*/


        if (pessoaJuridica == null) {
            throw new ExcecaoMsgErro("Pessoa juridica nao pode ser NULL");
        }

        if (pessoaJuridica.getId() == null && pesssoaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
            throw new ExcecaoMsgErro("Já existe CNPJ cadastrado com o número: " + pessoaJuridica.getCnpj());
        }


        if (pessoaJuridica.getId() == null && pesssoaRepository.existeInsEstadualCadastrado(pessoaJuridica.getInscEstadual()) != null) {
            throw new ExcecaoMsgErro("Já existe Inscrição estadual cadastrado com o número: " + pessoaJuridica.getInscEstadual());
        }


        if (!ValidaCNPJ.isCNPJ(pessoaJuridica.getCnpj())) {
            throw new ExcecaoMsgErro("Cnpj : " + pessoaJuridica.getCnpj() + " está inválido.");
        }

        if (pessoaJuridica.getId() == null || pessoaJuridica.getId() <= 0){
            for (int p = 0; p < pessoaJuridica.getEnderecos().size(); p++){

                CepDTO cepDTO = pessoaUserService.consultaCep(pessoaJuridica.getEnderecos().get(p).getCep());
                pessoaJuridica.getEnderecos().get(p).setBairro(cepDTO.getBairro());
                pessoaJuridica.getEnderecos().get(p).setCidade(cepDTO.getLocalidade());
                pessoaJuridica.getEnderecos().get(p).setComplemento(cepDTO.getComplemento());
                pessoaJuridica.getEnderecos().get(p).setRuaLogradouro(cepDTO.getLogradouro());
                pessoaJuridica.getEnderecos().get(p).setUf(cepDTO.getUf());
            }
        } else {
            for (int p = 0; p < pessoaJuridica.getEnderecos().size(); p++){
                Endereco enderecoTemp = enderecoRepository.findById(pessoaJuridica.getEnderecos().get(p).getId()).get();

                if (!enderecoTemp.getCep().equals(pessoaJuridica.getEnderecos().get(p).getCep())){

                    CepDTO cepDTO = pessoaUserService.consultaCep(pessoaJuridica.getEnderecos().get(p).getCep());
                    pessoaJuridica.getEnderecos().get(p).setBairro(cepDTO.getBairro());
                    pessoaJuridica.getEnderecos().get(p).setCidade(cepDTO.getLocalidade());
                    pessoaJuridica.getEnderecos().get(p).setComplemento(cepDTO.getComplemento());
                    pessoaJuridica.getEnderecos().get(p).setRuaLogradouro(cepDTO.getLogradouro());
                    pessoaJuridica.getEnderecos().get(p).setUf(cepDTO.getUf());
                }
            }
        }

        pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);

        return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
    }




    /*end-point é microsservicos é um API*/
    @ResponseBody
    @PostMapping(value = "**/salvarPf")
    public ResponseEntity<PessoaFisica> salvarPf(@RequestBody PessoaFisica pessoaFisica) throws ExcecaoMsgErro{

        if (pessoaFisica == null) {
            throw new ExcecaoMsgErro("Pessoa fisica não pode ser NULL");
        }

        if (pessoaFisica.getId() == null && pesssoaRepository.existeCpfCadastrado(pessoaFisica.getCpf()) != null) {
            throw new ExcecaoMsgErro("Já existe CPF cadastrado com o número: " + pessoaFisica.getCpf());
        }


        if (!ValidaCPF.isCPF(pessoaFisica.getCpf())) {
            throw new ExcecaoMsgErro("CPF : " + pessoaFisica.getCpf() + " está inválido.");
        }

        pessoaFisica = pessoaUserService.salvarPessoaFisica(pessoaFisica);

        return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);
    }

}


