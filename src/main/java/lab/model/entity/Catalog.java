package lab.model.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class Notebook. Entity class for root element storing
 * @author Anton Makukhin
 */
@XmlRootElement
public class Catalog {
    private Notebook notebook;

    public Catalog() {
        notebook = new Notebook();
    }

    public Catalog(Notebook notebook) {
        this.notebook = notebook;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    public Catalog getFilteredByBiggerCash(int cashMinBorder){
        Catalog filteredCatalog = new Catalog();
        Notebook filteredNotebook = new Notebook();
        filteredNotebook.setPersonList(notebook.filterPersonListByBiggerCash(cashMinBorder));
        filteredCatalog.setNotebook(filteredNotebook);
        return filteredCatalog;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "notebook=" + notebook +
                '}';
    }
}
