/**
 * Created by ankit on 2/10/17.
 */

package com.example.ankit.insticable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * represents the student's info as a class.
 */
public class instistudent implements Serializable {

    private String name;
    private String Hostel;
    private String Department;
    private List<String> interests;
    private String type;
    private int count;
    private String Degree;

    /**
     * Instantiates a new Instistudent.
     */
    public instistudent() {
        count=0;
        this.interests=new ArrayList<>();
    }

    /**
     * Instantiates a new Instistudent.
     *
     * @param name       Name of the student
     * @param Hostel     Hostel Number of the student
     * @param Department Department of the student
     * @param interests  Interests of the student
     * @param Degree     Degree of the student
     */
    public instistudent(String name, String Hostel, String Department,List<String> interests,String Degree) {
        this.name = name;
        this.Hostel= Hostel;
        this.Department=Department;
        this.interests=interests;
        this.Degree=Degree;
    }

    /**
     * Returns the hostel of the student instance.
     *
     * @return Hostel of the student
     */
    public String getHostel() {
        return Hostel;
    }

    /**
     * Returns the degree of the student instance.
     *
     * @return Degree of the student
     */
    public String getDegree() {
        return Degree;
    }

    /**
     * Sets degree of the student instance.
     *
     * @param degree degree of the student
     */
    public void setDegree(String degree) {
        Degree = degree;
    }

    /**
     * Sets hostel.
     *
     * @param Hostel the hostel
     */
    public void setHostel(String Hostel) {
        this.Hostel = Hostel;
    }

    /**
     * Returns the name of the student instance.
     *
     * @return Name of student
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the department of the student instance.
     *
     * @return the department.
     */
    public String getDepartment() {
        return Department;
    }

    /**
     * Sets department.
     *
     * @param Department the department
     */
    public void setDepartment(String Department) {
        this.Department = Department;
    }

    /**
     * Returns a list of interests the student instance.
     *
     * @return the interest's lists
     */
    public List<String> getinterests() {
        return interests;
    }

    /**
     * Sets  the student's interests as given.
     *
     * @param interests Interests given by the user
     */
    public void setinterests(List<String> interests) {
        this.interests = interests;
    }

    /**
     * Returns type(coordinator/student)
     *
     * @return
     */
    public String gettype() {
        return type;
    }

    /**
     * Sets the student type as given by user
     *
     * @param type the type
     */
    public void settype(String type) {
        this.type = type;
    }

    /**
     * returns count (if the user has submitted credentials or not).
     *
     * @return the int
     */
    public int getcount(){return count;}

    /**
     * Set count
     *
     * @param count the count
     */
    public void setCount(int count){this.count=count;}

}

