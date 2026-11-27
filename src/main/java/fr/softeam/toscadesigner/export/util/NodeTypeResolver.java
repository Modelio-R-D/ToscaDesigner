package fr.softeam.toscadesigner.export.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.vcore.smkernel.mapi.MObject;

import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeTemplate;
import fr.softeam.toscadesigner.api.tosca.standard.class_.TNodeType;
import fr.softeam.toscadesigner.export.checker.NodeTypeChecker;
import fr.softeam.toscadesigner.export.checker.TopologyTemplateChecker;
import fr.softeam.toscadesigner.impl.ToscaDesignerModule;

/**
 * Resolves TOSCA node types by their qualified name ({@code <package>.<type>}, as exported), wherever they
 * live: in the project or in a deployed model component (e.g. the MyrtusNodeTypes RAMC).
 */
public final class NodeTypeResolver {

    private NodeTypeResolver() {
        // Utility class - no instances
    }

    /** Qualified TOSCA name of a node type, as written by TNodeType.hbs: owner name + "." + type name. */
    public static String qualifiedName(Class nodeType) {
        MObject owner = nodeType.getCompositionOwner();
        return owner != null ? owner.getName() + "." + nodeType.getName() : nodeType.getName();
    }

    /** Namespace part of a qualified name ("eu.myrtus.MyrtusCompute" -> "eu.myrtus"). */
    public static String namespaceOf(String qualifiedName) {
        int dot = qualifiedName.lastIndexOf('.');
        return dot > 0 ? qualifiedName.substring(0, dot) : "";
    }

    /** Local name part of a qualified name ("eu.myrtus.MyrtusCompute" -> "MyrtusCompute"). */
    public static String localNameOf(String qualifiedName) {
        return qualifiedName.substring(qualifiedName.lastIndexOf('.') + 1);
    }

    /** True for TOSCA normative types, which never need to be packaged or imported. */
    public static boolean isNormative(String qualifiedName) {
        return qualifiedName == null || qualifiedName.trim().isEmpty() || qualifiedName.startsWith("tosca.");
    }

    /** All node types visible in the session (project and model components), by qualified name. */
    public static Map<String, Class> allNodeTypes() {
        IModelingSession session = ToscaDesignerModule.getInstance().getModuleContext().getModelingSession();
        NodeTypeChecker checker = new NodeTypeChecker();
        Map<String, Class> result = new HashMap<>();
        for (Class c : session.findByClass(Class.class)) {
            if (checker.isTypeOf(c)) {
                result.putIfAbsent(qualifiedName(c), c);
            }
        }
        return result;
    }

    /**
     * Node types that the node types and topology templates directly owned by {@code toscaModel} depend on
     * but that are defined elsewhere (e.g. in a model component): parents ({@code derivedFrom}) and node
     * template types, followed transitively. Normative tosca.* types are excluded.
     */
    public static List<Class> externalDependencies(ModelTree toscaModel) {
        NodeTypeChecker nodeTypeChecker = new NodeTypeChecker();
        TopologyTemplateChecker topologyChecker = new TopologyTemplateChecker();
        Set<String> owned = new HashSet<>();
        Deque<String> pending = new ArrayDeque<>();
        for (MObject child : toscaModel.getCompositionChildren()) {
            if (nodeTypeChecker.isTypeOf(child)) {
                owned.add(qualifiedName((Class) child));
                pending.add(Objects2.nullToEmpty(PropertyResolver.resolveProperty((Class) child, "derivedFrom")));
            } else if (topologyChecker.isTypeOf(child)) {
                for (MObject element : child.getCompositionChildren()) {
                    if (element instanceof Class && TNodeTemplate.canInstantiate(element)) {
                        TNodeType type = TNodeTemplate.safeInstantiate((Class) element).getNodeType();
                        if (type != null) {
                            pending.add(qualifiedName(type.getElement()));
                        }
                    }
                }
            }
        }
        Map<String, Class> all = allNodeTypes();
        Set<String> seen = new HashSet<>(owned);
        List<Class> result = new ArrayList<>();
        while (!pending.isEmpty()) {
            String name = pending.poll().trim();
            if (isNormative(name) || !seen.add(name)) {
                continue;
            }
            Class type = all.get(name);
            if (type != null) {
                result.add(type);
                pending.add(Objects2.nullToEmpty(PropertyResolver.resolveProperty(type, "derivedFrom")));
            }
        }
        return result;
    }

    private static final class Objects2 {
        static String nullToEmpty(String s) {
            return s == null ? "" : s;
        }
    }
}
