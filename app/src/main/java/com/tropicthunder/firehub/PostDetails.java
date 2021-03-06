package com.tropicthunder.firehub;

/**
 * Created by Bryan Lee on 4/6/2016.
 */
public class PostDetails {
    private String title, category, name, rating, coursePicture, teacherPicture, uid, description, venue, time, date, key;

    private String[] participants;

    public PostDetails(String title, String category, String name, String rating, String coursePicture, String teacherPicture, String uid, String description, String venue, String time, String date, String[] participants) {
        this.title = title;
        this.category = category;
        this.name = name;
        this.rating = rating;
        this.coursePicture = coursePicture;
        this.teacherPicture = teacherPicture;
        this.uid = uid;
        this.description = description;
        this.venue = venue;
        this.time = time;
        this.date = date;
        this.participants = participants;
    }

    public PostDetails() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getParticipants() {
        return participants;
    }

    public void setParticipants(String[] participants) {
        this.participants = participants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getUid() {
        return uid;
    }

    public String getCategory() {
        return category;
    }

    public String getCoursePicture() {
        return coursePicture;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getTeacherPicture() {
        return teacherPicture;
    }
}
