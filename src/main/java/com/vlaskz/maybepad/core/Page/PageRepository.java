package com.vlaskz.maybepad.core.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, String> {

    Page findByPath(String path);
    List<Page> findByParentId(String parentId);
}