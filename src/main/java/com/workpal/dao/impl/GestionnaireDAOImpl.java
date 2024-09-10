package com.workpal.dao.impl;

import com.workpal.dao.interfaces.GestionnareDAO;
import com.workpal.models.Gestionnaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionnaireDAOImpl implements GestionnareDAO {

private List<Gestionnaire> gestionnaires = new ArrayList<>();

@Override

    public void save(Gestionnaire gestionnaire){
    gestionnaires.add(gestionnaire);
    System.out.println("Gestionnare enregistré" + gestionnaire.getName() );

}

@Override
    public Optional <Gestionnaire> findByEmail(String email){
    return gestionnaires.stream().filter(e->e.getEmail().equals(email)).findFirst();


}

}
