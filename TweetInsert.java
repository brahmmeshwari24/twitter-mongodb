import com.mongodb.*;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by sreenivasanattam on 9/29/16.
 */
public class TweetInsert {

    static String array_names[] = {"John", "Tim", "Brit", "Robin", "Smith", "Lora", "Jennifer", "Lyla", "Victor", "Adam"};

    static String array_address[][] = {
            {"US", "FL", " Miami"},
            {"US", "FL", " Orlando"},
            {"US", "CA", "San Diego"},
            {"US", "FL", " Orlando"},
            {"US", "FL", " Orlando"},
            {"US", "NY", "New York"},
            {"US", "NY", "Buffalo"},
            {"US", "TX", " Houston"},
            {"US", "CA", "San Diego"},
            {"US", "TX", " Houston"}
    };
    public static void main(String[] args) {

        try

        {

            // Connect to mongodb
            MongoClient mongo = new MongoClient("localhost", 27017);

            // get database
            // if database doesn't exists, mongodb will create it for you
            DB db = mongo.getDB("test");

            // get collection
            // if collection doesn't exists, mongodb will create it for you
            DBCollection collection = db.getCollection("person");

            /**** Insert ****/
            // create a document to store key and value

            BasicDBObject document;


            String address[];


            for (int i = 0; i < array_names.length; i++) {
                document = new BasicDBObject();
                //value -> String
                document.append("name", array_names[i]);
                // value -> int
                document.append("age", (int) (Math.random() * 60));
                // value -> date
                document.append("join", new Date());
                // value -> array
                document.append("friends", pickFriends());

                address = pickAddress();
                // value --> document
                document.append("address", new BasicDBObject("country", address[0])
                        .append("state", address[1])
                        .append("city", address[2]));

                collection.insert(document);

            }

        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------------------------
    //These methods are just jused to build random data
    private static String[] pickFriends(){
        int numberOfFriends = (int) (Math.random()* 10);
        LinkedList<String> friends = new LinkedList<String>();
        int random = 0;
        while(friends.size() < numberOfFriends){
            random = (int) (Math.random()*10);
            if(!friends.contains(array_names[random]))
                friends.add(array_names[random]);

        }
        String a[] = {};
        return  friends.toArray(a);
    }
    private static String[] pickAddress(){
        int random = (int) (Math.random()*10);
        return array_address[random];
    }
}
