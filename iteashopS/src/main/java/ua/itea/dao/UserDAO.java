package ua.itea.dao;

import ua.itea.models.User;

public interface UserDAO {
	public User getUserByLogin(String login);
	public boolean saveUser(User user);
	public boolean updateUser(User user);
}
