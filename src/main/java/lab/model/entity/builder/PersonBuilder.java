package lab.model.entity.builder;

import lab.model.entity.Person;

public class PersonBuilder {
    private int id;
    private String name;
    private String address;
    private int cash;
    private String education;

    public PersonBuilder buildId(int id){
        this.id = id;
        return this;
    }

    public PersonBuilder buildName(String name){
        this.name = name;
        return this;
    }
    public PersonBuilder buildAddress(String address){
        this.address = address;
        return this;
    }

    public PersonBuilder buildCash(int cash){
        this.cash = cash;
        return this;
    }

    public PersonBuilder buildEducation(String education){
        this.education = education;
        return this;
    }

    public Person build(){
        Person person = new Person();
        person.setId(this.id);
        person.setName(this.name);
        person.setAddress(this.address);
        person.setCash(this.cash);
        person.setEducation(this.education);
        return person;
    }
}
