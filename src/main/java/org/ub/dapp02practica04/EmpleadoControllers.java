package org.ub.dapp02practica04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ub.dapp02practica04.dto.EmpleadoDto;

import javax.websocket.MessageHandler;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/empleados")
public class EmpleadoControllers {

    @Autowired
    private  RepositoryEmpleado repositoryEmpleado;



    @GetMapping
    public List<Empleado> findAll() {
        return repositoryEmpleado.findAll();
    }

    @GetMapping("/{id}")
    public Empleado findById(@PathVariable UUID id) {
        return repositoryEmpleado.findById(id).orElse(null);
    }

    @PostMapping()
    public ResponseEntity<Empleado> addEmpleado(@RequestBody EmpleadoDto empleadoDto) {

        Empleado empleado = new Empleado();
        empleado.setName(empleadoDto.getName());
        empleado.setPhone(empleadoDto.getPhone());
        empleado.setAddress(empleadoDto.getAddress());

        return new ResponseEntity<>(repositoryEmpleado.save(empleado), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado( @PathVariable UUID id, @RequestBody EmpleadoDto empleado) {

        Optional<Empleado> empleadoOptional = repositoryEmpleado.findById(id);

        if (empleadoOptional.isPresent()) {
            empleadoOptional.get().setName(empleado.getName());
            empleadoOptional.get().setPhone(empleado.getPhone());
            empleadoOptional.get().setAddress(empleado.getAddress());
            return new ResponseEntity<>(repositoryEmpleado.save(empleadoOptional.get()), HttpStatus.OK);
        }else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> deleteById(@PathVariable UUID id) {
        repositoryEmpleado.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

