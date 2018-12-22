package db;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectDB {
    public static MongoDatabase mongoDatabase = null;

    public static MongoDatabase connectToMongoDB() {
        MongoClient mongoClient = new MongoClient();
        mongoDatabase = mongoClient.getDatabase("user");
        System.out.println("Database Connected");

        return mongoDatabase;
    }

    public static String insertIntoToMongoDB(User user){
        String profile = user.getName();
        MongoDatabase mongoDatabase = connectToMongoDB();
        MongoCollection<Document> collection = mongoDatabase.getCollection("profile");
        Document document = new Document().append("name",user.getName()).append("id", user.getId());
        collection.insertOne(document);
        return profile + " has been registered";
    }

//    public String insertIntoMongoDB(List<Student> student,String profileName){
//        MongoDatabase mongoDatabase = connectToMongoDB();
//        MongoCollection myCollection = mongoDatabase.getCollection(profileName);
//        boolean collectionExists = mongoDatabase.listCollectionNames()
//                .into(new ArrayList<String>()).contains(profileName);
//        if(collectionExists) {
//            myCollection.drop();
//        }
//        for(int i=0; i<student.size(); i++){
//            MongoCollection<Document> collection = mongoDatabase.getCollection(profileName);
//            Document document = new Document().append("firstName", student.get(i).getFirstName()).append("lastName",
//                    student.get(i).getLastName()).append("score",student.get(i).getScore()).append("id", student.get(i).getId());
//            collection.insertOne(document);
//        }
//        return  "Student has been registered";
//    }

    public static List<User> readUserProfileFromMongoDB(){
        List<User> list = new ArrayList<User>();
        User user = new User();
        MongoDatabase mongoDatabase = connectToMongoDB();
        MongoCollection<Document> collection = mongoDatabase.getCollection("profile");
        BasicDBObject basicDBObject = new BasicDBObject();
        FindIterable<Document> iterable = collection.find(basicDBObject);
        for(Document doc:iterable){
            String name = (String)doc.get("name");
            user = new User();
            user.setName(name);
            int id = (int) doc.get("id");
            user.setId(id);
            user = new User(name,id);
            list.add(user);
            user = null;
        }
        return list;
    }

//    public List<Student> readStudentListFromMongoDB(String profileName){
//        List<Student> list = new ArrayList<Student>();
//        Student student = new Student();
//        MongoDatabase mongoDatabase = connectToMongoDB();
//        MongoCollection<Document> collection = mongoDatabase.getCollection(profileName);
//        BasicDBObject basicDBObject = new BasicDBObject();
//        FindIterable<Document> iterable = collection.find(basicDBObject);
//        for(Document doc:iterable){
//            String firstName = (String)doc.get("firstName");
//            student.setFirstName(firstName);
//            String lastName = (String)doc.get("lastName");
//            student.setLastName(lastName);
//            String score = (String)doc.get("score");
//            student.setScore(score);
//            String id = (String) doc.get("id");
//            student.setId(id);
//            student = new Student(student.getFirstName(),student.getLastName(),student.getScore(),student.getId());
//            list.add(student);
//        }
//        return list;
//    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter user name: " );
        String userName = sc.nextLine();
        System.out.println("Please Enter user id: " );
        int userId = sc.nextInt();
        User userInput = new User(userName, userId);
        insertIntoToMongoDB(userInput);
        List<User> user = readUserProfileFromMongoDB();
        for(User person:user){
            System.out.println(person.getName()+ " "+ person.getId());
        }
    }

}
