package com.beastwall.world.database.dao;

import java.util.List;

/**
 * @author Abdel Wadoud Rasmi
 * <p>
 * A parent to all DAO(s)
 */
public interface EntityDao<Entity> {

    List<Entity> getAll();

    void insert(Entity entity);

    void update(Entity entity);

    void updateAll(List<Entity> entities);

    void deleteAll(List<Entity> entities);

    void delete(Entity entity);

    int getCount();


}
