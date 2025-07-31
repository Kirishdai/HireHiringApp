package org.krish.hirehiringapp.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.krish.hirehiringapp.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImplementation implements SearchRepository {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private MongoConverter mongoConverter;

    @Override
    public List<Post> findByText(String text) {
        List<Post> posts = new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("krish");
        MongoCollection<Document> collection = database.getCollection("JobPost");

        // split the text into terms, e.g. "developer engineer" → ["developer", "engineer"]
        List<String> queries = Arrays.asList(text.split("\\s+"));

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$search",
                        new Document("text",
                                new Document("query", queries)
                                        .append("path", Arrays.asList("profile", "desc", "techs")))),
                new Document("$sort", new Document("exp", 1L))
        ));

        result.forEach(document -> posts.add(mongoConverter.read(Post.class, document)));

        return posts;
    }
}