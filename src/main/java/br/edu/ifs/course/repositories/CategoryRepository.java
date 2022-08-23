package br.edu.ifs.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifs.course.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
