package com.P001SpringBoot.back.exams.microservices.examsmicroservices.services;

import com.P001SpringBoot.back.exams.microservices.examsmicroservices.models.repository.AsignaturaRespository;
import com.P001SpringBoot.back.exams.microservices.examsmicroservices.models.repository.ExamenRepository;
import com.P001SpringBoot.back.models.entity.Asignatura;
import com.P001SpringBoot.back.models.entity.Examen;
import com.P001SpringBoot.back.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamenServiceImpl extends CommonService<Examen, ExamenRepository> implements ExamenService{

    @Autowired
    private AsignaturaRespository asignaturaRespository;

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findExamByName(String term) {
        return repository.findExamByName(term);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Asignatura> findAllAsignatura() {
        return asignaturaRespository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Long> findExamsIdWhithResponsesByQuestionId(Iterable<Long> questionId) {
        return repository.findExamsIdWhithResponsesByQuestionId(questionId);
    }


}
