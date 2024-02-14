package demo.Application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.Application.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByEmail(String email);

	List<User> findByType(String type);


}
