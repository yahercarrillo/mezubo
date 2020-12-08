package co.com.mezubo.carrillo.roulette.storage.controller;

import co.com.mezubo.carrillo.roulette.storage.dto.SpinWinner;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRouletteBets;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbUsers;
import co.com.mezubo.carrillo.roulette.storage.service.BetServiceCrud;
import co.com.mezubo.carrillo.roulette.storage.service.MzbRouletteServiceCrud;
import co.com.mezubo.carrillo.roulette.storage.service.UsersServiceCrud;
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
@RequestMapping("/v1/users")
public class UserController {

    @Resource
    UsersServiceCrud crud;

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        return crud.findAll();
    }

    @ApiOperation(value = "FindByNickName", notes = "Consulta un usuario por su nombre unico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = Object.class),
            @ApiResponse(code = 404, message = "404", response = String.class)})
    @GetMapping("/{nickname}")
    public ResponseEntity<Object> FindByNickName(@PathVariable("nickname") @Valid String nickname) {
        return crud.findByNickName(nickname);
    }


    @ApiOperation(value = "create User", notes = "Crea un usuario para juegos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = Object.class),
            @ApiResponse(code = 302, message = "302", response = String.class)})
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@Valid @RequestBody MzbUsers emp) {
        crud.insert(emp);
    }

    @ApiOperation(value = "Update User", notes = "Actualiza un usuario para juegos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = Object.class),
            @ApiResponse(code = 404, message = "404", response = String.class)})
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@Valid @RequestBody MzbUsers emp) {
        crud.update(emp);
    }
}
