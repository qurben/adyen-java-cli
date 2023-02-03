package io.github.qurben.action;

import java.io.IOException;

import com.adyen.Client;
import com.adyen.model.management.ApiCredential;
import com.adyen.model.management.ListMerchantApiCredentialsResponse;
import com.adyen.service.exception.ApiException;
import com.adyen.service.management.ApiCredentialsMerchantLevel;
import com.google.auto.service.AutoService;

@AutoService(ActionHandler.class)
public class CompanyApiCredentialList implements ActionHandler {

    @Override
    public String getName() {
        return "company:apicredential:list";
    }

    @Override
    public String execute(Client client, String... args) throws ApiException, IOException {
        ApiCredentialsMerchantLevel apiCredentialsMerchantLevel = new ApiCredentialsMerchantLevel(client);

        ListMerchantApiCredentialsResponse apiCredentials = apiCredentialsMerchantLevel.listApiCredentials("AdyenDevExECOM", null);

        for (ApiCredential apiCredential : apiCredentials.getData()) {
            System.out.println(apiCredential.getDescription());
        }

        return "";
    }
    
}
