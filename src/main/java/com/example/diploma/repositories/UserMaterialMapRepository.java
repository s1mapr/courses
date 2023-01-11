package com.example.diploma.repositories;

import com.example.diploma.enteties.UserCourseMaterialMap;
import com.example.diploma.enteties.UserCourseMaterialPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMaterialMapRepository extends JpaRepository<UserCourseMaterialMap, UserCourseMaterialPK> {



}
