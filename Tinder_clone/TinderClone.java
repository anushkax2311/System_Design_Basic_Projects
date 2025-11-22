import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



interface NotificationObserver {
    void update(String message);
}

class UserNotificationObserver implements NotificationObserver {
    private String userId;

    public UserNotificationObserver(String id) {
        userId = id;
    }

    public void update(String message) {
        System.out.println("notification for user " + userId + " : " + message);
    }
}

class NotificationService {
    private Map<String, NotificationObserver> observers;

    private static NotificationService instance;

    private NotificationService() {
        observers = new HashMap<>();
    }

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;

    }

    public void registerObserver(String userId, NotificationObserver observer) {
        observers.put(userId, observer);
    }

    public void removeObserver(String userId) {
        observers.remove(userId);
    }

    public void notifyUser(String userId, String message) {
        if (observers.containsKey(userId)) {
            observers.get(userId).update(message);
        }
    }

    public void notifyAll(String message) {
        for (Map.Entry<String, NotificationObserver> pair : observers.entrySet()) {
            pair.getValue().update(message);
        }
    }

}

enum Gender {
    MALE,
    FEMALE,
    NON_BINARY,
    OTHER
}

class Location {
    private double Latitude;
    private double Longitude;

    public Location() {
        Latitude = 0.0;
        Longitude = 0.0;
    }

    public Location(double lat, double lon) {
        Latitude = lat;
        Longitude = lon;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLatitude(double lat) {
        Latitude = lat;
    }

    public void setLongitude(double lon) {
        Longitude = lon;

    }

    public double distanceInKm(Location other) {
        final double earthRadiusKm = 6371.0;
        double dLat = (other.Latitude - Latitude) * Math.PI / 180.0;
        double dLon = (other.Longitude - Longitude) * Math.PI / 180.0;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Latitude * Math.PI / 180.0) * Math.cos(other.Latitude * Math.PI / 180.0) * Math.sin(dLon / 2)
                        * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadiusKm * c;
    }
}

class Interest {
    private String name;
    private String category;

    public Interest() {
        name = "";
        category = "";

    }

    public Interest(String n, String c) {
        name = n;
        category = c;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}

class Preference {
    private List<Gender> interestedIn;
    private int minAge;
    private int maxAge;
    private double maxDistance;
    private List<String> interests;

    public Preference() {
        interestedIn = new ArrayList<>();
        interests = new ArrayList<>();
        minAge = 18;
        maxAge = 100;
        maxDistance = 100.0;

    }

    public void addGenderPreference(Gender gender) {
        interestedIn.add(gender);
    }

    public void removeGenderPreference(Gender gender) {
        interestedIn.remove(gender);
    }

    public void SetAgeRange(int min, int max) {
        minAge = min;
        maxAge = max;
    }

    public void setmaxDistance(double distance) {
        maxDistance = distance;
    }

    public void addInterest(String interest) {
        interests.add(interest);
    }

    public void removeInterest(String interest) {
        interests.remove(interest);
    }

    public boolean isInterestedInGender(Gender gender) {
        return interestedIn.contains(gender);
    }

    public boolean isAgeInRange(int age) {
        return age >= minAge && age <= maxAge;
    }

    public boolean isDistanceAcceptable(double distance) {
        return distance <= maxDistance;
    }

    public List<String> getInterests() {
        return interests;
    }

    public List<Gender> getInterestedGenders() {
        return interestedIn;

    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public double getMaxDistance() {
        return maxDistance;
    }
}

class Message {
    private String senderId;
    private String content;
    private long timestamp;

    public Message(String sender, String msg) {
        senderId = sender;
        content = msg;
        timestamp = System.currentTimeMillis();
    }

    public String getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getFormattedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(timestamp));
    }

}

class ChatRoom {
    private String id;
    private List<String> participantIds;
    private List<Message> messages;

    public ChatRoom(String roomId, String user1Id, String user2Id) {
        id = roomId;
        participantIds = new ArrayList<>();
        participantIds.add(user1Id);
        participantIds.add(user2Id);
        messages = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addMessage(String senderId, String content) {
        Message msg = new Message(senderId, content);
        messages.add(msg);
    }

    public boolean hasParticipant(String userId) {
        return participantIds.contains(userId);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<String> getParticipants() {
        return participantIds;
    }

    public void displayChat() {
        System.out.println("==== Chat Room: " + id + "=====");
        for (Message msg : messages) {
            System.out.println("[" + msg.getFormattedTime() + "]" + msg.getSenderId() + ": " + msg.getContent());

        }
        System.out.println("=====================");
    }

}

class UserProfile {
    private String name;
    private int age;
    private Gender gender;
    private String bio;
    private List<String> photos;
    private List<Interest> interests;
    private Location location;

    public UserProfile() {
        name = "";
        age = 0;
        gender = Gender.OTHER;
        photos = new ArrayList<>();
        interests = new ArrayList<>();
        location = new Location();
    }

    public void setName(String n) {
        name = n;
    }

    public void setAge(int a) {
        age = a;
    }

    public void setGender(Gender g) {
        gender = g;
    }

    public void setBio(String b) {
        bio = b;
    }

    public void addPhoto(String photoUrl) {
        photos.add(photoUrl);
    }

    public void removePhoto(String photoUrl) {
        photos.remove(photoUrl);
    }

    public void addInterest(String name, String category) {
        Interest interest = new Interest(name, category);
        interests.add(interest);
    }

    public void removeInterest(String name) {
        interests.removeIf(i -> i.getName().equals(name));
    }

    public void setLocation(Location loc) {
        location = loc;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getBio() {
        return bio;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public Location getLocation() {
        return location;
    }

    public void display() {
        System.out.println("=====Profile====");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: ");
        switch (gender) {
            case MALE:
                System.out.println("Male");
                break;
            case FEMALE:
                System.out.println("Female");
                break;
            case NON_BINARY:
                System.out.println("Non-Binary");
                break;
            case OTHER:
                System.out.println("Other");
                break;

        }
        System.out.println();
        System.out.println("Bio: " + bio);
        System.out.println("Photos: ");
        for (String photo : photos) {
            System.out.println(photo + ",");
        }

        System.out.println();
        System.out.println("Interests: ");
        for (Interest i : interests) {
            System.out.println(i.getName() + "(" + i.getCategory() + "), ");
        }
        System.out.println();
        System.out.println("Location: " + location.getLatitude() + ", " + location.getLongitude());
        System.out.println("=======================");
    }
}

enum SwipeAction {
    LEFT,
    RIGHT
}

class User {
    private String id;
    private UserProfile profile;
    private Preference preference;
    private Map<String, SwipeAction> swipeHistory;
    private NotificationObserver notificationObserver;

    public User(String userId) {
        id = userId;
        profile = new UserProfile();
        preference = new Preference();
        swipeHistory = new HashMap<>();
        notificationObserver = new UserNotificationObserver(userId);
        NotificationService.getInstance().registerObserver(userId, notificationObserver);
    }

    public String getId() {
        return id;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public Preference getPreference() {
        return preference;
    }

    public void swipe(String otherUserId, SwipeAction action) {
        swipeHistory.put(otherUserId, action);
    }

    public boolean hasLiked(String otherUserId) {
        return swipeHistory.containsKey(otherUserId) && swipeHistory.get(otherUserId) == SwipeAction.RIGHT;
    }

    public boolean hasDisliked(String otherUserId) {
        return swipeHistory.containsKey(otherUserId) && swipeHistory.get(otherUserId) == SwipeAction.LEFT;
    }

    public boolean hasInteractedWith(String otherUserId) {
        return swipeHistory.containsKey(otherUserId);
    }

    public void displayProfile() {
        profile.display();
    }
}

interface LocationStrategy {
    List<User> findNearbyUsers(Location location, double maxDistance, List<User> allUSers);
}

class BasicLocationStrategy implements LocationStrategy {
    public List<User> findNearbyUsers(Location location, double maxDistance, List<User> allUsers) {
        List<User> nearbyUsers = new ArrayList<>();
        for (User user : allUsers) {
            double distance = location.distanceInKm(user.getProfile().getLocation());
            if (distance <= maxDistance) {
                nearbyUsers.add(user);
            }
        }
        return nearbyUsers;
    }

}

class LocationService {
    private LocationStrategy strategy;
    private static LocationService instance;

    private LocationService() {
        strategy = new BasicLocationStrategy();
    }

    public static LocationService getInstance() {
        if (instance == null) {
            instance = new LocationService();
        }
        return instance;
    }

    public void setStrategy(LocationStrategy newStrategy) {
        strategy = newStrategy;
    }

    public List<User> findNearbyUsers(Location location, double maxDistance, List<User> users) {
        return strategy.findNearbyUsers(location, maxDistance, users);
    }
}

enum MatcherType {
    BASIC,
    INTERESTS_BASED,
    LOCATION_BASED
}

interface Matcher {
    double calculateMatchScore(User user1, User user2);
}

class BasicMatcher implements Matcher {
    public double calculateMatchScore(User user1, User user2) {
        boolean user1LikesUser2Gender = user1.getPreference().isInterestedInGender(user2.getProfile().getGender());
        boolean user2LikesUser1Gender = user2.getPreference().isInterestedInGender(user1.getProfile().getGender());

        if (!user1LikesUser2Gender || !user2LikesUser1Gender) {
            return 0.0;
        }

        boolean user1LikesUser2Age = user1.getPreference().isAgeInRange(user2.getProfile().getAge());
        boolean user2LikesUser1Age = user2.getPreference().isAgeInRange(user1.getProfile().getAge());

        if (!user2LikesUser1Age || !user1LikesUser2Age) {
            return 0.0;
        }

        double distance = user1.getProfile().getLocation().distanceInKm(user2.getProfile().getLocation());
        boolean user1LikesUser2Distance = user1.getPreference().isDistanceAcceptable(distance);
        boolean user2LikesUser1Distance = user2.getPreference().isDistanceAcceptable(distance);

        if (!user2LikesUser1Distance || !user1LikesUser2Distance) {
            return 0.0;
        }
        return 0.5;
    }
}

class InterestsBasedMatcher implements Matcher {
    public double calculateMatchScore(User user1, User user2) {
        BasicMatcher basicMatcher = new BasicMatcher();
        double baseScore = basicMatcher.calculateMatchScore(user1, user2);
        if (baseScore == 0.0) {
            return 0.0;
        }

        List<String> user1InterestNames = new ArrayList<>();
        for (Interest interest : user1.getProfile().getInterests()) {
            user1InterestNames.add(interest.getName());
        }

        int sharedInterests = 0;
        for (Interest interest : user2.getProfile().getInterests()) {
            if (user1InterestNames.contains(interest.getName())) {
                sharedInterests++;
            }
        }

        double maxInterests = Math.max(user1.getProfile().getInterests().size(),
                user2.getProfile().getInterests().size());
        double interestScore = maxInterests > 0 ? 0.5 * ((double) sharedInterests / maxInterests) : 0.0;
        return baseScore + interestScore;
    }
}

class LocationBasedMatcher implements Matcher {
    public double calculateMatchScore(User user1, User user2) {
        InterestsBasedMatcher interestsMatcher = new InterestsBasedMatcher();
        double baseScore = interestsMatcher.calculateMatchScore(user1, user2);

        if (baseScore == 0.0) {
            return 0.0;
        }

        double distance = user1.getProfile().getLocation().distanceInKm(user2.getProfile().getLocation());
        double maxDistance = Math.min(user1.getPreference().getMaxDistance(), user2.getPreference().getMaxDistance());

        double proximityScore = maxDistance > 0 ? 0.2 * (1.0 - (distance / maxDistance)) : 0;
        return baseScore + proximityScore;
    }
}

class MatcherFactory {
    public static Matcher createMatcher(MatcherType type) {
        switch (type) {
            case BASIC:
                return new BasicMatcher();
            case INTERESTS_BASED:
                return new InterestsBasedMatcher();
            case LOCATION_BASED:
                return new LocationBasedMatcher();

            default:
                return new BasicMatcher();
        }
    }
}

class DatingApp {
    private List<User> users;
    private List<ChatRoom> chatRooms;
    private Matcher matcher;

    private static DatingApp instance;

    private DatingApp() {
        users = new ArrayList<>();
        chatRooms = new ArrayList<>();

        matcher = MatcherFactory.createMatcher(MatcherType.LOCATION_BASED);
    }

    public static DatingApp getInstance() {
        if (instance == null) {
            instance = new DatingApp();
        }
        return instance;
    }

    public void setMatcher(MatcherType type) {
        matcher = MatcherFactory.createMatcher(type);
    }

    public User createUser(String userId) {
        User user = new User(userId);
        users.add(user);
        return user;

    }

    public User getUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }

        }
        return null;
    }

    public List<User> findNearbyUsers(String userId, double maxDistance) {
        User user = getUserById(userId);
        if (user == null) {
            return new ArrayList<>();
        }

        List<User> nearByUsers = LocationService.getInstance().findNearbyUsers(user.getProfile().getLocation(),
                maxDistance, users);

        nearByUsers.remove(user);

        List<User> filteredUsers = new ArrayList<>();
        for (User otherUser : nearByUsers) {
            if (!user.hasInteractedWith(otherUser.getId())) {
                double score = matcher.calculateMatchScore(user, otherUser);
                if (score > 0) {
                    filteredUsers.add(otherUser);
                }

            }
        }

        return filteredUsers;

    }

    public boolean swipe(String userId, String targetUserId, SwipeAction action) {
        User user = getUserById(userId);
        User targetUser = getUserById((targetUserId));

        if (user == null || targetUser == null) {
            System.out.println("User not found.");
            return false;
        }
        user.swipe(targetUserId, action);

        if (action == SwipeAction.RIGHT && targetUser.hasLiked(userId)) {
            String chatRoomId = userId + "_" + targetUserId;
            ChatRoom chatRoom = new ChatRoom(chatRoomId, userId, targetUserId);
            chatRooms.add(chatRoom);

            NotificationService.getInstance().notifyUser(userId,
                    "You have a new match with " + targetUser.getProfile().getName() + "!");
            NotificationService.getInstance().notifyUser((targetUserId),
                    "You have a new match with " + user.getProfile().getName() + "!");
            return true;
        }
        return false;

    }

    public ChatRoom getChatRoom(String user1Id, String user2Id) {
        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.hasParticipant(user1Id) && chatRoom.hasParticipant(user2Id)) {
                return chatRoom;
            }

        }
        return null;
    }

    public void sendMessage(String senderId, String receiverId, String content) {
        ChatRoom chatRoom = getChatRoom(senderId, receiverId);
        if (chatRoom == null) {
            System.out.println("No chat room found between these users.");
            return;
        }
        chatRoom.addMessage(senderId, content);
        NotificationService.getInstance().notifyUser(receiverId,
                "New Message from" + getUserById(senderId).getProfile().getName());
    }

    public void displayUser(String userId) {
        User user = getUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        user.displayProfile();
    }

    public void displayChatRoom(String user1Id, String user2Id) {
        ChatRoom chatRoom = getChatRoom(user1Id, user2Id);
        if (chatRoom == null) {
            System.out.println("No chat room found between these users.");
            return;
        }
        chatRoom.displayChat();
    }
}

public class TinderClone {
    public static void main(String[] args) {
        DatingApp app = DatingApp.getInstance();

        User user1 = app.createUser("user1");
        User user2 = app.createUser("user2");

        UserProfile profile1 = user1.getProfile();
        profile1.setName("Rohan");
        profile1.setAge(28);
        profile1.setGender(Gender.MALE);
        profile1.setBio("I am a software developer");
        profile1.addPhoto("rohan_Photo.jpg");
        profile1.addInterest("Coding", "Programming");
        profile1.addInterest("Travel", "Lifestyle");
        profile1.addInterest("Music", "Entertainment");

        Preference pref1 = user1.getPreference();
        pref1.addGenderPreference(Gender.FEMALE);
        pref1.addInterest("Coding");
        pref1.addInterest("Travel");
        pref1.SetAgeRange(25, 30);
        pref1.setmaxDistance(10.0);

        Location location1 = new Location();
        location1.setLatitude(1.01);
        location1.setLongitude(1.02);
        profile1.setLocation(location1);

        UserProfile profile2 = user2.getProfile();
        profile2.setName("Neha");
        profile2.setAge(27);
        profile2.setGender(Gender.FEMALE);
        profile2.setBio("Art teacher who loves painting and traveling.");
        profile2.addPhoto("neha_photo1.jpg");
        profile2.addInterest("Painting", "Art");
        profile2.addInterest("Travel", "Lifestyle");
        profile2.addInterest("Music", "Entertainment");

        Preference pref2 = user2.getPreference();
        pref2.addGenderPreference(Gender.MALE);
        pref2.SetAgeRange(27, 30);
        pref2.setmaxDistance(15.0);
        pref2.addInterest("Coding");
        pref2.addInterest("Movies");

        Location location2 = new Location();
        location2.setLatitude(1.03);
        location2.setLongitude(1.04);
        profile2.setLocation(location2);

        System.out.println("----- User Profiles -----");

        app.displayUser("user1");
        app.displayUser("user2");

        System.out.println("\n--- Nearby Users for user1 (within km) ---");
        List<User> nearbyUsers = app.findNearbyUsers("user1", 5.0);
        System.out.println("Found" + nearbyUsers.size() + "nearby users");
        for (User user : nearbyUsers) {
            System.out.println("- " + user.getProfile().getName() + "(" + user.getId() + ")");

        }

        System.out.println("\n--- Swipe Actions ---");
        System.out.println("User1 swipes right on user2");
        app.swipe("user1", "user2", SwipeAction.RIGHT);
        System.out.println("User2 swipes right on User1");
        app.swipe("user2", "user1", SwipeAction.RIGHT);

        System.out.println("\n--- ChatRoom ---");
        app.sendMessage("user1", "user2", "Hello neha!");
        app.sendMessage("user2", "user1", "Hi Rohan");

        app.displayChatRoom("user1", "user2");
    }
}