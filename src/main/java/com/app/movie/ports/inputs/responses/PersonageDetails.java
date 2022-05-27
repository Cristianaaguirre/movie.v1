package com.app.movie.ports.inputs.responses;

import lombok.*;

import java.util.List;

@Builder
@Data
public class PersonageDetails {
   private Long id;
   private String image;
   private String name;
   private Integer age;
   private Integer weigth;
   private String history;
   private List<String> movies;
}
