package com.dtno.coockingmachine.CoockingMachine.DAOPackage;

import com.dtno.coockingmachine.CoockingMachine.entity.Ingredients;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface IngredientDAO extends CrudRepository<Ingredients, Long> {

    public List<Ingredients> findAll();


    Ingredients findByIngredientId(int ingredientId);

    @Query(nativeQuery = true, value = "select dish_id from dishtoingredient dti " +
            "WHERE dti.ingredient_id in :ingredients " +
            "GROUP BY dish_id " +
            "HAVING COUNT(found_rows()) = :size")
    public List<Integer> findAllByIngredientsList(
            @Param("ingredients") List<Integer> ingredients,
            @Param("size") int size);
}
