package br.edu.ifs.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifs.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
