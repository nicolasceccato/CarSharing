package carsharing.dao;

import carsharing.H2JDBCUtils;
import carsharing.models.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyDAOImpl implements AbstractDAO<Company> {

    private final List<Company> companies;
    static private final Connection connection = H2JDBCUtils.getConnection();
    private static final String SELECT = "SELECT * FROM COMPANY";
    private static final String BY_NAME = "WHERE NAME = ?";
    private static final String BY_ID = "WHERE ID = ?";

    private static final String INSERT = "INSERT INTO COMPANY (NAME) VALUES (?)";

    public CompanyDAOImpl() {
        this.companies = new ArrayList<>();
    }


    @Override
    public Optional<Company> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Company> getAll() {
        try (ResultSet rs = connection.createStatement().executeQuery(SELECT)) {
            companies.clear();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                companies.add(new Company(id, name));
            }

        } catch (SQLException e) {
            System.out.println("Problemas encontrados na busca da lista de empresas!");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return companies;
    }

    @Override
    public Company create(Company entity) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, entity.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problemas encontrados na inserção de uma nova empresa!");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(Company entity) {
        return false;
    }
}
