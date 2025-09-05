package fr.softeam.toscadesigner.handlers.propertypages;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;

/**
 * <p>This interface defines the contract of all property pages</p>
 */
@objid ("f25b3273-075f-4c94-baaa-50934af8e7eb")
public interface IPropertyContent {
    /**
     * This method handles the changes of the given property, identified by its row
     * index, of a selected element to a new value.
     * @param row : the row of the changed property
     * @param value : the new value of the property
     */
    @objid ("b5050450-a43e-4679-839c-3af03a393b4b")
    void changeProperty(int row, String value);

    /**
     * This method handles the construction of the property table of a selected
     * element
     * @param table : the property table to fulfill
     */
    @objid ("ee4e41b7-0ffb-466e-8c19-e09c89d7149d")
    void update(IModulePropertyTable table);
}

