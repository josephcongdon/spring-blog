package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> findByTitle(String title);

    Ad findByDescription(String title);

    @Query(nativeQuery = true, value = "select * from ads where title like :t%")
    List<Ad> findByTitleLike(@Param("t")String term);

}
