# Retroscope
Retroscope is a system to enable consistent distributed snapshots and consistent distributed parameter logging in distributed systems. 

**Structure of the project**
   - retroscope-library - main library with HLC and windowed-log APIs. Main class of the library is Retroscope. A library user can create an instance of Retroscope and use its methods to make HLC ticks, append to window-log and compute the differences between various points in the log.
   - retroscope-server - server side of the Retroscope API. Used to write server application that aggregate the parameters monitored with Retroscope.
   - retroscope-example - small example that creates new Retroscope instance, connects it to retroscope-server and appends some items to the log.
