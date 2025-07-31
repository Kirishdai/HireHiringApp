package org.krish.hirehiringapp.repository;

import org.krish.hirehiringapp.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, Integer> {
}
