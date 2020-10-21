package com.georgeciachir.primesfinder.resource;

import com.georgeciachir.primesfinder.resource.dto.PrimeResult;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
public class PrimeResultResourceAssembler implements RepresentationModelAssembler<PrimeResult, EntityModel<PrimeResult>> {

    private final String from;
    private final boolean includingFrom;

    @Override
    public EntityModel<PrimeResult> toModel(PrimeResult entity) {
        List<Link> links = new ArrayList<>();
        links.add(WebMvcLinkBuilder.linkTo(methodOn(PrimesController.class).nextPrime(from, includingFrom)).withSelfRel());
        return EntityModel.of(entity, links);
    }
}
