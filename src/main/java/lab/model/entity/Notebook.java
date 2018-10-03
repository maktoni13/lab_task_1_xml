package lab.model.entity;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Notebook. Entity class for notebook persons list collecting
 * @author Anton Makukhin
 */
public class Notebook {
    List<Person> personList;

    public Notebook() {
        personList = new ArrayList<>();
    }

    @XmlElement(name = "person")
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    List<Person> filterPersonListByBiggerCash(int cashMinBorder){
        return getPersonList()
                .stream()
                .filter(person -> (person.getCash() >= cashMinBorder))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "personList=" + personList +
                '}';
    }
}
