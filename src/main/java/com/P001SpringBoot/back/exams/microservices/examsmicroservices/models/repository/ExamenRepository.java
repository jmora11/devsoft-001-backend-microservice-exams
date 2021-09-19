package com.P001SpringBoot.back.exams.microservices.examsmicroservices.models.repository;

import com.P001SpringBoot.back.models.entity.Examen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ExamenRepository extends PagingAndSortingRepository<Examen, Long> {

    @Query("select e from Examen e where e.name like %?1%")
    public List<Examen> findExamByName(String term);

    @Query("select e.id from Pregunta p join p.examen e where p.id in ?1 group by e.id")
    public Iterable<Long> findExamsIdWhithResponsesByQuestionId(Iterable<Long> questionId);
}
