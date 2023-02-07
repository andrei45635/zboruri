package com.example.zboruri.utils.validator;

import com.example.zboruri.domain.Client;

public class ClientValidator implements Validator<Client>{
    @Override
    public void validate(Client client) {
        String errors = "";
        if(client.getName() == null){
            errors += "Invalid client name!\n";
        }
        if(client.getUsername() == null){
            errors += "Invalid username!\n";
        }
        if(!errors.isEmpty()){
            throw new ValidatorExcept(errors);
        }
    }
}
