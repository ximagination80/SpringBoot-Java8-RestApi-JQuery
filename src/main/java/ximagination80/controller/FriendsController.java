package ximagination80.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ximagination80.repository.Friend;
import ximagination80.repository.FriendRepository;

@RestController
public class FriendsController {

    @Autowired
    FriendRepository friendRepository;

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public Iterable<Friend> getFriends() {
        return friendRepository.findAll();
    }
}
