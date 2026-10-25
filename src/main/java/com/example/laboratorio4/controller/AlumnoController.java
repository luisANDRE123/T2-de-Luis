package com.example.laboratorio4.controller;

import com.example.laboratorio4.model.Alumno;
import com.example.laboratorio4.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin(origins = "*")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping
    public List<Alumno> listarAlumnos() {
        return alumnoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerPorId(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        if (alumno.isPresent()) {
            return ResponseEntity.ok(alumno.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Alumno> guardarAlumno(@RequestBody Alumno alumno) {
        Alumno nuevoAlumno = alumnoRepository.save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Long id, @RequestBody Alumno alumnoDetalles) {
        Optional<Alumno> alumnoOpt = alumnoRepository.findById(id);
        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get();
            alumno.setNombres(alumnoDetalles.getNombres());
            alumno.setCarrera(alumnoDetalles.getCarrera());
            alumno.setCiclo(alumnoDetalles.getCiclo());
            alumno.setPromedio(alumnoDetalles.getPromedio());
            Alumno actualizado = alumnoRepository.save(alumno);
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {
        if (alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}