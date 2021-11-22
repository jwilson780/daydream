package com.jake.daydream.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.jake.daydream.repository.DataAccessor;
import com.jake.daydream.repository.dao.Block;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BlockServiceTest {
  @Mock private DataAccessor dataAccessor;
  private BlockService blockService;

  @BeforeEach
  public void setup() {
    blockService = new BlockServiceImpl(dataAccessor);
  }

  @Test
  public void getAllTopLevelBlocksTest() {
    // SETUP
    String id = "testid";
    List<Block> blocks = new ArrayList<>();
    blocks.add(new Block().setId(id));
    when(dataAccessor.getAllTopLevelBlocks()).thenReturn(blocks);

    // INVOCATION
    Iterable<Block> allTopLevelBlocks = blockService.getAllTopLevelBlocks();

    // ASSERTION
    Block out = allTopLevelBlocks.iterator().next();
    assertEquals(out.getId(), id);
  }

  @Test
  public void getBlockByIdTest() {
    // SETUP
    String id = "testid";
    Block block = new Block().setId(id);
    when(dataAccessor.getBlockById(id)).thenReturn(java.util.Optional.ofNullable(block));

    // INVOCATION
    Optional<Block> out = blockService.getBlockById(id);

    // ASSERTION
    assertTrue(out.isPresent());
    assertEquals(out.get().getId(), id);
  }

  @Test
  public void insertBlockTest() {
    // SETUP
    String id = "testid";
    Block block = new Block().setId(id);
    when(dataAccessor.insertBlock(any())).thenReturn(block);

    // INVOCATION
    Block out = blockService.insertBlock();

    // ASSERTION
    assertEquals(out.getId(), id);
  }

  @Test
  public void updateBlockPropertiesTest() {
    // SETUP
    String id = "testid";
    Map<String, Object> map = new HashMap<>();
    map.put("TestKey", 1);
    Block newBlock = new Block().setId(id).setParameters(map);
    Block oldBlock = new Block().setId(id);
    when(dataAccessor.getBlockById(any())).thenReturn(Optional.ofNullable(oldBlock));
    when(dataAccessor.updateBlockById(any())).thenReturn(newBlock);

    // INVOCATION
    Optional<Block> out = blockService.updateBlockPropertiesById(newBlock);

    // ASSERTION
    assertTrue(out.isPresent());
    assertEquals(out.get().getId(), id);
  }

  @Test
  public void createBlockUnderParentTest() {
    // SETUP
    String id = "childId";
    String parent = "parentId";
    Block childBlock = new Block().setId(id);
    Block parentBlock = new Block().setId(parent);
    when(dataAccessor.getBlockById(any())).thenReturn(Optional.ofNullable(parentBlock));
    when(dataAccessor.insertBlock(any())).thenReturn(childBlock);

    // INVOCATION
    Optional<Block> out = blockService.createBlockUnderParent("parentId");

    // ASSERTION
    assertTrue(out.isPresent());
    assertEquals(out.get().getId(), id);
  }
}
