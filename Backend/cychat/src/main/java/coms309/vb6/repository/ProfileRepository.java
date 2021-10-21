package coms309.vb6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coms309.vb6.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	 Profile findById(int id);
	 void deleteById(int id);
}

