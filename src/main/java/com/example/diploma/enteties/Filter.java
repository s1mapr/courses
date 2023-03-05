package com.example.diploma.enteties;

import java.util.List;

public class Filter {

    private String title;
    private List<String> complexity;
    private List<String> numberOfStud;
    private List<String> author;
    private Double minPrice;
    private Double maxPrice;


    public List<String> getComplexity() {
        return complexity;
    }

    public void setComplexity(List<String> complexity) {
        this.complexity = complexity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getNumberOfStud() {
        return numberOfStud;
    }

    public void setNumberOfStud(List<String> numberOfStud) {
        this.numberOfStud = numberOfStud;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
