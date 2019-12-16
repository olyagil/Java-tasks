package com.epam.task3.util;

public class PaginationUtil {


    public static Integer getPage(String currentPage, int maxResults, double numPage) {

        Integer page;

        try {
            page = Integer.parseInt(currentPage);
        } catch (NumberFormatException | NullPointerException nfe) {
            page = 1;
        }
        if (page == null || page < 1) {
            page = 1;
        }
        if (page > numPage) {
            page = (int) numPage;
        }
        return page;

    }
}
