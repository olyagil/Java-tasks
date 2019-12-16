package com.epam.task3.service;


import com.epam.task3.entity.Review;

import java.util.List;

public interface ReviewService extends DataService<Review> {
    long getCountRows();

    List<Review> readPaginatedList(int page, int maxResults);
}
