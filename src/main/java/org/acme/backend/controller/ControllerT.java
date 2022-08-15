package org.acme.backend.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.backend.service.ServiceT;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.InputStream;

@Path("/api")
public class ControllerT {

    @Inject
    ServiceT serviceT;

    @POST
    @Consumes("application/json")
    @Produces("MediaType.text/html")
    @Path("/solution")
    public InputStream solve(String request) {
        int size;
        try {
            size = new ObjectMapper().readTree(request).get("size").asInt();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return serviceT.createPage(size);
    }
}
