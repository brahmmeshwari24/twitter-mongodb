import com.mongodb.*;

import java.util.Date;

/**
 * Created by sreenivasanattam on 9/29/16.
 */
public class TweetUpdate {


    public static void main(String[] args) {

        try {

            // Connect to mongodb
            MongoClient mongo = new MongoClient("localhost", 27017);

            // get database
            // if database doesn't exists, mongodb will create it for you
            DB db = mongo.getDB("test");

            // get collection
            // if collection doesn't exists, mongodb will create it for you
            DBCollection collection = db.getCollection("person");



            // get count
            System.out.println("All Persons: "+collection.getCount());
            //------------------------------------
            // get all document
            DBCursor cursor = collection.find();
            try {
                while(cursor.hasNext()) {
                    System.out.println(cursor.next());
                }
            } finally {
                cursor.close();
            }
            //------------------------------------

            // get documents by query
            BasicDBObject query = new BasicDBObject("age", new BasicDBObject("$gt", 40));

            cursor = collection.find(query);
            System.out.println("Person with age > 40 --> "+cursor.count());


            /**** Update ****/
            //update documents found by query "age > 30" with udpateObj "age = 20"
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("age", 20);


            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);

            collection.update(query, updateObj,false,true);

            /**** Find and display ****/
            cursor = collection.find(query);
            System.out.println("Person with age > 40 after update --> "+cursor.count());


            //get all again
            cursor = collection.find();
            try {
                while(cursor.hasNext()) {
                    System.out.println(cursor.next());
                }
            } finally {
                cursor.close();
            }

            /**** Done ****/
            System.out.println("Done");

        } catch (MongoException e) {
            e.printStackTrace();
        }

    }



}
