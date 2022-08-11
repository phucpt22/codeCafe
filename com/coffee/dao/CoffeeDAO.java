/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.dao;

import java.util.List;


public abstract class CoffeeDAO<E,K> {
    public abstract void insert(E entity);
    public abstract void update(E entity);
    public abstract void delete(K key);
    public abstract List<E> selectAll();
    public abstract E selectById(K key);
    public abstract List<E> selectBySql(String sql, Object...args);
    
}
