package demo.Application.repo;

import demo.Application.model.User;
import demo.Application.model.UserDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDocumentRepository extends JpaRepository<UserDocument, Long> {

	 List<UserDocument> findByUserId(User userId);


}
