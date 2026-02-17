"""
Modelio Jython Script to remove duplicate AssociationEnd elements.
Duplicates are identified per owner Class by (target, name).
"""

from org.modelio.metamodel.uml.statik import Class

if elt is None or not isinstance(elt, Class):
    print("Error: Please select any node to identify the package.")
else:
    parent_package = elt.getOwner()

    print("Removing duplicate AssociationEnd elements in package: " + parent_package.getName())
    print("=" * 60)

    # Collect classes
    classes = []
    for element in parent_package.getOwnedElement():
        if isinstance(element, Class):
            classes.append(element)

    print("Found " + str(len(classes)) + " classes to process")

    transaction = session.createTransaction("Remove Duplicate Association Ends")

    try:
        removed_count = 0
        kept_count = 0

        for cls in classes:
            owned_ends = cls.getOwnedEnd()
            seen = set()
            to_delete = []

            for end in owned_ends:
                # Identify target
                target = end.getTarget() if hasattr(end, 'getTarget') else None
                target_name = target.getName() if target else "<None>"

                # Identify name
                end_name = end.getName()
                if end_name is None:
                    end_name = ""

                key = (target_name, end_name)

                if key in seen:
                    to_delete.append(end)
                else:
                    seen.add(key)
                    kept_count += 1

            # Delete duplicates
            for end in to_delete:
                end.delete()
                removed_count += 1

        transaction.commit()

        print("\n" + "=" * 60)
        print("SUMMARY")
        print("  - AssociationEnd kept: " + str(kept_count))
        print("  - AssociationEnd removed: " + str(removed_count))
        print("\nSuccess! Duplicate AssociationEnd elements removed.")

    except Exception as e:
        transaction.rollback()
        print("Error: " + str(e))
        import traceback
        traceback.print_exc()
