package fr.softeam.toscadesigner.handlers.propertypages.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.softeam.toscadesigner.handlers.propertypages.IPropertyContent;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Attribute;

@objid ("ef6dc775-f55a-43cc-a411-dd5145d9546b")
public abstract class ToscaElementPropertyPage<T extends Object> implements IPropertyContent {
    @objid ("f2408d6e-28e4-41e9-a429-b02f0992aa8b")
    private final String _ownerSeparator = ".";

    @objid ("316d7501-5f47-4c29-9a02-15892aa8f45c")
    protected final String _eltSeparator = "; ";

    @objid ("907d654e-ee98-448e-b8dd-59963282abdf")
    protected final String _add = "Add ";

    @objid ("19fc213c-8d2d-4f9c-b497-f0cd006b48bb")
    protected final String _remove = "Remove ";

    @objid ("bb1c73de-caed-471e-b466-f86b73527224")
    protected final String _removeAll = "Remove all";

    @objid ("2b755914-7851-4b12-bf0c-82725ef9f44b")
    protected T _element;

    /**
     * This method handles the changes of the given property, identified by its row
     * index, of a selected element to a new value.
     * @param row : the row of the changed property
     * @param value : the new value of the property
     */
    @objid ("72314037-8122-4b02-8aee-b28469838f14")
    @Override
    public void changeProperty(int row, String value) {
        
    }

    /**
     * This method handles the construction of the property table of a selected
     * element
     * @param table : the property table to fulfill
     */
    @objid ("54227650-a7c0-47ee-adf3-24ee2e755443")
    @Override
    public void update(IModulePropertyTable table) {
        
    }

    @objid ("d92fe027-688f-4ca9-a1fc-f6318e34dc38")
    public  ToscaElementPropertyPage(T elt) {
        this._element = elt;
    }

    /**
     * Compute the name of the Model Element
     * @param elt : the model Element
     * @return the computed name
     */
    @objid ("11d471f4-2112-4d05-9728-39bdea4cc663")
    protected String getToscaName(ModelElement elt) {
        return elt.getCompositionOwner().getName() + this._ownerSeparator + elt.getName();
    }

    @objid ("9b6983a8-f9e6-47cf-9024-a9bcfe81a86a")
    protected String[] getToscaNames(List<? extends ModelElement> elts) {
        List<String> result = new ArrayList<>();
        result.add(" ");
        for (ModelElement elt : elts) {
            result.add(getToscaName(elt));
        }
        return result.toArray(new String[result.size()]);
    }

    @objid ("f687be00-1013-4a75-90ba-53a326547aa8")
    protected ModelElement getModelElt(List<? extends ModelElement> elts, String toscaName) {
        String name = toscaName.replaceFirst(this._add, "").replaceFirst(this._remove, "");

        for (ModelElement elt : elts) {
            if (getToscaName(elt).equals(name)) {
                return elt;
            }
        }
        return null;
    }

    @objid ("7eb0eb0a-c5d0-468d-8493-7a14b0aa8427")
    protected ModelElement getElt(List<ModelElement> elts, String name) {
        for (ModelElement elt : elts) {
            if (getToscaName(elt).equals(name))
                return elt;
        }
        return null;
    }

    @objid ("2e87a984-cf8c-4ff1-a876-548f1c7ae6c5")
    protected String[] getAddRemove(List<ModelElement> allElt, List<? extends ModelElement> added) {
        // List of added Elts , remove Elts
        List<String> result = new ArrayList<>();
        result.add("");
        // result.add(this._removeAll);

        // List of already added Elt
        List<ModelElement> addElt = new ArrayList<>();
        for (ModelElement elt : added) {
            addElt.add(elt);
        }

        // Compute the list of Added and Removed Elements
        for (ModelElement elt : allElt) {
            if (addElt.contains(elt)) {
                result.add(this._remove + getToscaName(elt));
            } else {
                result.add(this._add + getToscaName(elt));
            }
        }
        return result.toArray(new String[result.size()]);
    }

    @objid ("ac7eb6c0-cb5e-4daf-b518-1c8946b4d8e8")
    protected String getToscaValue(List<? extends ModelElement> elts) {
        String result = "";
        if (elts!=null) {
        Iterator<? extends ModelElement> it = elts.iterator();

        if (it.hasNext()) {
            result += getToscaName(it.next());
        }

        while (it.hasNext()) {
            result += this._eltSeparator + getToscaName(it.next());
        }
        }
        return result;
    }

    @objid ("18768d2d-a3f7-4f9c-8ce4-758b26c49d07")
    protected ModelElement getToscalElt(List<? extends ModelElement> elts, String toscaName) {
        String name = toscaName.replaceFirst(this._add, "").replaceFirst(this._remove, "");

        for (ModelElement elt : elts) {
            if (getToscaName(elt).equals(name)) {
                return elt;
            }
        }
        return null;
    }

    @objid ("b044148f-9657-49ce-a031-7e8c2ad2b00e")
    protected String getNotNull(String value) {
        if (value == null)
            return "";
        return value;
    }

    @objid ("f6b10951-3518-48be-8d3a-5ad31f9f1ae2")
    public static <T> List<ModelElement> extractModelElements(List<T> members) {
        List<ModelElement> members_elt = new ArrayList<>();
        for (int i = 0; i < members.size(); i++) {
            members_elt.add((ModelElement) ( members.get(i)));
        }
        return members_elt;
    }

    @objid ("eebfff9a-bcf8-4708-9c20-5e058443c5e4")
    public static <T> List<Attribute> extractAttributes(List<T> members) {
        List<Attribute> members_elt = new ArrayList<>();
        for (int i = 0; i < members.size(); i++) {
            members_elt.add((Attribute) ( members.get(i)));
        }
        return members_elt;
    }

}
