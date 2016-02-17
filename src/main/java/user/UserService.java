package user;

import user.repository.UserRepository;

/**
 * Created by Arne on 11/02/2016.
 */
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository repository){
        userRepository=repository;
    }



}
