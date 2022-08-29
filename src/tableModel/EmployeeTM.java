/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

/**
 *
 * @author Madhusha Prasad
 */
public class EmployeeTM {
    String eid;
    String name;
    String address;
    String salary;

    public EmployeeTM(String eid, String name, String address, String salary) {
        this.eid = eid;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

   
}
