"""
Modelio Jython Script to delete an Association by name in the selected package.
"""

from org.modelio.metamodel.uml.statik import Class, Association

TARGET_NAME = "Edge_Node_1_to_Fog_Node_1"

if elt is None or not isinstance(elt, Class):
    print("Error: Please select any node to identify the package.")
else:
    parent_package = elt.getOwner()

    print("Deleting association named: " + TARGET_NAME)
    print("Package: " + parent_package.getName())

    transaction = session.createTransaction("Delete Association By Name")

    try:
        deleted = 0
        for element in parent_package.getOwnedElement():
            if isinstance(element, Association):
                if element.getName() == TARGET_NAME:
                    element.delete()
                    deleted += 1

        transaction.commit()

        if deleted == 0:
            print("No association found with that name.")
        else:
            print("Deleted " + str(deleted) + " association(s).")

    except Exception as e:
        transaction.rollback()
        print("Error: " + str(e))
        import traceback
        traceback.print_exc()
