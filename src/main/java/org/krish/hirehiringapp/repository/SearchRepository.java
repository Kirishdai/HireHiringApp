package org.krish.hirehiringapp.repository;

import org.krish.hirehiringapp.model.Post;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String text);
}
