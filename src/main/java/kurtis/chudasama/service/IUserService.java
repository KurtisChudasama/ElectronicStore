package kurtis.chudasama.service;

import kurtis.chudasama.entity.User;

public interface IUserService {

    User findUserById(int id);

    User findUserByUsername(String username);

    void saveCustomer(User user);

    void saveAdmin(User user);
}
