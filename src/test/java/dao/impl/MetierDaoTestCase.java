package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.Assertions;



import org.junit.Before;
import org.junit.Test;

import dao1.MetierDao;
import entities.Metier;
import dao.impl.DataSourceProvider;

import static org.assertj.core.api.Assertions.fail;

public class MetierDaoTestCase {
	private MetierDao MetierDao = new MetierDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection();
				Statement StudyAdvisor = connection.createStatement()) {
			StudyAdvisor.executeUpdate("DELETE FROM Metier");
			StudyAdvisor.executeUpdate("INSERT INTO `Metier`(`idMetier`,`Nom`,`Description`) VALUES (1,'my title 1','summary')");
			StudyAdvisor.executeUpdate("INSERT INTO `Metier`(`idMetier`,`Nom`,`Description`) VALUES (2,'my title 2','summary2')");
		
		}
	}
	

	@Test
	public void shouldListMetiers() {
		// WHEN
		List<Metier> listMetiers = MetierDao.listMetiers();
		// THEN
		Assertions.assertThat(listMetiers).extracting("idMetier", "Nom", "Description").containsOnly(
				Assertions.tuple(1, "my title 1", "summary"),
				Assertions.tuple(2, "my title 2", "summary2") );
	}
	
	@Test
	public void shouldGetMetier() {
		// WHEN
		Metier Metier = MetierDao.getMetier(1);
		// THEN
		Assertions.assertThat(Metier).isNotNull();
		Assertions.assertThat(Metier.getIdMetier()).isEqualTo(1);
		Assertions.assertThat(Metier.getNom()).isEqualTo("my title 1");
		Assertions.assertThat(Metier.getDescription()).isEqualTo("summary");
		
	}
	@Test
	public void shouldNotGetMetier() {
		// WHEN
		Metier Metier = MetierDao.getMetier(0);
		// THEN
		Assertions.assertThat(Metier).isNull();
	}
	
	
	@Test
	public void shouldAddMetier() throws Exception {
		// GIVEN
		Metier MetierToAdd = new Metier(null, "New title", "New summary");
		// WHEN
		Metier MetierAdded = MetierDao.addMetier(MetierToAdd);
		// THEN
		Assertions.assertThat(MetierAdded).isNotNull();
		Assertions.assertThat(MetierAdded.getIdMetier()).isNotNull();
		Assertions.assertThat(MetierAdded.getNom()).isEqualTo("New title");
		Assertions.assertThat(MetierAdded.getDescription()).isEqualTo("New summary");
		
		try (Connection connection = DataSourceProvider.getDataSource().getConnection();
				PreparedStatement StudyAdvisor = connection.prepareStatement("SELECT * FROM Metier WHERE idMetier = ?")) {
			StudyAdvisor.setInt(1, MetierAdded.getIdMetier());
			try (ResultSet rs = StudyAdvisor.executeQuery()) {
				assertThat(rs.next()).isTrue();
				assertThat(rs.getInt("idMetier")).isEqualTo(MetierAdded.getIdMetier());
				assertThat(rs.getString("Nom")).isEqualTo("New title");
				assertThat(rs.getString("Description")).isEqualTo("New summary");
				assertThat(rs.next()).isFalse();
			}
		}
		
}
}