"""
Modelio Jython Script to name all AssociationEnd elements with meaningful names
Process OwnedEnd on each Class node
"""

from org.modelio.metamodel.uml.statik import Class, Association, AssociationEnd

if elt is None or not isinstance(elt, Class):
    print("Error: Please select any node to identify the package.")
else:
    parent_package = elt.getOwner()
    
    print("Processing AssociationEnd names in package: " + parent_package.getName())
    print("=" * 60)
    
    # Find all classes in the package
    classes = []
    for element in parent_package.getOwnedElement():
        if isinstance(element, Class):
            classes.append(element)
    
    print("Found " + str(len(classes)) + " classes to process")
    
    # Start transaction
    transaction = session.createTransaction("Name Association Ends")
    
    try:
        unnamed_count = 0
        named_count = 0
        
        print("Processing " + str(len(classes)) + " classes and their AssociationEnd elements...")
        print("Naming strategy: outgoing='to_<target>', incoming='from_<source>'")
        print()
        print("PROCESSING ALL OWNED ENDS:")
        
        for cls in classes:
            owned_ends = cls.getOwnedEnd()
            
            for i, end in enumerate(owned_ends):
                current_name = end.getName()
                
                # Only process unnamed ends
                if not current_name or current_name.strip() == "":
                    source = end.getSource() if hasattr(end, 'getSource') else None
                    target = end.getTarget() if hasattr(end, 'getTarget') else None
                    owner = end.getOwner() if hasattr(end, 'getOwner') else None
                    
                    if source and target:
                        source_name = source.getName()
                        target_name = target.getName()
                        
                        # Determine if this is an outgoing or incoming end
                        if owner == source:
                            # This is an outgoing end from source
                            new_name = "to_" + target_name
                        else:
                            # This is an incoming end to target
                            new_name = "from_" + source_name
                        
                        end.setName(new_name)
                        unnamed_count += 1
        
        transaction.commit()
        
        print("\n" + "=" * 60)
        print("SUMMARY")
        print("  - Unnamed AssociationEnd elements named: " + str(unnamed_count))
        print("  - Total processed: " + str(unnamed_count))
        print("\nSuccess! All unnamed AssociationEnd elements have been named.")
        
    except Exception as e:
        transaction.rollback()
        print("Error: " + str(e))
        import traceback
        traceback.print_exc()
