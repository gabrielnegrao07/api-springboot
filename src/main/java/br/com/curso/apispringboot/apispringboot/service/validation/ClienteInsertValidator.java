package br.com.curso.apispringboot.apispringboot.service.validation;

import br.com.curso.apispringboot.apispringboot.controller.exception.FieldMessage;
import br.com.curso.apispringboot.apispringboot.domain.enums.TipoCliente;
import br.com.curso.apispringboot.apispringboot.dto.ClienteNewDTO;
import br.com.curso.apispringboot.apispringboot.service.validation.utils.BR;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

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


//        Nesse for eu adiciono a minha lista de erros personalizada a lista de erros do framework.
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}