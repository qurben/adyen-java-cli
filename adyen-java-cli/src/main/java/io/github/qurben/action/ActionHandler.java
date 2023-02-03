package io.github.qurben.action;

import java.io.IOException;

import com.adyen.Client;
import com.adyen.service.exception.ApiException;

public interface ActionHandler {
    String getName();

    String execute(Client client, String... args) throws ApiException, IOException;
}
