package com.spring.cadastropessoa.service;

import com.spring.cadastropessoa.dao.PessoaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class PessoaService {

    @Autowired
    private PessoaDao pessoaDao;

    public boolean validarCpf(String cpf) {
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        try{
            Long.parseLong(cpf);
        } catch(NumberFormatException e){
            return false;
        }

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String resultado;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

            d1 = d1 + (11 - nCount) * digitoCPF;

            d2 = d2 + (12 - nCount) * digitoCPF;
        };

        resto = (d1 % 11);

        if (resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;

        d2 += 2 * digito1;

        resto = (d2 % 11);

        if (resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;

        String numeroDigitadoVerificado = cpf.substring(cpf.length() - 2, cpf.length());

        resultado = String.valueOf(digito1) + String.valueOf(digito2);

        return numeroDigitadoVerificado.equals(resultado);
    }

    public boolean verificarData(LocalDate dataDigitada) {
        Date data = java.sql.Date.valueOf(dataDigitada);
        if (data.after(new Date())) {
            return false;
        }
        return true;
    }
}
