package org.springguru.msscbrewery.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springguru.msscbrewery.services.BeerService;
import org.springguru.msscbrewery.web.model.BeerDto;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Deprecated
@Validated
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody BeerDto beerDTO){
        BeerDto savedDto = beerService.saveNewBeer(beerDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/"+savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleUpdate(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDTO){
        beerService.updateBeer(beerId, beerDTO);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerService.deleteById(beerId);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>>validationErrorHandler(ConstraintViolationException e){
        List<String> errors = new ArrayList<String>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() +" : "+constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> genericErrorHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String>validationErrorHandler(MethodArgumentNotValidException e){

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
