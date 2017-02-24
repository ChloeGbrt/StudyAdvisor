package dao1;

import java.util.List;
import entities.Metier;

public interface MetierDao {

	public List<Metier> listMetiers();

	public Metier getMetier(Integer idMetier);

	public Metier addMetier(Metier Metier);

	public Metier deleteMetier(Integer idMetier);


}
