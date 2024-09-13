package com.workpal;

import com.workpal.config.DatabaseConnection;
import com.workpal.dao.impl.OrganisateurDAOImpl;
import com.workpal.dao.impl.PersonneDAOImpl;
import com.workpal.dao.impl.WorkingSpaceDAOImpl;
import com.workpal.dao.interfaces.OrganisateurDAO;
import com.workpal.dao.interfaces.PersonneDAO;
import com.workpal.models.Membre;
import com.workpal.repository.impl.MembreRepositoryImpl;
import com.workpal.repository.impl.OrganisateurRepositoryImpl;
import com.workpal.repository.impl.PersonneRepositoryImpl;
import com.workpal.repository.impl.WorkingSpaceRepositoryImpl;
import com.workpal.repository.interfaces.OrganisateurRepository;
import com.workpal.repository.interfaces.PersonneRepository;
import com.workpal.services.Impl.OrganisateurServiceImpl;
import com.workpal.services.Impl.PersonneServiceImpl;
import com.workpal.services.Impl.WorkingSpaceServiceImpl;
import com.workpal.services.Interfaces.OrganisateurService;
import com.workpal.services.Interfaces.WorkingSpaceService;
import com.workpal.view.Auth;
import com.workpal.view.admin.OrganisateurView;
import com.workpal.view.auth.RegisterView;
import com.workpal.view.workingSpace.WorkingSpaceView;
import com.workpal.view.workpal.HomeView;
import java.sql.Connection;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {

        Auth auth = new Auth();
        auth.auth();


    }
}

