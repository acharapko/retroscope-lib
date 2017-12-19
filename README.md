# Retroscope
Retroscope is a comprehensive lightweight solution for monitoring and debugging of distributed systems. It allows users to query and reconstruct past consistent global states of an application. Retroscope achieves this by augmenting the system with Hybrid Logical Clocks (HLC) and streaming HLCstamped event logs to storage and processing, where HLC timestamps are used for constructing global (or nonlocal) snapshots upon request. Retroscope exposes a rich querying interface via query language (RQL) to facilitate searching for global/nonlocal predicates across past consistent states. Retroscope performs the search by advancing through global/nonlocal states in smallincremental steps, greatly reducing the amount of computation needed to arrive to each consistent state. Embarrassingly-parallel nature of Retroscope search algorithm allows it to use many distributed worker processes to handle a single query. 

Retroscoep uses Apache Ignite as its backbone for streaming, storage and compute.

**Structure of the project**
   - RetroLogServer - main library with HLC and log streaming APIs. Main class of the library is Retroscope. A library user can create an instance of Retroscope and use its methods to make HLC ticks and log changes in variables/states with Retroscope
   - RQLServer - server side of the Retroscope API. parses teh queries, schedules Ignite Compute workers.
   - IgniteServer - Just a wrapper against Apache Ignite with some Retroscope dependencies. Regualr Ignite with peer class loading can be used instead.  
   - RQLBench - small RQL Client to run some queries repeatedly and collect osme perfromance stats
   - RetroAppViewer - GUI to check on the propertis of Retroscope apps
   - RetrscopeExample - Some examples
