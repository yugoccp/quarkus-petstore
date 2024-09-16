package org.acme;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/pets")
@Produces(MediaType.TEXT_HTML)
public class PetViewController {

    @Inject
    Template petsView;

    @Inject
    Template petsEdit;

    @Inject
    PetService petService;

    @GET
    @Blocking
    public TemplateInstance getPets() {
        var pets = petService.getPets();
        return petsView.data("pets", pets);
    }

    @Path("create")
    @GET
    public TemplateInstance createView() {
        return petsEdit.data(
                "pet", new PetEntity(),
                "isUpdate", false);
    }

    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Blocking
    public TemplateInstance createPet(
            @FormParam("name") String name,
            @FormParam("age") Integer age,
            @FormParam("type") String type,
            @FormParam("gender") String gender
            ) {
        petService.createPet(name, age, type, gender);
        return getPets();
    }

    @Path("update/{id}")
    @GET
    @Blocking
    public TemplateInstance updateView(@PathParam("id") Long id) {
        var pet = petService.getPet(id);
        return petsEdit.data(
            "pet", pet,
            "isUpdate", true);
    }

    @Path("update/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Blocking
    public TemplateInstance updatePet(
            @PathParam("id") Long id,
            @FormParam("type") String type,
            @FormParam("age") Integer age,
            @FormParam("gender") String gender) {
        petService.updatePet(id, age, type, gender);
        return getPets();
    }

    @Path("delete/{id}")
    @GET
    @Blocking
    public TemplateInstance deletePet(@PathParam("id") Long petId) {
        petService.deletePet(petId);
        return getPets();
    }
}
