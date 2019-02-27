package br.com.curso.apispringboot.apispringboot.service.validation;

import br.com.curso.apispringboot.apispringboot.controller.exception.FieldMessage;
import br.com.curso.apispringboot.apispringboot.domain.Cliente;
import br.com.curso.apispringboot.apispringboot.domain.enums.TipoCliente;
import br.com.curso.apispringboot.apispringboot.dto.ClienteNewDTO;
import br.com.curso.apispringboot.apispringboot.repositories.ClienteRepository;
import br.com.curso.apispringboot.apispringboot.service.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO obj, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (obj.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(obj.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }
        if (obj.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(obj.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Cliente aux = clienteRepository.findByEmail(obj.getEmail());
        if (aux != null){
            list.add(new FieldMessage("email", "Email já existente"));
        }

//        Nesse for eu adiciono a minha lista de erros personalizada a lista de erros do framework.
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}