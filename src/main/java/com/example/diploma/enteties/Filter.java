package com.example.diploma.enteties;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Filter {
    private String title;
    private List<String> complexity;
    private List<String> numberOfStud;
    private List<String> author;
    private Double minPrice;
    private Double maxPrice;
    private List<String> subject;
}
