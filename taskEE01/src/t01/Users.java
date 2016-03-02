package t01;

import java.util.ArrayList;

/**
 * Created by Wais on 02.03.2016.
 */
public  class Users {

    private static Users instance = new Users();

    public ArrayList<User> usersArray;
    public boolean authorized;

    private Users() {
        usersArray = new ArrayList<>();
        usersArray.add(new User(Role.ADMIN, "admin", "admin"));
        usersArray.add(new User(Role.MODERATOR, "moderator", "moderator"));
        usersArray.add(new User(Role.USER, "Ilya", "Ilya"));
        usersArray.add(new User(Role.USER, "user", "user"));
        authorized = false;
    }

    public static Users getInstance() {
        return instance;
    }

    public class User {

        private Role role;
        private String login;
        private String password;
        private boolean signin;

        public User(Role role, String login, String password) {
            this.role = role;
            this.login = login;
            this.password = password;
            this.signin = false;
        }

        public void SignIn() {
            signin = true;
        }

        public void SignOut() {
            signin = false;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}