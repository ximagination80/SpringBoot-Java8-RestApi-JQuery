package ximagination80;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ximagination80.repository.Friend;
import ximagination80.repository.FriendRepository;
import ximagination80.repository.Item;
import ximagination80.repository.ItemRepository;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class Application {

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Application has been started. Please open url:");
            System.out.println("http://localhost:8080/");

            Friend friend1 = friendRepository.save(createFriend("Friend 1"));
            Friend friend2 = friendRepository.save(createFriend("Friend 2"));
            Friend friend3 = friendRepository.save(createFriend("Friend 3"));
            Friend friend4 = friendRepository.save(createFriend("Friend 4"));
            Friend friend5 = friendRepository.save(createFriend("Friend 5"));

            itemRepository.save(createItem("Item 1", friend1));
            itemRepository.save(createItem("Item 2", friend2));
            itemRepository.save(createItem("Item 3", friend3));
            itemRepository.save(createItem("Item 4", null));
            itemRepository.save(createItem("Item 5", friend1));
            itemRepository.save(createItem("Item 6", null));
            itemRepository.save(createItem("Item 7", null));
            itemRepository.save(createItem("Item 8", friend4));
            itemRepository.save(createItem("Item 9", friend3));
            itemRepository.save(createItem("Item 10", null));
            itemRepository.save(createItem("Item 11", friend1));
            itemRepository.save(createItem("Item 12", friend3));
            itemRepository.save(createItem("Item 13", null));
            itemRepository.save(createItem("Item 14", friend1));
            itemRepository.save(createItem("Item 15", null));
            itemRepository.save(createItem("Item 16", friend3));
            itemRepository.save(createItem("Item 17", friend1));
            itemRepository.save(createItem("Item 18", friend5));
        };
    }


    private Item createItem(String name, Friend friend) {
        Item item = new Item();
        item.setName(name);
        item.setFriend(friend);
        return item;
    }

    private Friend createFriend(String name) {
        Friend friend = new Friend();
        friend.setName(name);
        return friend;
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Bye bye!");
    }

}
