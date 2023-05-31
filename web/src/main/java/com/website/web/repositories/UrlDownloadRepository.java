package com.website.web.repositories;

import com.website.web.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlDownloadRepository extends JpaRepository<Url, Long> {
}
