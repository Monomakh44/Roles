package com.website.web.repositories;

import com.website.web.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GetImageRepository extends JpaRepository<Image, Long> {
}
