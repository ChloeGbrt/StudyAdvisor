package managers;


import java.util.List;


import dao1.MetierDao;

import dao.impl.MetierDaoImpl;


import entities.Metier;




public class MetierLibrary {
	
	private static class MetierLibraryHolder {
		private final static MetierLibrary instance = new MetierLibrary();
	}
	
	public static MetierLibrary getInstance() {
		return MetierLibraryHolder.instance;
	}
	
	private MetierDao MetierDao = new MetierDaoImpl();
	;	

	private MetierLibrary() {
	}

	public List<Metier> listMetiers() {
		return MetierDao.listMetiers();
	}

	public Metier getMetier(Integer idMetier) {
		return MetierDao.getMetier(idMetier);
	}

	public Metier addMetier(Metier Metier) {
		return MetierDao.addMetier(Metier);
	}
}