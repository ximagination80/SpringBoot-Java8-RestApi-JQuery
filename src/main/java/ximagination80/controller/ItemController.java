package ximagination80.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ximagination80.repository.Friend;
import ximagination80.repository.FriendRepository;
import ximagination80.repository.Item;
import ximagination80.repository.ItemRepository;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    FriendRepository friendRepository;

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public Iterable<Item> getItems() {
        return itemRepository.findAll();
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity createItem(CreateItemBody body) {
        Friend friend = friendRepository.findOne(body.friendId);
        Item item = new Item();
        item.setName(body.name);
        item.setFriend(friend);
        return ResponseEntity.ok(itemRepository.save(item));
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity updateItem(@PathVariable(value = "id") Long id, UpdateItemBody body) {
        Item old = itemRepository.findOne(id);
        old.setName(body.name);
        old.setFriend(friendRepository.findOne(body.friendId));
        return ResponseEntity.ok(itemRepository.save(old));
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        itemRepository.delete(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    public static class CreateItemBody {
        private String name;
        private Long friendId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getFriendId() {
            return friendId;
        }

        public void setFriendId(Long friendId) {
            this.friendId = friendId;
        }
    }

    public static class UpdateItemBody {
        private String name;
        private Long friendId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getFriendId() {
            return friendId;
        }

        public void setFriendId(Long friendId) {
            this.friendId = friendId;
        }
    }
}
