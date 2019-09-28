package ua.itea.controllers;

import ua.itea.dao.UserDAO;
import ua.itea.dao.DAOFactory;
import ua.itea.models.User;
import ua.itea.utils.Hash;

public class LoginController {

	public LoginController() {
	}

	public Object getPrincipal(String login, String password) {
		DAOFactory factory = DAOFactory.getDAOFactory(1);
		UserDAO userDAO = factory.getUserDAO();
		User user = userDAO.getUserByLogin(login);
		if (user != null) {
			if (user.getPassword().equals(Hash.hash(password))) {
				return user;
			}
		}
		return null;
	}
}
