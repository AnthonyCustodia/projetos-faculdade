package com.senac.resource;

import com.senac.dto.CadastroAnimalDto;
import com.senac.service.AnimalService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;

@Path("/animais") //Igual ao @RequestMapping
@Consumes(MediaType.APPLICATION_JSON) // Os dois fazem o mesmo que
@Produces(MediaType.APPLICATION_JSON) // @RestController
public class AnimalResource {

    @Inject
    AnimalService animalService;

    @GET
    public Response listar() {
        var animais = animalService.listar();
        return Response.ok(animais).build();
    }

    @POST
    public Response cadastrar(CadastroAnimalDto cadastroAnimalDto) {
        animalService.criar(cadastroAnimalDto);
        return Response.status(201).build();
    }

}
