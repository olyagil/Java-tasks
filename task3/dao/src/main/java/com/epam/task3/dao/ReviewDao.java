package com.epam.task3.dao;


import com.epam.task3.entity.Review;

import java.util.List;

public interface ReviewDao extends Dao<Review> {
    List<Review> readPaginatedList(int page, int maxResults);

    long getCountRows();
}
