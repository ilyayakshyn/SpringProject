package com.dtno.coockingmachine.CoockingMachine.DAOPackage;

import com.dtno.coockingmachine.CoockingMachine.entity.User;
import com.dtno.coockingmachine.CoockingMachine.entity.UserDishes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserDishDAO extends CrudRepository<UserDishes, Long> {
    List<UserDishes> findAll();
    UserDishes findAllByDishId(int dish_id);
    List<UserDishes> findAllByUser(User user);

    int deleteByDishId(int dish_id);

    UserDishes save(UserDishes userDishes);

}
