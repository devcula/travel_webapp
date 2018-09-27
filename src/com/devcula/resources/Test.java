package com.devcula.resources;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Test {
	 private final long id;
     private String summary;
     private String description;
     private Status status;
     private int priority;

     public enum Status {
             CREATED, ASSIGNED, CANCELED, COMPLETED
     }

     public Test(long id, String summary, String description, Status status, int priority) {
             this.id = id;
             this.summary = summary;
             this.description = description;
             this.status = status;
             this.priority = priority;
     }

     public long getId() {
             return id;
     }

     public String getSummary() {
             return summary;
     }

     public void setSummary(String summary) {
             this.summary = summary;
     }

     public String getDescription() {
             return description;
     }

     public void setDescription(String description) {
             this.description = description;
     }

     public Status getStatus() {
             return status;
     }

     public void setStatus(Status status) {
             this.status = status;
     }

     public int getPriority() {
             return priority;
     }

     public void setPriority(int priority) {
             this.priority = priority;
     }

     @Override
     public String toString() {
             return "Task [id=" + id + ", summary=" + summary + ", description=" + description + ", status=" + status
                             + ", priority=" + priority + "]";
     }
     
     public static void main(String[] args) {
    	 List<Test> list = new ArrayList<Test>();
         for (int i = 0; i < 20; i++) {
                 list.add(new Test(i, "Test1", "Test2", Test.Status.ASSIGNED, 10));
         }
         Gson gson = new Gson();
         Type type = new TypeToken<List<Test>>() {}.getType();
         String json = gson.toJson(list, type);
         System.out.println(json);
         List<Test> fromJson = gson.fromJson(json, type);

         for (Test test : fromJson) {
                 System.out.println(test);
         }
	}
}
