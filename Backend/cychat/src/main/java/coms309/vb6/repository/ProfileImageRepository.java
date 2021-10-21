package coms309.vb6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coms309.vb6.model.ProfileImage;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Integer>{

	ProfileImage findById(int id); 
	void deleteById(int id);
}
