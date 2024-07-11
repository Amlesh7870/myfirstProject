package in.spring.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.spring.springboot.entity.Users;

@Repository
public interface UserDAORepository extends JpaRepository<Users, Integer> {

	Users findUserByUserName(String username);

	

}
