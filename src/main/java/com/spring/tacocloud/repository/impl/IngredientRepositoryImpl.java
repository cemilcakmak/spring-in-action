package com.spring.tacocloud.repository.impl;

import com.spring.tacocloud.domain.Ingredient;
import com.spring.tacocloud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public IngredientRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public <S extends Ingredient> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Ingredient> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("SELECT id, name, type FROM Ingredient",
                this::mapRowToIngredient);
    }

    @Override
    public Iterable<Ingredient> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Ingredient entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Ingredient> entities) {

    }

    @Override
    public void deleteAll() {

    }

    public Ingredient findOne(String id) {
        return jdbc.queryForObject(
                "SELECT id, name. type FROM Ingredient WHERE id = ?",
                this::mapRowToIngredient, id);
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update(
                "INSERT INTO Ingredient (id, name, type)" +
                        "VALUES (?, ?, ?) ",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());

        return ingredient;
    }
}
