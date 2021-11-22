package com.jake.daydream.service;

import com.jake.daydream.repository.dao.Block;
import java.util.Optional;

/** Block Service handles all business logic associated with the Blocks */
public interface BlockService {
  /**
   * Fetches all top level blocks. Top level blocks are blocks without parents
   *
   * @return List of Blocks
   */
  Iterable<Block> getAllTopLevelBlocks();

  /**
   * Fetches a block for a given id
   *
   * @param id - id of block to fetch
   * @return Optional Block
   */
  Optional<Block> getBlockById(String id);

  /**
   * Creates a new block
   *
   * @return the created block
   */
  Block insertBlock();

  /**
   * Updates a block with the passed id
   *
   * @param block - the block to update
   * @return Optional updated block
   */
  Optional<Block> updateBlockPropertiesById(Block block);

  /**
   * Creates a new block under the parent block
   *
   * @param parentId - id of the parent block to create block under
   * @return Optional new block
   */
  Optional<Block> createBlockUnderParent(String parentId);
}
