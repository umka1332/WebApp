package ua.itea;

public class Auth {

 public boolean getLogin(String login, String password) {
  //������� � ����, ������������
  if (login.equals("admin") && password.equals("123")) {
   return true;
  }
  return false;
 }
}