package coms309.vb6.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import coms309.vb6.model.Story;
import coms309.vb6.model.User;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {
	 Story findById(int id);
	 void deleteById(int id);
	 @Query( "select s from Story s where s.user in :users ORDER BY s.time_stamp DESC" )
	 List<Story>findByIdByOrderBytime_stampDesc(Set<User>users);
	 @Query(" select s from Story s where s.user = :user ORDER BY s.time_stamp DESC")
	 List<Story>findByIdByOrderBytime_stampDesc(User user);
}
