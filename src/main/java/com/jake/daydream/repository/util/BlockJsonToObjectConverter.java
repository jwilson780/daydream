/* (C) 2021 */
package com.jake.daydream.repository.util;

public class BlockJsonToObjectConverter extends JsonMapConverter<String, Object> {
  public BlockJsonToObjectConverter() {
    super(String.class, Object.class);
  }
}
