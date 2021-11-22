package com.jake.daydream.service;

import com.jake.daydream.repository.DataAccessor;
import com.jake.daydream.repository.dao.Block;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BlockServiceImpl implements BlockService {

  private final DataAccessor dataAccessor;

  BlockServiceImpl(DataAccessor dataAccessor) {
    this.dataAccessor = dataAccessor;
  }

  @Override
  public Iterable<Block> getAllTopLevelBlocks() {
    // top level blocks are blocks with no parents
    return dataAccessor.getAllTopLevelBlocks();
  }

  @Override
  public Optional<Block> getBlockById(String id) {
    return dataAccessor.getBlockById(id);
  }

  @Override
  public Block insertBlock() {
    return dataAccessor.insertBlock(new Block());
  }

  @Override
  public Optional<Block> updateBlockPropertiesById(Block block) {
    // mainly to ensure we don't upsert, so check for existence
    Optional<Block> blockById = dataAccessor.getBlockById(block.getId());
    if (blockById.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(dataAccessor.updateBlockById(block));
  }

  @Override
  public Optional<Block> createBlockUnderParent(String parentId) {
    // lookup parent
    Optional<Block> parent = dataAccessor.getBlockById(parentId);
    if (parent.isEmpty()) {
      return Optional.empty();
    }
    // set reference
    Block child = parent.get().addChild();
    return Optional.of(dataAccessor.insertBlock(child));
  }
}
