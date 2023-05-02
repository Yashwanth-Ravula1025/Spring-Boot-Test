package com.UST.curdoperations.repository;

import com.UST.curdoperations.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentDetails,Integer> {

}
