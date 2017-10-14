/**
 * Created by ankit on 2/10/17.
 */

package com.example.ankit.insticable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class instistudent implements Serializable {

    private String name;
    private String Hostel;
    private String Department;
    private List<String> interests;
    private String type;
    private int count;
    private String Degree;

    public instistudent() {
        count=0;
        this.interests=new ArrayList<>();
    }

    public instistudent(String name, String Hostel, String Department,List<String> interests,String Degree) {
        this.name = name;
        this.Hostel= Hostel;
        this.Department=Department;
        this.interests=interests;
        this.Degree=Degree;
    }

    public String getHostel() {
        return Hostel;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public void setHostel(String Hostel) {
        this.Hostel = Hostel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public List<String> getinterests() {
        return interests;
    }

    public void setinterests(List<String> interests) {
        this.interests = interests;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }

    public int getcount(){return count;}

    public void setCount(int count){this.count=count;}

}

