package org.springguru.msscbrewery.services.v2;

import org.springguru.msscbrewery.web.model.BeerDto;
import org.springguru.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerDtoV2 getBeerById(UUID beerId);

    BeerDtoV2 saveNewBeer(BeerDtoV2 beerDTOV2);

    void updateBeer(UUID beerId, BeerDtoV2 beerDTOV2);

    void deleteById(UUID beerId);
}
