"""
Modelio Jython Script to duplicate Fog_Node_1 class 10 times (Fog_Node_1 through Fog_Node_10)
Select Fog_Node_1 and run this script
"""

from org.modelio.metamodel.uml.statik import Class

if elt is None:
    print("Error: No element selected. Please select Fog_Node_1 class.")
elif not isinstance(elt, Class):
    print("Error: Selected element is not a Class. Please select Fog_Node_1 class.")
else:
    original_node = elt
    parent_package = original_node.getOwner()
    
    print("Original node: " + original_node.getName())
    
    # Start transaction
    transaction = session.createTransaction("Duplicate Fog Nodes with Properties")
    
    try:
        # Create Fog_Node_2 through Fog_Node_10
        for i in range(2, 11):
            # Create new class
            new_node = session.getModel().createElement("Standard.Class")
            new_node.setName("Fog_Node_" + str(i))
            parent_package.getOwnedElement().add(new_node)
            
            # Copy stereotypes
            for stereotype in original_node.getExtension():
                new_node.getExtension().add(stereotype)
            
            # Copy attributes
            for attribute in original_node.getOwnedAttribute():
                new_attribute = session.getModel().createElement("Standard.Attribute")
                new_attribute.setName(attribute.getName())
                new_attribute.setValue(attribute.getValue())
                if attribute.getType() is not None:
                    new_attribute.setType(attribute.getType())
                new_attribute.setVisibility(attribute.getVisibility())
                
                # Copy stereotypes of the attribute
                for attr_stereotype in attribute.getExtension():
                    new_attribute.getExtension().add(attr_stereotype)
                
                new_node.getOwnedAttribute().add(new_attribute)
            
            # Copy PropertyTables (layer and type properties)
            try:
                original_properties = original_node.getProperties()
                for prop in original_properties:
                    print("  Copying property: " + str(type(prop).__name__))
                    
                    # TypedPropertyTable has getType() instead of getDefinition()
                    prop_type = None
                    if hasattr(prop, 'getType'):
                        prop_type = prop.getType()
                    elif hasattr(prop, 'getDefinition'):
                        prop_type = prop.getDefinition()
                    
                    if prop_type is None:
                        print("    - SKIP: Property has no type/definition")
                        continue
                    
                    print("    - Type/Definition: " + str(prop_type))
                    
                    # Create PropertyTable
                    prop_metaclass = prop.getMClass().getName()
                    new_prop = session.getModel().createElement(prop_metaclass)
                    
                    # Set type/definition BEFORE attaching to owner (required)
                    if hasattr(new_prop, 'setType'):
                        new_prop.setType(prop_type)
                        print("    - Set type")
                    elif hasattr(new_prop, 'setDefinition'):
                        new_prop.setDefinition(prop_type)
                        print("    - Set definition")
                    
                    # Copy basic attributes
                    if hasattr(prop, 'getName') and prop.getName():
                        new_prop.setName(prop.getName())
                    
                    # Copy content - could be a Map or a String
                    if hasattr(prop, 'getContent'):
                        try:
                            original_content = prop.getContent()
                            if original_content is not None:
                                # Check if it's a Map (has keySet) or a String
                                if hasattr(original_content, 'keySet'):
                                    # It's a Map
                                    new_content = new_prop.getContent()
                                    for key in original_content.keySet():
                                        value = original_content.get(key)
                                        new_content.put(key, value)
                                    print("    - Copied content map with " + str(original_content.size()) + " entries")
                                else:
                                    # It's a String - update the value for fog layer
                                    if hasattr(new_prop, 'setContent'):
                                        # Replace "edge" with "fog" in content if needed
                                        content_str = str(original_content)
                                        if "edge" in content_str.lower():
                                            content_str = content_str.replace("edge", "fog").replace("Edge", "Fog").replace("EDGE", "FOG")
                                        if "EdgeCompute" in content_str:
                                            content_str = content_str.replace("EdgeCompute", "FogCompute")
                                        new_prop.setContent(content_str)
                                        print("    - Copied content string: " + content_str)
                                    else:
                                        print("    - Content is: " + str(type(original_content)) + " = " + str(original_content))
                        except Exception as e:
                            print("    - Content copy error: " + str(e))
                    
                    # Attach to new_node AFTER setting all properties
                    new_node.getProperties().add(new_prop)
                    print("    - Attached property to node")
                            
            except Exception as e:
                print("  Properties copy error: " + str(e))
            
            # Copy tagged values
            for tag in original_node.getTag():
                new_tag = session.getModel().createElement("Infrastructure.TaggedValue")
                new_tag.setDefinition(tag.getDefinition())
                new_node.getTag().add(new_tag)
                
                for param in tag.getActual():
                    new_param = session.getModel().createElement("Infrastructure.TagParameter")
                    new_param.setValue(param.getValue())
                    new_tag.getActual().add(new_param)
            
            print("Created: " + new_node.getName())
        
        transaction.commit()
        print("\nSuccess! Created 9 Fog node copies (Fog_Node_2 through Fog_Node_10).")
        
    except Exception as e:
        transaction.rollback()
        print("Error: " + str(e))
        import traceback
        traceback.print_exc()
