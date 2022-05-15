package com.coresquad.paracasa.controller;

import com.coresquad.paracasa.entity.*;
import com.coresquad.paracasa.exception.TypeNotFoundException;
import com.coresquad.paracasa.repository.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TypeRepo typeRepo;

    @GetMapping("/public/users")
    @ApiOperation(value = "Busca todos los usuarios registrados en ParaCasa App", notes = "Devuelve una colección del modelo User", response = User[].class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Success", response = User[].class) })
    Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/public/types")
    @ApiOperation(value = "Find all Types of food", notes = "Retrieving the collection of Types", response = Type[].class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Type[].class) })
    Iterable<Type> getAllTypes() {
        return typeRepo.findAll();
    }

    @PostMapping("/private/types")
    @ApiOperation(value = "Guarda un nuevo tipo de plato", notes = "Devuelve el tipo creado", response = Type.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Category.class) })
    Type newEmployee(@RequestBody Type newType) {
        return typeRepo.save(newType);
    }

    @PutMapping("/private/types/{id}")
    @ApiOperation(value = "Actualiza un tipo de plato", notes = "Devuelve el tipo actualizado", response = Type.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Category.class) })
    Type updateType(@PathVariable("id") Integer id, @RequestBody Type type) throws TypeNotFoundException {
        Type dbType = typeRepo.findById(id).orElseThrow(() -> new TypeNotFoundException(id));

        dbType.setId(id);
        dbType.setTipo(type.getTipo());

        typeRepo.save(dbType);
        return dbType;
    }

    @DeleteMapping("/private/types/{id}")
    @ApiOperation(value = "Elimina un tipo de plato", notes = "Devuelve un mensaje si es success", response = String.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Success", response = String.class) })
    public String removeType(@PathVariable Integer id) {
        typeRepo.deleteById(id);
        return "Type con id " + id + " eliminado correctamente!";
    }
}