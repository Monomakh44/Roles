package com.website.web.repo;

import com.website.web.models.Info;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface InfoRepository extends CrudRepository<Info, Long> {
}
