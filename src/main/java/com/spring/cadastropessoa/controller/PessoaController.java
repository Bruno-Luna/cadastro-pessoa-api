package com.spring.cadastropessoa.controller;

import com.spring.cadastropessoa.dao.ContatoDao;
import com.spring.cadastropessoa.dao.PessoaDao;
import com.spring.cadastropessoa.model.ContatoModel;
import com.spring.cadastropessoa.model.PessoaModel;
import com.spring.cadastropessoa.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    @Autowired
    private PessoaDao pessoaDao;

    @Autowired
    private ContatoDao contatoDao;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping()
    public List<PessoaModel> listarTodas(){
      return pessoaDao.listarTodasPessoas();
    }

    @GetMapping("/{id}")
    public Object buscarPessoaId(@PathVariable(value = "id") Long id){
        return pessoaDao.buscarPessoaPorId(id);
    }

    @PostMapping()
    public void cadastrarPessoa(@Valid @RequestBody PessoaModel pessoa) {
        Boolean cpfValido = pessoaService.validarCpf(pessoa.getCpf());
        Boolean dataValida = pessoaService.verificarData(pessoa.getDatanascimento());

        if(!cpfValido){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido.");
        } else if(!dataValida){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data digitada não pode ser maior que a data atual.");
        }

        pessoaDao.inserirPessoa(pessoa);
    }

    @PutMapping()
    public void atualizarPessoa(@Valid @RequestBody PessoaModel pessoa) {
        Boolean cpfValido = pessoaService.validarCpf(pessoa.getCpf());
        Boolean dataValida = pessoaService.verificarData(pessoa.getDatanascimento());

        if(!cpfValido){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido.");
        } else if(!dataValida){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data informada não pode ser maior que a data atual.");
        }
        pessoaDao.atualizarPessoa(pessoa);

    }
    @DeleteMapping("/{id}")
    public void deletarPessoa(@PathVariable(value = "id") Long id){

         List<ContatoModel>  listaContatoPessoa = contatoDao.listarContatosPorIdPessoa(id);

         if(listaContatoPessoa.isEmpty()){
            pessoaDao.excluirPessoa(id);
         } else {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Registro possui contatos vinculados.");
         }
    }
}
