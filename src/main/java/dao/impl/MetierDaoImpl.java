package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao1.MetierDao;
import entities.Metier;

public class MetierDaoImpl implements MetierDao {

	@Override
	public List<Metier> listMetiers() {
		String query = "SELECT * FROM Metier ORDER BY Nom";
		List<Metier> Metiers = new ArrayList<>(); 
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) {
					while(resultSet.next()) {
						Metier Metier = new Metier(resultSet.getInt("idMetier"), resultSet.getString("Nom"), resultSet.getString("Description"));
						Metiers.add(Metier);
					}
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Metiers;
	}

	@Override
	public Metier getMetier(Integer idMetier) {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM Metier WHERE idMetier = ?")) {
				statement.setInt(1, idMetier);
				try (ResultSet resultSet = statement.executeQuery()) {
					if(resultSet.next()) {
						return new Metier(resultSet.getInt("idMetier"), resultSet.getString("Nom"), resultSet.getString("Description") );
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	
	@Override
	public Metier addMetier(Metier Metier) {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO Metier(Nom,Description) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, Metier.getNom());
				statement.setString(2, Metier.getDescription());
				statement.executeUpdate();
				
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if(resultSet.next()) {
						Metier.setIdMetier(resultSet.getInt(1));
						return Metier;
					}				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	} 
	
	@Override
	public Metier deleteMetier(Integer idMetier){
		   try(Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			   String sqlQuery = "DELETE FROM Metier WHERE idMetier="+idMetier;
			   try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
			   statement.executeUpdate();
			   }
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return null;
	   }


}