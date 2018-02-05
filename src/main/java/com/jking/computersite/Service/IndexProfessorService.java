package com.jking.computersite.Service;

import com.jking.computersite.entity.IndexProfessor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IndexProfessorService {

    List<IndexProfessor> getAll();

    void add(IndexProfessor indexProfessor);

    IndexProfessor find(Integer id);

    int delete(Integer id);

    int update(IndexProfessor indexProfessor);
}
