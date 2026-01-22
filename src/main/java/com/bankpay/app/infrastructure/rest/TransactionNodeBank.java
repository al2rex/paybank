package com.bankpay.app.infrastructure.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "externalPayBank")
public interface TransactionNodeBank {

    @GET
    String checkTrasaction(@QueryParam("num") Integer idTransaction, @QueryParam("txt") String nameBank);

}
