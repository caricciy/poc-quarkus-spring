package br.com.a4a.web;

import br.com.a4a.subpay.domain.vo.Money;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/account")
public class AccountController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        Money money = Money.of(200);
        return Response.ok(money).build();
    }
}
