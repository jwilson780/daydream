package com.jake.daydream.repository;

import com.jake.daydream.repository.dao.Block;
import java.util.Optional;

public interface DataAccessor {

  /**
   * Finds all blocks where parent is null
   *
   * @return all blocks with no parents
   */
  Iterable<Block> getAllTopLevelBlocks();

  /**
   * Finds block by id
   *
   * @param id - id of the block to fetch
   * @return the block or Optional.empty()
   */
  Optional<Block> getBlockById(String id);

  /**
   * Inserts a new block
   *
   * @return - the newly created block
   */
  Block insertBlock(Block block);

  /**
   * Updates a block
   *
   * @param block - block to update
   * @return the updated block
   */
  Block updateBlockById(Block block);
}
