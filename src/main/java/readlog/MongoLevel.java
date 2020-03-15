package readlog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.bson.Document;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * @Description: 刷号账号最后的等级
 * @Author: shenpeng
 * @Date: 2019/1/2
 */
public class MongoLevel {

    private static final String IP = "10.2.145.23";

    private static final int PORT = 34004;

    private static final String DB = "kos_201";

    private static final String path = "/Users/playcrab/Desktop/log/role/uid-level.log";

    public static void readLevelsByUids(Set<String> uids) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(path));

        MongoClient mongoClient = new MongoClient(IP, PORT);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(DB);

        BasicDBList dbList = new BasicDBList();
        for (String uid : uids) {
            dbList.add(Integer.valueOf(uid));
        }

        BasicDBObject condition = new BasicDBObject("uid", new BasicDBObject("$in", dbList));

        BasicDBObject fields = new BasicDBObject("level", 1).append("_id", 0).append("uid", 1);

        MongoCollection<Document> collection = mongoDatabase.getCollection("players");

        FindIterable<Document> iterable = collection.find(condition).projection(fields);

        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            bw.write(cursor.next().toJson() + "\n");
            bw.flush();
        }

        bw.close();
    }

}
