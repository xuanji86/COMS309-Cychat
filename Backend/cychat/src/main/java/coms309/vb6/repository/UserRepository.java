package coms309.vb6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms309.vb6.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	 User findById(int id);
	 void deleteById(int id);
}
