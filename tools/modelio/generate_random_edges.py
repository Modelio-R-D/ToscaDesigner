"""
Modelio Jython Script to generate random edges (associations) between nodes
Rules:
- Edge nodes can connect to: other Edge nodes, Fog nodes
- Fog nodes can connect to: Edge nodes, other Fog nodes, Cloud nodes
- Cloud nodes can connect to: only Fog nodes
"""

from org.modelio.metamodel.uml.statik import Class, Association
import random

# Configuration
NUM_EDGE_TO_FOG = 50      # Each edge connects to 1 fog (as per yaml)
NUM_FOG_TO_CLOUD = 10     # Each fog connects to cloud (as per yaml)
NUM_EDGE_TO_EDGE = 30     # Random edge-to-edge connections
NUM_FOG_TO_FOG = 5        # Random fog-to-fog connections

# Find all nodes in the model
def find_nodes_by_pattern(session, package, pattern):
    """Find all classes matching the pattern in the package"""
    nodes = []
    for element in package.getOwnedElement():
        if isinstance(element, Class) and pattern in element.getName():
            nodes.append(element)
    return nodes

if elt is None or not isinstance(elt, Class):
    print("Error: Please select any node (Edge_Node_1, Fog_Node_1, or Cloud_Node_1) to identify the package.")
else:
    parent_package = elt.getOwner()
    
    print("Scanning for nodes in package: " + parent_package.getName())
    
    # Find all nodes by layer
    edge_nodes = []
    fog_nodes = []
    cloud_nodes = []
    
    for element in parent_package.getOwnedElement():
        if isinstance(element, Class):
            name = element.getName()
            if name.startswith("Edge_Node_"):
                edge_nodes.append(element)
            elif name.startswith("Fog_Node_"):
                fog_nodes.append(element)
            elif name.startswith("Cloud_Node_"):
                cloud_nodes.append(element)
    
    print("Found:")
    print("  - " + str(len(edge_nodes)) + " Edge nodes")
    print("  - " + str(len(fog_nodes)) + " Fog nodes")
    print("  - " + str(len(cloud_nodes)) + " Cloud nodes")
    
    if len(edge_nodes) == 0 or len(fog_nodes) == 0 or len(cloud_nodes) == 0:
        print("Error: Not all node types found. Make sure Edge_Node_*, Fog_Node_*, and Cloud_Node_* exist.")
    else:
        # Start transaction
        transaction = session.createTransaction("Generate Random Edges")
        
        try:
            edge_count = 0
            
            # 1. Edge-to-Fog connections (as per yaml - each edge connects to a fog)
            print("\n=== Creating Edge-to-Fog connections ===")
            for edge_node in edge_nodes:
                fog_node = random.choice(fog_nodes)
                
                # Create association using createAssociation method
                assoc = session.getModel().createAssociation(edge_node, fog_node, edge_node.getName() + "_to_" + fog_node.getName())
                
                print("  Created: " + assoc.getName() + " (ID: " + str(assoc.getUuid()) + ")")
                print("    Owner: " + str(assoc.getCompositionOwner()))
                print("    Ends: " + str(len(assoc.getEnd())))
                for end in assoc.getEnd():
                    print("      End from " + str(end.getSource().getName() if end.getSource() else "None") + " to " + str(end.getTarget().getName() if end.getTarget() else "None"))
                
                edge_count += 1
            
            print("  Created " + str(edge_count) + " Edge-to-Fog connections")
            
            # 2. Fog-to-Cloud connections (all fog connect to cloud)
            print("\n=== Creating Fog-to-Cloud connections ===")
            fog_to_cloud_count = 0
            for fog_node in fog_nodes:
                for cloud_node in cloud_nodes:
                    # Create association using createAssociation method
                    assoc = session.getModel().createAssociation(fog_node, cloud_node, fog_node.getName() + "_to_" + cloud_node.getName())
                    
                    print("  Created: " + assoc.getName() + " (Type: " + str(assoc.getMClass().getName()) + ")")
                    print("    Ends count: " + str(len(assoc.getEnd())))
                    
                    fog_to_cloud_count += 1
            
            print("  Created " + str(fog_to_cloud_count) + " Fog-to-Cloud connections")
            
            # 3. Random Edge-to-Edge connections
            print("\n=== Creating random Edge-to-Edge connections ===")
            edge_to_edge_count = 0
            for i in range(NUM_EDGE_TO_EDGE):
                edge1 = random.choice(edge_nodes)
                edge2 = random.choice(edge_nodes)
                
                # Avoid self-loops
                if edge1 == edge2:
                    continue
                
                # Create association using createAssociation method
                assoc = session.getModel().createAssociation(edge1, edge2, edge1.getName() + "_to_" + edge2.getName())
                
                if edge_to_edge_count < 3:  # Only show first 3 for brevity
                    print("  Created: " + assoc.getName())
                
                edge_to_edge_count += 1
            
            print("  Created " + str(edge_to_edge_count) + " Edge-to-Edge connections")
            
            # 4. Random Fog-to-Fog connections
            print("\n=== Creating random Fog-to-Fog connections ===")
            fog_to_fog_count = 0
            for i in range(NUM_FOG_TO_FOG):
                fog1 = random.choice(fog_nodes)
                fog2 = random.choice(fog_nodes)
                
                # Avoid self-loops
                if fog1 == fog2:
                    continue
                
                # Create association using createAssociation method
                assoc = session.getModel().createAssociation(fog1, fog2, fog1.getName() + "_to_" + fog2.getName())
                
                print("  Created: " + assoc.getName())
                
                fog_to_fog_count += 1
            
            print("  Created " + str(fog_to_fog_count) + " Fog-to-Fog connections")
            
            # Summary
            total = edge_count + fog_to_cloud_count + edge_to_edge_count + fog_to_fog_count
            print("\n=== SUMMARY ===")
            print("Total edges created: " + str(total))
            print("  - Edge-to-Fog: " + str(edge_count))
            print("  - Fog-to-Cloud: " + str(fog_to_cloud_count))
            print("  - Edge-to-Edge: " + str(edge_to_edge_count))
            print("  - Fog-to-Fog: " + str(fog_to_fog_count))
            
            # Verify associations in model
            print("\n=== VERIFICATION ===")
            print("Checking first Edge_Node's associations:")
            test_node = edge_nodes[0]
            print("  Node: " + test_node.getName())
            print("  OwnedEnd count: " + str(len(test_node.getOwnedEnd())))
            print("  TargetingEnd count: " + str(len(test_node.getTargetingEnd())))
            
            # Print some associations from package
            print("\nAssociations in package:")
            assoc_in_package = 0
            for element in parent_package.getOwnedElement():
                if isinstance(element, Association):
                    if assoc_in_package < 5:  # Only show first 5
                        print("  - " + element.getName())
                    assoc_in_package += 1
            print("  Total associations in package: " + str(assoc_in_package))
            
            transaction.commit()
            print("\nSuccess! Random edges generated.")
            
        except Exception as e:
            transaction.rollback()
            print("Error: " + str(e))
            import traceback
            traceback.print_exc()
