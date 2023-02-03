package io.github.qurben.action;

import java.io.IOException;

import com.adyen.Client;
import com.adyen.service.exception.ApiException;

@AutoService(ActionHandler.class)
public class CompanyApiCredentialCreate implements ActionHandler {

    @Override
    public String execute(Client client, String... args) throws ApiException, IOException {
        

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getHelp() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
