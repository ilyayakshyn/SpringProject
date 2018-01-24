package com.dtno.coockingmachine.CoockingMachine.DAOPackage;

import com.dtno.coockingmachine.CoockingMachine.entity.Dishes;
import com.dtno.coockingmachine.CoockingMachine.entity.DishToIngredients;
import com.dtno.coockingmachine.CoockingMachine.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DishDAO extends CrudRepository<Dishes, Long> {
    public List<Dishes> findAll();
    public Dishes findByDishId(int dishId);
}
