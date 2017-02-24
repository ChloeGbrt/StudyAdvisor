package dao.mock.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import dao1.MetierDao;

import entities.Metier;


public class MetierDaoMockImpl implements MetierDao {
	private TreeMap<Integer, Metier> MetiersList;

	@Override
	public List<Metier> listMetiers() {
		return new ArrayList<Metier>(MetiersList.values());

	}

	@Override
	public Metier getMetier(Integer idMetier) {
		
		return MetiersList.get(idMetier);
	}

	@Override
	public Metier addMetier(Metier Metier) {
		Integer idMetier = MetiersList.lastKey() + 1;
		Metier.setIdMetier(idMetier);
		MetiersList.put(idMetier, Metier);
		return Metier;
	}

	@Override
	public Metier deleteMetier(Integer idMetier) {
		// TODO Auto-generated method stub
		return null;
	}
}
