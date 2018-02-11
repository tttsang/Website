package com.jking.computersite.service;

import com.jking.computersite.entity.IndexProfessor;

import java.util.List;

public interface IndexProfessorService {

    List<IndexProfessor> getAll();

    void add(IndexProfessor indexProfessor);

    IndexProfessor find(Integer id);

    int delete(Integer id);

    int update(IndexProfessor indexProfessor);
}
