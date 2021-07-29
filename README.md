# X10D-dynamic-jar-loader
Load jar dynamically

To write a program that is compatible with this loader, implement X10Sion interface (which is included)
When implemented, override all functions.

X10SionCompatibility: Return X10SION_COMPAT
X10SionVersion: Return X10SION_VERSION
getBuild: Return your program's build as integer
getName: Return your program's name
getVendor: Return your name
getVersion: Return your program's version
run(Object[] parameters): This method will be called, so main codes should go here.
