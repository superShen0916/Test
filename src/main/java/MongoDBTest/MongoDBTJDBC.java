package MongoDBTest;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBTJDBC {
    public static void main(String[] args) {
        try {
            // 连接到mongoDB服务
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            System.out.println("连接数据库成功");
            // 创建集合
            // mongoDatabase.createCollection("javaTest");
            // System.out.println("创建集合成功");

            // 获取集合
            MongoCollection<Document> collection = mongoDatabase.getCollection("mycoll");
            System.out.println("获取集合成功");

            // // 插入文档
            // // 1.创建文档 key-value格式
            // // 2.创建文档集合
            // // 3.插入集合
            // Document document = new Document("title",
            // "MongoDB").append("value", 2);
            // Document document2 = new Document("title",
            // "mongodb").append("value", 1);
            // Document document3 = new Document("title",
            // "mongodb").append("value", 3);
            // List<Document> list = new ArrayList<Document>();
            // list.add(document);
            // list.add(document2);
            // list.add(document3);
            // collection.insertMany(list);
            // System.out.println("文档插入成功");

            // // 更新文档 将value为1的替换为3
            // BasicDBObject qurey = new BasicDBObject();
            // qurey.put("value", 3);
            // Document newDocument = new Document();
            // newDocument.put("value", 1);
            // Document document = new Document("$set", newDocument);
            // collection.updateMany(qurey, document);
            // System.out.println("更新成功");

            // 删除文档
            Document document = new Document("value", 1);
            collection.deleteMany(document);

            // 检索所有文档
            // 1.获取迭代器FindIterable<Document>
            // 2.获取游标MongoCursor<Document>
            // 3.通过游标遍历检索出的文档的集合
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> cursor = findIterable.iterator();
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            // System.err.println(e.getClass().getName() + ": " +
            // e.getMessage());
            e.printStackTrace();
        }

    }
}
