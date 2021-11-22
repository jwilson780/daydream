package com.jake.daydream.api;

import com.jake.daydream.repository.dao.Block;
import com.jake.daydream.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BlockControllerImpl implements BlockController {

  private final BlockService blockService;

  @Autowired
  public BlockControllerImpl(BlockService blockService) {
    this.blockService = blockService;
  }

  @GetMapping("/block")
  public Iterable<Block> allTopLevelBlocks() {
    return blockService.getAllTopLevelBlocks();
  }

  @GetMapping("/block/{id}")
  public Block blockById(@PathVariable String id) {
    return blockService
        .getBlockById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PutMapping("/block")
  public Block createBlock() {
    return blockService.insertBlock();
  }

  @PutMapping("/block/{id}")
  public Block createBlockUnderParent(@PathVariable String id) {
    return blockService
        .createBlockUnderParent(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/block/{id}")
  public Block updateParametersForBlock(@PathVariable String id, @RequestBody Block block) {
    return blockService
        .updateBlockPropertiesById(block.setId(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }
}
