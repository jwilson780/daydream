package com.jake.daydream.repository.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jake.daydream.repository.util.BlockJsonToObjectConverter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Accessors(chain = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "block")
public class Block {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column
  String id;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JsonIgnoreProperties("children")
  @JoinColumn(name = "parent")
  Block parent;

  @OneToMany(mappedBy = "parent")
  @JsonIgnoreProperties("parent")
  @Column
  Set<Block> children = new HashSet<>();

  public Block addChild() {
    Block child = new Block();
    this.children.add(child);
    child.setParent(this);
    return child;
  }

  @Column
  @Convert(converter = BlockJsonToObjectConverter.class)
  Map<String, Object> parameters;

  protected boolean hasParameter(String key) {
    return parameters.containsKey(key);
  }

  protected Object getParameter(String key) {
    return parameters.get(key);
  }

  protected void setParameter(String key, Object value) {
    if (value == null) {
      parameters.remove(key);
    } else {
      parameters.put(key, value);
    }
  }
}
