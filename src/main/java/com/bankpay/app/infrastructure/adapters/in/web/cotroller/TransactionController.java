package com.bankpay.app.infrastructure.adapters.in.web.cotroller;

import com.bankpay.app.application.ProcessTransferUseCase;
import com.bankpay.app.infrastructure.adapters.in.web.dto.TransaccionRequestDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/transaction")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterForReflection
public class TransactionController {
    @Inject
    ProcessTransferUseCase processTransferUseCase;

    @POST
    @Path("/process")
    @Blocking
    public Uni<Response> processTransaction(TransaccionRequestDTO request) {
        return processTransferUseCase.execute(request);
    }

}
