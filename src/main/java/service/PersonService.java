package service;

import entity.Person;
import util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonService implements IService<Person> {
    private Connection cnx;

    public PersonService() {
        cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void insert(Person person) throws SQLException {
        String request = "insert into person(nom,prenom,age) values(?,?,?)";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(request, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, person.getNom());
            preparedStatement.setString(2, person.getPrenom());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    person.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void delete(Person person) throws SQLException {
        String request = "delete from person where id = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(request)) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Person person) throws SQLException {
        String request = "update person set nom = ?, prenom = ?, age = ? where id = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(request)) {
            preparedStatement.setString(1, person.getNom());
            preparedStatement.setString(2, person.getPrenom());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setInt(4, person.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Person> readAll() throws SQLException {
        String request = "SELECT * FROM person";
        List<Person> personnes = new ArrayList<>();
        try (Statement statement = cnx.createStatement();
             ResultSet resultSet = statement.executeQuery(request)) {
            while (resultSet.next()) {
                Person person = new Person(resultSet.getString("nom")
                        , resultSet.getString("prenom")
                        , resultSet.getInt("age"));
                person.setId(resultSet.getInt("id"));
                personnes.add(person);
            }
        }
        return personnes;
    }

    @Override
    public Person readById(int id) throws SQLException {
        String request = "SELECT * FROM person where id = ?";
        Person person = null;
        try (PreparedStatement preparedStatement = cnx.prepareStatement(request)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                person = new Person(resultSet.getString("nom")
                        , resultSet.getString("prenom")
                        , resultSet.getInt("age"));
                person.setId(resultSet.getInt("id"));
            }
        }
        return person;
    }
}