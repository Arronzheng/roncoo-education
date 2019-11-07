package com.roncoo.education.util.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class Tree<T extends Serializable> implements Serializable {

    private List<T> list = null;
}
