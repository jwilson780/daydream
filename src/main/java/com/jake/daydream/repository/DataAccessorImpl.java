package com.jake.daydream.repository;

import com.jake.daydream.repository.dao.Block;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataAccessorImpl implements DataAccessor {

  private final BlockRepository blockRepository;

  @Autowired
  public DataAccessorImpl(BlockRepository blockRepository) {
    this.blockRepository = blockRepository;
  }

  @Override
  public Iterable<Block> getAllTopLevelBlocks() {
    return blockRepository.findAllTopLevelBlocks();
  }

  @Override
  public Optional<Block> getBlockById(String id) {
    return blockRepository.findById(id);
  }

  @Override
  public Block insertBlock(Block block) {
    return blockRepository.save(block);
  }

  @Override
  public Block updateBlockById(Block block) {
    return blockRepository.save(block);
  }
}
