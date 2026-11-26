# Build the MYRTUS node types (tosca-types/myrtus/node-types.yaml v2.0.0) as TOSCA Designer
# elements, plus a ModelComponentArchive artifact ready to be packaged as a RAMC.
#
# Usage (Modelio 5.4 + TOSCA Designer >= 0.6.00, ToscaLibrary deployed):
#   1. Create/open a dedicated UML project (not a project you deploy the RAMC into).
#   2. Run this script, either from Modelio's script runner or through ScriptServer
#      (python modelio-cli.py build_myrtus_node_types.py).
#   3. Save the project, select the 'MyrtusNodeTypes' artifact and package it as a model component
#      (or use Modelio.getInstance().getModelComponentService().packageModelComponent(...)).
# Set DRY_RUN = True to build everything and roll it back (checks the API, not Model Shield rules).
# Jython 2.7.
from java.lang import Class as JClass, Object as JObject
from org.modelio.metamodel.uml.statik import Package, Class, DataType, Artifact
from org.modelio.metamodel.uml.infrastructure import Stereotype

DRY_RUN = False            # True = build everything, then roll back
NS = "eu.myrtus"
RAMC_NAME = "MyrtusNodeTypes"
RAMC_VERSION = "2.0.00"

def s(x):
    try: return unicode(x).encode('ascii', 'replace')
    except: return repr(x)

# ---------- TOSCA Designer proxy classes via the module class loader ----------
tosca_peer = [p for p in Modelio.getInstance().getModuleService().getAllPeerModules()
              if p is not None and 'ToscaDesigner' in repr(p)][0]
CL = JObject.getClass(tosca_peer).getClassLoader()
def proxy(name):
    return JClass.forName('fr.softeam.toscadesigner.api.tosca.' + name, True, CL)
P_NodeType   = proxy('standard.class_.TNodeType')
P_ReqDefType = proxy('standard.class_.TRequirementDefinitionType')
P_ReqDef     = proxy('standard.class_.TRequirementDefinition')
P_PropDef    = proxy('standard.attribute.PropertyDefinitionType')
P_CapType    = proxy('standard.class_.TCapabilityType')
P_RelType    = proxy('standard.class_.TRelationshipType')
from org.modelio.api.modelio.model import IModelingSession
from org.modelio.metamodel.uml.statik import Class as UClass, Attribute as UAttr
def jcall(cls, mname, argtypes, target, *args):
    return cls.getMethod(mname, argtypes).invoke(target, list(args))
def create(cls):
    return jcall(cls, 'create', [IModelingSession], None, modelingSession)
def inst(cls, elt):
    return jcall(cls, 'instantiate', [UAttr if cls is P_PropDef else UClass], None, elt)

# ---------- guards ----------
project = [r for r in modelingSession.getModel().getModelRoots() if r.getMClass().getName() == 'Project'][0]
root_pkg = [c for c in project.getCompositionChildren() if isinstance(c, Package)][0]
for p in modelingSession.findByClass(Package):
    if s(p.getName()) == NS and p.getStatus().isModifiable():
        raise Exception("Package '" + NS + "' already exists in this project; aborting.")

# ---------- library lookups (read-only ToscaLibrary / PredefinedTypes) ----------
def lib_class(owner, name, stereo):
    for c in modelingSession.findByClass(Class):
        o = c.getCompositionOwner()
        if s(c.getName()) == name and o is not None and s(o.getName()) == owner and c.isStereotyped("ToscaDesigner", stereo):
            return c
    raise Exception("Library element not found: " + owner + "/" + name)
LIB = {
    'tosca.nodes.Compute':           lib_class('nodes', 'Compute', 'TNodeType'),
    'tosca.nodes.Root':              lib_class('nodes', 'Root', 'TNodeType'),
    'tosca.nodes.SoftwareComponent': lib_class('nodes', 'SoftwareComponent', 'TNodeType'),
    'tosca.nodes.Abstract.Compute':  lib_class('nodes', 'Abstract.Compute', 'TNodeType'),
}
CAP_COMPUTE = lib_class('capabilities', 'Compute', 'TCapabilityType')
REL = {'HostedOn': lib_class('relationships', 'hostedOn', 'TRelationshipType'),
       'DependsOn': lib_class('relationships', 'dependsOn', 'TRelationshipType')}

def datatype(name, prefer_predefined=True):
    found = [d for d in modelingSession.findByClass(DataType) if s(d.getName()) == name]
    pre = [d for d in found if 'PredefinedTypes' in s(d.getCompositionOwner().getName()) or d.getUuid().startswith('00000004')]
    if prefer_predefined and pre: return pre[0]
    if found: return found[0]
    raise Exception("DataType not found: " + name)
TYPES = {'string': datatype('string'), 'integer': datatype('integer'), 'float': datatype('float'),
         'boolean': datatype('boolean'), 'map': datatype('Map', False)}

# ---------- spec (from node-types.yaml v2.0.0) ----------
def P(name, typ, desc, required=False, constraint=None):
    return (name, typ, desc, required, constraint)
SPEC = [
  ('MyrtusCompute', 'tosca.nodes.Compute', 'tosca.nodes.Compute',
   'A compute node at any continuum layer, with explicit tier and sustainability properties.', [
    P('edge_zone', 'string', 'Continuum tier occupied by this compute node.', True, 'valid_values: [edge, fog, cloud]'),
    P('location', 'string', 'Physical or logical location identifier.'),
    P('docker_runtime', 'string', 'Container runtime available on this node.'),
    P('monitoring_enabled', 'boolean', 'Whether a monitoring agent is enabled on this node.'),
    P('tdp_watts', 'float', 'Vendor-rated peak power draw under full load, in watts.', False, 'greater_or_equal: 0'),
    P('renewable_energy_ratio', 'float', "Fraction of the node's energy supply from renewables.", False, 'in_range: [0.0, 1.0]'),
    P('cooling_pue', 'float', 'Power Usage Effectiveness: total facility power divided by IT equipment power.', False, 'greater_or_equal: 1.0'),
   ], []),
  ('MyrtusEdgeDevice', 'tosca.nodes.Root', 'tosca.nodes.Root',
   'A physical endpoint such as a sensor, camera, or VR headset that produces or consumes data but is not a compute hosting target.', [
    P('protocol', 'string', 'Communication protocol used by the device.', False, 'valid_values: [MQTT, CoAP, HTTP, LoRaWAN, custom]'),
    P('data_rate_mbps', 'float', 'Peak data rate in megabits per second.', False, 'greater_or_equal: 0'),
    P('mobility', 'string', 'Whether the device is stationary or mobile.', False, 'valid_values: [static, mobile]'),
    P('power_source', 'string', "Primary source of the device's power.", False, 'valid_values: [battery, solar, grid, harvested]'),
    P('max_power_w', 'float', 'Peak device power consumption in watts.', False, 'greater_or_equal: 0'),
    P('idle_power_w', 'float', 'Device power consumption while idle, in watts.', False, 'greater_or_equal: 0'),
    P('duty_cycle', 'float', 'Fraction of time the device is active.', False, 'in_range: [0.0, 1.0]'),
    P('sleep_latency_ms', 'integer', 'Time for the device to wake from deep sleep, in milliseconds.', False, 'greater_or_equal: 0'),
   ], []),
  # Text-only parent: ToscaLibrary 0.0.05 has no tosca.nodes.network.Network node type
  ('MyrtusNetwork', 'tosca.nodes.network.Network', 'tosca.nodes.Root',
   'A network link between continuum tiers, exposing its capacity, latency, energy use, and carbon intensity.', [
    P('bandwidth_mbps', 'float', 'Link capacity in megabits per second.', False, 'greater_or_equal: 0'),
    P('latency_ms', 'float', 'One-way propagation latency in milliseconds.', False, 'greater_or_equal: 0'),
    P('tier_src', 'string', 'Source tier connected by this link.', False, 'valid_values: [device, edge, fog, cloud]'),
    P('tier_dst', 'string', 'Destination tier connected by this link.', False, 'valid_values: [device, edge, fog, cloud]'),
    P('link_type', 'string', 'Physical or logical link technology.', False, 'valid_values: [fiber, 5G, WiFi6, LoRaWAN, ethernet]'),
    P('energy_per_gb_kwh', 'float', 'Energy required to transfer one gigabyte over this link.', False, 'greater_or_equal: 0'),
    P('carbon_intensity_gco2_kwh', 'float', 'Link infrastructure carbon intensity in gCO2 per kWh.', False, 'greater_or_equal: 0'),
   ], []),
  ('MyrtusAccelerator', 'tosca.nodes.Abstract.Compute', 'tosca.nodes.Abstract.Compute',
   'A dedicated hardware accelerator hosted by a MYRTUS compute node.', [
    P('accelerator_type', 'string', 'Accelerator architecture or processor type.', False, 'valid_values: [GPU, FPGA, CGRA, NPU, RISC-V]'),
    P('compute_units', 'integer', 'Number of accelerator compute units.', False, 'greater_or_equal: 0'),
    P('memory_gb', 'float', 'Accelerator memory capacity in gigabytes.', False, 'greater_or_equal: 0'),
    P('tdp_watts', 'float', 'Peak power draw under full load, in watts.', False, 'greater_or_equal: 0'),
    P('gflops_per_watt', 'float', 'Floating-point throughput per watt.', False, 'greater_or_equal: 0'),
    P('idle_power_ratio', 'float', 'Idle power as a fraction of peak power.', False, 'in_range: [0.0, 1.0]'),
    P('reconfigurable', 'boolean', 'Whether the accelerator can be reconfigured at runtime.'),
   ], [('host', 'MyrtusCompute', 'HostedOn', '1', '1')]),
  ('MyrtusSWComponent', 'tosca.nodes.SoftwareComponent', 'tosca.nodes.SoftwareComponent',
   'A containerized microservice deployable across cloud, fog, and edge compute nodes.', [
    P('docker_image', 'string', 'Required OCI container image reference.', True),
    P('resource_limits', 'map', 'CPU and memory bounds for the component (keys cpu and mem).', False, 'entry_schema: string'),
    P('health_check_endpoint', 'string', 'HTTP path used to check component health.'),
    P('auto_restart', 'boolean', 'Whether to restart the component after a failure.'),
    P('energy_profile', 'string', 'Energy footprint class and adaptation behavior.', False, 'valid_values: [high, medium, low, adaptive]'),
   ], [('host', 'MyrtusCompute', 'HostedOn', '1', '1'),
       ('accelerator', 'MyrtusAccelerator', 'DependsOn', '0', 'UNBOUNDED')]),
]

# Descriptions are exported unquoted by TNodeType.hbs and HTML-escaped:
# keep them free of ": " and apostrophes so the generated YAML stays valid.
def describe(desc, cons):
    txt = desc.replace(": ", ", ").replace("'s ", " ").replace("'", "")
    if cons:
        k, v = [x.strip() for x in cons.split(":", 1)]
        if k == "valid_values":
            txt += " Allowed values " + v + "."
        elif k == "greater_or_equal":
            txt += " Minimum " + v + "."
        elif k == "in_range":
            lo, hi = [x.strip() for x in v.strip("[]").split(",")]
            txt += " Range " + lo + " to " + hi + "."
        elif k == "entry_schema":
            txt += " Entries of type " + v + "."
    return txt

# ---------- build ----------
model = modelingSession.getModel()
txn = modelingSession.createTransaction("Build MYRTUS node types RAMC")
_sc = globals().get("scriptControl")      # only defined under ScriptServer
_registered = _sc is not None and not DRY_RUN
if _registered:
    _sc.registerTransaction(txn)          # ScriptServer rolls back on error/cancel
try:

    container = model.createPackage(RAMC_NAME, root_pkg)
    ns_pkg = model.createPackage(NS, container)
    ns_pkg.addStereotype("ToscaDesigner", "ToscaModel")

    types = {}
    for (tname, derived_text, derived_model, tdesc, props, reqs) in SPEC:
        nt = create(P_NodeType)
        elt = nt.getElement()
        elt.setName(tname)
        ns_pkg.getOwnedElement().add(elt)
        nt.setTargetNamespace(NS)
        nt.setDescription(tdesc)
        nt.setAbstract(False)
        nt.setFinal(False)
        # Write into the TEntityType table (the string-based setProperty would create an
        # untyped table keyed on TNodeType, rejected at commit by model-shield rule E230)
        st_entity = [x for x in elt.getExtension() if x.getName() == "TNodeType"][0].getParent()
        assert st_entity.getName() == "TEntityType"
        elt.setProperty(st_entity, "derivedFrom", derived_text)
        # derivedFrom link, same encoding as the Face App model (child -> parent, role=derivedFrom)
        dep = model.createDependency(elt, LIB[derived_model], "ModelerModule", "MDAAssocDep")
        dep.setName("derivedFrom")
        dep.putTagValue("ModelerModule", "role", "derivedFrom")
        for (pname, ptype, pdesc, preq, pcons) in props:
            pd = create(P_PropDef)
            a = pd.getElement()
            a.setName(pname)
            elt.getOwnedAttribute().add(a)
            a.setType(TYPES[ptype])
            a.setMultiplicityMin("1" if preq else "0")
            a.setMultiplicityMax("1")
            full = describe(pdesc, pcons)
            pd.setDescription(full)
            pd.setRequired(preq)
        types[tname] = (nt, elt, reqs)
        print "node type " + NS + "." + tname + " <- " + derived_text + " (" + str(len(props)) + " properties)"

    for tname in [x[0] for x in SPEC]:
        nt, elt, reqs = types[tname]
        if not reqs: continue
        rdt = create(P_ReqDefType)
        relt = rdt.getElement()
        relt.setName("Requirements")
        elt.getOwnedElement().add(relt)
        for (rname, target, rel, lo, hi) in reqs:
            rd = create(P_ReqDef)
            rde = rd.getElement()
            rde.setName(rname)
            relt.getOwnedElement().add(rde)
            rd.setName(rname)
            rd.setCapability(inst(P_CapType, CAP_COMPUTE))
            rd.setNodeType(inst(P_NodeType, types[target][1]))
            rd.setRelationshipType(inst(P_RelType, REL[rel]))
            rd.setLowerBound(lo)
            rd.setUpperBound(hi)
            print "  requirement " + tname + "." + rname + " -> " + target + " via " + rel + " [" + lo + ", " + hi + "]"

    # ---------- model component artifact ----------
    art = model.createArtifact(RAMC_NAME, container, None)
    art.addStereotype("ModelerModule", "ModelComponentArchive")
    art.putTagValue("ModelerModule", "ModelComponentVersion", RAMC_VERSION)
    art.putTagValue("ModelerModule", "ModelComponentProvider", "MYRTUS project")
    from java.util import ArrayList
    contributors = ArrayList()
    contributors.add("ModelerModule#9.4.00")
    contributors.add("ToscaDesigner#0.0.00")
    art.putTagValues("ModelerModule", "ModelComponentContributors", contributors)
    man = model.createManifestation()
    man.setOwner(art)
    man.setUtilizedElement(ns_pkg)
    print "artifact " + RAMC_NAME + " " + RAMC_VERSION + " manifests " + NS

    if DRY_RUN:
        print "DRY RUN: rolling back"
        txn.rollback()
    elif _registered:
        _sc.commitRegisteredTransaction()
        print "COMMITTED (save the project to persist)"
    else:
        txn.commit()
        print "COMMITTED (save the project to persist)"
except:
    if not _registered:
        txn.rollback()
    raise
