package com.bankpay.app.infrastructure.rest;

import com.bankpay.app.domain.Transaction;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "externalPayBank")
public interface TransactionNodeBank {

    @POST
    String checkTrasaction(Transaction transaction);

}
