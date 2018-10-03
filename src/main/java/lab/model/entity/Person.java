package lab.model.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Class Person. Entity class for person describing
 * @author Anton Makukhin
 */
@XmlType(propOrder = {"name", "address", "cash", "education"})
public class Person {
    private int id;
    private String name;
    private String address;
    private int cash;
    private String education;

    public Person() {
    }

    public Person(int id, String name, String address, int cash, String education) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cash = cash;
        this.education = education;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }

    public int getCash() {
        return cash;
    }

    @XmlElement
    public void setCash(int cash) {
        this.cash = cash;
    }

    public String getEducation() {
        return education;
    }

    @XmlElement
    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cash=" + cash +
                ", education='" + education + '\'' +
                '}';
    }
}
