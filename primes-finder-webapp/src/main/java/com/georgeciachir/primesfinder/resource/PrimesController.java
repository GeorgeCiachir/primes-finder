package com.georgeciachir.primesfinder.resource;

import com.georgeciachir.primesfinder.resource.dto.PrimeResult;
import com.georgeciachir.primesfinder.resource.dto.PrimeResultMapper;
import com.georgeciachir.primesfinder.service.PrimesService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.georgeciachir.primesfinder.resource.PrimesResource.RESOURCE_ENDPOINT;

@RestController
@RequestMapping(RESOURCE_ENDPOINT)
@RequiredArgsConstructor
public class PrimesController implements PrimesResource {

    private final PrimesService primesService;
    private final PrimeResultMapper primeResultMapper;

    /**
     * @inheritDoc
     */
    @Override
    public EntityModel<PrimeResult> nextPrime(
            @RequestParam String from,
            @RequestParam(defaultValue = "true") boolean includingFrom) {
        String nextPrime = primesService.findNextPrime(from, includingFrom);
        PrimeResultResourceAssembler responseAssembler = new PrimeResultResourceAssembler(from, includingFrom);
        PrimeResult response = primeResultMapper.toPrimeResponse(nextPrime);
        return responseAssembler.toModel(response);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isPrime(String toTest) {
        return primesService.isPrime(toTest);
    }
}
