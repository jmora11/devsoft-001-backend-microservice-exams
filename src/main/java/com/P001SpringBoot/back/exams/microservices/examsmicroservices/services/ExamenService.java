package com.P001SpringBoot.back.exams.microservices.examsmicroservices.services;

import com.P001SpringBoot.back.models.entity.Asignatura;
import com.P001SpringBoot.back.models.entity.Examen;
import com.P001SpringBoot.back.service.ICommonService;

import java.util.List;

public interface ExamenService extends ICommonService<Examen> {
    public List<Examen> findExamByName(String term);

    public Iterable<Asignatura> findAllAsignatura();

    public Iterable<Long> findExamsIdWhithResponsesByQuestionId(Iterable<Long> questionId);
}
