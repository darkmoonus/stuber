package uet.vav.stuber.models;

import java.io.Serializable;

/**
 * Created by darkmoonus on 4/9/16.
 */
public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private int age;
    private String address;
    private String skills;
    private double rating;
    private double hireRate;
    private String experiences;
    private String projects;

    public User() {

    }

    public User(String id, String name, String email, int age, String address, String skills,
                double rating, double hireRate, String experiences, String projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
        this.skills = skills;
        this.rating = rating;
        this.hireRate = hireRate;
        this.experiences = experiences;
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", skills='" + skills + '\'' +
                ", rating=" + rating +
                ", hireRate=" + hireRate +
                ", experiences='" + experiences + '\'' +
                ", projects='" + projects + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (Double.compare(user.rating, rating) != 0) return false;
        if (Double.compare(user.hireRate, hireRate) != 0) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (skills != null ? !skills.equals(user.skills) : user.skills != null) return false;
        if (experiences != null ? !experiences.equals(user.experiences) : user.experiences != null)
            return false;
        return !(projects != null ? !projects.equals(user.projects) : user.projects != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(hireRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (experiences != null ? experiences.hashCode() : 0);
        result = 31 * result + (projects != null ? projects.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getHireRate() {
        return hireRate;
    }

    public void setHireRate(double hireRate) {
        this.hireRate = hireRate;
    }

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }
}
