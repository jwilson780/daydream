package com.jake.daydream.api;

import com.jake.daydream.repository.dao.Block;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/** Rest Controller for the Block Api endpoints */
public interface BlockController {

  /**
   * Gets all Top level Blocks, which are blocks without children
   *
   * @return List of Blocks
   */
  Iterable<Block> allTopLevelBlocks();

  /**
   * Gets a block by given id
   *
   * @param id - id of the block to fetch
   * @return Block
   */
  Block blockById(@PathVariable String id);

  /**
   * Creates a block
   *
   * @return the created block
   */
  Block createBlock();

  /**
   * Creates a child block under the given parent id
   *
   * @param parentId - id of the parent
   * @return - the new child block
   */
  Block createBlockUnderParent(@PathVariable String parentId);

  /**
   * Update the parameters of the block by id
   *
   * @param id - id of the block to update
   * @return the updated block
   */
  Block updateParametersForBlock(@PathVariable String id, @RequestBody Block block);
}
