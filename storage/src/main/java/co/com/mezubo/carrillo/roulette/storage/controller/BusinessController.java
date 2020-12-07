package co.com.mezubo.carrillo.roulette.storage.controller;

import co.com.mezubo.carrillo.roulette.storage.dto.SpinWinner;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRouletteBets;
import co.com.mezubo.carrillo.roulette.storage.service.BetServiceCrud;
import co.com.mezubo.carrillo.roulette.storage.service.MzbRouletteServiceCrud;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Controlador version 1 de las operaciones de roulette
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
@RestController
@RequestMapping("/v1/roulette")
public class BusinessController {

    @Resource
    MzbRouletteServiceCrud mzbRouletteServiceCrud;

    @Autowired
    BetServiceCrud betServiceCrud;

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        return mzbRouletteServiceCrud.findAll();
    }

    @ApiOperation(value = "FindById", notes = "Consulta de una mesa por su id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = Object.class),
            @ApiResponse(code = 404, message = "404", response = String.class)})
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") @Valid String id) {
        return mzbRouletteServiceCrud.findById(id);
    }

    @ApiOperation(value = "FindByCode", notes = "Consulta de una mesa por su codigo unico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = Object.class),
            @ApiResponse(code = 404, message = "404", response = String.class)})
    @GetMapping("/code/{code}")
    public ResponseEntity<Object> getByCode(@PathVariable("code") @Valid String code) {
        return mzbRouletteServiceCrud.findByCode(code);
    }

    @ApiOperation(value = "create Roulette", notes = "Crea una mesa de juego")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = Object.class),
            @ApiResponse(code = 302, message = "302", response = String.class)})
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> createRoulette(@Valid @RequestBody MzbRoulette emp) {
        return mzbRouletteServiceCrud.insert(emp);

    }

    @ApiOperation(value = "create Bet", notes = "Crea una apuesta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = Object.class),
            @ApiResponse(code = 302, message = "302", response = Object.class),
            @ApiResponse(code = 422, message = "422", response = Object.class),
            @ApiResponse(code = 423, message = "423", response = String.class)})
    @PostMapping(value = "/createBet")
    @ResponseStatus(HttpStatus.OK)
    public void createBet(@Valid @RequestBody MzbRouletteBets emp) {
        betServiceCrud.insert(emp);

    }

    @ApiOperation(value = "create Bet", notes = "Crea una apuesta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = Object.class),
            @ApiResponse(code = 302, message = "302", response = Object.class),
            @ApiResponse(code = 422, message = "422", response = Object.class),
            @ApiResponse(code = 423, message = "423", response = String.class)})
    @PutMapping(value = "/updateBet")
    @ResponseStatus(HttpStatus.OK)
    public void updateBet(@Valid @RequestBody MzbRouletteBets emp) {
        betServiceCrud.update(emp);
    }


    @GetMapping(value = "/closeRoulette/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void closeRoulette(@Valid @PathVariable("id") String id) {
        betServiceCrud.closeRoulette(id);
    }

    @GetMapping(value = "/openRoulette/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void openRoulette(@Valid @PathVariable("id") String id) {
        betServiceCrud.openRoulette(id);
    }

    @GetMapping(value = "/spinRoulette/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SpinWinner> spinRoulette(@Valid @PathVariable("id") String id) {
        return betServiceCrud.spinRoulette(id);
    }


}
