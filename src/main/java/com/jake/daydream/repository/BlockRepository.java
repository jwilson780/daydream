package com.jake.daydream.repository;

import com.jake.daydream.repository.dao.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block, String> {
  @Query(value = "select * from block where parent is null", nativeQuery = true)
  Iterable<Block> findAllTopLevelBlocks();
}
