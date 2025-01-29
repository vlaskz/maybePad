package com.vlaskz.maybepad.page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, String> {

    Page findByPath(String path);
    @Query("SELECT p.path FROM Page p WHERE p.path LIKE CONCAT(:pathPrefix, '/%') AND p.path <> :pathPrefix")
    List<String> findPathsStartingWith(@Param("pathPrefix") String pathPrefix);

}