package com.task.infra.repository;

import com.task.core.dto.CodeDto;
import com.task.core.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface CodeRepository extends JpaRepository<CodeEntity, String> {

    @Query("""
            SELECT new com.task.core.dto.CodeDto(ce)
            FROM CodeEntity ce
            WHERE
            ce.id LIKE :substring%
            """)
    Stream<CodeDto> findByIdStartsWith(@Param("substring") String substring);

}
