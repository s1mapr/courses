package com.example.diploma.repositories;


import com.example.diploma.enteties.UserTaskMap;
import com.example.diploma.enteties.UserTaskPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTaskMapRepository extends JpaRepository<UserTaskMap, UserTaskPK> {


}
