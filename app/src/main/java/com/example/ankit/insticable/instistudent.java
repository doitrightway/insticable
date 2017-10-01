/**
 * Created by ankit on 2/10/17.
 */

package com.example.ankit.insticable;

import java.util.List;

public class instistudent {

    private String name;
    private int Hostel;
    private String Department;
    private List<String> interests;

    public instistudent() {
    }

    public instistudent(String name, int Hostel, String Department,List interests) {
        this.name = name;
        this.Hostel= Hostel;
        this.Department=Department;
        this.interests=interests;
    }

    public int getHostel() {
        return Hostel;
    }

    public void setText(int Hostel) {
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

    public void setName(List interests) {
        this.interests = interests;
    }
}

