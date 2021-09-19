package com.P001SpringBoot.back.exams.microservices.examsmicroservices.controllers;

import com.P001SpringBoot.back.controllers.CommonController;
import com.P001SpringBoot.back.exams.microservices.examsmicroservices.services.ExamenService;
import com.P001SpringBoot.back.models.entity.Examen;
import com.P001SpringBoot.back.models.entity.Pregunta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

    @GetMapping("/answered-by-questions")
    public ResponseEntity<?> obtenerExamenesRespondidosPorPreguntasIdRespondidas(@RequestParam List<Long> preguntaIds) {
        return ResponseEntity.ok().body(service.findExamsIdWhithResponsesByQuestionId(preguntaIds));
    }

    @PutMapping("/update-exam/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id){

        if (result.hasErrors()) {
            return this.validar(result);
        }

        Optional<Examen> optionalExamen = service.findById(id);

        if(!optionalExamen.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Examen examenToUpdate = optionalExamen.get();
        examenToUpdate.setName(examen.getName());

        List<Pregunta> eliminadas = examenToUpdate.getPreguntas()
                .stream()
                .filter(pregunta -> !examen.getPreguntas().contains(pregunta))
                .collect(Collectors.toList());

        eliminadas.forEach(examenToUpdate::removePregunta);

        examenToUpdate.setPreguntas(examen.getPreguntas());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenToUpdate));
    }

    @GetMapping("/filter/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term) {
        return ResponseEntity.ok(service.findExamByName(term));
    }

    @GetMapping("/asignatures/all")
    public ResponseEntity<?> listarAsignaturas() {
        return ResponseEntity.ok(service.findAllAsignatura());
    }
}
